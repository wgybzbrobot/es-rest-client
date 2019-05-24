package com.jusdt.es.client.core;

import org.elasticsearch.test.ESIntegTestCase;
import org.junit.Test;

import com.jusdt.es.client.common.AbstractIntegrationTest;
import com.jusdt.es.common.client.QueryResult;
import com.jusdt.es.common.core.SearchShards;

import java.io.IOException;
import java.util.Map;

/**
 * @author cihat keser
 */
@ESIntegTestCase.ClusterScope(scope = ESIntegTestCase.Scope.SUITE, numDataNodes = 1)
public class SearchShardsIntegrationTest extends AbstractIntegrationTest {
    static final String INDEX = "twitter";
    static final String TYPE = "tweet";

    @Test
    public void testBasicFlow() throws IOException {
        SearchShards searchShards = new SearchShards.Builder()
                .addIndex(INDEX)
                .build();
        QueryResult result = client.execute(searchShards);
        assertTrue(result.getErrorMessage(), result.isSucceeded());

        Map source = result.getSourceAsObject(Map.class);
        assertTrue(source.containsKey("nodes"));
        assertTrue(source.containsKey("shards"));
    }
}
