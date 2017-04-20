package com.airline.interceptor;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class TemplateInterceptor extends AbstractInterceptor {
	private Logger log = Logger.getLogger(this.getClass());

	/**
	 * description
	 * 
	 * @param result
	 */
	public String intercept(ActionInvocation invocation) throws Exception {
		log.debug("start interceptor");
		String result = "input";

		// intercept logic
		invocation.invoke();

		log.debug("end interceptor - result: " + result);
		return result;
	}
}
