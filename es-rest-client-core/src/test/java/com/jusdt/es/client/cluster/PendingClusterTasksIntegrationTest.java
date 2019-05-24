package com.jusdt.es.client.cluster;

import com.google.common.collect.Iterables;
import com.jusdt.es.client.common.AbstractIntegrationTest;
import com.jusdt.es.common.client.JestResult;
import com.jusdt.es.common.cluster.PendingClusterTasks;

import org.elasticsearch.test.ESIntegTestCase;
import org.junit.Test;

import java.io.IOException;

@ESIntegTestCase.ClusterScope(scope = ESIntegTestCase.Scope.SUITE, numDataNodes = 1)
public class PendingClusterTasksIntegrationTest extends AbstractIntegrationTest {
    @Test
    public void pendingClusterTasks() throws IOException {
        PendingClusterTasks pendingClusterTasks = new PendingClusterTasks.Builder().build();
        JestResult result = client.execute(pendingClusterTasks);
        assertTrue(result.getErrorMessage(), result.isSucceeded());

        assertTrue(result.getJsonObject().get("tasks").isJsonArray());
        assertTrue(Iterables.isEmpty(result.getJsonObject().getAsJsonArray("tasks")));
    }
}
