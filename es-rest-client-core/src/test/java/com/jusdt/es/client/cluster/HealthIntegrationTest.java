package com.jusdt.es.client.cluster;

import org.elasticsearch.test.ESIntegTestCase;
import org.junit.Test;

import com.jusdt.es.client.common.AbstractIntegrationTest;
import com.jusdt.es.common.client.QueryResult;
import com.jusdt.es.common.cluster.Health;

import static org.elasticsearch.test.hamcrest.ElasticsearchAssertions.assertAcked;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * @author Neil Gentleman
 */
@ESIntegTestCase.ClusterScope(scope = ESIntegTestCase.Scope.SUITE, numDataNodes = 1)
public class HealthIntegrationTest extends AbstractIntegrationTest {

    @Test
    public void health() throws Exception {
        QueryResult result = client.execute(new Health.Builder().build());
        assertTrue(result.getErrorMessage(), result.isSucceeded());
        assertThat(
                result.getJsonObject().get("status").getAsString(),
                anyOf(equalTo("green"), equalTo("yellow"), equalTo("red"))
        );
    }

    @Test
    public void healthWithIndex() throws Exception {
        assertAcked(prepareCreate("test1").get());
        final Health request = new Health.Builder()
                .addIndex("test1")
                .build();
        QueryResult result = client.execute(request);
        assertTrue(result.getErrorMessage(), result.isSucceeded());
        assertThat(
                result.getJsonObject().get("status").getAsString(),
                anyOf(equalTo("green"), equalTo("yellow"), equalTo("red"))
        );
    }

    @Test
    public void healthWaitForStatus() throws Exception {
        final Health request = new Health.Builder()
                .waitForStatus(Health.Status.GREEN)
                .build();
        QueryResult result = client.execute(request);
        assertTrue(result.getErrorMessage(), result.isSucceeded());
        assertEquals("green", result.getJsonObject().get("status").getAsString());
    }

    @Test
    public void healthWithTimeout() throws Exception {
        final Health request = new Health.Builder()
                .addIndex("test1")
                .timeout(1)
                .build();
        QueryResult result = client.execute(request);
        assertFalse(result.getErrorMessage(), result.isSucceeded());
        assertEquals(408, result.getResponseCode());
    }

    @Test
    public void healthOnlyLocal() throws Exception {
        final Health request = new Health.Builder()
                .local()
                .build();
        QueryResult result = client.execute(request);
        assertTrue(result.getErrorMessage(), result.isSucceeded());
        assertThat(
                result.getJsonObject().get("status").getAsString(),
                anyOf(equalTo("green"), equalTo("yellow"), equalTo("red"))
        );
    }

    @Test
    public void healthWaitForNoRelocatingShards() throws Exception {
        final Health request = new Health.Builder()
                .waitForNoRelocatingShards()
                .build();
        QueryResult result = client.execute(request);
        assertTrue(result.getErrorMessage(), result.isSucceeded());
        assertThat(
                result.getJsonObject().get("status").getAsString(),
                anyOf(equalTo("green"), equalTo("yellow"), equalTo("red"))
        );
    }

    @Test
    public void healthLevelShards() throws Exception {
        final Health request = new Health.Builder()
                .level(Health.Level.SHARDS)
                .build();
        QueryResult result = client.execute(request);
        assertTrue(result.getErrorMessage(), result.isSucceeded());
        assertThat(
                result.getJsonObject().get("status").getAsString(),
                anyOf(equalTo("green"), equalTo("yellow"), equalTo("red"))
        );
    }
}
