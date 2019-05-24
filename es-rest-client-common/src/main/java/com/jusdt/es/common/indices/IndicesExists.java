package com.jusdt.es.common.indices;

import java.util.Collection;

import com.jusdt.es.common.action.AbstractMultiIndexActionBuilder;
import com.jusdt.es.common.action.GenericResultAbstractAction;

/**
 * @author Dogukan Sonmez
 */
public class IndicesExists extends GenericResultAbstractAction {

    protected IndicesExists(Builder builder) {
        super(builder);
    }

    @Override
    public String getRestMethodName() {
        return "HEAD";
    }

    public static class Builder extends AbstractMultiIndexActionBuilder<IndicesExists, Builder> {

        public Builder(String index){
            addIndex(index);
        }

        public Builder(Collection<? extends String> indices){
            addIndices(indices);
        }

        @Override
        public IndicesExists build() {
            return new IndicesExists(this);
        }
    }

}
