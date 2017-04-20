package com.airline.manager;

import org.apache.log4j.Logger;

import com.airline.bean.UserBean;
import com.airline.dao.LoginDao;
import com.airline.exception.ConnectionException;
import com.airline.exception.SystemException;

public class LoginManager {
	private Logger log = Logger.getLogger(this.getClass());
	/**
	 * 
	 * @param username
	 * @param password
	 * @return userInfo
	 * @throws ConnectionException
	 * @throws SystemException
	 */
	public UserBean retrieveUserInfo(String username, String password) throws ConnectionException, SystemException {
		LoginDao loginDao = new LoginDao();
		UserBean userInfo = null;

		try {
			userInfo = loginDao.retrieveUserInfo(username, password);
		} catch (ConnectionException e) {
			throw e;
		} catch (SystemException e) {
			throw e;
		} catch (Exception e) {
			log.error("Unknown error in " + this.getClass());
			throw e;
		}

		return userInfo;

	}
}
