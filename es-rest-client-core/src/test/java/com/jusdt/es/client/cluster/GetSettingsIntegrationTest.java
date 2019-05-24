package com.jusdt.es.client.cluster;


import org.elasticsearch.test.ESIntegTestCase;
import org.junit.Test;

import com.jusdt.es.client.common.AbstractIntegrationTest;
import com.jusdt.es.common.client.QueryResult;
import com.jusdt.es.common.cluster.GetSettings;

import java.io.IOException;
import java.util.Map;

/**
 * @author cihat keser
 */
@ESIntegTestCase.ClusterScope(scope = ESIntegTestCase.Scope.SUITE, numDataNodes = 1)
public class GetSettingsIntegrationTest extends AbstractIntegrationTest {

    @Test
    public void nullSourceShouldFailOnServer() throws IOException {
        GetSettings getSettings = new GetSettings.Builder().build();
        QueryResult result = client.execute(getSettings);
        assertTrue(result.getErrorMessage(), result.isSucceeded());

        Map settings  = result.getSourceAsObject(Map.class);
        assertTrue(settings.containsKey("persistent"));
        assertTrue(settings.containsKey("transient"));
    }

}
