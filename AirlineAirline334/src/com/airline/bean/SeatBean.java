package com.airline.bean;

public class SeatBean {
	private String seatId;
	private String row;
	private String code;
	private String actualSeat;

	public String getRow() {
		return row;
	}

	public void setRow(String row) {
		this.row = row;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSeatId() {
		return seatId;
	}

	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}

	public String getActualSeat() {
		return actualSeat;
	}

	public void setActualSeat(String actualSeat) {
		this.actualSeat = actualSeat;
	}

	@Override
	public String toString() {
		return "SeatBean [seatId=" + seatId + ", row=" + row + ", code=" + code
				+ ", actualSeat=" + actualSeat + "]";
	}
}
