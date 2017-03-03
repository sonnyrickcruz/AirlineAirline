package com.airline.action;

import org.apache.log4j.Logger;

public class LogOutAction extends BaseAction{
	private Logger log = Logger.getLogger(this.getClass());
	
	public String execute() {
		log.debug("start action");
		
		String result = "success";
		session.clear();
		
		log.debug("end action - result: " + result);
		return result;
	}
}
