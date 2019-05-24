package com.jusdt.es.common.action;

import com.google.gson.Gson;
import com.jusdt.es.common.client.JestResult;

/**
 * @author cihat keser
 */
public abstract class GenericResultAbstractDocumentTargetedAction extends AbstractDocumentTargetedAction<JestResult> {

    public GenericResultAbstractDocumentTargetedAction(Builder builder) {
        super(builder);
    }

    @Override
    public JestResult createNewElasticSearchResult(String responseBody, int statusCode, String reasonPhrase, Gson gson) {
        return createNewElasticSearchResult(new JestResult(gson), responseBody, statusCode, reasonPhrase, gson);
    }

}
