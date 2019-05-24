package com.jusdt.es.common.cluster;

import com.jusdt.es.common.action.AbstractMultiINodeActionBuilder;
import com.jusdt.es.common.action.GenericResultAbstractAction;
import com.jusdt.es.common.client.config.ElasticSearchVersion;

public class Stats extends GenericResultAbstractAction {

	protected Stats(Builder builder) {
		super(builder);
	}

	@Override
	protected String buildURI(ElasticSearchVersion elasticsearchVersion) {
		return super.buildURI(elasticsearchVersion) + "/_cluster/stats/nodes/" + nodes;
	}

	@Override
	public String getRestMethodName() {
		return "GET";
	}

	public static class Builder extends AbstractMultiINodeActionBuilder<Stats, Builder> {

		@Override
		public Stats build() {
			return new Stats(this);
		}

	}

}
