package com.airline.constant;

public class MyFlightsSqlConstants {
	public final static String MY_FLIGHTS = "SELECT b.book_id, f.flight_id, r.route_id, o.airport_location AS 'origin', d.airport_location AS 'destination', DATE(f.departure_schedule) AS 'departure_date', TIME(f.departure_schedule) AS 'departure_time', DATE(f.arrival_schedule) AS 'arrival_date', TIME(f.arrival_schedule) AS 'arrival_time' FROM user u"
			+ " INNER JOIN book b ON b.user_id = u.user_id"
			+ " INNER JOIN flight f ON f.flight_id = b.flight_id"
			+ " INNER JOIN route r ON r.route_id = f.route_id"
			+ " INNER JOIN airport o ON o.airport_id = r.origin_id"
			+ " INNER JOIN airport d ON d.airport_id = r.destination_id"
				+ " WHERE u.user_id = ? ORDER BY f.departure_schedule DESC";
}
