package com.airline.bean;

public class FlightBean {

	private String flightStatus;
	private String flightId;
	private RouteBean route;
	private String airplaneId;
	private String departureDate;
	private String departureTime;
	private String arrivalDate;
	private String arrivalTime;
	private int seatingCapacity;
	private int numberOfOccupiedSeats;
	private boolean isActive;

	public String getFlightStatus() {
		return flightStatus;
	}
	public void setFlightStatus(String flightStatus) {
		this.flightStatus = flightStatus;
	}
	public String getFlightId() {
		return flightId;
	}
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}
	public RouteBean getRoute() {
		return route;
	}
	public void setRoute(RouteBean route) {
		this.route = route;
	}
	public String getAirplaneId() {
		return airplaneId;
	}
	public void setAirplaneId(String airplaneId) {
		this.airplaneId = airplaneId;
	}
	public String getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public String getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public int getSeatingCapacity() {
		return seatingCapacity;
	}
	public void setSeatingCapacity(int seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}
	public int getNumberOfOccupiedSeats() {
		return numberOfOccupiedSeats;
	}
	public void setNumberOfOccupiedSeats(int numberOfOccupiedSeats) {
		this.numberOfOccupiedSeats = numberOfOccupiedSeats;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	@Override
	public String toString() {
		return "FlightBean [flightStatus=" + flightStatus + ", flightId=" + flightId + ", route=" + route
				+ ", airplaneId=" + airplaneId + ", departureDate=" + departureDate + ", departureTime=" + departureTime
				+ ", arrivalDate=" + arrivalDate + ", arrivalTime=" + arrivalTime + ", seatingCapacity="
				+ seatingCapacity + ", numberOfOccupiedSeats=" + numberOfOccupiedSeats + ", isActive=" + isActive + "]";
	}
}
