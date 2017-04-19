package com.airline.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.airline.bean.AirplaneBean;
import com.airline.bean.AirportBean;
import com.airline.bean.BookBean;
import com.airline.bean.FlightBean;
import com.airline.bean.PaxBean;
import com.airline.bean.PriceBean;
import com.airline.bean.RouteBean;
import com.airline.bean.TicketBean;
import com.airline.bean.UserBean;
import com.airline.dao.SearchFlightsDao;
import com.airline.exception.BusinessException;
import com.airline.exception.ConnectionException;
import com.airline.exception.SystemException;
import com.airline.factory.OriginDestination;
import com.airline.util.StringUtils;

public class SearchFlightsManager {
	private Logger log = Logger.getLogger(this.getClass());

	/**
	 * Processing the list of routes for searching flights origin and
	 * destination
	 * 
	 * @return routeList
	 * @throws BusinessException
	 * @throws SystemException
	 * @throws ConnectionException
	 */
	public List<RouteBean> processRouteList(String term) throws BusinessException, SystemException, ConnectionException {
		log.debug("start");

		List<RouteBean> routeList = null;

		SearchFlightsDao searchFlightsDao = new SearchFlightsDao();
		try {
			// Logic Here!
			routeList = searchFlightsDao.retrieveRoutes();
		} catch (ConnectionException e) {
			throw e;
		} catch (SystemException e) {
			throw e;
		} catch (Exception e) {
			log.error("There was an unknown error while processing route list. " + e);
			throw new BusinessException(e);
		}

		log.debug("end");
		return routeList;
	}
	
	/**
	 * Processing the list of origin for searching flights
	 * 
	 * @return destinationList
	 * @throws BusinessException
	 * @throws SystemException
	 * @throws ConnectionException
	 */
	public List<String> processSearchOrginBySearchTerm(String term) throws BusinessException, SystemException, ConnectionException {
		log.debug("start");

		List<String> resultList = new ArrayList<>();

		OriginDestination originDestination = OriginDestination.getInstance();
		try {
			List<String> originList = originDestination.getOrigins();
			if (term.length() == 0) {
				return resultList;
			}
			for (String string : originList) {
				if (StringUtils.isInString(string, term)) {
					resultList.add(string);
				}
			}
		} catch (Exception e) {
			log.error("There was an unknown error while processing origin list. " + e);
			throw new BusinessException(e);
		}

		log.debug("end");
		return resultList;
	}
	
	/**
	 * Processing the list of destination for searching flights
	 * 
	 * @return destinationList
	 * @throws BusinessException
	 * @throws SystemException
	 * @throws ConnectionException
	 */
	public List<String> processDestinationBySearchTermOrigin(String term, String origin) throws BusinessException, SystemException, ConnectionException {
		log.debug("start");

		List<String> destinationList = null;

		SearchFlightsDao searchFlightsDao = new SearchFlightsDao();
		try {
			destinationList = searchFlightsDao.retrieveDestinationBySearchTermOrigin(term, origin);
		} catch (ConnectionException e) {
			throw e;
		} catch (SystemException e) {
			throw e;
		} catch (Exception e) {
			log.error("There was an unknown error while processing destination list. " + e);
			throw new BusinessException(e);
		}

		log.debug("end");
		return destinationList;
	}
	
	/**
	 * Processing the route information
	 * 
	 * @param searchOrigin, searchDestination, noOfPax
	 * @return route
	 * @throws BusinessException
	 * @throws SystemException
	 * @throws ConnectionException
	 */
	public RouteBean processRouteInformationByOriginDestination(String searchOrigin, String searchDestination, double noOfPax) throws BusinessException, SystemException, ConnectionException {
		log.debug("start searchOrigin: " + searchOrigin + " searchDestination: " + searchDestination + " noOfPax: " + noOfPax);
		 
		RouteBean route = null;
		
		SearchFlightsDao searchFlightsDao = new SearchFlightsDao();
		try {
			route = searchFlightsDao.retrieveRouteInformationByOriginDestination(searchOrigin, searchDestination);
			route.setPax(noOfPax);
		} catch (ConnectionException e) {
			throw e;
		} catch (SystemException e) {
			throw e;
		} catch (Exception e) {
			log.error("There was an unknown error while processing route information. " + e);
			throw new BusinessException(e);
		}
		
		log.debug("end origin: " + route.getOrigin().getAirportId() + " destination: " + route.getDestination().getAirportId());
		return route;
	}
	
	/**
	 * Processing the ticket bean for session
	 * 
	 * @throws BusinessException
	 */
	public TicketBean processTicket() throws BusinessException {
		log.debug("start");
		
		TicketBean ticket = new TicketBean();
		try {
			// Set the airplane
			AirplaneBean airplane = new AirplaneBean();
			ticket.setAirplane(airplane);

			// Set the flight and the route
			RouteBean route = new RouteBean();
			route.setDestination(new AirportBean());
			AirportBean origin = new AirportBean();
			route.setOrigin(origin);
			FlightBean flight = new FlightBean();
			flight.setRoute(route);
			ticket.setRoute(route);
			ticket.setFlight(flight);

			// Set the User who will be booking the flight
			UserBean user = new UserBean();
			ticket.setUser(user);
			
			// Set the price of the to be booked flight
			PriceBean price = new PriceBean();
			ticket.setPrice(price);
			
			// Set the passengers info
			List<PaxBean> paxList = new ArrayList<PaxBean>();
			ticket.setPassengers(paxList);
			
			// Set the booking details
			BookBean book = new BookBean();
			book.setFlightBean(flight);
			ticket.setBook(book);
			
		} catch (Exception e) {
			log.error("There was an unknown error while processing the ticket. " + e);
			throw new BusinessException(e);
		}
		
		log.debug("end");
		return ticket;
	}
}
