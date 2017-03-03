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
import com.airline.bean.BookBean;
import com.airline.bean.FlightBean;
import com.airline.bean.RouteBean;
import com.airline.constant.MyFlightsSqlConstants;
import com.airline.exception.ConnectionException;
import com.airline.exception.SystemException;

public class MyFLightsDao extends BaseDao {
	private Logger log = Logger.getLogger(this.getClass());

	/**
	 * Retrieve Flown FLights of the user
	 * 
	 * @param userId
	 * @return
	 * @throws ConnectionException
	 * @throws SystemException
	 */
	public List<BookBean> retrieveFlownFlights(String userId) throws ConnectionException, SystemException {
		log.debug("start - userId: " + userId);
		List<BookBean> flownFlightsList = null;

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			flownFlightsList = new ArrayList<BookBean>();
			conn = getConnection();
			ps = conn.prepareStatement(MyFlightsSqlConstants.MY_FLIGHTS);
			ps.setString(1, userId);
			rs = ps.executeQuery();

			while (rs.next()) {
				BookBean bookBean = new BookBean();
				
				bookBean.setBookId(rs.getString("book_id"));
				
				FlightBean flightBean = new FlightBean();
				flightBean.setFlightId(rs.getString("flight_id"));
				//departure date and time
				flightBean.setDepartureDate(rs.getString("departure_date"));
				flightBean.setDepartureTime(rs.getString("departure_time"));
				//arrival date and time
				flightBean.setArrivalDate(rs.getString("arrival_date"));
				flightBean.setArrivalTime(rs.getString("arrival_time"));
				
				RouteBean routeBean = new RouteBean();
				routeBean.setRouteId(rs.getString("route_id"));
				//origin
				AirportBean airportBean = new AirportBean();
				airportBean.setLocation(rs.getString("origin"));
				routeBean.setOrigin(airportBean);
				//destination
				airportBean = new AirportBean();
				airportBean.setLocation(rs.getString("destination"));
				routeBean.setDestination(airportBean);
				
				flightBean.setRoute(routeBean);
				bookBean.setFlightBean(flightBean);
				
				flownFlightsList.add(bookBean);
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

		log.debug("end");
		return flownFlightsList;
	}

}
