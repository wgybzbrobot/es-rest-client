package com.jusdt.es.client.indices.script;

import java.io.IOException;

import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.common.bytes.BytesArray;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;

import com.jusdt.es.client.common.AbstractIntegrationTest;
import com.jusdt.es.common.client.JestResult;
import com.jusdt.es.common.indices.script.GetStoredScript;
import com.jusdt.es.common.indices.script.ScriptLanguage;

public class GetStoredScriptIntegrationTest extends AbstractIntegrationTest {

	private static final String lang = ScriptLanguage.PAINLESS.pathParameterName;
	private static final String SCRIPT = "{" + "    \"script\": {" + "       \"lang\": \"" + lang + "\","
			+ "       \"source\": \"return 42;\"}" + "}";

	@Test
	public void createStoredScript() throws IOException {
		String name = "mylilscript";

		AcknowledgedResponse response = client().admin().cluster().preparePutStoredScript().setId(name)
				.setContent(new BytesArray(SCRIPT), XContentType.JSON).get();
		assertTrue("could not create stored script on server", response.isAcknowledged());

		GetStoredScript getStoredScript = new GetStoredScript.Builder(name).setLanguage(ScriptLanguage.PAINLESS)
				.build();
		JestResult result = client.execute(getStoredScript);
		assertTrue(result.getErrorMessage(), result.isSucceeded());
	}

}