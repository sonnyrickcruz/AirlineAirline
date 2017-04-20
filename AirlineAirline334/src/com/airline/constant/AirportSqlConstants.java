package com.airline.constant;

public class AirportSqlConstants {

	public final static String RETRIEVE_AIRPORT_ORIGIN = "SELECT CONCAT('(',a.airport_id,')',' ',a.airport_location) AS locations FROM airport a WHERE CONCAT('(',a.airport_id,')',' ',a.airport_location) LIKE ?";
	public final static String RETRIEVE_AIRPORT_DESTINATION ="SELECT CONCAT('(',a.airport_id,')',' ',a.airport_location) AS locations FROM airport a WHERE CONCAT('(',a.airport_id,')',' ',a.airport_location) != ? AND CONCAT('(',a.airport_id,')',' ',a.airport_location) LIKE ?";
}
