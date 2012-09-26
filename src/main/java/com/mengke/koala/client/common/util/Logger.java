package com.mengke.koala.client.common.util;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.logging.client.LogConfiguration;
import com.sencha.gxt.core.shared.FastMap;

import java.util.logging.Level;

/**
 * Created by IntelliJ IDEA.
 * User: michaelmeng
 * Date: 12-8-30
 *
 * TODO add js supported
 */
public class Logger {

	/* value to use in log4j.xml for name in <category/> element */
	public final static String defaultCategory = "client";

	private static FastMap<Logger> _loggers;

	private String _category;

	private java.util.logging.Logger log;

	static {
		_loggers = new FastMap<Logger>();

		// unit test environment has problem with native method
		// so catch exception and ignore b/c native not used in unit test
		if (GWT.isClient()) {
			defineJsProxyClass(getLogger());
		}
	}

	private Logger(String category) {
		_category = category;
		log = java.util.logging.Logger.getLogger(category);
	}

	public static Logger getLogger() {
		return getLogger(defaultCategory);
	}

	/**
	 * Provide convenience method for creating a logger
	 * with a category matching the fully-qualified name of a class.
	 *
	 * @param clazz
	 * @return
	 */
	public static Logger getLogger(Class<?> clazz) {
		return getLogger(clazz.getName());
	}

	/**
	 * Return a Log instance based on category.
	 * Keep a map of Log instances by category.
	 *
	 * @param category
	 * @return Log instance for category
	 */
	public static Logger getLogger(String category) {
		// use default category if none specified
		if (category == null || category.length() == 0) {
			return getLogger(defaultCategory);
		}

		Logger log = _loggers.get(category);

		if (log == null) {
			// create and map logger for category
			log = new Logger(category);
			_loggers.put(category, log);
		}

		return log;
	}

	public String getCategory() {
		return _category;
	}

	/* debug */

	public void debug(String message) {
		log.config(message);
	}


	/* info */

	public void info(String message) {
		log.info(message);
	}

	/* warn */

	public void warn(String message) {
		log.warning(message);
	}

	/* error */

	public void error(String message) {
		log.severe(message);
	}

	/* is<Level>Enabled */

	public boolean isDebugEnabled() {
		return log.isLoggable(Level.CONFIG);
	}

	public boolean isInfoEnabled() {
		return log.isLoggable(Level.INFO);
	}

	public boolean isLoggingEnabled() {
		return LogConfiguration.loggingIsEnabled();
	}

	/**
	 * Provide a native javascript class that can be used in javascript that
	 * in turn calls back into this Logger class.
	 * This method is called once to define the javascript class.
	 */
	private static native JavaScriptObject defineJsProxyClass(Logger logger) /*-{
		$wnd.koala = $wnd.koala || {};
		$wnd.koala.Log = $wnd.koala.Log || {};	// same package as original Koala js class

		$wnd.koala.Log.debug = function(message) {
			logger.@com.mengke.koala.client.common.util.Logger::debug(Ljava/lang/String;)(message);
		};

		$wnd.koala.Log.info = function(message) {
			logger.@com.mengke.koala.client.common.util.Logger::info(Ljava/lang/String;)(message);
		};

		$wnd.koala.Log.warn = function(message) {
			logger.@com.mengke.koala.client.common.util.Logger::warn(Ljava/lang/String;)(message);
		};

		$wnd.koala.Log.error = function(message) {
			logger.@com.mengke.koala.client.common.util.Logger::error(Ljava/lang/String;)(message);
		};

	}-*/;
}
