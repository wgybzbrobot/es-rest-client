package com.jusdt.es.common.cluster;

import org.junit.Test;

import com.jusdt.es.common.client.config.ElasticSearchVersion;
import com.jusdt.es.common.cluster.NodesStats;

import static org.junit.Assert.assertEquals;

/**
 * @author cihat keser
 */
public class NodesStatsTest {

    @Test
    public void testUriGeneration() throws Exception {
        NodesStats action = new NodesStats.Builder()
                .build();
        assertEquals("/_nodes/_all/stats", action.getURI(ElasticSearchVersion.UNKNOWN));
    }

    @Test
    public void testUriGenerationWithSingleNode() throws Exception {
        NodesStats action = new NodesStats.Builder()
                .addNode("james")
                .withOs()
                .withJvm()
                .build();
        assertEquals("/_nodes/james/stats/os,jvm", action.getURI(ElasticSearchVersion.UNKNOWN));
    }

}
