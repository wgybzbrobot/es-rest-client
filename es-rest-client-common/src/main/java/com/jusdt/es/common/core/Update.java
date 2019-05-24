package com.jusdt.es.common.core;

import com.jusdt.es.common.action.BulkableAction;
import com.jusdt.es.common.action.SingleResultAbstractDocumentTargetedAction;
import com.jusdt.es.common.client.config.ElasticSearchVersion;
import com.jusdt.es.common.params.Parameters;

public class Update extends SingleResultAbstractDocumentTargetedAction implements BulkableAction<DocumentResult> {

    protected Update(Builder builder) {
        super(builder);
        this.payload = builder.payload;
    }

    @Override
    public String getBulkMethodName() {
        return "update";
    }

    @Override
    protected String buildURI(ElasticSearchVersion elasticsearchVersion) {
        return super.buildURI(elasticsearchVersion) + "/_update";
    }

    @Override
    public String getRestMethodName() {
        return "POST";
    }

    @Override
    public String getPathToResult() {
        return "ok";
    }

    public static class Builder extends SingleResultAbstractDocumentTargetedAction.Builder<Update, Builder> {
    	
        private final Object payload;

        public Builder(Object payload) {
            this.payload = payload;
        }

        public Update build() {
            return new Update(this);
        }
        
    }

    public static class VersionBuilder extends Builder {
    	
        public VersionBuilder(Object payload, Long version) {
            super(payload);
            this.setParameter(Parameters.VERSION, version);
        }
        
    }
    
}
