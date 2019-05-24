package com.jusdt.es.common.indices.aliases;

import com.jusdt.es.common.action.AbstractMultiIndexActionBuilder;
import com.jusdt.es.common.action.GenericResultAbstractAction;
import com.jusdt.es.common.client.config.ElasticSearchVersion;

public class AliasExists extends GenericResultAbstractAction {
    private String alias;

    protected AliasExists(Builder builder, String alias) {
        super(builder);
        this.alias = alias;
    }

    @Override
    public String getRestMethodName() {
        return "HEAD";
    }

    @Override
    protected String buildURI(ElasticSearchVersion elasticsearchVersion) {
        return super.buildURI(elasticsearchVersion) + "/_alias/" + alias;
    }

    public static class Builder extends AbstractMultiIndexActionBuilder<AliasExists, Builder> {
        protected String alias = "*";

        public Builder alias(String alias) {
            this.alias = alias;
            return this;
        }

        @Override
        public AliasExists build() {
            return new AliasExists(this, alias);
        }
    }
}
