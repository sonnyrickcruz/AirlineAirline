package com.airline.interceptor;

import org.apache.log4j.Logger;

import com.airline.action.BaseAction;
import com.airline.constant.ErrorConstants;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class AccessInterceptor extends AbstractInterceptor {
	private Logger log = Logger.getLogger(this.getClass());

	/**
	 * Intercept first the user before proceeding to the next step
	 * 
	 * @return result
	 */
	public String intercept(ActionInvocation invocation) throws Exception {
		log.debug("start interceptor");
		String result = "";
		BaseAction action = (BaseAction) invocation.getAction();
		try {
			if (action.getSession().get("user") != null) {
				result = invocation.invoke();
			} else {
				log.info("There was a user that tries to use the system without loggin in.");
				action.setErrorMessage(ErrorConstants.UNAUTHORIZED_ACCESS);
				result = action.error;
			}
		} catch (Exception e) {
			log.debug("There was an unknown exception occurred while intercepting the user access. " + e);
			action.setErrorMessage(ErrorConstants.SYSTEM_EXCEPTION);
			result = action.error;
		}

		log.debug("end interceptor - result: " + result);
		return result;
	}

}
