package com.mengke.koala.shared.solrcore.domain;

import com.mengke.koala.client.common.rpc.RPCResult;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: michaelmeng
 * Date: 12-9-19
 */
public class SolrCoresResult extends RPCResult {

	private List<SolrCoreBaseInfo> solrCoreList;

	public List<SolrCoreBaseInfo> getSolrCoreList() {
		return solrCoreList;
	}

	public void setSolrCoreList(List<SolrCoreBaseInfo> solrCoreList) {
		this.solrCoreList = solrCoreList;
	}
}
