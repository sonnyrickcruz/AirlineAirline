package com.airline.action;

import java.util.List;

import org.apache.log4j.Logger;

import com.airline.bean.FlightBean;
import com.airline.bean.TicketBean;
import com.airline.exception.BusinessException;
import com.airline.exception.ConnectionException;
import com.airline.exception.SystemException;
import com.airline.manager.SelectFlightsManager;
import com.airline.util.DateUtil;

public class SelectFlightAction extends BaseAction {
	private Logger log = Logger.getLogger(this.getClass());
	private List<FlightBean> flights;
	private String departureDate;
	private String flightId;

	/**
	 * displays flights available for the selected route and date
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
	 * displays flights available for an specific update
	 * 
	 * @param result
	 */
	public String executeSnippet() {
		log.debug("start action");
		String result = success;
		
		try {
			TicketBean ticket = (TicketBean) session.get("ticket");
			String routeId = ticket.getFlight().getRoute().getRouteId();
			departureDate = DateUtil.toMySqlDateString(departureDate);
			
			SelectFlightsManager selectFlightsManager = new SelectFlightsManager();
			log.info("Retrieving flights for routeId: " + routeId + " and departureDate: " + departureDate);
			flights = selectFlightsManager.retrieveFlightsByDateRouteId(departureDate, routeId);
			log.info("There are " + flights.size() + " flights for routeId: " + routeId + " and departureDate: " + departureDate);
		} catch (BusinessException | SystemException | ConnectionException e) {
			log.error(e);
		}
		
		log.debug("end action - result: " + result);
		return result;
	}

	/**
	 * description
	 * 
	 * @param result
	 */
	public String executeSubmitAction() {
		log.debug("start action");
		String result = success;

		try {
			SelectFlightsManager selectFlightsManager = new SelectFlightsManager();
			TicketBean ticket = (TicketBean) session.get("ticket");
			if (ticket != null) {
				ticket.setFlight(selectFlightsManager.retrieveFlightById(flightId));
				log.debug(selectFlightsManager.retrieveFlightById(flightId));
			}
			
			session.put("ticket", ticket);
		} catch (Exception e) {
			log.error(e);
		}
		
		log.debug("end action - result: " + result);
		return result;
	}

	public List<FlightBean> getFlights() {
		return flights;
	}

	public void setFlights(List<FlightBean> flights) {
		this.flights = flights;
	}

	public String getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}
	
}
