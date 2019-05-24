package com.jusdt.es.client.indices.script;

import static com.jusdt.es.common.indices.script.ScriptLanguage.PAINLESS;

import java.io.IOException;

import org.elasticsearch.action.admin.cluster.storedscripts.GetStoredScriptResponse;
import org.elasticsearch.test.ESIntegTestCase;
import org.junit.Test;

import com.jusdt.es.client.common.AbstractIntegrationTest;
import com.jusdt.es.common.client.QueryResult;
import com.jusdt.es.common.indices.script.CreateStoredScript;

@ESIntegTestCase.ClusterScope(scope = ESIntegTestCase.Scope.TEST, numDataNodes = 1)
public class CreateStoredScriptIntegrationTest extends AbstractIntegrationTest {

	@Test
	public void createAStoredScript() throws IOException {
		String name = "script-test";
		String script = "int aVariable = 1; return aVariable";

		CreateStoredScript createStoredScript = new CreateStoredScript.Builder(name).setLanguage(PAINLESS)
				.setSource(script).build();
		QueryResult result = client.execute(createStoredScript);
		assertTrue(result.getErrorMessage(), result.isSucceeded());

		GetStoredScriptResponse getStoredScriptResponse = client().admin().cluster().prepareGetStoredScript()
				.setId(name).get();
		assertNotNull(getStoredScriptResponse.getSource());
		assertEquals(script, getStoredScriptResponse.getSource().getSource());
	}

}
