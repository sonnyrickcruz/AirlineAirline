package com.airline.constant;

public class RouteSqlConstants {
	public static final String RETRIEVE_ROUTE_ID = "SELECT r.route_id AS routeId, travel_time AS travelTime FROM route r WHERE r.origin_id = ? AND r.destination_id = ?";
	public static final String RETRIEVE_ROUTES = "SELECT route_id, origin_id, origin.airport_location as 'origin_location', origin.airport_name as 'origin_name', destination_id, destination.airport_location as 'destination_location', destination.airport_name as 'destination_name', price, travel_time FROM route INNER JOIN airport as origin ON origin.airport_id = origin_id INNER JOIN airport as destination ON destination.airport_id = destination_id";

	private RouteSqlConstants() {
	}
}
