package com.jusdt.es.common.action;

import java.util.Collection;

import com.jusdt.es.common.client.QueryResult;

/**
 * Represents an Action that can be included in a Bulk request.
 */
public interface BulkableAction<T extends QueryResult> extends DocumentTargetedAction<T> {

	String getBulkMethodName();

	Collection<Object> getParameter(String key);

}
