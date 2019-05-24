package com.jusdt.es.common.client.config;

import java.util.concurrent.TimeUnit;

public interface ReapableConnectionManager {
    void closeIdleConnections(long idleTimeout, TimeUnit unit);
}
