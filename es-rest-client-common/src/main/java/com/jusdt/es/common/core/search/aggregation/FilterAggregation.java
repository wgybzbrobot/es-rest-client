package com.jusdt.es.common.core.search.aggregation;

import com.google.gson.JsonObject;

import static com.jusdt.es.common.core.search.aggregation.AggregationField.DOC_COUNT;

import java.util.Objects;

/**
 * @author cfstout
 */
public class FilterAggregation extends Bucket {

    public static final String TYPE = "filter";

    public FilterAggregation(String name, JsonObject filterAggregation) {
        super(name, filterAggregation, filterAggregation.get(String.valueOf(DOC_COUNT)).getAsLong());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), TYPE);
    }

}
