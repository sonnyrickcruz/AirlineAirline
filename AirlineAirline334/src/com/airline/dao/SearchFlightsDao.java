package com.airline.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.airline.bean.AirportBean;
import com.airline.bean.RouteBean;
import com.airline.exception.ConnectionException;
import com.airline.exception.SystemException;

public class SearchFlightsDao extends BaseDao {
	private Logger log = Logger.getLogger(this.getClass());


	/**
	 * Retrieving the list of routes for the search flights function
	 * 
	 * @return routeList
	 * @throws ConnectionException
	 * @throws SystemException
	 */
	public List<RouteBean> retrieveRoutes() throws ConnectionException, SystemException {
		log.debug("start");
		List<RouteBean> routeList = null;

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			routeList = new ArrayList<RouteBean>();
			RouteBean route = null;
			AirportBean airport = null;
			conn = getConnection();
			ps = conn.prepareStatement("SELECT route_id, origin_id, origin.airport_location AS origin_location, origin.airport_name AS origin_airport_name, destination_id, destination.airport_location AS destination_location, destination.airport_name AS destination_airport_name FROM route INNER JOIN airport AS origin ON route.origin_id = origin.airport_id INNER JOIN airport AS destination ON route.destination_id = destination.airport_id;");
			rs = ps.executeQuery();

			while (rs.next()) {
				route = new RouteBean();
				//origin
				airport = new AirportBean();
				airport.setAirportId(rs.getString("origin_id"));
				airport.setLocation(rs.getString("origin_location"));
				airport.setName(rs.getString("origin_airport_name"));
				route.setOrigin(airport);
				//destination
				airport = new AirportBean();
				airport.setAirportId(rs.getString("destination_id"));
				airport.setLocation(rs.getString("destination_location"));
				airport.setName(rs.getString("destination_airport_name"));
				route.setDestination(airport);
				route.setRouteId(rs.getString("route_id"));
				routeList.add(route);
			}

		} catch (ConnectionException e) {
			throw e;
		} catch (SQLTimeoutException e) {
			log.error("There was an SQLTimeoutException while retrieving route list." + e);
			throw new SystemException(e);
		} catch (SQLException e) {
			log.error("There was an SQLException while retrieving route list." + e);
			throw new SystemException(e);
		} catch (Exception e) {
			log.error("There was an unknown exception while retrieving route list." + e);
			throw new SystemException(e);
		} finally {
			closeResources(conn, ps, rs);
		}

