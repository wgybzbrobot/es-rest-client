package com.jusdt.es.common.client;


import java.io.Closeable;
import java.io.IOException;
import java.util.Set;

import com.jusdt.es.common.action.Action;


/**
 * @author Dogukan Sonmez
 */
public interface JestClient extends Closeable {

    <T extends JestResult> T execute(Action<T> clientRequest) throws IOException;

    <T extends JestResult> void executeAsync(Action<T> clientRequest, JestResultHandler<? super T> jestResultHandler);

    /**
     * @deprecated Use {@link #close()} instead.
     */
    @Deprecated
    void shutdownClient();

    void setServers(Set<String> servers);
}
