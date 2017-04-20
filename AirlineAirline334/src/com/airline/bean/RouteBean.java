package com.airline.bean;

public class RouteBean {

	private String routeId;
	private double price;
	private double pax;
	private int travelTime;
	private AirportBean destination;
	private AirportBean origin;
	
	public String getRouteId() {
		return routeId;
	}
	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getPax() {
		return pax;
	}
	public void setPax(double pax) {
		this.pax = pax;
	}

	public int getTravelTime() {
		return travelTime;
	}
	public void setTravelTime(int travelTime) {
		this.travelTime = travelTime;
	}
	public AirportBean getDestination() {
		return destination;
	}
	public void setDestination(AirportBean destination) {
		this.destination = destination;
	}
	public AirportBean getOrigin() {
		return origin;
	}
	public void setOrigin(AirportBean origin) {
		this.origin = origin;
	}
	
	@Override
	public String toString() {
		return "RouteBean [routeId=" + routeId + ", price=" + price + ", pax=" + pax + ", travelTime=" + travelTime
				+ ", destination=" + destination + ", origin=" + origin + "]";
	}
}
