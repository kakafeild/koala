package com.mengke.koala.client.solrcore;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HasWidgets;
import com.mengke.koala.client.common.util.StringUtils;
import com.mengke.koala.shared.solrcore.domain.SolrCoreBaseInfo;

import static com.mengke.koala.client.common.util.Assert.assertArgNotNull;

/**
 * Created by IntelliJ IDEA.
 * User: michaelmeng
 * Date: 12-8-29
 */
public class SolrCorePresenterImpl implements SolrCorePresenter {

	private SolrCoreView solrCoreView;
	private SolrCoreBaseInfo solrCore;

	public SolrCorePresenterImpl(SolrCoreBaseInfo solrCore) {
		assertArgNotNull(solrCore, "SolrCorePresenter constructor solrCore is null");
		this.solrCore = solrCore;
	}

	private SolrCoreView getSolrCoreView() {
		if (solrCoreView == null) {
			solrCoreView = new SolrCoreViewImpl(this);
		}
		return solrCoreView;
	}

	@Override
	public void go(HasWidgets hasWidgets) {
		hasWidgets.add(getSolrCoreView().asWidget());
	}

	@Override
	public SolrCoreBaseInfo getSolrCore() {
		return solrCore;
	}

	@Override
	public void setSolrCore(SolrCoreBaseInfo solrCore) {
		this.solrCore = solrCore;
	}

	@Override
	public String getQueryUrl() {
		return getSolrRequestUrl("select");
	}

	@Override
	public String getDataImportUrl() {
		return getSolrRequestUrl("dataimport");
	}

	public String getSolrRequestUrl(String handler) {
		String baseUrl = GWT.getHostPageBaseURL();
		String coreName = solrCore.getCoreName();
		StringBuffer sb = new StringBuffer();
		sb.append(baseUrl).append(coreName);
		sb.append("/").append(handler);
		return sb.toString();
	}

	@Override
	public String getSolrCoreName() {
		String coreName = solrCore.getCoreName();
		if (StringUtils.isBlank(coreName)) {
			coreName = "collection";
		}
		return coreName;
	}
}
