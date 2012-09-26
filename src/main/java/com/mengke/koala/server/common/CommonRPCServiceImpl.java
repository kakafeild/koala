package com.mengke.koala.server.common;

import com.mengke.koala.client.common.rpc.CommonRPCService;
import com.mengke.koala.client.common.rpc.RPCResult;

/**
 * Created by IntelliJ IDEA.
 * User: michaelmeng
 * Date: 12-9-19
 */
public class CommonRPCServiceImpl extends AutowiredRemoteServiceServlet implements CommonRPCService {

	private static final long serialVersionUID = 1L;

	public <R extends RPCResult> R buildRPCResult(Class<R> resultClazz) {
		R result = null;
		try {
			result = resultClazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return result;
	}

}
