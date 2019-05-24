package com.jusdt.es.client.cluster;

import org.elasticsearch.test.ESIntegTestCase;
import org.junit.Test;

import com.jusdt.es.client.common.AbstractIntegrationTest;
import com.jusdt.es.common.client.QueryResult;
import com.jusdt.es.common.cluster.NodesInfo;

import java.io.IOException;

/**
 * @author Dogukan Sonmez
 */
@ESIntegTestCase.ClusterScope(scope = ESIntegTestCase.Scope.SUITE, numDataNodes = 1)
public class NodesInfoIntegrationTest extends AbstractIntegrationTest {

    @Test
    public void nodesInfoWithoutNodeAndInfo() throws IOException {
        QueryResult result = client.execute(new NodesInfo.Builder().build());
        assertTrue(result.getErrorMessage(), result.isSucceeded());
    }

    @Test
    public void nodesInfoWithNodeWithoutInfo() throws IOException {
        NodesInfo nodesInfo = new NodesInfo.Builder().addNode("node1").build();
        QueryResult result = client.execute(nodesInfo);
        assertTrue(result.getErrorMessage(), result.isSucceeded());
    }

    @Test
    public void nodesInfoWithoutNodeWithInfo() throws IOException {
        NodesInfo nodesInfo = new NodesInfo.Builder().withOs().build();
        QueryResult result = client.execute(nodesInfo);
        assertTrue(result.getErrorMessage(), result.isSucceeded());
    }

    @Test
    public void nodesInfoWithNodeAndWithInfo() throws IOException {
        NodesInfo nodesInfo = new NodesInfo.Builder().addNode("node1").withOs().build();
        QueryResult result = client.execute(nodesInfo);
        assertTrue(result.getErrorMessage(), result.isSucceeded());
    }

    @Test
    public void nodesInfoWithMultipleNodeAndWithoutInfo() throws IOException {
        NodesInfo nodesInfo = new NodesInfo.Builder().addNode("node1").addNode("node2").build();
        QueryResult result = client.execute(nodesInfo);
        assertTrue(result.getErrorMessage(), result.isSucceeded());
    }

    @Test
    public void nodesInfoWithMultipleNodeAndMultipleInfo() throws IOException {
        NodesInfo nodesInfo = new NodesInfo.Builder()
                .addNode("node1")
                .addNode("node2")
                .withProcess()
                .withOs()
                .build();
        QueryResult result = client.execute(nodesInfo);
        assertTrue(result.getErrorMessage(), result.isSucceeded());
    }
}
