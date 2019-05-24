package com.jusdt.es.client.core.search.aggregation;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.test.ESIntegTestCase;
import org.junit.Test;

import com.jusdt.es.client.common.AbstractIntegrationTest;
import com.jusdt.es.common.core.Search;
import com.jusdt.es.common.core.SearchResult;
import com.jusdt.es.common.core.search.aggregation.Aggregation;
import com.jusdt.es.common.core.search.aggregation.FiltersAggregation;

/**
 * @author cfstout
 */
@ESIntegTestCase.ClusterScope(scope = ESIntegTestCase.Scope.TEST, numDataNodes = 1)
public class FiltersAggregationIntegrationTest extends AbstractIntegrationTest {

	private final String INDEX = "filters_aggregation";
	private final String TYPE = "document";

	@Test
	public void testGetFiltersAggregation() throws IOException {

		createIndex(INDEX);
		AcknowledgedResponse putMappingResponse = client().admin().indices()
				.putMapping(new PutMappingRequest(INDEX).type(TYPE).source(
						"{\"document\":{\"properties\":{\"msg\":{\"store\":true,\"type\":\"text\"}}}}",
						XContentType.JSON))
				.actionGet();

		assertTrue(putMappingResponse.isAcknowledged());

		index(INDEX, TYPE, null, "{\"msg\":\"warn\"}");
		index(INDEX, TYPE, null, "{\"msg\":\"warn\"}");
		index(INDEX, TYPE, null, "{\"msg\":\"error\"}");
		index(INDEX, TYPE, null, "{\"msg\":\"other\"}");
		refresh();
		ensureSearchable(INDEX);

		String query = "{\n" + "    \"query\" : {\n" + "        \"match_all\" : {}\n" + "    },\n"
				+ "    \"aggs\" : {\n" + "        \"filters1\" : {\n" + "            \"filters\" : {\n"
				+ "               \"filters\" : {\n"
				+ "                   \"errors\" : { \"term\" : { \"msg\" : \"error\"}},\n"
				+ "                   \"warnings\" : { \"term\" : { \"msg\" : \"warn\"}}\n" + "               }\n"
				+ "            }\n" + "        }\n" + "    }\n" + "}";
		Search search = new Search.Builder(query).addIndex(INDEX).addType(TYPE).build();
		SearchResult result = client.execute(search);
		assertTrue(result.getErrorMessage(), result.isSucceeded());

		FiltersAggregation filters = result.getAggregations().getFiltersAggregation("filters1");
		assertEquals("filters1", filters.getName());
		assertTrue(1L == filters.getBucketMap().get("errors").getCount());
		assertTrue(2L == filters.getBucketMap().get("warnings").getCount());

		Aggregation aggregation = result.getAggregations().getAggregation("filters1", FiltersAggregation.class);
		assertTrue(aggregation instanceof FiltersAggregation);
		FiltersAggregation filtersByType = (FiltersAggregation) aggregation;
		assertEquals(filters, filtersByType);

		Map<String, Class> nameToTypeMap = new HashMap<String, Class>();
		nameToTypeMap.put("filters1", FiltersAggregation.class);
		List<Aggregation> aggregations = result.getAggregations().getAggregations(nameToTypeMap);
		assertEquals(1, aggregations.size());
		assertTrue(aggregations.get(0) instanceof FiltersAggregation);
		FiltersAggregation filtersWithMap = (FiltersAggregation) aggregations.get(0);
		assertEquals(filters, filtersWithMap);
	}

	@Test
	public void testGetAnonymousFiltersAggregation() throws IOException {
		createIndex(INDEX);
		AcknowledgedResponse putMappingResponse = client().admin().indices()
				.putMapping(new PutMappingRequest(INDEX).type(TYPE).source(
						"{\"document\":{\"properties\":{\"msg\":{\"store\":true,\"type\":\"text\"}}}}",
						XContentType.JSON))
				.actionGet();

		assertTrue(putMappingResponse.isAcknowledged());

		index(INDEX, TYPE, null, "{\"msg\":\"warn\"}");
		index(INDEX, TYPE, null, "{\"msg\":\"warn\"}");
		index(INDEX, TYPE, null, "{\"msg\":\"error\"}");
		index(INDEX, TYPE, null, "{\"msg\":\"other\"}");
		refresh();
		ensureSearchable(INDEX);

		String query = "{\n" + "    \"query\" : {\n" + "        \"match_all\" : {}\n" + "    },\n"
				+ "    \"aggs\" : {\n" + "        \"filters1\" : {\n" + "            \"filters\" : {\n"
				+ "               \"filters\" : [\n" + "                   { \"term\" : { \"msg\" : \"error\"}},\n"
				+ "                   { \"term\" : { \"msg\" : \"warn\"}}\n" + "               ]\n" + "            }\n"
				+ "        }\n" + "    }\n" + "}";
		Search search = new Search.Builder(query).addIndex(INDEX).addType(TYPE).build();
		SearchResult result = client.execute(search);
		assertTrue(result.getErrorMessage(), result.isSucceeded());

		FiltersAggregation filters = result.getAggregations().getFiltersAggregation("filters1");
		assertEquals("filters1", filters.getName());
		assertEquals(2, filters.getBuckets().size());
		assertEquals(1, filters.getBuckets().get(0).getCount().longValue());
		assertEquals(2, filters.getBuckets().get(1).getCount().longValue());

		Aggregation aggregation = result.getAggregations().getAggregation("filters1", FiltersAggregation.class);
		assertTrue(aggregation instanceof FiltersAggregation);
		FiltersAggregation filtersByType = (FiltersAggregation) aggregation;
		assertEquals(filters, filtersByType);

		Map<String, Class> nameToTypeMap = new HashMap<String, Class>();
		nameToTypeMap.put("filters1", FiltersAggregation.class);
		List<Aggregation> aggregations = result.getAggregations().getAggregations(nameToTypeMap);
		assertEquals(1, aggregations.size());
		assertTrue(aggregations.get(0) instanceof FiltersAggregation);
		FiltersAggregation filtersWithMap = (FiltersAggregation) aggregations.get(0);
		assertEquals(filters, filtersWithMap);
	}
}
