package com.airline.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;

import org.apache.log4j.Logger;

import com.airline.bean.UserBean;
import com.airline.constant.SqlConstants;
import com.airline.exception.ConnectionException;
import com.airline.exception.SystemException;

public class LoginDao extends BaseDao{
	private Logger log = Logger.getLogger(this.getClass());
	public UserBean retrieveUserInfo(String username,String password) throws ConnectionException, SystemException {
		log.debug("start - Username: " + username );

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		UserBean userInfo = null;
		
		try {
			log.debug("Trying to get user information");
			userInfo = new UserBean();
			conn = getConnection();
			ps = conn.prepareStatement(SqlConstants.LOGIN_QUERY);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();

			while (rs.next()) {
				userInfo.setFirstName(rs.getString("first_name"));
				userInfo.setLastName(rs.getString("last_name"));
				userInfo.setUsername(rs.getString("username"));
				userInfo.setType(rs.getString("role"));
				userInfo.setId(rs.getString("userId"));
			}

		} catch (ConnectionException e) {
			throw e;
		} catch (SQLTimeoutException e) {
			log.error("There was an SQLTimeoutException while getting some list. " + e);
			throw new SystemException(e);
		} catch (SQLException e) {
			log.error("There was an SQLException while getting some list. " + e);
			throw new SystemException(e);
		} catch (Exception e) {
			log.error("There was an unknown exception while getting some list. " + e);
			throw new SystemException(e);
		} finally {
			closeResources(conn, ps, rs);
		}

		log.debug("end");
		return userInfo;
	}
}
