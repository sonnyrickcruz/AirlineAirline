package com.airline.manager;

import java.util.List;

import org.apache.log4j.Logger;

import com.airline.bean.AirportBean;
import com.airline.dao.AirplaneDao;
import com.airline.dao.AirportDao;
import com.airline.exception.BusinessException;
import com.airline.exception.ConnectionException;
import com.airline.exception.SystemException;

public class AirportManager {
	private Logger log = Logger.getLogger(this.getClass());
	public List<String> retrieveAirportList(String term) throws SystemException, BusinessException, ConnectionException{
		log.debug("START - retrieveAirportList");
		List<String> airportList = null;
		System.out.println(term);
		AirportDao airportDao = new AirportDao();
		try {
			airportList = airportDao.getAirportList(term);
		} catch (ConnectionException e) {
			throw e;
		} catch (SystemException e) {
			throw e;
		} catch (Exception e) {
			log.error("There was an unknown error while processing sample list. " + e);
			throw new BusinessException(e);
		}
		log.debug("END - retrieveAirportList");
		return airportList;
	}
	
	public List<String> retrieveAirportListWithoutOrigin(String origin, String term) throws SystemException, BusinessException, ConnectionException{
		log.debug("START - retrieveAirportList");
		List<String> airportList = null;
		System.out.println(term);
		AirportDao airportDao = new AirportDao();
		try {
			airportList = airportDao.getAirportListWithoutOrigin(origin, term);
		} catch (ConnectionException e) {
			throw e;
		} catch (SystemException e) {
			throw e;
		} catch (Exception e) {
			log.error("There was an unknown error while processing sample list. " + e);
			throw new BusinessException(e);
		}
		log.debug("END - retrieveAirportList");
		return airportList;
	}
}
