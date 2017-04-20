package com.airline.manager;

import java.util.List;

import org.apache.log4j.Logger;

import com.airline.bean.RouteBean;
import com.airline.dao.RouteDao;
import com.airline.exception.BusinessException;
import com.airline.exception.ConnectionException;
import com.airline.exception.SystemException;

public class RouteManager {
	private Logger log = Logger.getLogger(this.getClass());

	public RouteBean retrieveRouteId(String originId, String destinationId)
			throws ConnectionException, SystemException, BusinessException {
		log.debug("START - retrieveRouteId");
		RouteBean route = null;
		RouteDao routeDao = new RouteDao();
		try {
			route = routeDao.getRouteId(originId, destinationId);
		} catch (SystemException | ConnectionException e) {
			throw e;
		} catch (Exception e) {
			log.error("There was an unknown error while processing sample list. " + e);
			throw new BusinessException(e);
		}
		log.debug("END - retrieveRouteId");
		return route;
	}

	public List<RouteBean> processRoutes() throws BusinessException, SystemException, ConnectionException {
		log.debug("START - processRoutes");
		List<RouteBean> route;
		RouteDao routeDao = new RouteDao();
		try {
			route = routeDao.getRoutes();
		} catch (SystemException | ConnectionException e) {
			throw e;
		} catch (Exception e) {
			log.error("There was an unknown error while processing sample list. " + e);
			throw new BusinessException(e);
		}
		log.debug("END - processRoutes");
		return route;
	}
}
