package com.airline.bean;

public class PaxBean {
	private PassengerBean passenger;
	private MealBean meal;
	private SeatBean seat;
	private BaggageBean baggage;
	private InsuranceBean insurance;
	
	public PassengerBean getPassenger() {
		return passenger;
	}

	public void setPassenger(PassengerBean passenger) {
		this.passenger = passenger;
	}

	public MealBean getMeal() {
		return meal;
	}

	public void setMeal(MealBean meal) {
		this.meal = meal;
	}

	public SeatBean getSeat() {
		return seat;
	}

	public void setSeat(SeatBean seat) {
		this.seat = seat;
	}

	public BaggageBean getBaggage() {
		return baggage;
	}

	public void setBaggage(BaggageBean baggage) {
		this.baggage = baggage;
	}

	@Override
	public String toString() {
		return "PaxBean [passenger=" + passenger + ", meal=" + meal + ", seat=" + seat + ", baggage=" + baggage
				+ ", insurance=" + insurance + "]";
	}

	public InsuranceBean getInsurance() {
		return insurance;
	}

	public void setInsurance(InsuranceBean insurance) {
		this.insurance = insurance;
	}

}
