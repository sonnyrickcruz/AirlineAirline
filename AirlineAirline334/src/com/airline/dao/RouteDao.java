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
import com.airline.constant.RouteSqlConstants;
import com.airline.exception.ConnectionException;
import com.airline.exception.SystemException;

public class RouteDao extends BaseDao {
	private Logger log = Logger.getLogger(this.getClass());
	public RouteBean getRouteId(String originId, String destinationId) throws SystemException, ConnectionException {
		log.debug("START - getRouteId");
		log.debug("Origin: "+originId);
		log.debug("Destination: "+destinationId);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		RouteBean route = new RouteBean();
		
		try {
			log.debug("Connecting to DB..");
			conn = getConnection();
			ps = conn.prepareStatement(RouteSqlConstants.RETRIEVE_ROUTE_ID);
			ps.setString(1, originId);
			ps.setString(2, destinationId);
			rs = ps.executeQuery();

			if (rs.next()) {
				route.setTravelTime(rs.getInt("travelTime"));
				route.setRouteId(rs.getString("routeId"));
			}
		} catch (ConnectionException e) {
			throw e;
		} catch (SQLTimeoutException e) {
			log.error("There was an SQLTimeoutException while getting some list. " + e);
			throw new SystemException(e);
		} catch (SQLException e) {
			log.error("There was an SQLException while getting some list. " + e);
			throw new SystemException(e);
		} catch (Exception e) {
			log.error("There was an unknown exception while getting some list. " + e);
			throw new SystemException(e);
		} finally {
			closeResources(conn, ps, rs);
		}
		log.debug("END - getRouteId");
		return route;
	}
	
	public List<RouteBean> getRoutes() throws SystemException, ConnectionException {
		log.debug("START - getRoutes");
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<RouteBean> routes = new ArrayList<>();
		RouteBean route;
		AirportBean origin;
		AirportBean destination;
		
		try {
			log.debug("Connecting to DB..");
			conn = getConnection();
			ps = conn.prepareStatement(RouteSqlConstants.RETRIEVE_ROUTES);
			rs = ps.executeQuery();
			while (rs.next()) {
				route = new RouteBean();
				route.setRouteId(rs.getString("route_id"));
				route.setTravelTime(rs.getInt("travel_time"));
				route.setPrice(rs.getDouble("price"));
				
				origin = new AirportBean();
				origin.setAirportId(rs.getString("origin_id"));
				origin.setLocation(rs.getString("origin_location"));
				origin.setName(rs.getString("origin_name"));
				route.setOrigin(origin);
				
				destination = new AirportBean();
				destination.setAirportId(rs.getString("destination_id"));
				destination.setLocation(rs.getString("destination_location"));
				destination.setName(rs.getString("destination_name"));
				route.setDestination(destination);
				
				routes.add(route);
			}
		} catch (ConnectionException e) {
			throw e;
		} catch (SQLTimeoutException e) {
			log.error("There was an SQLTimeoutException while getting some list. " + e);
			throw new SystemException(e);
		} catch (SQLException e) {
			log.error("There was an SQLException while getting some list. " + e);
			throw new SystemException(e);
		} catch (Exception e) {
			log.error("There was an unknown exception while getting some list. " + e);
			throw new SystemException(e);
		} finally {
			closeResources(conn, ps, rs);
		}
		log.debug("END - getRoutes");
		return routes;
	}

}
