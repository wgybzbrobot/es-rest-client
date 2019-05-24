package com.jusdt.es.common.snapshot;

import com.jusdt.es.common.client.config.ElasticSearchVersion;

/**
 * @author happyprg(hongsgo@gmail.com)
 */
public class SnapshotStatus extends AbstractSnapshotAction {

    protected SnapshotStatus(Builder builder) {
        super(builder);
    }

    @Override
    protected String buildURI(ElasticSearchVersion elasticsearchVersion) {
        return super.buildURI(elasticsearchVersion) + "/_status";
    }

    @Override
    public String getRestMethodName() {
        return "GET";
    }

    public static class Builder extends AbstractSnapshotAction.MultipleSnapshotBuilder<SnapshotStatus, Builder> {
        public Builder(String repository) {
            super(repository);
        }

        @Override
        public SnapshotStatus build() {
            return new SnapshotStatus(this);
        }
    }
}
