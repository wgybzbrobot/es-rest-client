package com.jusdt.es.common.action;

import com.jusdt.es.common.client.JestResult;

/**
 * Represents an Action that <b>can <i>(but NOT necessarily does)</i></b> operate on a targeted single document on Elasticsearch.
 *
 * @author cihat keser
 */
public interface DocumentTargetedAction<T extends JestResult> extends Action<T> {

    String getIndex();

    String getType();

    String getId();
}
