package com.jusdt.es.common.core;

import com.jusdt.es.common.action.SingleResultAbstractDocumentTargetedAction;
import com.jusdt.es.common.client.config.ElasticSearchVersion;

public class Explain extends SingleResultAbstractDocumentTargetedAction {

    protected Explain(Builder builder) {
        super(builder);
        this.payload = builder.query;
    }

    @Override
    public String getRestMethodName() {
        return "POST";
    }

    @Override
    protected String buildURI(ElasticSearchVersion elasticsearchVersion) {
        return super.buildURI(elasticsearchVersion) + "/_explain";
    }

    public static class Builder extends SingleResultAbstractDocumentTargetedAction.Builder<Explain, Builder> {
        private final Object query;

        public Builder(String index, String type, String id, Object query) {
            this.index(index);
            this.type(type);
            this.id(id);
            this.query = query;
        }

        public Explain build() {
            return new Explain(this);
        }
    }

}
