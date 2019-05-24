package com.jusdt.es.common.client;

public interface QueryResultHandler<T> {

	void completed(T result);

	void failed(Exception ex);

}
