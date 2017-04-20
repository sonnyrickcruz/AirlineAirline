package com.airline.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.airline.bean.FlightBean;
import com.airline.exception.ConnectionException;
import com.airline.exception.SystemException;

public class SelectFlightDao extends BaseDao {
	private Logger log = Logger.getLogger(this.getClass());

	/**
	 * Retrieves flight details by departure date and route id
	 * 
	 * @param departureDate, routeId
	 * @return flightList
	 * @throws ConnectionException
	 * @throws SystemException
	 */
	public List<FlightBean> retrieveFlightsByDateRouteId(String departureDate, String routeId) throws ConnectionException, SystemException {
		log.debug("start - departureDate: " + departureDate + " routeId: " + routeId);
		List<FlightBean> flightList = null;

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			flightList = new ArrayList<FlightBean>();
			FlightBean flight = null;
			
			conn = getConnection();
			ps = conn.prepareStatement("SELECT flight.flight_id, flight.airplane_id, DATE(departure_schedule) AS departure_date, TIME(departure_schedule) AS departure_time, DATE(arrival_schedule) AS arrival_date, TIME(arrival_schedule) AS arrival_time, (seating_capacity - COUNT(book_assignment.book_assignment_id)) AS seats_left, seating_capacity, remove_id FROM flight LEFT JOIN airplane ON flight.airplane_id = airplane.airplane_id LEFT JOIN aircraft ON aircraft.aircraft_id = airplane.aircraft_id LEFT JOIN book ON flight.flight_id = book.flight_id LEFT JOIN book_assignment ON book.book_id = book_assignment.book_id WHERE route_id = ? AND DATE(departure_schedule) = ? GROUP BY flight_id");
			ps.setString(1, routeId);
			ps.setString(2, departureDate);
			rs = ps.executeQuery();

			while (rs.next()) {
				flight = new FlightBean();
				flight.setFlightId(rs.getString("flight_id"));
				flight.setAirplaneId(rs.getString("airplane_id"));
				flight.setDepartureDate(rs.getString("departure_date"));
				flight.setDepartureTime(rs.getString("departure_time"));
				flight.setArrivalDate(rs.getString("arrival_date"));
				flight.setArrivalTime(rs.getString("arrival_time"));
				flight.setSeatingCapacity(rs.getInt("seating_capacity"));
				flight.setNumberOfOccupiedSeats(rs.getInt("seats_left"));
				if (rs.getString("remove_id") == null ) {
					flight.setFlightStatus("Active");
				} else {
					flight.setFlightStatus("Cancelled");
				}
				flightList.add(flight);
			}

		} catch (ConnectionException e) {
			throw e;
		} catch (SQLTimeoutException e) {
			log.error("There was an SQLTimeoutException while getting flight list. " + e);
			throw new SystemException(e);
		} catch (SQLException e) {
			log.error("There was an SQLException while getting flight list. " + e);
			throw new SystemException(e);
		} catch (Exception e) {
			log.error("There was an unknown exception while getting flight list. " + e);
			throw new SystemException(e);
		} finally {
			closeResources(conn, ps, rs);
		}

		log.debug("end flightListSize: " + flightList.size());
		return flightList;
	}

}
