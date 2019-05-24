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
import com.jusdt.es.common.core.search.aggregation.SumAggregation;

/**
 * @author cfstout
 */
@ESIntegTestCase.ClusterScope(scope = ESIntegTestCase.Scope.TEST, numDataNodes = 1)
public class SumAggregationIntegrationTest extends AbstractIntegrationTest {

	private final String INDEX = "sum_aggregation";
	private final String TYPE = "document";

	@Test
	public void testGetSumAggregation() throws IOException {
		createIndex(INDEX);
		AcknowledgedResponse putMappingResponse = client().admin().indices()
				.putMapping(new PutMappingRequest(INDEX).type(TYPE).source(
						"{\"document\":{\"properties\":{\"num\":{\"store\":true,\"type\":\"integer\"}}}}",
						XContentType.JSON))
				.actionGet();

		assertTrue(putMappingResponse.isAcknowledged());

		index(INDEX, TYPE, null, "{\"num\":2}");
		index(INDEX, TYPE, null, "{\"num\":3}");
		refresh();
		ensureSearchable(INDEX);

		String query = "{\n" + "    \"query\" : {\n" + "        \"match_all\" : {}\n" + "    },\n"
				+ "    \"aggs\" : {\n" + "        \"sum1\" : {\n" + "            \"sum\" : {\n"
				+ "                \"field\" : \"num\"\n" + "            }\n" + "        }\n" + "    }\n" + "}";
		Search search = new Search.Builder(query).addIndex(INDEX).addType(TYPE).build();
		SearchResult result = client.execute(search);
		assertTrue(result.getErrorMessage(), result.isSucceeded());

		SumAggregation sum = result.getAggregations().getSumAggregation("sum1");
		assertEquals("sum1", sum.getName());
		assertEquals(new Double(5), sum.getSum());

		Aggregation aggregation = result.getAggregations().getAggregation("sum1", SumAggregation.class);
		assertTrue(aggregation instanceof SumAggregation);
		SumAggregation sumByType = (SumAggregation) aggregation;
		assertEquals(sum, sumByType);

		Map<String, Class> nameToTypeMap = new HashMap<String, Class>();
		nameToTypeMap.put("sum1", SumAggregation.class);
		List<Aggregation> aggregations = result.getAggregations().getAggregations(nameToTypeMap);
		assertEquals(1, aggregations.size());
		assertTrue(aggregations.get(0) instanceof SumAggregation);
		SumAggregation sumWithMap = (SumAggregation) aggregations.get(0);
		assertEquals(sum, sumWithMap);
	}

	@Test
	public void testBadAggregationQueryResult() throws IOException {
		createIndex(INDEX);
		AcknowledgedResponse putMappingResponse = client().admin().indices()
				.putMapping(new PutMappingRequest(INDEX).type(TYPE).source(
						"{\"document\":{\"properties\":{\"num\":{\"store\":true,\"type\":\"integer\"}}}}",
						XContentType.JSON))
				.actionGet();

		assertTrue(putMappingResponse.isAcknowledged());

		index(INDEX, TYPE, null, "{\"num\":2}");
		index(INDEX, TYPE, null, "{\"num\":3}");
		refresh();
		ensureSearchable(INDEX);

		String query = "{\n" + "    \"query\" : {\n" + "        \"match_all\" : {}\n" + "    },\n"
				+ "    \"aggs\" : {\n" + "        \"sum1\" : {\n" + "            \"sum\" : {\n"
				+ "                \"field\" : \"bad_field\"\n" + "            }\n" + "        }\n" + "    }\n" + "}";
		Search search = new Search.Builder(query).addIndex(INDEX).addType(TYPE).build();
		SearchResult result = client.execute(search);
		assertTrue(result.getErrorMessage(), result.isSucceeded());
		SumAggregation sum = result.getAggregations().getSumAggregation("sum1");
		assertEquals(new Double(0), sum.getSum());

		Aggregation aggregation = result.getAggregations().getAggregation("sum1", SumAggregation.class);
		assertTrue(aggregation instanceof SumAggregation);
		SumAggregation sumByType = (SumAggregation) aggregation;
		assertEquals(sum, sumByType);

		Map<String, Class> nameToTypeMap = new HashMap<String, Class>();
		nameToTypeMap.put("sum1", SumAggregation.class);
		List<Aggregation> aggregations = result.getAggregations().getAggregations(nameToTypeMap);
		assertEquals(1, aggregations.size());
		assertTrue(aggregations.get(0) instanceof SumAggregation);
		SumAggregation sumWithMap = (SumAggregation) aggregations.get(0);
		assertEquals(sum, sumWithMap);
	}

}
