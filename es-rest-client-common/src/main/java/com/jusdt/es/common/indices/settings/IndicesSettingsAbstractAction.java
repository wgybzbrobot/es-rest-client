package com.jusdt.es.common.indices.settings;

import com.jusdt.es.common.action.GenericResultAbstractAction;
import com.jusdt.es.common.client.config.ElasticSearchVersion;

/**
 * @author cihat keser
 */
public abstract class IndicesSettingsAbstractAction extends GenericResultAbstractAction {

    protected IndicesSettingsAbstractAction(Builder builder) {
        super(builder);
    }

    @Override
    protected String buildURI(ElasticSearchVersion elasticsearchVersion) {
        return super.buildURI(elasticsearchVersion) + "/_settings";
    }

}
