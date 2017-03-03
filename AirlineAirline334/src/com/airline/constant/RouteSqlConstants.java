package com.airline.constant;

public class RouteSqlConstants {

	public final static String RETRIEVE_ROUTE_ID = "SELECT r.route_id AS routeId, travel_time AS travelTime FROM route r WHERE r.origin_id = ? AND r.destination_id = ?";

}
