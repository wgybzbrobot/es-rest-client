package com.jusdt.es.common.indices;

import com.jusdt.es.common.action.GenericResultAbstractAction;
import com.jusdt.es.common.client.config.ElasticSearchVersion;

/**
 * @author cihat keser
 */
public class CloseIndex extends GenericResultAbstractAction {

    protected CloseIndex(Builder builder) {
        super(builder);

        this.indexName = builder.index;
    }

    @Override
    protected String buildURI(ElasticSearchVersion elasticsearchVersion) {
        return super.buildURI(elasticsearchVersion) + "/_close";
    }

    @Override
    public String getRestMethodName() {
        return "POST";
    }

    public static class Builder extends GenericResultAbstractAction.Builder<CloseIndex, Builder> {
        private String index;

        public Builder(String index) {
            this.index = index;
        }

        @Override
        public CloseIndex build() {
            return new CloseIndex(this);
        }
    }
}
