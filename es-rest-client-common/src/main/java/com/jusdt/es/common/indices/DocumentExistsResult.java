package com.jusdt.es.common.indices;

import com.google.gson.Gson;
import com.jusdt.es.common.client.QueryResult;

public class DocumentExistsResult extends QueryResult {

    DocumentExistsResult(Gson gson) {
        super(gson);
    }

    public boolean documentExists() {
        return isSucceeded();
    }
}
