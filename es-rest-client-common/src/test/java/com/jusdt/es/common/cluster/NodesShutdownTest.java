package com.jusdt.es.common.cluster;

import org.junit.Test;

import com.jusdt.es.common.client.config.ElasticSearchVersion;
import com.jusdt.es.common.cluster.NodesShutdown;

import static org.junit.Assert.assertEquals;

/**
 * @author cihat keser
 */
public class NodesShutdownTest {

    @Test
    public void testBuildURI() throws Exception {
        NodesShutdown action = new NodesShutdown.Builder().build();
        assertEquals("/_nodes/_all/_shutdown", action.getURI(ElasticSearchVersion.UNKNOWN));
    }

    @Test
    public void testBuildURIWithDelay() throws Exception {
        NodesShutdown action = new NodesShutdown.Builder().delay("5s").build();
        assertEquals("/_nodes/_all/_shutdown?delay=5s", action.getURI(ElasticSearchVersion.UNKNOWN));
    }

    @Test
    public void testBuildURIWithNodes() throws Exception {
        NodesShutdown action = new NodesShutdown.Builder().addNode("_local").build();
        assertEquals("/_nodes/_local/_shutdown", action.getURI(ElasticSearchVersion.UNKNOWN));
    }

    @Test
    public void testBuildURIWithNodeAttributeWildcard() throws Exception {
        NodesShutdown action = new NodesShutdown.Builder().addNode("ra*:2*").build();
        assertEquals("/_nodes/ra*:2*/_shutdown", action.getURI(ElasticSearchVersion.UNKNOWN));
    }
}
