package com.airline.action;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.airline.constant.ErrorConstants;
import com.airline.exception.BusinessException;
import com.airline.exception.ConnectionException;
import com.airline.exception.SystemException;
import com.airline.manager.RouteManager;
import com.google.gson.Gson;

public class RouteAction extends BaseAction {

	private String jsonString;
	private String origin;
	private String destination;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String execute() {
		String result = success;
		Gson gson = new Gson();
		String originId = null;
		String destinationId = null;

		RouteManager routeManager = new RouteManager();
		Pattern pattern = Pattern.compile(".* \\(([A-Z]+)\\)");
		Matcher match = Pattern.compile("\\(([^)]+)\\)").matcher(origin);
		if (match.find()) {
			originId = match.group(1);
		}
		match = Pattern.compile("\\(([^)]+)\\)").matcher(destination);
		if (match.find()) {
			destinationId = match.group(1);
		}
		try {
			message = gson.toJson(routeManager.retrieveRouteId(originId, destinationId));
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




}