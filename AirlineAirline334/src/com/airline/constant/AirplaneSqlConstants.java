package com.airline.constant;

public class AirplaneSqlConstants {

	public final static String RETRIEVE_AIRCRAFT_ID = "SELECT concat('(',f.airplane_id,') ',ai.manufacturer,' ',ai.aircraft_id) AS aircraftInfo FROM flight f INNER JOIN airplane ap ON ap.airplane_id = f.airplane_id INNER JOIN aircraft ai ON ai.aircraft_id = ap.aircraft_id WHERE f.route_id = ? GROUP BY f.airplane_id";
}
