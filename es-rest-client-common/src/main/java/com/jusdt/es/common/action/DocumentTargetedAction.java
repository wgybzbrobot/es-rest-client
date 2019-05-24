package com.jusdt.es.common.action;

import com.jusdt.es.common.client.QueryResult;

/**
 * Represents an Action that <b>can <i>(but NOT necessarily does)</i></b> operate on a targeted single document on Elasticsearch.
 */
public interface DocumentTargetedAction<T extends QueryResult> extends Action<T> {

	String getIndex();

	String getType();

	String getId();

}
