package com.jusdt.es.common.indices;

import com.google.gson.Gson;
import com.jusdt.es.common.client.JestResult;

public class DocumentExistsResult extends JestResult {

    DocumentExistsResult(Gson gson) {
        super(gson);
    }

    public boolean documentExists() {
        return isSucceeded();
    }
}
