package com.airline.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.airline.bean.AirplaneBean;
import com.airline.constant.ErrorConstants;
import com.airline.exception.BusinessException;
import com.airline.exception.ConnectionException;
import com.airline.exception.SystemException;
import com.airline.manager.AirplaneManager;
import com.airline.manager.AirportManager;
import com.airline.manager.SearchFlightsManager;
import com.google.gson.Gson;

public class AircraftAction extends BaseAction {
	private Logger log = Logger.getLogger(this.getClass());
	private String routeId;
	private String message;
	private String action;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * This is method is used to retrieve the list of aircrafts
	 * 
	 * @return result
	 */
	public String execute() {
		log.debug("START - execute");
		String result = input;
	
		log.debug("END - execute");
		return result;
	}
	
	public String executeAutocomplete() {
		String result = success;
		Gson gson = new Gson();
		routeId = routeId.replace('"', ' ');
		AirplaneManager airplaneManager = new AirplaneManager();
		try {
			if (action.equals("selectAirplane")) {
				message = gson.toJson(airplaneManager.retrieveAirplaneId(routeId));
			}
		} catch (BusinessException e) {
			errorMessage = ErrorConstants.BUSINESS_EXCEPTION;
			result = input;
		} catch (SystemException e) {
			errorMessage = ErrorConstants.SYSTEM_EXCEPTION;
			result = error;
		} catch (ConnectionException e) {
			errorMessage = ErrorConstants.CONNECTION_EXCEPTION;
			result = error;
		} catch (Exception e) {
			errorMessage = ErrorConstants.BUSINESS_EXCEPTION;
			result = input;
		}

		return result;
	}

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}



}
