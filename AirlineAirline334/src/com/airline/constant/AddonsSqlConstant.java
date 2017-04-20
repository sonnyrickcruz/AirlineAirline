package com.airline.constant;

public class AddonsSqlConstant {
	public final static String MEAL_QUERY ="SELECT meal_id AS mealId, name AS mealName, description AS mealDescription, price AS mealPrice, image_uri AS imageURI FROM meal;";
	public final static String INSURANCE_QUERY = "SELECT insurance_id AS insuranceId,price AS insurancePrice FROM insurance;";
	public final static String BAGGAGE_QUERY = "SELECT baggage_id AS baggageId,weight AS baggageWeight,price AS baggagePrice FROM baggage;";
}
