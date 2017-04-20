package com.airline.manager;

import java.util.List;

import org.apache.log4j.Logger;

import com.airline.dao.DaoTemplate;
import com.airline.exception.BusinessException;
import com.airline.exception.ConnectionException;
import com.airline.exception.SystemException;

public class ManagerTemplate {
	private Logger log = Logger.getLogger(this.getClass());

	/**
	 * This is a sample manager
	 * 
	 * @param sampleParam
	 * @return sampleList
	 * @throws BusinessException
	 * @throws SystemException
	 * @throws ConnectionException
	 */
	public List<String> processSampleList(String sampleParam)
			throws BusinessException, SystemException, ConnectionException {
		log.debug("start");

		List<String> sampleList = null;

		DaoTemplate daoTemplate = new DaoTemplate();
		try {
			// Logic Here!
			sampleList = daoTemplate.retrieveSample(sampleParam);
		} catch (ConnectionException e) {
			throw e;
		} catch (SystemException e) {
			throw e;
		} catch (Exception e) {
			log.error("There was an unknown error while processing sample list. " + e);
			throw new BusinessException(e);
		}

		log.debug("end");
		return sampleList;
	}
}
