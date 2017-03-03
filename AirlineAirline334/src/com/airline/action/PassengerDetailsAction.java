package com.airline.action;

import org.apache.log4j.Logger;

public class PassengerDetailsAction extends BaseAction {
	private Logger log = Logger.getLogger(this.getClass());

	/**
	 * displays the passenger details input
	 * 
	 * @param result
	 */
	public String execute() {
		log.debug("start action");
		String result = input;
		
		log.debug("end action - result: " + result);
		return result;
	}

	/**
	 * description
	 * 
	 * @param result
	 */
	public String executeAction() {
		log.debug("start action");
		String result = input;

		// execute Logic
/*		ManagerTemplate managerTemplate = new ManagerTemplate();
		try {
			List<String> list =  managerTemplate.processSampleList("2");
		} catch (BusinessException e) {
			errorMessage = ErrorConstants.BUSINESS_EXCEPTION;
			result = input;
		} catch (SystemException e) {
			errorMessage = ErrorConstants.SYSTEM_EXCEPTION;
			result = error;
		} catch (ConnectionException e) {
			errorMessage = ErrorConstants.CONNECTION_EXCEPTION;
			result = error;
		} catch (Exception e){
			errorMessage = ErrorConstants.BUSINESS_EXCEPTION;
			result = input;
		}*/
		
		log.debug("end action - result: " + result);
		return result;
	}
}
