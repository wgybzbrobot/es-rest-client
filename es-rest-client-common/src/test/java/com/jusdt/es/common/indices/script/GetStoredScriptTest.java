package com.jusdt.es.common.indices.script;

import org.junit.Before;
import org.junit.Test;

import com.jusdt.es.common.client.config.ElasticsearchVersion;
import com.jusdt.es.common.indices.script.GetStoredScript;

import static com.jusdt.es.common.indices.script.ScriptLanguage.JAVASCRIPT;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * @author cihat keser
 */
public class GetStoredScriptTest {

    private static final String A_NAME = "a_name";
    private GetStoredScript script;

    @Before
    public void setUp() {
        GetStoredScript.Builder builder = new GetStoredScript.Builder(A_NAME).setLanguage(JAVASCRIPT);
        script = builder.build();
    }

    @Test
    public void methodIsGet() {
        assertEquals("GET", script.getRestMethodName());
    }

    @Test
    public void scriptingLanguageIsSetIntoPath() {
        assertThat(script.buildURI(ElasticsearchVersion.UNKNOWN), containsString("/_scripts/"));
    }

    @Test
    public void nameOfTheScriptIsSetIntoPath() {
        assertThat(script.buildURI(ElasticsearchVersion.UNKNOWN), containsString("/_scripts/" + A_NAME));
    }

}