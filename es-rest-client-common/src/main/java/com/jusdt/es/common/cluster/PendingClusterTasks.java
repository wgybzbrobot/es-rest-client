package com.jusdt.es.common.cluster;

import com.jusdt.es.common.action.AbstractAction;
import com.jusdt.es.common.action.GenericResultAbstractAction;
import com.jusdt.es.common.client.config.ElasticsearchVersion;

public class PendingClusterTasks extends GenericResultAbstractAction {
    protected PendingClusterTasks(Builder builder) {
        super(builder);
    }

    @Override
    protected String buildURI(ElasticsearchVersion elasticsearchVersion) {
        return super.buildURI(elasticsearchVersion) + "/_cluster/pending_tasks";
    }

    @Override
    public String getRestMethodName() {
        return "GET";
    }

    public static class Builder extends AbstractAction.Builder<PendingClusterTasks, Builder> {
        @Override
        public PendingClusterTasks build() {
            return new PendingClusterTasks(this);
        }
    }
}
