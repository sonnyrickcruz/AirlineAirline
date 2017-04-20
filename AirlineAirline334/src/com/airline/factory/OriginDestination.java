package com.airline.factory;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.airline.bean.AirportBean;
import com.airline.bean.RouteBean;
import com.airline.exception.BusinessException;
import com.airline.exception.ConnectionException;
import com.airline.exception.SystemException;
import com.airline.manager.RouteManager;

public class OriginDestination {
	private Logger log = Logger.getLogger(this.getClass());
	private static OriginDestination instance = new OriginDestination();
	private List<RouteBean> routeListCache;
	private Map<String, List<String>> originMapCache;
	private Map<String, List<String>> destinationMapCache;
	private Date lastUpdate;

	private OriginDestination() {
	}

	public static synchronized OriginDestination getInstance() {
		return instance;
	}

	private boolean isEmpty() {
		if (routeListCache != null && !routeListCache.isEmpty()) {
			return false;
		}
		return true;
	}

	private boolean isUpdated() {
		if (lastUpdate != null && lastUpdate.equals(new Date())) {
			return true;
		}
		return false;
	}

	private void recreate() {
		log.debug("start - recreate");
		RouteManager routeManager = new RouteManager();
		try {
			routeListCache = routeManager.processRoutes();
			lastUpdate = new Date();
		} catch (BusinessException | SystemException | ConnectionException e) {
			log.error(e);
		}
		log.debug("end - recreate " + routeListCache);
	}

	private void setOrigins() {
		if (isEmpty() && !isUpdated()) {
			recreate();
		}
		try {
			originMapCache = new HashMap<>();
			String originKey;
			String destinationString;
			List<String> origins;
			for (RouteBean routeBean : routeListCache) {
				originKey = generateKey(routeBean.getOrigin());
				destinationString = generateKey(routeBean.getDestination());
				if (originMapCache.containsKey(originKey)) {
					origins = originMapCache.get(originKey);
				} else {
					origins = new ArrayList<>();
				}
				origins.add(destinationString);
				originMapCache.put(originKey, origins);
			}
		} catch (Exception e) {
			log.error(e);
		}
	}

	private void setDestinations() {
		if (isEmpty() || !isUpdated()) {
			recreate();
		}
		try {
			destinationMapCache = new HashMap<>();
			String destinationKey;
			String originString;
			List<String> destinations;
			for (RouteBean routeBean : routeListCache) {
				destinationKey = generateKey(routeBean.getDestination());
				originString = generateKey(routeBean.getOrigin());
				if (destinationMapCache.containsKey(destinationKey)) {
					destinations = destinationMapCache.get(destinationKey);
				} else {
					destinations = new ArrayList<>();
				}
				destinations.add(originString);
				destinationMapCache.put(destinationKey, destinations);
			}
		} catch (Exception e) {
			log.error(e);
		}
	}

	private String generateKey(AirportBean airport) {
		String result = null;
		if (airport != null) {
			result = "(" + airport.getAirportId() + ")" + " " + airport.getLocation();
		}
		return result;
	}

	public List<String> getOrigins() {
		List<String> origins = new ArrayList<>();
		try {
			if (!(originMapCache != null && !originMapCache.isEmpty())) {
				setOrigins();
			}
			origins =  new ArrayList<>(originMapCache.keySet());
		} catch (Exception e) {
			log.error(e);
		}
		return origins;
	}

	public List<String> getDestinations() {
		List<String> destinations = new ArrayList<>();
		try {
			if (!(destinationMapCache != null && !destinationMapCache.isEmpty())) {
				setDestinations();
			}
			destinations = new ArrayList<>(destinationMapCache.keySet());
		} catch (Exception e) {
			log.error(e);
		}
		return destinations;
	}
	
	public List<RouteBean> getRoutes() {
		if (isEmpty() || !isUpdated()) {
			recreate();
		}
		
		return routeListCache;
	}
}
