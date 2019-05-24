package com.jusdt.es.common.cluster;

import com.jusdt.es.common.action.AbstractAction;
import com.jusdt.es.common.action.GenericResultAbstractAction;
import com.jusdt.es.common.client.config.ElasticsearchVersion;

/**
 * Retrieve cluster wide settings.
 *
 * @author cihat keser
 */
public class GetSettings extends GenericResultAbstractAction {

    protected GetSettings(Builder builder) {
        super(builder);
    }

    protected String buildURI(ElasticsearchVersion elasticsearchVersion) {
        return super.buildURI(elasticsearchVersion) + "/_cluster/settings";
    }

    @Override
    public String getRestMethodName() {
        return "GET";
    }

    public static class Builder extends AbstractAction.Builder<GetSettings, Builder> {

        @Override
        public GetSettings build() {
            return new GetSettings(this);
        }
    }

}
