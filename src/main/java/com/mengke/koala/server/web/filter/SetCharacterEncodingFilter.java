package com.mengke.koala.server.web.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

public class SetCharacterEncodingFilter
				implements Filter {

	protected String encoding;
	protected FilterConfig filterConfig;
	protected boolean ignore;

	private static Logger logger = LoggerFactory.getLogger(SetCharacterEncodingFilter.class);

	/**
	 * Constructor
	 */
	public SetCharacterEncodingFilter() {
		encoding = null;
		filterConfig = null;
		ignore = true;
	}

	/**
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
		encoding = null;
		filterConfig = null;
	}

	/**
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
					throws IOException, ServletException {
		logger.debug("Starting filter.....");
		if (ignore || request.getCharacterEncoding() == null) {
			String encoding = selectEncoding(request);
			if (encoding != null)
				request.setCharacterEncoding(encoding);
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig filterConfig)
					throws ServletException {
		this.filterConfig = filterConfig;
		encoding = filterConfig.getInitParameter("encoding");
		String value = filterConfig.getInitParameter("ignore");
		if (value == null)
			ignore = true;
		else if (value.equalsIgnoreCase("true"))
			ignore = true;
		else if (value.equalsIgnoreCase("yes"))
			ignore = true;
		else
			ignore = false;
	}

	/**
	 * @param request
	 * @return
	 */
	protected String selectEncoding(ServletRequest request) {
		return encoding;
	}
}