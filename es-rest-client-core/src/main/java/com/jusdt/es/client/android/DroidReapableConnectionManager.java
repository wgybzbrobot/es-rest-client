package com.jusdt.es.client.android;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import com.jusdt.es.common.client.config.ReapableConnectionManager;

import java.util.concurrent.TimeUnit;

public class DroidReapableConnectionManager implements ReapableConnectionManager {

    private final PoolingHttpClientConnectionManager connectionManager;

    public DroidReapableConnectionManager(PoolingHttpClientConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public void closeIdleConnections(long idleTimeout, TimeUnit unit) {
        connectionManager.closeIdleConnections(idleTimeout, unit);
    }
}
