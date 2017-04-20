package com.airline.constant;

public class SqlConstants {
	// SQL connection constants
	public final static String DB_DATASOURCE = "java:comp/env/airlinedb";
	
	// Sample queries
	public final static String SAMPLE_QUERY = "SELECT seat_id FROM seat WHERE seat_id = ?";
	public final static String LOGIN_QUERY ="SELECT username,first_name,last_name,role,user_id AS userId FROM user WHERE username=? AND password=?";
}
