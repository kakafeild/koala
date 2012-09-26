package com.mengke.koala.client.common.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mengke.koala.shared.common.rpc.RPCFailureException;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: michaelmeng
 * Date: 12-9-19
 */
public abstract class KoalaAsyncCallback<T extends RPCResult> implements AsyncCallback<T> {

	private Date startDateTime;

	public KoalaAsyncCallback() {
		// Default constructor
		startDateTime = new Date();
	}

	@Override
	public final void onFailure(Throwable caught) {
		handleFailure(caught);
	}

	@Override
	public void onSuccess(T result) {
		if (!result.isSuccess()) {
			onFailure(new RPCFailureException(result));
			return;
		}

		try {
			this.handleResult(result);
		} catch (Exception ex) {
			handleFailure(ex);
		}
	}

	abstract protected void handleResult(T result);

	protected void handleFailure(Throwable t) {
		// TODO add failure messaging
	}
}
