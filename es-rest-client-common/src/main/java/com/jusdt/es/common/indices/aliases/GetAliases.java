package com.jusdt.es.common.indices.aliases;

import com.google.common.base.Joiner;
import com.jusdt.es.common.action.AbstractMultiIndexActionBuilder;
import com.jusdt.es.common.action.GenericResultAbstractAction;
import com.jusdt.es.common.client.config.ElasticSearchVersion;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author cihat keser
 */
public class GetAliases extends GenericResultAbstractAction {
    protected String aliasName;

    protected GetAliases(Builder builder) {
        super(builder);
        aliasName = builder.getJoinedAliases();
    }

    @Override
    public String getRestMethodName() {
        return "GET";
    }

    @Override
    protected String buildURI(ElasticSearchVersion elasticsearchVersion) {
        if (aliasName == null){
            return super.buildURI(elasticsearchVersion) + "/_alias";
        } else {
            return super.buildURI(elasticsearchVersion) + "/_alias/" + aliasName;
        }
    }

    public static class Builder extends AbstractMultiIndexActionBuilder<GetAliases, Builder> {

        protected Set<String> aliasNames = new LinkedHashSet<String>();

        public String getJoinedAliases() {
            if (aliasNames.size() > 0) {
                return Joiner.on(',').join(aliasNames);
            } else {
                return null;
            }
        }

        public Builder addAlias(String aliasName) {
            this.aliasNames.add(aliasName);
            return this;
        }

        public Builder addAliases(Collection<? extends String> aliasNames) {
            this.aliasNames.addAll(aliasNames);
            return this;
        }


        @Override
        public GetAliases build() {
            return new GetAliases(this);
        }
    }
}
