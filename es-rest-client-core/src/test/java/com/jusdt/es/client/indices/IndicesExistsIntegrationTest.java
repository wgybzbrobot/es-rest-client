package com.jusdt.es.client.indices;

import org.elasticsearch.test.ESIntegTestCase;
import org.junit.Before;
import org.junit.Test;

import com.jusdt.es.client.common.AbstractIntegrationTest;
import com.jusdt.es.common.action.Action;
import com.jusdt.es.common.client.QueryResult;
import com.jusdt.es.common.indices.IndicesExists;

import java.io.IOException;

/**
 * @author cihat keser
 */
@ESIntegTestCase.ClusterScope(scope = ESIntegTestCase.Scope.SUITE, numDataNodes = 1)
public class IndicesExistsIntegrationTest extends AbstractIntegrationTest {

    static final String INDEX_1_NAME = "osman";
    static final String INDEX_2_NAME = "john";

    @Before
    public void setup() {
        createIndex(INDEX_1_NAME, INDEX_2_NAME);
    }

    @Test
    public void multiIndexNotExists() throws IOException {
        Action action = new IndicesExists.Builder("qwe").addIndex("asd").build();

        QueryResult result = client.execute(action);
        assertFalse(result.isSucceeded());
    }

    @Test
    public void multiIndexExists() throws IOException {
        Action action = new IndicesExists.Builder(INDEX_1_NAME).addIndex(INDEX_2_NAME).build();

        QueryResult result = client.execute(action);
        assertTrue(result.getErrorMessage(), result.isSucceeded());
    }

    @Test
    public void indexExists() throws IOException {
        Action action = new IndicesExists.Builder(INDEX_1_NAME).build();

        QueryResult result = client.execute(action);
        assertTrue(result.getErrorMessage(), result.isSucceeded());
    }

    @Test
    public void indexNotExists() throws IOException {
        Action action = new IndicesExists.Builder("nope").build();

        QueryResult result = client.execute(action);
        assertFalse(result.isSucceeded());
    }

}
