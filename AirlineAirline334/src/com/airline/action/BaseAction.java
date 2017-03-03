package com.airline.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.airline.constant.ActionConstants;

/**
 * This action implements the SessionAware interface. This class also serves as
 * the base action class for actions and interceptors
 */
public abstract class BaseAction implements SessionAware {
	protected Map<String, Object> session;
	protected String errorMessage;
	public final String input = ActionConstants.INPUT;
	public final String success = ActionConstants.SUCCESS;
	public final String submit = ActionConstants.SUBMIT;
	public final String error = ActionConstants.ERROR;

	/**
	 * This method sets the session to the implemented SessionAware interface
	 * 
	 * @param session
	 */
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	/**
	 * This method gets the session from the implemented SessionAware interface
	 * 
	 * @return session
	 */
	public Map<String, Object> getSession() {
		return session;
	}

	/**
	 * This method gets the errorMessage from BaseAction class
	 * 
	 * @return errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * This method sets the errorMessage to BaseAction class
	 * 
	 * @param errorMessage
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	abstract String execute();
	//abstract String executeAction();
}
