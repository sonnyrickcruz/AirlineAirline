package com.airline.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.airline.bean.AirportBean;
import com.airline.constant.ErrorConstants;
import com.airline.exception.BusinessException;
import com.airline.exception.ConnectionException;
import com.airline.exception.SystemException;
import com.airline.manager.AirportManager;
import com.airline.manager.SearchFlightsManager;
import com.google.gson.Gson;

public class AirportAction extends BaseAction {
	private Logger log = Logger.getLogger(this.getClass());
	private String jsonString;
	private String message;
	private String action;
	private String term;
	private String origin;
	/**
	 * This method is used to retrieve the list of airports
	 * 
	 * @return result
	 */
	private List<AirportBean> airportList;

	public String executeAutocomplete() {
		String result = success;
		Gson gson = new Gson();
		AirportManager airportManager = new AirportManager();
		SearchFlightsManager searchFlightsManager = new SearchFlightsManager();
		try {
			if (action.equals("searchOrigin")) {
				//message = gson.toJson(airportManager.retrieveAirportList());
				message = gson.toJson(airportManager.retrieveAirportList(term));
				
			} else if (action.equals("searchDestination")) {
				message = gson.toJson(airportManager.retrieveAirportListWithoutOrigin(origin,term));
			}
			System.out.println(message);
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

	public String execute() {
		log.debug("START - execute");
		String result = input;

		log.debug("END - execute");
		return result;
	}

	public List<AirportBean> getAirportList() {
		return airportList;
	}

	public void setAirportList(List<AirportBean> airportList) {
		this.airportList = airportList;
	}

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}

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

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

}
