package com.jusdt.es.common.core;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.jusdt.es.common.client.QueryResult;

public class DocumentResult extends QueryResult {

	public DocumentResult(Gson gson) {
		super(gson);
	}

	public String getIndex() {
		return getAsString(jsonObject.get("_index"));
	}

	public String getType() {
		return getAsString(jsonObject.get("_type"));
	}

	public String getId() {
		return getAsString(jsonObject.get("_id"));
	}

	public Long getVersion() {
		return getAsLong(jsonObject.get("_version"));
	}

	private String getAsString(JsonElement jsonElement) {
		if (jsonElement == null) {
			return null;
		} else {
			return jsonElement.getAsString();
		}
	}

	private Long getAsLong(JsonElement jsonElement) {
		if (jsonElement == null) {
			return null;
		} else {
			return jsonElement.getAsLong();
		}
	}

}
