package com.mengke.koala.client.common.rpc;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Created by IntelliJ IDEA.
 * User: michaelmeng
 * Date: 12-9-19
 */
public class RPCResult implements IsSerializable {

	private boolean success = true;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
