package com.jusdt.es.client.fields;

import com.google.common.collect.ImmutableMap;
import com.jusdt.es.client.common.AbstractIntegrationTest;
import com.jusdt.es.common.client.JestResult;
import com.jusdt.es.common.core.DocumentResult;
import com.jusdt.es.common.core.Index;
import com.jusdt.es.common.fields.FieldCapabilities;

import org.elasticsearch.test.ESIntegTestCase;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@ESIntegTestCase.ClusterScope(scope = ESIntegTestCase.Scope.SUITE, numDataNodes = 1)
public class FieldCapabilitiesIntegrationTest extends AbstractIntegrationTest {

    private static final String INDEX = "twitter";
    private static final String TYPE = "tweet";
    private static final String TEST_FIELD = "test_name";
    private static final List FIELDS = Collections.singletonList(TEST_FIELD);

    @Test
    public void testFieldStats() throws IOException {

        Map<String, String> source = ImmutableMap.of(
                TEST_FIELD, "testFieldStats");

        DocumentResult documentResult = client.execute(
                new Index.Builder(source)
                        .index(INDEX)
                        .type(TYPE)
                        .refresh(true)
                        .build()
        );

        assertTrue(documentResult.getErrorMessage(), documentResult.isSucceeded());

        FieldCapabilities fieldCapabilities = new FieldCapabilities.Builder(FIELDS).build();

        JestResult fieldCapabilitiesResult = client.execute(fieldCapabilities);

        assertTrue(fieldCapabilitiesResult.getErrorMessage(), fieldCapabilitiesResult.isSucceeded());
    }
}
