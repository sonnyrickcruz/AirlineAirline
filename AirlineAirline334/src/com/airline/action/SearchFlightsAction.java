package com.airline.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.airline.bean.RouteBean;
import com.airline.bean.TicketBean;
import com.airline.exception.BusinessException;
import com.airline.exception.ConnectionException;
import com.airline.exception.SystemException;
import com.airline.manager.SearchFlightsManager;
import com.google.gson.Gson;

public class SearchFlightsAction extends BaseAction {
	private Logger log = Logger.getLogger(this.getClass());
	List<RouteBean> routeList;
	List<String> originList;
	private String message;
	private String action;
	private String term;
	private String origin;
	private String searchDepartureDate;
	private String searchOrigin;
	private String searchDestination;
	private Double noOfPax;

	/**
	 * This action is to display the input for searching flights
	 * 
	 * @param result
	 */
	public String execute() {
		log.debug("start action");
		String result = input;

		TicketBean ticket = (TicketBean) session.get("ticket");
		SearchFlightsManager searchFlightsManager = new SearchFlightsManager();
		try {
			if (ticket == null) {
				ticket = searchFlightsManager.processTicket();
				session.put("ticket", ticket);
			}
		} catch (BusinessException e) {
			result = error;
			errorMessage = e.getMessage();
		}
		log.debug("end action - result: " + result);
		return result;
	}

	/**
	 * This action is communicating with the ajax for the autocomplete
	 * 
	 * @return result
	 */
	public String executeAutocompleteAction() {
		log.debug("start action");
		String result = success;
		Gson gson = new Gson();
		SearchFlightsManager searchFlightsManager = new SearchFlightsManager();
		try {
			if (action.equals("searchOrigin")) {
				// originList =
				// searchFlightsManager.processSearchOrginBySearchTerm(term);
				// message =
				// gson.toJson(searchFlightsManager.processSearchOrginBySearchTerm(term));
				message = gson.toJson(searchFlightsManager.processSearchOrginBySearchTerm(term));
				// System.out.println(jsonString);
			} else if (action.equals("searchDestination")) {
				message = gson.toJson(searchFlightsManager.processDestinationBySearchTermOrigin(term, origin));
			}
			// jsonObject.add("origins", gson.toJsonTree(originList));
		} catch (BusinessException | SystemException | ConnectionException e) {
			result = error;
			errorMessage = e.getMessage();
		}
		log.debug("end action");
		return result;
	}

	public String executeSubmitAction() {
		log.debug("start action");
		String result = submit;

		try {
			SearchFlightsManager searchFlightsManager = new SearchFlightsManager();
			TicketBean ticket = (TicketBean) session.get("ticket");

			if (ticket == null) {
				ticket = searchFlightsManager.processTicket();
				session.put("ticket", ticket);
			}

			ticket.getFlight().setRoute((searchFlightsManager.processRouteInformationByOriginDestination(searchOrigin,
					searchDestination, noOfPax)));
			ticket.getFlight().setDepartureDate(searchDepartureDate);

			session.put("ticket", ticket);
		} catch (Exception e) {
			result = error;
			errorMessage = e.getMessage();
			e.printStackTrace();
		}

		log.debug("end action");
		return result;
	}

	public Double getNoOfPax() {
		return noOfPax;
	}

	public void setNoOfPax(Double noOfPax) {
		this.noOfPax = noOfPax;
	}

	public String getSearchDepartureDate() {
		return searchDepartureDate;
	}

	public void setSearchDepartureDate(String searchDepartureDate) {
		this.searchDepartureDate = searchDepartureDate;
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

	public List<RouteBean> getRouteList() {
		return routeList;
	}

	public void setRouteList(List<RouteBean> routeList) {
		this.routeList = routeList;
	}

	public List<String> getOriginList() {
		return originList;
	}

	public void setOriginList(List<String> originList) {
		this.originList = originList;
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
