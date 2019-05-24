package com.jusdt.es.common.core;

import com.jusdt.es.common.action.GenericResultAbstractAction;
import com.jusdt.es.common.client.config.ElasticSearchVersion;

public class Validate extends GenericResultAbstractAction {

    protected Validate(Builder builder) {
        super(builder);

        this.indexName = builder.index;
        this.typeName = builder.type;
        this.payload = builder.query;
    }

    @Override
    protected String buildURI(ElasticSearchVersion elasticsearchVersion) {
        return super.buildURI(elasticsearchVersion) + "/_validate/query";
    }

    @Override
    public String getRestMethodName() {
        return "POST";
    }

    @Override
    public String getPathToResult() {
        return "valid";
    }

    public static class Builder extends GenericResultAbstractAction.Builder<Validate, Builder> {
    	
        private final Object query;
        private String index;
        private String type;

        public Builder(Object query) {
            this.query = query;
        }

        public Builder index(String val) {
            index = val;
            return this;
        }

        public Builder type(String val) {
            type = val;
            return this;
        }

        public Validate build() {
            return new Validate(this);
        }
        
    }
    
}
