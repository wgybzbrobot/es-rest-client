package com.jusdt.es.common.indices;

import com.jusdt.es.common.action.AbstractMultiIndexActionBuilder;
import com.jusdt.es.common.action.GenericResultAbstractAction;
import com.jusdt.es.common.client.config.ElasticSearchVersion;

/**
 * @author Dogukan Sonmez
 * @author cihat keser
 */
public class Refresh extends GenericResultAbstractAction {

    protected Refresh(Builder builder) {
        super(builder);
    }

    @Override
    public String getRestMethodName() {
        return "POST";
    }

    @Override
    protected String buildURI(ElasticSearchVersion elasticsearchVersion) {
        return super.buildURI(elasticsearchVersion) + "/_refresh";
    }

    public static class Builder extends AbstractMultiIndexActionBuilder<Refresh, Builder> {

        @Override
        public Refresh build() {
            return new Refresh(this);
        }
    }
}
