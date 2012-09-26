package com.mengke.koala.server.common;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created by IntelliJ IDEA.
 * User: michaelmeng
 * Date: 12-9-19
 */
public class AutowiredRemoteServiceServlet extends RemoteServiceServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		autoWire(config.getServletContext());
	}

	protected void autoWire(ServletContext context) {
		WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
		ctx.getAutowireCapableBeanFactory().autowireBean(this);
	}
}
