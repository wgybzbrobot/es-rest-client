package com.jusdt.es.common.indices.mapping;

import com.jusdt.es.common.action.AbstractMultiTypeActionBuilder;
import com.jusdt.es.common.action.GenericResultAbstractAction;
import com.jusdt.es.common.client.config.ElasticSearchVersion;

/**
 * @author ferhat
 * @author cihat keser
 */
public class GetMapping extends GenericResultAbstractAction {

    protected GetMapping(Builder builder) {
        super(builder);
    }

    @Override
    public String getRestMethodName() {
        return "GET";
    }

    @Override
    protected String buildURI(ElasticSearchVersion elasticsearchVersion) {
        return super.buildURI(elasticsearchVersion) + "/_mapping";
    }

    public static class Builder extends AbstractMultiTypeActionBuilder<GetMapping, Builder> {

        @Override
        public GetMapping build() {
            return new GetMapping(this);
        }
    }

}
