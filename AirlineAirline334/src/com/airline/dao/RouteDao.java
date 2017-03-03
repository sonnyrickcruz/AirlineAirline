package com.airline.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.List;

import org.apache.log4j.Logger;

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

}
