package com.jusdt.es.common.core.search.aggregation;

import com.google.gson.JsonObject;

import static com.jusdt.es.common.core.search.aggregation.AggregationField.VALUE;

import java.util.Objects;

/**
 * @author cfstout
 */
public class ValueCountAggregation extends MetricAggregation {

    public static final String TYPE = "value_count";

    private Long valueCount;

    public ValueCountAggregation(String name, JsonObject valueCountAggregation) {
        super(name, valueCountAggregation);
        valueCount = valueCountAggregation.get(String.valueOf(VALUE)).getAsLong();
    }

    public Long getValueCount() {
        return valueCount;
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

        ValueCountAggregation rhs = (ValueCountAggregation) obj;
        return super.equals(obj)
                && Objects.equals(valueCount, rhs.valueCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), valueCount);
    }
}
