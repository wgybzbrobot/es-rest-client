package com.jusdt.es.common.cluster;

import org.junit.Test;

import com.jusdt.es.common.client.config.ElasticsearchVersion;
import com.jusdt.es.common.cluster.Stats;

import static org.junit.Assert.assertEquals;

public class StatsTest {
    @Test
    public void testUriGeneration() {
        Stats action = new Stats.Builder().build();
        assertEquals("/_cluster/stats/nodes/_all", action.getURI(ElasticsearchVersion.UNKNOWN));
    }

    @Test
    public void testUriGenerationWithSpecificNodes() {
        Stats action = new Stats.Builder()
                .addNode("test1")
                .addNode("test2")
                .build();
        assertEquals("/_cluster/stats/nodes/test1,test2", action.getURI(ElasticsearchVersion.UNKNOWN));
    }
}
