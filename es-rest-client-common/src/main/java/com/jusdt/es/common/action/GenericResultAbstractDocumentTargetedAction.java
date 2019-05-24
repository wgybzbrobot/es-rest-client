package com.jusdt.es.common.action;

import com.google.gson.Gson;
import com.jusdt.es.common.client.QueryResult;

public abstract class GenericResultAbstractDocumentTargetedAction extends AbstractDocumentTargetedAction<QueryResult> {

	public GenericResultAbstractDocumentTargetedAction(Builder<?, ?> builder) {
		super(builder);
	}

	@Override
	public QueryResult createNewElasticSearchResult(String responseBody, int statusCode, String reasonPhrase,
			Gson gson) {
		return createNewElasticSearchResult(new QueryResult(gson), responseBody, statusCode, reasonPhrase, gson);
	}

}
