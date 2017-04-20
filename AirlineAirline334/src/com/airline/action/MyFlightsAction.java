package com.airline.action;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.airline.bean.BookBean;
import com.airline.bean.UserBean;
import com.airline.constant.ErrorConstants;
import com.airline.exception.BusinessException;
import com.airline.exception.ConnectionException;
import com.airline.exception.SystemException;
import com.airline.manager.MyFlightsManager;

public class MyFlightsAction extends BaseAction {
	private Logger log = Logger.getLogger(this.getClass());

	public String execute() {
		log.debug("start action");
		String result = input;
		
		UserBean userBean = (UserBean) session.get("userInfo");

		// execute Logic
		MyFlightsManager myFlightsManager = new MyFlightsManager();
		Map<String, List<BookBean>> myFlights = null;
		List<BookBean> flownFlightsList = null;
		List<BookBean> futureFlightsList = null;
		List<BookBean> currentFlightsList = null;
		List<BookBean> myAllFLightsList = null;
		try {
			myFlights =  myFlightsManager.processMyFlights(userBean.getId());
			
			for(String key : myFlights.keySet()){
				if(key.equalsIgnoreCase("flownFlights")){
					flownFlightsList = myFlights.get(key);
				}else if(key.equalsIgnoreCase("currentFlights")){
					currentFlightsList = myFlights.get(key);
				}else if(key.equalsIgnoreCase("futureFlights")){
					futureFlightsList = myFlights.get(key);
				}else if(key.equalsIgnoreCase("myAllFLights")){
					myAllFLightsList = myFlights.get(key);
				}
			}
			
			session.put("flownFlightsList", flownFlightsList);
			session.put("currentFlightsList", currentFlightsList);
			session.put("futureFlightsList", futureFlightsList);
			session.put("myAllFLightsList", myAllFLightsList);
		} catch (BusinessException e) {
			errorMessage = ErrorConstants.BUSINESS_EXCEPTION;
			result = input;
		} catch (SystemException e) {
			errorMessage = ErrorConstants.SYSTEM_EXCEPTION;
			result = error;
		} catch (ConnectionException e) {
			errorMessage = ErrorConstants.CONNECTION_EXCEPTION;
			result = error;
		} catch (Exception e){
			errorMessage = ErrorConstants.BUSINESS_EXCEPTION;
			result = input;
		}
		
		log.debug("end action - result: " + result);
		return result;
	}
}
