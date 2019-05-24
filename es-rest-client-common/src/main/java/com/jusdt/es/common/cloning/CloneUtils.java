package com.jusdt.es.common.cloning;

import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 * This class is just a workaround for the non-public deepCopy methods in Gson.
 */
public class CloneUtils {

	public static JsonElement deepClone(JsonElement jsonElement) {
		if (jsonElement instanceof JsonObject) {
			return deepCloneObject(jsonElement);
		} else if (jsonElement instanceof JsonArray) {
			return deepCloneArray(jsonElement);
		} else if (jsonElement instanceof JsonPrimitive) {
			return jsonElement;
		}

		return JsonNull.INSTANCE;
	}

	private static JsonElement deepCloneObject(JsonElement jsonElement) {
		JsonObject jsonObject = (JsonObject) jsonElement;
		JsonObject result = new JsonObject();

		for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
			result.add(entry.getKey(), deepClone(entry.getValue()));
		}

		return result;
	}

	private static JsonElement deepCloneArray(JsonElement jsonElement) {
		JsonArray jsonArray = (JsonArray) jsonElement;
		JsonArray result = new JsonArray();

		for (JsonElement element : jsonArray) {
			result.add(deepClone(element));
		}

		return result;
	}

}
