package com.airline.bean;

public class BookBean {
	private String bookId;
	private FlightBean flightBean;
	private String bookDate;
	private String bookTime;
	
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public FlightBean getFlightBean() {
		return flightBean;
	}
	public void setFlightBean(FlightBean flightBean) {
		this.flightBean = flightBean;
	}
	public String getBookDate() {
		return bookDate;
	}
	public void setBookDate(String bookDate) {
		this.bookDate = bookDate;
	}
	public String getBookTime() {
		return bookTime;
	}
	public void setBookTime(String bookTime) {
		this.bookTime = bookTime;
	}
	
	@Override
	public String toString() {
		return "RouteBean [bookId=" + bookId + ", flightBean=" + flightBean + ", bookDate=" + bookDate + ", bookTime=" + bookTime + "]";
	}
}
