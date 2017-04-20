package com.airline.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.airline.bean.AirportBean;
import com.airline.constant.AirplaneSqlConstants;
import com.airline.constant.AirportSqlConstants;
import com.airline.exception.ConnectionException;
import com.airline.exception.SystemException;

public class AirportDao extends BaseDao {
	private Logger log = Logger.getLogger(this.getClass());
	/**
	 * This method is used to get the list of all the airports.
	 * @return airportList
	 * @throws SQLException
	 * @throws SystemException
	 * @throws ConnectionException
	 */
	public List<String> getAirportList(String term) throws SQLException, SystemException, ConnectionException{
		log.debug("START - getAirportList");
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<String> airportList = new ArrayList<String>();
		
		try {
			log.debug("Connecting to DB..");
			conn = getConnection();
			ps = conn.prepareStatement(AirportSqlConstants.RETRIEVE_AIRPORT_ORIGIN);
			ps.setString(1, "%"+term+"%");
			rs = ps.executeQuery();
			
			while(rs.next()){
				airportList.add(rs.getString("locations"));
				log.debug("New object added to the list.");
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
		} finally{
			closeResources(conn,ps,rs);
		}
		log.debug("END - getAirportList");
		return airportList;
	}
	
	public List<String> getAirportListWithoutOrigin(String origin, String term) throws SQLException, SystemException, ConnectionException{
		log.debug("START - getAirportList");
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<String> airportList = new ArrayList<String>();
		
		try {
			log.debug("Connecting to DB..");
			conn = getConnection();
			ps = conn.prepareStatement(AirportSqlConstants.RETRIEVE_AIRPORT_DESTINATION);
			ps.setString(1, origin);
			ps.setString(2, "%"+term+"%");
			rs = ps.executeQuery();
			
			while(rs.next()){
				airportList.add(rs.getString("locations"));
				log.debug("New object added to the list.");
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
		} finally{
			closeResources(conn,ps,rs);
		}
		log.debug("END - getAirportList");
		return airportList;
	}
}
