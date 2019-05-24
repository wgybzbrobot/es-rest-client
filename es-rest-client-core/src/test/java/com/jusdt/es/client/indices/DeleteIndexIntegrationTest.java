package com.jusdt.es.client.indices;

import org.elasticsearch.test.ESIntegTestCase;
import org.junit.Test;

import com.jusdt.es.client.common.AbstractIntegrationTest;
import com.jusdt.es.common.client.QueryResult;
import com.jusdt.es.common.indices.DeleteIndex;

import java.io.IOException;

/**
 * @author ferhat sobay
 */
@ESIntegTestCase.ClusterScope(scope = ESIntegTestCase.Scope.SUITE, numDataNodes = 1)
public class DeleteIndexIntegrationTest extends AbstractIntegrationTest {

    @Test
    public void deleteIndex() throws IOException {
        String indexName = "newindex";
        createIndex(indexName);

        DeleteIndex indicesExists = new DeleteIndex.Builder(indexName).build();
        QueryResult result = client.execute(indicesExists);
        assertTrue(result.getErrorMessage(), result.isSucceeded());
    }

    @Test
    public void deleteNonExistingIndex() throws IOException {
        DeleteIndex deleteIndex = new DeleteIndex.Builder("newindex2").build();
        QueryResult result = client.execute(deleteIndex);
        assertFalse("Delete request should fail for an index that does not exist", result.isSucceeded());
    }

}
