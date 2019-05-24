package com.jusdt.es.client.indices;


import org.elasticsearch.test.ESIntegTestCase;
import org.junit.Test;

import com.jusdt.es.client.common.AbstractIntegrationTest;
import com.jusdt.es.common.client.JestResult;
import com.jusdt.es.common.indices.template.GetTemplate;
import com.jusdt.es.common.indices.template.PutTemplate;

import java.io.IOException;

/**
 * @author asierdelpozo
 */
@ESIntegTestCase.ClusterScope(scope = ESIntegTestCase.Scope.SUITE, numDataNodes = 1)
public class PutTemplateIntegrationTest extends AbstractIntegrationTest {

    @Test
    public void testPutTemplate() throws IOException {
        PutTemplate putTemplate = new PutTemplate.Builder("new_template_1",
                "{	" +
                        "\"template\" : \"*\"," +
                        "\"order\" : 0," +
                        "\"settings\" : {" +
                        "	\"number_of_shards\" : 1" +
                        "}," +
                        "\"mappings\" : {" +
                        "	\"type1\" : {" +
                        "		\"_source\" : { \"enabled\" : false }" +
                        "	}" +
                        "}" +
                        "}")
                .build();

        JestResult result = client.execute(putTemplate);
        assertTrue(result.getErrorMessage(), result.isSucceeded());

        GetTemplate getTemplate = new GetTemplate.Builder("new_template_1").build();
        result = client.execute(getTemplate);
        assertTrue(result.getErrorMessage(), result.isSucceeded());
    }

}
