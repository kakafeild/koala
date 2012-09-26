package com.mengke.koala.server.web.filter;

import com.mengke.koala.shared.Constants;
import org.apache.solr.core.CoreContainer;
import org.apache.solr.servlet.SolrDispatchFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

/**
 * Created by IntelliJ IDEA.
 * User: michaelmeng
 * Date: 12-7-19
 */
public class SharedSolrCoresDispatchFilter extends SolrDispatchFilter {

	private static Logger logger = LoggerFactory.getLogger(SharedSolrCoresDispatchFilter.class);

	public void init(FilterConfig config) throws ServletException {
		logger.info("SharedSolrCoresDispatchFilter.init()");

		cores = (CoreContainer) config.getServletContext().getAttribute(Constants.CORE_CONTAINER);

		logger.info("SharedSolrCoresDispatchFilter.init() done");
	}
}
