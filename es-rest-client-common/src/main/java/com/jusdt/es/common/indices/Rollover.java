package com.jusdt.es.common.indices;

import java.util.HashMap;
import java.util.Map;

import com.jusdt.es.common.action.AbstractAction;
import com.jusdt.es.common.action.GenericResultAbstractAction;
import com.jusdt.es.common.client.config.ElasticSearchVersion;

public class Rollover extends GenericResultAbstractAction {

    private boolean isDryRun;

    protected Rollover(Rollover.Builder builder) {
        super(builder);

        this.indexName = builder.index;
        Map<String, Object> rolloverConditions = new HashMap<>();
        if (builder.conditions != null) {
            rolloverConditions.put("conditions", builder.conditions);
        }
        if (builder.settings != null) {
            rolloverConditions.put("settings", builder.settings);
        }
        this.payload = rolloverConditions;

        isDryRun = builder.isDryRun;
    }

    @Override
    public String getRestMethodName() {
        return "POST";
    }

    @Override
    protected String buildURI(ElasticSearchVersion elasticsearchVersion) {
        return super.buildURI(elasticsearchVersion) + "/_rollover" + (isDryRun ? "?dry_run" : "");
    }

    public static class Builder extends AbstractAction.Builder<Rollover, Rollover.Builder> {

        private String index;
        private Object conditions;
        private Object settings;
        private boolean isDryRun;

        public Builder(String index) {
            this.index = index;
        }

        public Rollover.Builder conditions(Object conditions) {
            this.conditions = conditions;
            return this;
        }

        public Rollover.Builder setDryRun(boolean dryRun) {
            this.isDryRun = dryRun;
            return this;
        }

        public Rollover.Builder settings(Object settings) {
            this.settings = settings;
            return this;
        }

        @Override
        public Rollover build() {
            return new Rollover(this);
        }
    }
}
