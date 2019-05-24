package com.jusdt.es.common.cluster;

import com.jusdt.es.common.action.AbstractAction;
import com.jusdt.es.common.action.GenericResultAbstractAction;
import com.jusdt.es.common.client.config.ElasticSearchVersion;

/**
 * Retrieve cluster wide settings.
 */
public class GetSettings extends GenericResultAbstractAction {

	protected GetSettings(Builder builder) {
		super(builder);
	}

	@Override
	protected String buildURI(ElasticSearchVersion elasticsearchVersion) {
		return super.buildURI(elasticsearchVersion) + "/_cluster/settings";
	}

	@Override
	public String getRestMethodName() {
		return "GET";
	}

	public static class Builder extends AbstractAction.Builder<GetSettings, Builder> {

		@Override
		public GetSettings build() {
			return new GetSettings(this);
		}

	}

}
