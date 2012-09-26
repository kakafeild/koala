package com.mengke.koala.client.solrcore;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.mengke.koala.client.common.rpc.CommonRPCService;
import com.mengke.koala.shared.solrcore.domain.SolrCoresResult;

/**
 * Created by IntelliJ IDEA.
 * User: michaelmeng
 * Date: 12-9-19
 */
@RemoteServiceRelativePath("SolrCoreRPCService")
public interface SolrCoreRPCService extends CommonRPCService {

	SolrCoresResult getSolrCores();

	public static class App {
		private static SolrCoreRPCServiceAsync ourInstance = GWT.create(SolrCoreRPCService.class);

		public static synchronized SolrCoreRPCServiceAsync getInstance() {
			return ourInstance;
		}
	}

}
