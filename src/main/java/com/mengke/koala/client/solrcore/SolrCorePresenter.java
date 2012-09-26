package com.mengke.koala.client.solrcore;

import com.mengke.koala.client.Presenter;
import com.mengke.koala.shared.solrcore.domain.SolrCoreBaseInfo;

/**
 * Created by IntelliJ IDEA.
 * User: michaelmeng
 * Date: 12-8-29
 */
public interface SolrCorePresenter extends Presenter {

	SolrCoreBaseInfo getSolrCore();

	void setSolrCore(SolrCoreBaseInfo solrCore);

	String getQueryUrl();

	String getDataImportUrl();

	String getSolrCoreName();
}
