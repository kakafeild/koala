package com.mengke.koala.server.context;

import org.springframework.web.context.WebApplicationContext;

/**
 * Created by IntelliJ IDEA.
 * User: michaelmeng
 */
public interface Initializable {

	/**
	 * This method will be called by the Initializer bean which catches
	 * the Spring application context load event. If you want to get
	 * initialized with the context info, simply add your bean (that
	 * implements this interface ) to the bean ref list in the Initializer
	 * bean configuration.
	 *
	 * @param ctx the Spring applicaton context.
	 */
	public void initialize(WebApplicationContext ctx);
}
