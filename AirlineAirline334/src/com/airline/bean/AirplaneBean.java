package com.airline.bean;

public class AirplaneBean {

	private String airplaneId;
	private String type;
	private String model;
	private String maker;
	
	public String getAirplaneId() {
		return airplaneId;
	}
	public void setAirplaneId(String airplaneId) {
		this.airplaneId = airplaneId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getMaker() {
		return maker;
	}
	public void setMaker(String maker) {
		this.maker = maker;
	}
	@Override
	public String toString() {
		return "AirplaneBean [airplaneId=" + airplaneId + ", type=" + type + ", model=" + model + ", maker=" + maker
				+ "]";
	}
	
}
