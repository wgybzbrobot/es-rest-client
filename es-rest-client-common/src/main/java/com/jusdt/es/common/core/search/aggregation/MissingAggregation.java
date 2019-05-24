package com.jusdt.es.common.core.search.aggregation;

import com.google.gson.JsonObject;

import static com.jusdt.es.common.core.search.aggregation.AggregationField.DOC_COUNT;

import java.util.Objects;

/**
 * @author cfstout
 */

public class MissingAggregation extends MetricAggregation {
    public static final String TYPE = "missing";

    private Long missing;

    public MissingAggregation(String name, JsonObject missingAggregation) {
        super(name, missingAggregation);
        missing = missingAggregation.get(String.valueOf(DOC_COUNT)).getAsLong();
    }

    public Long getMissing() {
        return missing;
    }



    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }

        MissingAggregation rhs = (MissingAggregation) obj;
        return super.equals(obj) && Objects.equals(missing, rhs.missing);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), missing);
    }
}
