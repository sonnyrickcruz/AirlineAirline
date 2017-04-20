package com.airline.manager;

import java.util.List;

import org.apache.log4j.Logger;

import com.airline.bean.AirplaneBean;
import com.airline.dao.AirplaneDao;
import com.airline.exception.BusinessException;
import com.airline.exception.ConnectionException;
import com.airline.exception.SystemException;

public class AirplaneManager {
	private Logger log = Logger.getLogger(this.getClass());
	public List<String> retrieveAirplaneId(String routeId) throws SystemException, BusinessException, ConnectionException{
		log.debug("START - retrieveAirplaneId");
		log.debug("Route ID: "+routeId);
		List<String> airplaneList = null;
		
		AirplaneDao airplaneDao = new AirplaneDao();
		try {
			airplaneList = airplaneDao.getAirplaneList(routeId);
		} catch (ConnectionException e) {
			throw e;
		} catch (SystemException e) {
			throw e;
		} catch (Exception e) {
			log.error("There was an unknown error while processing sample list. " + e);
			throw new BusinessException(e);
		}
		log.debug("END - retrieveAirplaneId");
		return airplaneList;
	}
}
