package com.jusdt.es.common.indices;

import com.jusdt.es.common.action.AbstractMultiIndexActionBuilder;
import com.jusdt.es.common.action.GenericResultAbstractAction;
import com.jusdt.es.common.client.config.ElasticSearchVersion;

/**
 * @author Dogukan Sonmez
 * @author cihat keser
 */
public class Flush extends GenericResultAbstractAction {

    protected Flush(Builder builder) {
        super(builder);
    }

    @Override
    protected String buildURI(ElasticSearchVersion elasticsearchVersion) {
        return super.buildURI(elasticsearchVersion) + "/_flush";
    }

    @Override
    public String getRestMethodName() {
        return "POST";
    }

    public static class Builder extends AbstractMultiIndexActionBuilder<Flush, Builder> {
        public Builder force(boolean force) {
            return setParameter("force", force);
        }

        public Builder force() {
            return force(true);
        }

        public Builder waitIfOngoing(boolean waitIfOngoing) {
            return setParameter("wait_if_ongoing", waitIfOngoing);
        }

        public Builder waitIfOngoing() {
            return waitIfOngoing(true);
        }

        @Override
        public Flush build() {
            return new Flush(this);
        }
    }
}
