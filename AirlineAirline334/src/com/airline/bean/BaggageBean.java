package com.airline.bean;

public class BaggageBean {
	private String baggageId;
	private int kilograms;
	private double price;

	public int getKilograms() {
		return kilograms;
	}

	public void setKilograms(int kilograms) {
		this.kilograms = kilograms;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getBaggageId() {
		return baggageId;
	}

	public void setBaggageId(String baggageId) {
		this.baggageId = baggageId;
	}

	@Override
	public String toString() {
		return "BaggageBean [baggageId=" + baggageId + ", kilograms=" + kilograms + ", price=" + price + "]";
	}
	
}
