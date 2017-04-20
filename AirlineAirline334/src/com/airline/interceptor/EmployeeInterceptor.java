package com.airline.interceptor;

import org.apache.log4j.Logger;

import com.airline.action.BaseAction;
import com.airline.bean.UserBean;
import com.airline.constant.ErrorConstants;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class EmployeeInterceptor extends AbstractInterceptor {
	private Logger log = Logger.getLogger(this.getClass());

	/**
	 * Intercept first if the user of the system is an employee
	 * 
	 * @param result
	 */
	public String intercept(ActionInvocation invocation) throws Exception {
		log.debug("start interceptor");
		String result = "";
		BaseAction action = (BaseAction) invocation.getAction();

		try {
			UserBean user = (UserBean) action.getSession().get("user");
			if ("employee".equalsIgnoreCase(user.getType())) {
				result = invocation.invoke();
			} else {
				log.info("There was a customer that tries to access the employee's page.");
				action.setErrorMessage(ErrorConstants.UNAUTHORIZED_ACCESS);
				result = action.error;
			}
		} catch (Exception e) {
			log.debug("There was an unknown exception occurred while intercepting the employee access. " + e);
			action.setErrorMessage(ErrorConstants.SYSTEM_EXCEPTION);
			result = action.error;
		}

		log.debug("end interceptor - result: " + result);
		return result;
	}
}
