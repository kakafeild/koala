package com.mengke.koala.server.solrcore;

import com.mengke.koala.client.solrcore.SolrCoreRPCService;
import com.mengke.koala.server.common.CommonRPCServiceImpl;
import com.mengke.koala.shared.solrcore.domain.SolrCoreBaseInfo;
import com.mengke.koala.shared.solrcore.domain.SolrCoresResult;
import org.apache.commons.io.FileUtils;
import org.apache.solr.core.CoreContainer;
import org.apache.solr.core.SolrCore;
import org.apache.solr.util.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static com.mengke.koala.client.common.util.Assert.assertArgNotNull;
import static com.mengke.koala.client.common.util.Assert.assertStateNotEmpty;

/**
 * Created by IntelliJ IDEA.
 * User: michaelmeng
 * Date: 12-9-19
 */
public class SolrCoreRPCServiceImpl extends CommonRPCServiceImpl implements SolrCoreRPCService {

	private static Logger logger = LoggerFactory.getLogger(SolrCoreRPCService.class);

	public static final String CORE_CONTAINER = "org.apache.solr.CoreContainer";

	private CoreContainer coreContainer;
	private HttpServletRequest request;

	@Override
	public SolrCoresResult getSolrCores() {
		SolrCoresResult solrCoresResult = buildRPCResult(SolrCoresResult.class);
		Collection<SolrCore> solrCores = coreContainer.getCores();
		assertStateNotEmpty(solrCores, "SolrCoreRPCService getSolrCores there's no core loaded");
		List<SolrCoreBaseInfo> solrCoreList = new ArrayList<SolrCoreBaseInfo>();
		for (SolrCore solrCore : solrCores) {
			SolrCoreBaseInfo baseInfo = new SolrCoreBaseInfo();
			baseInfo.setCoreName(solrCore.getName());
			baseInfo.setDataDir(normalizePath(solrCore.getDataDir()));
			baseInfo.setInstanceDir(normalizePath(solrCore.getResourceLoader().getInstanceDir()));
			baseInfo.setConfig(solrCore.getConfigResource());
			baseInfo.setSchema(solrCore.getSchemaResource());
			baseInfo.setStartTime(new Date(solrCore.getStartTime()));
			baseInfo.setUptime(System.currentTimeMillis() - solrCore.getStartTime());

			long size = getIndexSize(solrCore);
			baseInfo.setSizeInBytes(size);
			baseInfo.setIndexSize(NumberUtils.readableSize(size));
			solrCoreList.add(baseInfo);
		}
		solrCoresResult.setSolrCoreList(new ArrayList<SolrCoreBaseInfo>(solrCoreList));
		return solrCoresResult;
	}

	private long getIndexSize(SolrCore core) {
		return FileUtils.sizeOfDirectory(new File(core.getIndexDir()));
	}

	protected static String normalizePath(String path) {
		if (path == null)
			return null;
		path = path.replace('/', File.separatorChar);
		path = path.replace('\\', File.separatorChar);
		return path;
	}

	/**
	 * Override this method to get solr core instances.
	 *
	 * @param request the HTTP request being serviced
	 * @return the content of the incoming request encoded as a string.
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected String readContent(HttpServletRequest request) throws ServletException, IOException {
		this.request = request;
		CoreContainer container = (CoreContainer) request.getAttribute(CORE_CONTAINER);
		assertArgNotNull(container, "SolrCoreRPCService readContent request doesn't have the attribute called ["
						+ CORE_CONTAINER + "]");

		setCoreContainer(container);
		logger.info("Attribute [" + CORE_CONTAINER + "] loaded");
		return super.readContent(request);
	}

	public CoreContainer getCoreContainer() {
		return coreContainer;
	}

	public void setCoreContainer(CoreContainer coreContainer) {
		this.coreContainer = coreContainer;
	}

}
