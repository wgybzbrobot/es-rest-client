package com.jusdt.es.client.cluster;

import org.elasticsearch.test.ESIntegTestCase;
import org.junit.Test;

import com.jusdt.es.client.common.AbstractIntegrationTest;
import com.jusdt.es.common.client.QueryResult;
import com.jusdt.es.common.cluster.NodesHotThreads;

import java.io.IOException;

/**
 * @author cihat keser
 */
@ESIntegTestCase.ClusterScope(scope = ESIntegTestCase.Scope.SUITE, numDataNodes = 2)
public class NodesHotThreadsIntegrationTest extends AbstractIntegrationTest {

    @Test
    public void allNodesHotThreads() throws IOException {
        String firstNode = internalCluster().getNodeNames()[0];
        String secondNode = internalCluster().getNodeNames()[1];

        QueryResult result = client.execute(new NodesHotThreads.Builder().build());
        assertTrue(result.getErrorMessage(), result.isSucceeded());

        assertTrue(result.getJsonString().contains("interval=500ms"));
        assertNodePresent(result, firstNode);
        assertNodePresent(result, secondNode);
    }

    @Test
    public void singleNodeHotThreads() throws IOException {
        String firstNode = internalCluster().getNodeNames()[0];
        String secondNode = internalCluster().getNodeNames()[1];

        QueryResult result = client.execute(new NodesHotThreads.Builder().addNode(firstNode).build());
        assertTrue(result.getErrorMessage(), result.isSucceeded());

        assertNodePresent(result, firstNode);
        assertNodeMissing(result, secondNode);
    }

    @Test
    public void singleNodeHotThreadsWithCustomInterval() throws IOException {
        String firstNode = internalCluster().getNodeNames()[0];
        String secondNode = internalCluster().getNodeNames()[1];

        QueryResult result = client.execute(new NodesHotThreads.Builder()
                .addNode(firstNode)
                .interval("100ms")
                .build());
        assertTrue(result.getErrorMessage(), result.isSucceeded());

        String rawJson = result.getJsonString();
        assertTrue(rawJson, rawJson.contains("interval=100ms"));
        assertNodePresent(result, firstNode);
        assertNodeMissing(result, secondNode);
    }

    private void assertNodePresent(QueryResult result, String node) {
        assertTrue(result.getJsonString().contains("::: {" + node + "}{"));
    }

    private void assertNodeMissing(QueryResult result, String node) {
        assertFalse(result.getJsonString().contains("::: {" + node + "}{"));
    }

}
