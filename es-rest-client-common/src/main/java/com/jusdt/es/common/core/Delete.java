package com.jusdt.es.common.core;

import com.jusdt.es.common.action.BulkableAction;
import com.jusdt.es.common.action.SingleResultAbstractDocumentTargetedAction;

public class Delete extends SingleResultAbstractDocumentTargetedAction implements BulkableAction<DocumentResult> {

    protected Delete(Builder builder) {
        super(builder);
    }

    @Override
    public String getRestMethodName() {
        return "DELETE";
    }

    @Override
    public String getPathToResult() {
        return "ok";
    }

    @Override
    public String getBulkMethodName() {
        return "delete";
    }

    public static class Builder extends SingleResultAbstractDocumentTargetedAction.Builder<Delete, Builder> {

        public Builder(String id) {
            this.id(id);
        }

        public Delete build() {
            return new Delete(this);
        }

    }
    
}
