package com.jusdt.es.common.core;

import com.google.gson.Gson;
import com.jusdt.es.common.action.AbstractAction;
import com.jusdt.es.common.action.AbstractMultiTypeActionBuilder;
import com.jusdt.es.common.client.config.ElasticSearchVersion;

public class Count extends AbstractAction<CountResult> {

	protected Count(Builder builder) {
		super(builder);
		this.payload = builder.query;
	}

	@Override
	protected String buildURI(ElasticSearchVersion elasticsearchVersion) {
		return super.buildURI(elasticsearchVersion) + "/_count";
	}

	@Override
	public String getPathToResult() {
		return "count";
	}

	@Override
	public CountResult createNewElasticSearchResult(String responseBody, int statusCode, String reasonPhrase,
			Gson gson) {
		return createNewElasticSearchResult(new CountResult(gson), responseBody, statusCode, reasonPhrase, gson);
	}

	@Override
	public String getRestMethodName() {
		return "POST";
	}

	public static class Builder extends AbstractMultiTypeActionBuilder<Count, Builder> {

		private String query;

		public Builder query(String query) {
			this.query = query;
			return this;
		}

		@Override
		public Count build() {
			return new Count(this);
		}
	}

}
