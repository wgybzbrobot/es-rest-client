package com.jusdt.es.common.core;

import java.util.List;

import com.google.gson.Gson;
import com.jusdt.es.common.client.QueryResult;

public class CountResult extends QueryResult {

	public CountResult(CountResult countResult) {
		super(countResult);
	}

	public CountResult(Gson gson) {
		super(gson);
	}

	@Override
	@Deprecated
	public <T> T getSourceAsObject(Class<T> clazz) {
		return super.getSourceAsObject(clazz);
	}

	@Override
	@Deprecated
	public <T> List<T> getSourceAsObjectList(Class<T> type) {
		return super.getSourceAsObjectList(type);
	}

	public Double getCount() {
		Double count = null;

		if (isSucceeded) {
			count = getSourceAsObject(Double.class);
		}

		return count;
	}

}
