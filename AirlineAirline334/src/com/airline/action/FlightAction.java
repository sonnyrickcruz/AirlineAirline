package com.airline.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.airline.constant.ErrorConstants;
import com.airline.exception.BusinessException;
import com.airline.exception.ConnectionException;
import com.airline.exception.SystemException;
import com.airline.manager.RouteManager;
import com.airline.util.DateUtil;
import com.google.gson.Gson;

public class FlightAction extends BaseAction{

	private String routeId;
	private String travelTime;
	private String selectAirplane;
	private String date;

	public String getDate() {
		return date;
	}
	public String getSelectAirplane() {
		return selectAirplane;
	}
	public void setSelectAirplane(String selectAirplane) {
		this.selectAirplane = selectAirplane;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getRouteId() {
		return routeId;
	}
	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}
	public String getTravelTime() {
		return travelTime;
	}
	public void setTravelTime(String travelTime) {
		this.travelTime = travelTime;
	}
	
	public String execute() {
		String result = input;
		String airplaneId = null;
		routeId = routeId.replace('"', ' ');
		travelTime = travelTime.replace('}', ' ');
		
		System.out.println(routeId);
		System.out.println(travelTime);
		Pattern pattern = Pattern.compile(".* \\(([0-9]+)\\)");
		Matcher match = Pattern.compile("\\(([^)]+)\\)").matcher(selectAirplane);
		if (match.find()) {
			airplaneId = match.group(1);
		}
		System.out.println(airplaneId);
		System.out.println(date);
		DateUtil dateUtil = new DateUtil();
		String deptDate = dateUtil.toMySqlDateString(date);
		System.out.println(deptDate);
		/*try {
			
			
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
		}*/
		
		return result;
	}
}
