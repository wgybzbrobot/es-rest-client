package com.jusdt.es.common.indices.mapping;


import com.jusdt.es.common.action.GenericResultAbstractAction;
import com.jusdt.es.common.client.config.ElasticSearchVersion;

/**
 * @author Dogukan Sonmez
 * @author François Thareau
 */
public class DeleteMapping extends GenericResultAbstractAction {

    protected DeleteMapping(Builder builder) {
        super(builder);

        this.indexName = builder.index;
        this.typeName = builder.type;
    }

    @Override
    protected String buildURI(ElasticSearchVersion elasticsearchVersion) {
        return super.buildURI(elasticsearchVersion) + "/_mapping";
    }

    @Override
    public String getRestMethodName() {
        return "DELETE";
    }

    public static class Builder extends GenericResultAbstractAction.Builder<DeleteMapping, Builder> {
        private String index;
        private String type;

        public Builder(String index, String type) {
            this.index = index;
            this.type = type;
        }

        @Override
        public DeleteMapping build() {
            return new DeleteMapping(this);
        }
    }
}
