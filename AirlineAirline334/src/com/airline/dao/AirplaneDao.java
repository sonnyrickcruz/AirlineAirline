package com.airline.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.airline.bean.AirplaneBean;
import com.airline.constant.AirplaneSqlConstants;
import com.airline.exception.ConnectionException;
import com.airline.exception.SystemException;

public class AirplaneDao extends BaseDao {
	private Logger log = Logger.getLogger(this.getClass());
	/**
	 * This method is used to get the list of all the aircrafts
	 * @param routeId
	 * @return aircraftList
	 * @throws SystemException
	 * @throws ConnectionException
	 */
	public List<String> getAirplaneList(String routeId) throws SystemException, ConnectionException{
		log.debug("START - getAirplaneList");
		log.debug("Route ID: "+routeId);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<String> aircraftList = new ArrayList<String>();
		
		try{
			log.debug("Connecting to DB..");
			conn = getConnection();
			ps = conn.prepareStatement(AirplaneSqlConstants.RETRIEVE_AIRCRAFT_ID);
			ps.setString(1, routeId);
			rs = ps.executeQuery();
			while(rs.next()){
				aircraftList.add(rs.getString("aircraftInfo"));
				log.debug("New item added in the list");
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
		}finally{
			closeResources(conn,ps,rs);
		}
		log.debug("END - getAirplaneList");
		return aircraftList;
	}
}
