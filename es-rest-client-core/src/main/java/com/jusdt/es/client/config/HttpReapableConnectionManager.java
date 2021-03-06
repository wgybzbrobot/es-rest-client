package com.jusdt.es.client.config;

import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.nio.conn.NHttpClientConnectionManager;

import com.jusdt.es.common.client.config.ReapableConnectionManager;

import java.util.concurrent.TimeUnit;

public class HttpReapableConnectionManager implements ReapableConnectionManager {
    private final HttpClientConnectionManager connectionManager;
    private final NHttpClientConnectionManager nConnectionManager;

    public HttpReapableConnectionManager(HttpClientConnectionManager connectionManager, NHttpClientConnectionManager nConnectionManager) {
        if(connectionManager == null || nConnectionManager == null) throw new IllegalArgumentException();

        this.connectionManager = connectionManager;
        this.nConnectionManager = nConnectionManager;
    }

    @Override
    public void closeIdleConnections(long idleTimeout, TimeUnit unit) {
        connectionManager.closeIdleConnections(idleTimeout, unit);
        nConnectionManager.closeIdleConnections(idleTimeout, unit);
    }
}
