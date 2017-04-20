package com.airline.bean;

public class AirportBean {

	private String airportId;
	private String name;
	private String location;
	
	public String getAirportId() {
		return airportId;
	}
	public void setAirportId(String airportId) {
		this.airportId = airportId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "AirportBean [airportId=" + airportId + ", name=" + name + ", location=" + location + "]";
	}
	
}
