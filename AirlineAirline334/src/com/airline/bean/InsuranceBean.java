package com.airline.bean;

public class InsuranceBean {
	private String insuranceId;
	private double price;

	public String getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(String insuranceId) {
		this.insuranceId = insuranceId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "InsuranceBean [insuranceId=" + insuranceId + ", price=" + price + "]";
	}
}
