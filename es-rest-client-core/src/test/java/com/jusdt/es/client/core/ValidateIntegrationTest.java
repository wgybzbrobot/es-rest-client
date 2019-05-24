package com.jusdt.es.client.core;

import org.elasticsearch.test.ESIntegTestCase;
import org.junit.Before;
import org.junit.Test;

import com.jusdt.es.client.common.AbstractIntegrationTest;
import com.jusdt.es.common.action.Action;
import com.jusdt.es.common.client.JestResult;
import com.jusdt.es.common.core.Validate;
import com.jusdt.es.common.params.Parameters;

import java.io.IOException;

/**
 * @author Dogukan Sonmez
 */
@ESIntegTestCase.ClusterScope(scope = ESIntegTestCase.Scope.SUITE, numDataNodes = 1)
public class ValidateIntegrationTest extends AbstractIntegrationTest {

    @Before
    public void setup() {
        createIndex("twitter");
    }

    @Test
    public void validateQueryWithIndex() throws IOException {
        Validate validate = new Validate.Builder("{\n" +
                "  \"query\" : {\n" +
                "    \"bool\" : {\n" +
                "      \"must\" : {\n" +
                "        \"query_string\" : {\n" +
                "          \"query\" : \"*:*\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"filter\" : {\n" +
                "        \"term\" : { \"user\" : \"kimchy\" }\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}")
                .index("twitter")
                .setParameter(Parameters.EXPLAIN, true)
                .build();
        executeTestCase(validate);
    }

    @Test
    public void validateQueryWithIndexAndType() throws IOException {
        executeTestCase(new Validate.Builder("{\n" +
                "  \"query\" : {\n" +
                "    \"bool\" : {\n" +
                "      \"must\" : {\n" +
                "        \"query_string\" : {\n" +
                "          \"query\" : \"*:*\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"filter\" : {\n" +
                "        \"term\" : { \"user\" : \"kimchy\" }\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}").index("twitter").type("tweet").build());
    }

    private void executeTestCase(Action action) throws RuntimeException, IOException {
        JestResult result = client.execute(action);
        assertTrue(result.getErrorMessage(), result.isSucceeded());
        assertTrue((Boolean) result.getValue("valid"));
    }
}
