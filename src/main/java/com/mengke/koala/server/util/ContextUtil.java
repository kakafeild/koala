package com.mengke.koala.server.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by IntelliJ IDEA.
 * User: michaelmeng
 * Date: 12-5-2
 * <p/>
 * ContextUtil is the placeholder of the Spring ApplicationContext in the
 * system. The ApplicationContext is a class variable and may be set by a
 * service client or as a result of loading the beans definition files in the
 * classpath.
 */
public class ContextUtil {

	/**
	 * the logger instance
	 */
	private static Logger logger = LoggerFactory.getLogger(ContextUtil.class);
	private static String[] defaultCtxResource = {"classpath*:applicationContext.xml", "classpath*:/spring/applicationContext-*.xml"};
	private static ApplicationContext ac = null;


	/**
	 * Sets the ApplicationContext. The idea is to have a single
	 * ApplicationContext in the entire system so bean references can be shared
	 * and don't have to be reloaded again. This is typically invoked by a service
	 * client, for example, the web application, that previously has loaded its
	 * own ApplicationContext (WebApplicationContext). As a result, a single
	 * ApplicationContext in the system is in place.
	 *
	 * @param ctx the application context
	 */
	public static void setApplicationContext(ApplicationContext ctx) {
		if (ac == null || ctx instanceof WebApplicationContext) {
			if (!ctx.getClass().getName().contains("$Proxy")) {
				ac = ctx;
			} else {
				logger.error("You cannot set the context to a proxy!!!", new Exception());
			}
		}
	}

	/**
	 * Return the Spring application context that was loaded at system startup
	 *
	 * @return the ApplicationContext
	 */
	public static synchronized ApplicationContext getApplicationContext() {
		//Ensure that the context only get initialized once and that most of the
		//the requesters will not enter the synchronized block (only those that
		//threads the called while the context is being initialized by the first
		//thread to call).
		if (ac == null) {
			synchronized (ContextUtil.class) {
				if (ac == null) {
					ac = new ClassPathXmlApplicationContext(defaultCtxResource);
				}
			}
		}
		return ac;
	}

	/**
	 * Retrieve a Spring bean from the ApplicationContext
	 *
	 * @param beanName the bean id
	 * @param clazz    the type that the bean is expected to be
	 * @return the Spring bean
	 */
	public static synchronized <T> T getBean(String beanName, Class<T> clazz) {
		return clazz.cast(getApplicationContext().getBean(beanName));
	}
}
