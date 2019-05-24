package com.jusdt.es.common.action;

import java.util.Map;

import com.google.gson.Gson;
import com.jusdt.es.common.client.QueryResult;
import com.jusdt.es.common.client.config.ElasticSearchVersion;

public interface Action<T extends QueryResult> {

	String getRestMethodName();

	String getURI(ElasticSearchVersion elasticsearchVersion);

	String getData(Gson gson);

	String getPathToResult();

	Map<String, Object> getHeaders();

	T createNewElasticSearchResult(String responseBody, int statusCode, String reasonPhrase, Gson gson);

}
