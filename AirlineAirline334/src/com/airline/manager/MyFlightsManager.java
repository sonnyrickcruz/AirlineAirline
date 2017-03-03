package com.airline.manager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.airline.bean.BookBean;
import com.airline.bean.FlightBean;
import com.airline.dao.MyFLightsDao;
import com.airline.exception.BusinessException;
import com.airline.exception.ConnectionException;
import com.airline.exception.SystemException;

public class MyFlightsManager {
	private Logger log = Logger.getLogger(this.getClass());

	/*** 
	 * @param userId
	 * @return flownFlightsList
	 * @throws BusinessException
	 * @throws SystemException
	 * @throws ConnectionException
	 */
	public Map<String, List<BookBean>> processMyFlights(String userId)
			throws BusinessException, SystemException, ConnectionException {
		log.debug("start");
		
		Map<String, List<BookBean>> myFlights = new HashMap<String, List<BookBean>>();
		
		List<BookBean> flightsList = null;
		List<BookBean> flownFlightsList = new ArrayList<BookBean>();
		List<BookBean> futureFlightsList = new ArrayList<BookBean>();
		List<BookBean> currentFlightsList = new ArrayList<BookBean>();

		MyFLightsDao myFlightsDao = new MyFLightsDao();
		try {
			// Logic Here!
			flightsList = myFlightsDao.retrieveFlownFlights(userId);
			
			String departureDate;
			String departureTime;
			String arrivalDate;
			String arrivalTime;
			
			String departure;
			String arrival;
			
			//format date
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date dateobj = new Date();
			String dateToday = df.format(dateobj);
			
			FlightBean flightBean = null;
			
			//check if flights of user is flown, future or current
			for(BookBean myList : flightsList){
				departureDate = myList.getFlightBean().getDepartureDate();
				departureTime = myList.getFlightBean().getDepartureTime();
				arrivalDate = myList.getFlightBean().getArrivalDate();
				arrivalTime = myList.getFlightBean().getArrivalTime();
				
				departure = departureDate + " " + departureTime;
				arrival = arrivalDate + " " + arrivalTime;

                flightBean = myList.getFlightBean();
                
				 if (dateToday.compareTo(arrival) > 0) {
		                log.info(dateToday + " today is after arrival " + arrival);
		                flightBean.setFlightStatus("Flown");
		                myList.setFlightBean(flightBean);
		                flownFlightsList.add(myList);
		            } else if (dateToday.compareTo(departure) > 0 && dateToday.compareTo(arrival) < 0) {
		            	log.info(dateToday + " today is after departure " + departure + " today is before arrival " + arrival);
		                flightBean.setFlightStatus("Current");
		                myList.setFlightBean(flightBean);
		                currentFlightsList.add(myList);
		            } else if (dateToday.compareTo(departure) < 0) {
		            	log.info(dateToday + " today is before departure " + departure);
		                flightBean.setFlightStatus("Future");
		                myList.setFlightBean(flightBean);
		                futureFlightsList.add(myList);
		            }
			}
			
			myFlights.put("flownFlights", flownFlightsList);
			myFlights.put("currentFlights", currentFlightsList);
			myFlights.put("futureFlights", futureFlightsList);
			myFlights.put("myAllFLights", flightsList);
		} catch (ConnectionException e) {
			throw e;
		} catch (SystemException e) {
			throw e;
		} catch (Exception e) {
			log.error("There was an unknown error while processing sample list. " + e);
			throw new BusinessException(e);
		}

		log.debug("end");
		return myFlights;
	}
}
