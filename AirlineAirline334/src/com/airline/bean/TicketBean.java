package com.airline.bean;

import java.util.List;

public class TicketBean {
	private AirplaneBean airplane;
	private FlightBean flight;
	private UserBean user;
	private RouteBean route;
	private PriceBean price;
	private List<PaxBean> passengers;
	private BookBean book;
	public AirplaneBean getAirplane() {
		return airplane;
	}
	public void setAirplane(AirplaneBean airplane) {
		this.airplane = airplane;
	}
	public FlightBean getFlight() {
		return flight;
	}
	public void setFlight(FlightBean flight) {
		this.flight = flight;
	}
	public UserBean getUser() {
		return user;
	}
	public void setUser(UserBean user) {
		this.user = user;
	}
	public RouteBean getRoute() {
		return route;
	}
	public void setRoute(RouteBean route) {
		this.route = route;
	}
	public PriceBean getPrice() {
		return price;
	}
	public void setPrice(PriceBean price) {
		this.price = price;
	}
	public List<PaxBean> getPassengers() {
		return passengers;
	}
	public void setPassengers(List<PaxBean> passengers) {
		this.passengers = passengers;
	}
	public BookBean getBook() {
		return book;
	}
	public void setBook(BookBean book) {
		this.book = book;
	}
	@Override
	public String toString() {
		return "TicketBean [airplane=" + airplane + ", flight=" + flight + ", user=" + user + ", route=" + route
				+ ", price=" + price + ", passengers=" + passengers + ", book=" + book + "]";
	}

}