		log.debug("end");
		return routeList;
	}
	
	/**
	 * Retrieving the list of origin
	 * 
	 * @param term
	 * @return originList
	 * @throws ConnectionException
	 * @throws SystemException
	 */
	public List<String> retrieveOriginBySearchTerm(String term) throws ConnectionException, SystemException {
		log.debug("start term: " + term);
		List<String> originList = null;

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		term = "%" + term + "%";
		
		try {
			originList = new ArrayList<String>();
			conn = getConnection();
			ps = conn.prepareStatement("SELECT CONCAT('(', airport_id, ')', ' ', airport_location) AS origin FROM airport WHERE CONCAT('(', airport_id, ')', ' ', airport_location) LIKE ?");
			ps.setString(1, term);
			rs = ps.executeQuery();

			while (rs.next()) {
				originList.add(rs.getString("origin"));
			}

		} catch (ConnectionException e) {
			throw e;
		} catch (SQLTimeoutException e) {
			log.error("There was an SQLTimeoutException while retrieving origin list." + e);
			throw new SystemException(e);
		} catch (SQLException e) {
			log.error("There was an SQLException while retrieving origin list." + e);
			throw new SystemException(e);
		} catch (Exception e) {
			log.error("There was an unknown exception while retrieving origin list." + e);
			throw new SystemException(e);
		} finally {
			closeResources(conn, ps, rs);
		}

		log.debug("end");
		return originList;
	}
	
	/**
	 * Retrieving the list of destination
	 * 
	 * @param term
	 * @param origin
	 * @return originList
	 * @throws ConnectionException
	 * @throws SystemException
	 */
	public List<String> retrieveDestinationBySearchTermOrigin(String term, String origin) throws ConnectionException, SystemException {
		log.debug("start term: " + term + " origin: " + origin);
		List<String> destinationList = null;

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		term = "%" + term + "%";
		
		try {
			destinationList = new ArrayList<String>();
			conn = getConnection();
			ps = conn.prepareStatement("SELECT CONCAT('(', destination.airport_id, ')', ' ', destination.airport_location) AS destination FROM route INNER JOIN airport AS origin ON route.origin_id = origin.airport_id INNER JOIN airport AS destination ON route.destination_id = destination.airport_id WHERE CONCAT('(', origin.airport_id, ')', ' ', origin.airport_location) = ? AND CONCAT('(', destination.airport_id, ')', ' ', destination.airport_location) LIKE ?");
			ps.setString(1, origin);
			ps.setString(2, term);
			rs = ps.executeQuery();

			while (rs.next()) {
				destinationList.add(rs.getString("destination"));
			}

		} catch (ConnectionException e) {
			throw e;
		} catch (SQLTimeoutException e) {
			log.error("There was an SQLTimeoutException while retrieving destination list." + e);
			throw new SystemException(e);
		} catch (SQLException e) {
			log.error("There was an SQLException while retrieving destination list." + e);
			throw new SystemException(e);
		} catch (Exception e) {
			log.error("There was an unknown exception while retrieving destination list." + e);
			throw new SystemException(e);
		} finally {
			closeResources(conn, ps, rs);
		}

		log.debug("end");
		return destinationList;
	}
	
	/**
	 * Retrieving route information for the routebean
	 * 
	 * @param searchOrigin
	 * @param searchDestination
	 * @return route
	 * @throws ConnectionException
	 * @throws SystemException
	 */
	public RouteBean retrieveRouteInformationByOriginDestination(String searchOrigin, String searchDestination) throws ConnectionException, SystemException {
		log.debug("start searchOrigin: " + searchOrigin + " searchDestination: " + searchDestination);
		RouteBean route = null;
		AirportBean origin = null;
		AirportBean destination = null;
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			route = new RouteBean();
			conn = getConnection();
			ps = conn.prepareStatement("SELECT origin_id, origin.airport_location AS origin_location, origin.airport_name AS origin_name, destination_id, destination.airport_location AS destination_location, destination.airport_name AS destination_name, route_id, price, travel_time FROM route INNER JOIN airport AS origin ON route.origin_id = origin.airport_id INNER JOIN airport AS destination ON route.destination_id = destination.airport_id WHERE CONCAT('(', origin.airport_id, ')', ' ', origin.airport_location) = ? AND CONCAT('(', destination.airport_id, ')', ' ', destination.airport_location) = ?");
			ps.setString(1, searchOrigin);
			ps.setString(2, searchDestination);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				origin = new AirportBean();
				origin.setAirportId(rs.getString("origin_id"));
				origin.setLocation(rs.getString("origin_location"));
				origin.setName(rs.getString("origin_name"));
				
				destination = new AirportBean();
				destination.setAirportId(rs.getString("destination_id"));
				destination.setLocation(rs.getString("destination_location"));
				destination.setName(rs.getString("destination_name"));
				
				route = new RouteBean();
				route.setOrigin(origin);
				route.setDestination(destination);
				route.setPrice(rs.getDouble("price"));
				route.setRouteId(rs.getString("route_id"));
				route.setTravelTime(rs.getInt("travel_time"));
			}
			
		} catch (ConnectionException e) {
			throw e;
		} catch (SQLTimeoutException e) {
			log.error("There was an SQLTimeoutException while retrieving route information." + e);
			throw new SystemException(e);
		} catch (SQLException e) {
			log.error("There was an SQLException while retrieving route information." + e);
			throw new SystemException(e);
		} catch (Exception e) {
			log.error("There was an unknown exception while retrieving route information." + e);
			throw new SystemException(e);
		} finally {
			closeResources(conn, ps, rs);
		}
		
		log.debug("end origin: " + route.getOrigin().getAirportId() + " destination: " + route.getDestination().getAirportId());
		return route;
	}

}
