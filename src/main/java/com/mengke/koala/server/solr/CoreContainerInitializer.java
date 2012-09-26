package com.mengke.koala.server.solr;

import com.mengke.koala.shared.Constants;
import com.mengke.koala.server.context.Initializable;
import org.apache.solr.core.CoreContainer;
import org.apache.solr.core.SolrCore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by IntelliJ IDEA.
 * User: michaelmeng
 * Date: 12-7-18
 */
public class CoreContainerInitializer implements Initializable {

	private static Logger logger = LoggerFactory.getLogger(CoreContainerInitializer.class);

	protected String pathPrefix = null; // strip this from the beginning of a path
	protected CoreContainer cores;

	@Override
	public void initialize(WebApplicationContext ctx) {
		logger.info("CoreContainerInitializer.initialize()");

		String solrHome = ctx.getServletContext().getRealPath("/WEB-INF/solr");
		System.setProperty("solr.solr.home", solrHome);
		logger.info("Setting system property 'solr.solr.home' to [" + solrHome + "]");

		CoreContainer.Initializer init = createInitializer();
		try {
			// web.xml configuration
			this.pathPrefix = ctx.getServletContext().getInitParameter("path-prefix");

			this.cores = init.initialize();
			ctx.getServletContext().setAttribute(Constants.CORE_CONTAINER, cores);
			logger.info("user.dir=" + System.getProperty("user.dir"));
		}
		catch( Throwable t ) {
			// catch this so our filter still works
			logger.error( "Could not start Solr. Check solr/home property and the logs");
			SolrCore.log(t);
		}

		logger.info("CoreContainerInitializer.initialize() done");
	}

	protected CoreContainer.Initializer createInitializer() {
		return new CoreContainer.Initializer();
	}
}
