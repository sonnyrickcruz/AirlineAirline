package com.airline.manager;

import java.util.List;

import org.apache.log4j.Logger;

import com.airline.bean.BaggageBean;
import com.airline.bean.InsuranceBean;
import com.airline.bean.MealBean;
import com.airline.bean.TicketBean;
import com.airline.dao.AddonsDao;
import com.airline.exception.ConnectionException;
import com.airline.exception.SystemException;

public class AddonsManager {
	private Logger log = Logger.getLogger(this.getClass());
	public List<MealBean> retrieveMealList() throws ConnectionException, SystemException {
		AddonsDao addonsDao = new AddonsDao();
		List<MealBean> mealList = null;

		try {
			mealList = addonsDao.retrieveMealList();
		} catch (ConnectionException e) {
			throw e;
		} catch (SystemException e) {
			throw e;
		} catch (Exception e) {
			log.error("Unknown error in " + this.getClass());
			throw e;
		}

		return mealList;

	}
	
	public List<InsuranceBean> retrieveInsuranceList() throws ConnectionException, SystemException {
		AddonsDao addonsDao = new AddonsDao();
		List<InsuranceBean> insuranceList = null;

		try {
			insuranceList = addonsDao.retrieveInsuranceList();
		} catch (ConnectionException e) {
			throw e;
		} catch (SystemException e) {
			throw e;
		} catch (Exception e) {
			log.error("Unknown error in " + this.getClass());
			throw e;
		}

		return insuranceList;

	}
	
	public List<BaggageBean> retrieveBaggageList() throws ConnectionException, SystemException {
		AddonsDao addonsDao = new AddonsDao();
		List<BaggageBean> baggageList = null;

		try {
			baggageList = addonsDao.retrieveBaggageList();
		} catch (ConnectionException e) {
			throw e;
		} catch (SystemException e) {
			throw e;
		} catch (Exception e) {
			log.error("Unknown error in " + this.getClass());
			throw e;
		}

		return baggageList;

	}
	

	
}
