package com.jusdt.es.common.core;

import com.jusdt.es.common.action.AbstractMultiTypeActionBuilder;
import com.jusdt.es.common.action.GenericResultAbstractAction;
import com.jusdt.es.common.client.config.ElasticSearchVersion;

/**
 * Delete By Query API  is removed in Elasticsearch version 2.0.
 * You need to install the plugin with the same name for this action to work.
 */
public class DeleteByQuery extends GenericResultAbstractAction {

	protected DeleteByQuery(Builder builder) {
		super(builder);

		this.payload = builder.query;
	}

	@Override
	protected String buildURI(ElasticSearchVersion elasticsearchVersion) {
		return super.buildURI(elasticsearchVersion) + "/_delete_by_query";
	}

	@Override
	public String getPathToResult() {
		return "ok";
	}

	@Override
	public String getRestMethodName() {
		return "POST";
	}

	public static class Builder extends AbstractMultiTypeActionBuilder<DeleteByQuery, Builder> {

		private String query;

		public Builder(String query) {
			this.query = query;
		}

		@Override
		public DeleteByQuery build() {
			return new DeleteByQuery(this);
		}

	}

}
