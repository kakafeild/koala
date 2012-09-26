package com.mengke.koala.server.context;

import com.mengke.koala.server.util.ContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: michaelmeng
 */
public class Initializer implements ApplicationListener<ApplicationContextEvent> {

	private static Logger logger = LoggerFactory.getLogger(Initializer.class);
	private static boolean initializationComplete = false;
	private static boolean initializationFailed = false;

	/**
	 * the list of components to be initialized
	 */
	private List<Initializable> components = new ArrayList<Initializable>();
	private boolean initialized = false;

	/**
	 * This method is called by Spring at app startup. It gets the application
	 * context loaded by Spring (and passed in the event parameter )and sets it up
	 * into the ContextUtil
	 *
	 * @param event a Spring event we will examine for context load complete
	 */
	@Override
	public void onApplicationEvent(ApplicationContextEvent event) {
		try {
			if (!initialized && event.getSource() instanceof WebApplicationContext) {
				logger.info("Koala Initializer initializing components.");
				WebApplicationContext ctx = (WebApplicationContext) event.getSource();
				ContextUtil.setApplicationContext(ctx);
				synchronized (ctx) {
					for (Initializable i : components) {
						try {
							i.initialize(ctx);
						} catch (Exception ex) {
							logger.error("Initialization failed for " + i.getClass().getName(), ex);
						}
					}
					initializationComplete = true;
					logger.info("Koala initialization completed .");
				}
			}
		} catch (Exception e) {
			initializationFailed = true;
			logger.error("Could not initialize the application", e);
		} finally {
			initialized = true;
		}
	}

	/**
	 * Set by Spring during the creation of the initializer. This is the list of
	 * bean references that we will initialize when the application context is
	 * finally loaded.
	 *
	 * @param components
	 */
	public void setComponents(List<Initializable> components) {
		this.components = components;
	}

	/**
	 * Check if initialization and loading of all Spring beans is completed. A
	 * value of true indicates that all Spring beans have been loaded into the
	 * <tt>ApplicationContext</tt>
	 *
	 * @return true=initialization of all Spring beans is complete
	 */
	public static synchronized boolean isInitializationComplete() {
		return initializationComplete;
	}

	/**
	 * Method : isInitializationFailed
	 * Allows a check for initialization failure.
	 *
	 * @return true if the init failed
	 */
	public static synchronized boolean isInitializationFailed() {
		return initializationFailed;
	}


}
