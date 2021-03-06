package com.jusdt.es.client.http;

import java.io.IOException;
import java.util.concurrent.Semaphore;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jusdt.es.client.JestClientFactory;
import com.jusdt.es.client.config.HttpClientConfig;
import com.jusdt.es.client.http.JestHttpClient;
import com.jusdt.es.common.client.QueryResult;
import com.jusdt.es.common.client.QueryResultHandler;
import com.jusdt.es.common.indices.Stats;

import io.netty.handler.codec.http.HttpResponseStatus;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

/** Test the situation where there's a misconfigured proxy between the Jest
 * client and the server.  If the proxy speaks text/html instead of
 * application/json, we should not throw a generic JsonSyntaxException.
 */
public class FailingProxyTest {
    JestClientFactory factory = new JestClientFactory();
    private FailingProxy proxy;
    private JestHttpClient client;
    private Stats status;

    @Before
    public void setUp() throws IOException {
        proxy = new FailingProxy();

        String url = proxy.getUrl();
        factory.setHttpClientConfig(new HttpClientConfig.Builder(url).build());
        client = (JestHttpClient) factory.getObject();
        assertThat(client, is(not(nullValue())));

        status = new Stats.Builder().build();
    }

    @After
    public void tearDown() throws IOException {
        client.close();
        proxy.stop();
    }

    @Test
    public void testWithFailingProxy() throws InterruptedException, IOException {
        Exception exception = runSynchronously();
        validateFailingProxyException(exception);
    }

    @Test
    public void testAsyncWithFailingProxy() throws InterruptedException, IOException {
        Exception exception = runAsynchronously();
        validateFailingProxyException(exception);
    }

    private void validateFailingProxyException(final Exception e) {
        assertThat(e, is(not(Matchers.nullValue())));
        final String message = e.toString();
        assertThat(message, not(containsString("Use JsonReader.setLenient(true)")));
        assertThat(message, containsString("text/html"));
        assertThat(message, containsString("should be json: HTTP/1.1 400 Bad Request"));
    }

    @Test
    public void testWithBrokenResponse() throws InterruptedException, IOException {
        proxy.setErrorStatus(HttpResponseStatus.FORBIDDEN);
        proxy.setErrorContentType("application/json");
        proxy.setErrorMessage("banana");   // <-- this is not json at all!
        Exception exception = runSynchronously();
        validateBrokenResponseException(exception);
    }

    @Test
    public void testAsyncWithBrokenResponse() throws InterruptedException, IOException {
        proxy.setErrorStatus(HttpResponseStatus.FORBIDDEN);
        proxy.setErrorContentType("application/json");
        proxy.setErrorMessage("banana");   // <-- this is not json at all!
        Exception exception = runAsynchronously();
        validateBrokenResponseException(exception);
    }

    private void validateBrokenResponseException(final Exception e) {
        assertThat(e, is(not(Matchers.nullValue())));
        final String message = e.toString();
        assertThat(message, not(containsString("Use JsonReader.setLenient(true)")));
        assertThat(message, containsString("should be json: HTTP/1.1 400 Bad Request"));
    }

    private Exception runSynchronously() {
        Exception exception = null;
        try {
            final QueryResult result = client.execute(status);
        } catch (Exception e) {
            exception = e;
        }
        return exception;
    }

    private Exception runAsynchronously() throws InterruptedException {
        final ResultHandler resultHandler = new ResultHandler();
        client.executeAsync(status, resultHandler);
        return resultHandler.get();
    }

    private class ResultHandler implements QueryResultHandler {
        private final Semaphore sema = new Semaphore(0);
        private Exception exception = null;

        @Override
        public void completed(final Object result) {
            sema.release();
        }

        @Override
        public void failed(final Exception ex) {
            exception = ex;
            sema.release();
        }

        public Exception get() throws InterruptedException {
            sema.acquire();
            return exception;
        }
    }
}
