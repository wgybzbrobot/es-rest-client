package com.jusdt.es.common.action;

import com.google.gson.Gson;
import com.jusdt.es.common.core.DocumentResult;

public abstract class SingleResultAbstractDocumentTargetedAction
		extends AbstractDocumentTargetedAction<DocumentResult> {

	public SingleResultAbstractDocumentTargetedAction(Builder<?, ?> builder) {
		super(builder);
	}

	@Override
	public DocumentResult createNewElasticSearchResult(String responseBody, int statusCode, String reasonPhrase,
			Gson gson) {
		return createNewElasticSearchResult(new DocumentResult(gson), responseBody, statusCode, reasonPhrase, gson);
	}

}
