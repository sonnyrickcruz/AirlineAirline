package com.airline.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.airline.constant.SqlConstants;
import com.airline.exception.ConnectionException;
import com.airline.exception.SystemException;

public class DaoTemplate extends BaseDao {
	private Logger log = Logger.getLogger(this.getClass());

	/**
	 * This is a sample update DAO
	 * 
	 * @param toUpdate
	 * @return resultCount
	 * @throws ConnectionException
	 * @throws SystemException
	 */
	public int updateSample(String toUpdate) throws ConnectionException, SystemException {
		log.debug("start - toUpdate: " + toUpdate);

		Connection conn = null;
		PreparedStatement ps = null;
		int resultCount = 0;

		try {
			conn = getConnection();
			ps = conn.prepareStatement("");
			ps.setString(1, toUpdate);
			resultCount = ps.executeUpdate();
		} catch (ConnectionException e) {
			throw e;
		} catch (SQLTimeoutException e) {
			log.error("There was an SQLTimeoutException while updating some table. " + e);
			throw new SystemException(e);
		} catch (SQLException e) {
			log.error("There was an SQLException while updating some table. " + e);
			throw new SystemException(e);
		} catch (Exception e) {
			log.error("There was an unknown exception while updating some table. " + e);
			throw new SystemException(e);
		} finally {
			closeResources(conn, ps, null);
		}

		log.debug("end - resultCount " + resultCount);
		return resultCount;
	}

	/**
	 * This is a sample of retrieving
	 * 
	 * @param sampleParam
	 * @return
	 * @throws ConnectionException
	 * @throws SystemException
	 */
	public List<String> retrieveSample(String sampleParam) throws ConnectionException, SystemException {
		log.debug("start - sampleParam: " + sampleParam);
		List<String> sampleList = null;

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			sampleList = new ArrayList<String>();
			conn = getConnection();
			ps = conn.prepareStatement(SqlConstants.SAMPLE_QUERY);
			ps.setString(1, sampleParam);
			rs = ps.executeQuery();

			while (rs.next()) {
				sampleList.add(rs.getString("seat_id"));
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
		return sampleList;
	}

}
