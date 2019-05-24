package com.jusdt.es.common.cluster;

import com.google.gson.JsonObject;
import com.jusdt.es.common.action.AbstractMultiINodeActionBuilder;
import com.jusdt.es.common.action.GenericResultAbstractAction;
import com.jusdt.es.common.client.config.ElasticSearchVersion;

/**
 * Allows to get the current hot threads on each node in the cluster.
 * <b>This API is experimental.</b>
 */
public class NodesHotThreads extends GenericResultAbstractAction {

	protected NodesHotThreads(Builder builder) {
		super(builder);
	}

	@Override
	protected String buildURI(ElasticSearchVersion elasticsearchVersion) {
		return super.buildURI(elasticsearchVersion) + "/_nodes/" + nodes + "/hot_threads";
	}

	@Override
	protected JsonObject parseResponseBody(String responseBody) {
		return new JsonObject();
	}

	@Override
	public String getRestMethodName() {
		return "GET";
	}

	public static class Builder extends AbstractMultiINodeActionBuilder<NodesHotThreads, Builder> {

		public Builder settings(boolean value) {
			return setParameter("settings", value);
		}

		/**
		 * number of hot threads to provide, defaults to 3
		 */
		public Builder threads(Integer value) {
			return setParameter("threads", value);
		}

		/**
		 * the interval to do the second sampling of threads. Defaults to 500ms
		 */
		public Builder interval(String value) {
			return setParameter("interval", value);
		}

		/**
		 * The type to sample, defaults to cpu, but supports wait and block to see hot threads that are in wait or block state
		 */
		public Builder type(String value) {
			return setParameter("type", value);
		}

		@Override
		public NodesHotThreads build() {
			return new NodesHotThreads(this);
		}

	}

}
