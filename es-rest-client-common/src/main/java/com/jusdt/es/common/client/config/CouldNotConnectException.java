package com.jusdt.es.common.client.config;

import java.io.IOException;

/**
 * Wrapper for a the host address of an HTTPConnectException
 */
public class CouldNotConnectException extends IOException {

	private static final long serialVersionUID = -8619334076377259566L;

	private final String host;

	public CouldNotConnectException(String host, Throwable cause) {
		super("Could not connect to " + host, cause);
		this.host = host;
	}

	public String getHost() {
		return host;
	}

}
