package com.airline.bean;

public class PriceBean {
	private double totalMealPrice;
	private double totalInsurancePrice;
	private double totalBaggagePrice;
	private double totalAirFare;
	private double tax;
	private double basePrice;
	public double getTotalMealPrice() {
		return totalMealPrice;
	}
	public void setTotalMealPrice(double totalMealPrice) {
		this.totalMealPrice = totalMealPrice;
	}
	public double getTotalInsurancePrice() {
		return totalInsurancePrice;
	}
	public void setTotalInsurancePrice(double totalInsurancePrice) {
		this.totalInsurancePrice = totalInsurancePrice;
	}
	public double getTotalBaggagePrice() {
		return totalBaggagePrice;
	}
	public void setTotalBaggagePrice(double totalBaggagePrice) {
		this.totalBaggagePrice = totalBaggagePrice;
	}
	public double getTotalAirFare() {
		return totalAirFare;
	}
	public void setTotalAirFare(double totalAirFare) {
		this.totalAirFare = totalAirFare;
	}
	public double getTax() {
		return tax;
	}
	public void setTax(double tax) {
		this.tax = tax;
	}
	public double getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}
	@Override
	public String toString() {
		return "PriceBean [totalMealPrice=" + totalMealPrice + ", totalInsurancePrice=" + totalInsurancePrice
				+ ", totalBaggagePrice=" + totalBaggagePrice + ", totalAirFare=" + totalAirFare + ", tax=" + tax
				+ ", basePrice=" + basePrice + "]";
	}
	
	
}
