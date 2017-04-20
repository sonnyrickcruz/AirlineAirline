package com.airline.action;

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
	private String route;
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
				message = gson.toJson(searchFlightsManager.processSearchOrginBySearchTerm(term));
			} else if (action.equals("searchDestination")) {
				message = gson.toJson(searchFlightsManager.processDestinationBySearchTermOrigin(term, origin));
			}
		} catch (BusinessException | SystemException | ConnectionException e) {
			result = error;
			errorMessage = e.getMessage();
		}
		log.debug("end action");
		return result;
	}
	
	public String executeGetRouteList() {
		log.debug("start action");
		String result = success;
		Gson gson = new Gson();
		SearchFlightsManager searchFlightsManager = new SearchFlightsManager();
		try {
			message = gson.toJson(searchFlightsManager.processRoutes());
		} catch (BusinessException | SystemException | ConnectionException e) {
			log.error(e);
		}
		return result;
	}

	public String executeSubmitAction() {
		log.debug("start action");
		String result = submit;

		try {
			SearchFlightsManager searchFlightsManager = new SearchFlightsManager();
			Gson gson = new Gson();
			TicketBean ticket = (TicketBean) session.get("ticket");
			RouteBean routeBean;
			if (ticket == null) {
				ticket = searchFlightsManager.processTicket();
				session.put("ticket", ticket);
			}
			if (route != null) {
				routeBean = gson.fromJson(route, RouteBean.class);
				routeBean.setPax(noOfPax);
				ticket.getFlight().setRoute(routeBean);
			}
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

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

}
