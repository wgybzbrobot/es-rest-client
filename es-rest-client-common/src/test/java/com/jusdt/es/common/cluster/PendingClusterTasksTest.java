package com.jusdt.es.common.cluster;

import org.junit.Test;

import com.jusdt.es.common.client.config.ElasticsearchVersion;
import com.jusdt.es.common.cluster.PendingClusterTasks;

import static org.junit.Assert.assertEquals;

public class PendingClusterTasksTest {
    @Test
    public void testUriGeneration() {
        PendingClusterTasks action = new PendingClusterTasks.Builder().build();
        assertEquals("/_cluster/pending_tasks", action.getURI(ElasticsearchVersion.UNKNOWN));
    }
}
