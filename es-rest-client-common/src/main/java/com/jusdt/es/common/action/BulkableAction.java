package com.jusdt.es.common.action;

import java.util.Collection;

import com.jusdt.es.common.client.JestResult;

/**
 * Represents an Action that can be included in a Bulk request.
 *
 * @author cihat keser
 */
public interface BulkableAction<T extends JestResult> extends DocumentTargetedAction<T> {

    String getBulkMethodName();

    Collection<Object> getParameter(String key);

}
