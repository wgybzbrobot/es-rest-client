package com.jusdt.es.common.fields;

import java.util.HashMap;
import java.util.Map;

import com.jusdt.es.common.action.AbstractAction;
import com.jusdt.es.common.action.GenericResultAbstractAction;
import com.jusdt.es.common.client.config.ElasticsearchVersion;
import com.jusdt.es.common.params.Parameters;

public class FieldCapabilities extends GenericResultAbstractAction {
    protected FieldCapabilities(FieldCapabilities.Builder builder) {
        super(builder);

        this.indexName = builder.index;

        Map<String, Object> fieldStatsBody = new HashMap<>();
        fieldStatsBody.put("fields", builder.fields);

        this.payload = fieldStatsBody;
    }

    @Override
    public String getRestMethodName() {
        return "POST";
    }

    @Override
    protected String buildURI(ElasticsearchVersion elasticsearchVersion) {
        String buildURI = super.buildURI(elasticsearchVersion);
        if (buildURI.isEmpty())
            return "_field_caps";

        return buildURI + "/_field_caps";
    }


    public static class Builder extends AbstractAction.Builder<FieldCapabilities, FieldCapabilities.Builder> {

        private String index;
        private Object fields;

        public Builder(Object fields) {
            this.fields = fields;
        }

        public FieldCapabilities.Builder setIndex(String index) {
            this.index = index;
            return this;
        }

        public FieldCapabilities.Builder setLevel(String level) {
            parameters.put(Parameters.LEVEL, level);
            return this;
        }

        @Override
        public FieldCapabilities build() {
            return new FieldCapabilities(this);
        }
    }
}
