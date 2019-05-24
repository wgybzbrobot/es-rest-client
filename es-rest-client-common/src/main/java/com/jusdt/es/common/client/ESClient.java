package com.jusdt.es.common.client;

import java.io.Closeable;
import java.io.IOException;
import java.util.Set;

import com.jusdt.es.common.action.Action;

public interface ESClient extends Closeable {

	<T extends QueryResult> T execute(Action<T> clientRequest) throws IOException;

	<T extends QueryResult> void executeAsync(Action<T> clientRequest, QueryResultHandler<? super T> jestResultHandler);

	/**
	 * @deprecated Use {@link #close()} instead.
	 */
	@Deprecated
	void shutdownClient();

	void setServers(Set<String> servers);

}
