package com.airline.bean;

public class MealBean {
	private String mealId;
	private String name;
	private double price;
	private String description;
	private String imgURI;

	public String getMealId() {
		return mealId;
	}

	public void setMealId(String mealId) {
		this.mealId = mealId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImgURI() {
		return imgURI;
	}

	public void setImgURI(String imgURI) {
		this.imgURI = imgURI;
	}

	@Override
	public String toString() {
		return "MealBean [mealId=" + mealId + ", name=" + name + ", price=" + price + ", description=" + description
				+ ", imgURI=" + imgURI + "]";
	}

}
