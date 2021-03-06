package com.jusdt.es.common.core.search.aggregation;

import com.google.gson.JsonObject;

import java.util.List;

/**
 * @author cfstout
 */
public abstract class BucketAggregation extends Aggregation {

    public BucketAggregation(String name, JsonObject root) {
        super(name, root);
    }

    abstract List<? extends Bucket> getBuckets();
}
