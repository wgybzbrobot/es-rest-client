package com.jusdt.es.common.action;

import com.google.gson.Gson;
import com.jusdt.es.common.client.QueryResult;

public abstract class GenericResultAbstractAction extends AbstractAction<QueryResult> {

	public GenericResultAbstractAction() {
	}

	public GenericResultAbstractAction(Builder<?, ?> builder) {
		super(builder);
	}

	@Override
	public QueryResult createNewElasticSearchResult(String responseBody, int statusCode, String reasonPhrase,
			Gson gson) {
		return createNewElasticSearchResult(new QueryResult(gson), responseBody, statusCode, reasonPhrase, gson);
	}

}
