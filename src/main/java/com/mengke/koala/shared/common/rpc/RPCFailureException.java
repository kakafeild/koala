package com.mengke.koala.shared.common.rpc;

import com.mengke.koala.client.common.rpc.RPCResult;

/**
 * Created by IntelliJ IDEA.
 * User: michaelmeng
 * Date: 12-9-19
 */
public class RPCFailureException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private RPCResult result;


	public RPCResult getResult() {
		return result;
	}

	public void setResult(RPCResult result) {
		this.result = result;
	}

	public RPCFailureException() {
		super();
	}

	public RPCFailureException(RPCResult r) {
		this.result = r;
	}

}
