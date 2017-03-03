package com.airline.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.airline.bean.BaggageBean;
import com.airline.bean.InsuranceBean;
import com.airline.bean.MealBean;
import com.airline.constant.AddonsSqlConstant;
import com.airline.exception.ConnectionException;
import com.airline.exception.SystemException;

public class AddonsDao extends BaseDao{
	private Logger log = Logger.getLogger(this.getClass());
	public List<MealBean> retrieveMealList() throws ConnectionException, SystemException {
		log.debug("start");

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<MealBean> mealList = null;
		MealBean mealInfo = null;
		
		try {
			log.debug("Trying to get meal information");
			mealList = new ArrayList<MealBean>();
			conn = getConnection();
			ps = conn.prepareStatement(AddonsSqlConstant.MEAL_QUERY);
			rs = ps.executeQuery();

			while (rs.next()) {
				mealInfo = new MealBean();
				mealInfo.setMealId(rs.getString("mealId"));
				mealInfo.setName(rs.getString("mealName"));
				mealInfo.setDescription(rs.getString("mealDescription"));
				mealInfo.setPrice(rs.getDouble("mealPrice"));
				mealInfo.setImgURI(rs.getString("imageURI"));
				mealList.add(mealInfo);
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
		return mealList;
	}
	
	public List<InsuranceBean> retrieveInsuranceList() throws ConnectionException, SystemException {
		log.debug("start");

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<InsuranceBean> insuranceList = null;
		InsuranceBean insuranceInfo = null;
		
		try {
			log.debug("Trying to get insurance information");
			insuranceList = new ArrayList<InsuranceBean>();
			conn = getConnection();
			ps = conn.prepareStatement(AddonsSqlConstant.INSURANCE_QUERY);
			rs = ps.executeQuery();

			while (rs.next()) {
				insuranceInfo = new InsuranceBean();
				insuranceInfo.setInsuranceId(rs.getString("insuranceId"));
				insuranceInfo.setPrice(rs.getDouble("insurancePrice"));
				insuranceList.add(insuranceInfo);
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
		return insuranceList;
	}
	
	public List<BaggageBean> retrieveBaggageList() throws ConnectionException, SystemException {
		log.debug("start");

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<BaggageBean> baggageList = null;
		BaggageBean baggageInfo = null;
		
		try {
			log.debug("Trying to get insurance information");
			baggageList = new ArrayList<BaggageBean>();
			conn = getConnection();
			ps = conn.prepareStatement(AddonsSqlConstant.BAGGAGE_QUERY);
			rs = ps.executeQuery();

			while (rs.next()) {
				baggageInfo = new BaggageBean();
				baggageInfo.setBaggageId(rs.getString("baggageId"));
				baggageInfo.setPrice(rs.getDouble("baggagePrice"));
				baggageInfo.setKilograms(rs.getInt("baggageWeight"));
				baggageList.add(baggageInfo);
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
		return baggageList;
	}
}
