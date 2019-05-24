package com.jusdt.es.common.indices;

import com.jusdt.es.common.action.AbstractMultiIndexActionBuilder;
import com.jusdt.es.common.action.GenericResultAbstractAction;
import com.jusdt.es.common.client.config.ElasticSearchVersion;

/**
 * @author Dogukan Sonmez
 * @author cihat keser
 */
public class ClearCache extends GenericResultAbstractAction {

    protected ClearCache(Builder builder) {
        super(builder);
    }

    @Override
    protected String buildURI(ElasticSearchVersion elasticsearchVersion) {
        return super.buildURI(elasticsearchVersion) + "/_cache/clear";
    }

    @Override
    public String getRestMethodName() {
        return "POST";
    }

    public static class Builder extends AbstractMultiIndexActionBuilder<ClearCache, Builder> {

        public Builder filter(boolean filter) {
            setParameter("filter", filter);
            return this;
        }

        public Builder fieldData(boolean fieldData) {
            setParameter("field_data", fieldData);
            return this;
        }

        public Builder bloom(boolean bloom) {
            setParameter("bloom", bloom);
            return this;
        }

        @Override
        public ClearCache build() {
            return new ClearCache(this);
        }
    }
}
