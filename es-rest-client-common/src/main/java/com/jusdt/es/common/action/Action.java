package com.jusdt.es.common.action;

import com.google.gson.Gson;
import com.jusdt.es.common.client.JestResult;
import com.jusdt.es.common.client.config.ElasticsearchVersion;

import java.util.Map;

/**
 * @author Dogukan Sonmez
 */
public interface Action<T extends JestResult> {

    String getRestMethodName();

    String getURI(ElasticsearchVersion elasticsearchVersion);

    String getData(Gson gson);

    String getPathToResult();

    Map<String, Object> getHeaders();

    T createNewElasticSearchResult(String responseBody, int statusCode, String reasonPhrase, Gson gson);
}
