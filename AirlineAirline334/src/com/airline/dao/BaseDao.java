package com.airline.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.airline.constant.SqlConstants;
import com.airline.exception.BusinessException;
import com.airline.exception.ConnectionException;
import com.airline.exception.SystemException;

/**
 * This class is where the connection to the database occurs and also the
 * closing of the connection
 */
public abstract class BaseDao {
	private Logger log = Logger.getLogger(BaseDao.class);

	/**
	 * Connection to the database
	 * 
	 * @throws ConnectionException
	 */
	protected Connection getConnection() throws ConnectionException {
		log.debug("start");
		Connection connection = null;

		try {
			Context context = new InitialContext();
			DataSource datasource = (DataSource) context.lookup(SqlConstants.DB_DATASOURCE);
			connection = datasource.getConnection();
		} catch (NamingException e) {
			log.error("Naming Exception was thrown by the context while connecting to the database pool. " + e);
			throw new ConnectionException(e);
		} catch (SQLException e) {
			log.error("SQL Exception was thrown by the getConnection while connectiong to the database pool. " + e);
			throw new ConnectionException(e);
		} catch (Exception e) {
			log.error("There was an unknown exception happened while connecting to the database. " + e);
			throw new ConnectionException(e);
		}

		log.debug("end");
		return connection;
	}

	/**
	 * Closing the database pool connection
	 * 
	 * @param conn
	 *            - Connection
	 * @param statement
	 *            - Statement
	 * @param rs
	 *            - ResultSet
	 * @throws BusinessException
	 * @throws SystemException
	 */
	protected void closeResources(Connection conn, Statement statement, ResultSet rs) throws SystemException {
		log.debug("start");

		try {

			if (conn != null) {
				conn.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (rs != null) {
				rs.close();
			}

		} catch (SQLException e) {
			log.error("SQL Exception happened while closing the connection through the database. " + e);
			throw new SystemException(e);
		} catch (Exception e) {
			log.error("There was an unknown exception happened while closing the connection. " + e);
			throw new SystemException(e);
		}

		log.debug("end");
	}
}
