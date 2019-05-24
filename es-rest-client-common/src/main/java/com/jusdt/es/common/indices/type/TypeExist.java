package com.jusdt.es.common.indices.type;

import com.jusdt.es.common.action.AbstractMultiTypeActionBuilder;
import com.jusdt.es.common.action.GenericResultAbstractAction;
import com.jusdt.es.common.client.config.ElasticSearchVersion;

/**
 * @author happyprg(hongsgo @ gmail.com)
 */
public class TypeExist extends GenericResultAbstractAction {

    TypeExist(Builder builder) {

        super(builder);
    }

    @Override
    protected String getURLCommandExtension(ElasticSearchVersion elasticsearchVersion) {
        return "_mapping";
    }

    @Override
    public String getRestMethodName() {
        return "HEAD";
    }

    public static class Builder extends AbstractMultiTypeActionBuilder<TypeExist, Builder> {

        public Builder(String index) {
            this.addIndex(index);
        }

        @Override
        public TypeExist build() {
            return new TypeExist(this);
        }
    }

}
