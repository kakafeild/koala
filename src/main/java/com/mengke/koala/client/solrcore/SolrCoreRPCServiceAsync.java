package com.mengke.koala.client.solrcore;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.mengke.koala.shared.solrcore.domain.SolrCoresResult;

/**
 * Created by IntelliJ IDEA.
 * User: michaelmeng
 * Date: 12-9-19
 */
public interface SolrCoreRPCServiceAsync {

	void getSolrCores(AsyncCallback<SolrCoresResult> callback);

}
