package com.jusdt.es.client.indices;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

import org.elasticsearch.test.ESIntegTestCase;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.io.Resources;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jusdt.es.client.common.AbstractIntegrationTest;
import com.jusdt.es.common.action.Action;
import com.jusdt.es.common.client.QueryResult;
import com.jusdt.es.common.indices.Analyze;

@ESIntegTestCase.ClusterScope(scope = ESIntegTestCase.Scope.SUITE, numDataNodes = 1)
public class AnalyzeIntegrationTest extends AbstractIntegrationTest {

	private static String sample_book;

	@BeforeClass
	public static void setupOnce() throws IOException, URISyntaxException {
		sample_book = Resources.toString(Resources.getResource("io/searchbox/sample_book.json"),
				StandardCharsets.UTF_8);
		assertNotNull(sample_book);
	}

	@Before
	public void setup() {
		createIndex("articles");
	}

	@Test
	public void testWithAnalyzer() throws IOException {
		Action action = new Analyze.Builder().analyzer("standard").text(sample_book).build();
		expectTokens(action, 22);
	}

	@Test
	public void testWithAnalyzerWithTextFormat() throws IOException {
		Action action = new Analyze.Builder().analyzer("standard").text(sample_book)
				//.format("text")
				.build();
		QueryResult result = client.execute(action);
		assertTrue(result.getErrorMessage(), result.isSucceeded());

		JsonObject resultObj = result.getJsonObject();
		assertNotNull(resultObj);
		JsonArray tokens = resultObj.getAsJsonArray("tokens");
		assertNotNull(tokens);
	}

	@Test
	public void testWithCustomTransientAnalyzer() throws IOException {
		Action action = new Analyze.Builder().tokenizer("keyword").filter("lowercase").text(sample_book).build();
		expectTokens(action, 1);
	}

	private void expectTokens(Action action, int numberOfExpectedTokens) throws IOException {
		QueryResult result = client.execute(action);
		assertTrue(result.getErrorMessage(), result.isSucceeded());

		JsonObject resultObj = result.getJsonObject();
		assertNotNull(resultObj);
		JsonArray tokens = resultObj.getAsJsonArray("tokens");
		assertEquals(numberOfExpectedTokens, tokens.getAsJsonArray().size());
	}

}
