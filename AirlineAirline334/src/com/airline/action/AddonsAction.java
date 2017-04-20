package com.airline.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.airline.bean.BaggageBean;
import com.airline.bean.InsuranceBean;
import com.airline.bean.MealBean;
import com.airline.bean.PassengerBean;
import com.airline.bean.PaxBean;
import com.airline.bean.TicketBean;
import com.airline.exception.ConnectionException;
import com.airline.exception.SystemException;
import com.airline.manager.AddonsManager;


/*inputs
 * no. of passengers
 * paxbean
 * 
 * 
 * 
 */

public class AddonsAction extends BaseAction {
	private Logger log = Logger.getLogger(this.getClass());

	private List<MealBean> mealList;
	private List<PassengerBean> passengerList;
	private String message;
	private String[] mealArray;
	private String[] baggageArray;
	private String[] insuranceArray;

	public String execute() {
		log.debug("Start");
		passengerList = new ArrayList<PassengerBean>();
		List<InsuranceBean> insuranceList;
		List<BaggageBean> baggageList;
		AddonsManager addonsManager = new AddonsManager();
		try {
			mealList = addonsManager.retrieveMealList();
			insuranceList = addonsManager.retrieveInsuranceList();
			baggageList = addonsManager.retrieveBaggageList();
			// this is a dummy data
			//sa magtutuloy nito, iassign lang sa passengerList yung list ng passenger.(input po ito)
			for (int i = 0; i < 10; i++) {
				PassengerBean passengers = new PassengerBean();
				passengers.setFirstName("Test" + i);
				passengers.setLastName("LastName" + i);
				if (i % 2 == 0) {
					passengers.setPrefix("Mr");
				} else {
					passengers.setPrefix("Ms");
				}
				passengerList.add(passengers);
			}
			// -------------END OF DUMMY
			// DATA------------------------------------

			if ("done".equals(message)) {
				TicketBean ticket = (TicketBean) session.get("ticket");
				MealBean passMeal = null;
				BaggageBean passBaggage = null;
				InsuranceBean passInsurance = null;
				List<PaxBean> passengerList = ticket.getPassengers();
				
				int counter =0;
				for (PaxBean passenger : passengerList) {
					passMeal = new MealBean();
					for (MealBean meal : mealList) {
						if (mealArray[counter].equals(meal.getMealId())){
							passMeal = meal;
						}
					}
					for (BaggageBean baggage : baggageList) {
						if (baggageArray[counter].equals(baggage.getBaggageId())){
							passBaggage = baggage;
						}
					}
					for (InsuranceBean insurance : insuranceList) {
						if (insuranceArray[counter].equals(insurance.getInsuranceId())){
							passInsurance = insurance;
						}
					}
					passenger.setMeal(passMeal);
					passenger.setBaggage(passBaggage);
					passenger.setInsurance(passInsurance);
				}
				System.out.println("#####################################");

			} else {

				message = "success";
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

			}
		} catch (ConnectionException | SystemException e) {
			// errorpage
		}

		return message;
	}

	public List<PassengerBean> getPassengerList() {
		return passengerList;
	}

	public void setPassengerList(List<PassengerBean> passengerList) {
		this.passengerList = passengerList;
	}

	public List<MealBean> getMealList() {
		return mealList;
	}

	public void setMealList(List<MealBean> mealList) {
		this.mealList = mealList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String[] getMealArray() {
		return mealArray;
	}

	public void setMealArray(String[] mealArray) {
		this.mealArray = mealArray;
	}

	public String[] getBaggageArray() {
		return baggageArray;
	}

	public void setBaggageArray(String[] baggageArray) {
		this.baggageArray = baggageArray;
	}

	public String[] getInsuranceArray() {
		return insuranceArray;
	}

	public void setInsuranceArray(String[] insuranceArray) {
		this.insuranceArray = insuranceArray;
	}
}
