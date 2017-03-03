package com.airline.action;

import org.apache.log4j.Logger;

public class SearchMyFlightsAction extends BaseAction {
	private Logger log = Logger.getLogger(this.getClass());

	private String searchOrigin;
	private String searchDestination;
	private String departure;
	private String arrival;
	
	public String execute() {
		log.debug("start action");
		String result = success;
		
		// execute Logic
		System.out.println(searchOrigin + " - " + searchDestination);
		System.out.println(departure + " - " + arrival);
		
		log.debug("end action - result: " + result);
		return result;
	}

	public String getSearchOrigin() {
		return searchOrigin;
	}

	public void setSearchOrigin(String searchOrigin) {
		this.searchOrigin = searchOrigin;
	}

	public String getSearchDestination() {
		return searchDestination;
	}

	public void setSearchDestination(String searchDestination) {
		this.searchDestination = searchDestination;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}
}
