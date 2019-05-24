package com.jusdt.es.common.core;

import org.junit.Test;

import com.jusdt.es.common.client.config.ElasticSearchVersion;
import com.jusdt.es.common.core.Ping;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PingTest {
    @Test
    public void testBasicUriGeneration() {
        Ping ping = new Ping.Builder().build();

        assertEquals("GET", ping.getRestMethodName());
        assertNull(ping.getData(null));
        assertEquals("", ping.getURI(ElasticSearchVersion.UNKNOWN));
    }
}
