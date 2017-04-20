package com.airline.action;



import org.apache.log4j.Logger;

import com.airline.bean.UserBean;
import com.airline.exception.ConnectionException;
import com.airline.exception.SystemException;
import com.airline.manager.LoginManager;
import com.google.gson.Gson;

/**
 * 
 * @author emmanuel.portillo
 * This method is used for logging into the system.
 */
public class LoginAction extends BaseAction{
	private Logger log = Logger.getLogger(this.getClass());
	
	private String username;
	private String password;
	private String message;
	private String errorMessage;
	
	public String execute(){
		log.debug("START ACTION");
		log.debug("Username"+username+password);
		LoginManager loginManager = new LoginManager();
		UserBean userInfo = null;
		Gson gson = new Gson();	
			try {
				userInfo = loginManager.retrieveUserInfo(username, password);
				if(userInfo.getFirstName() != null){
					session.put("userInfo", userInfo);
					message = gson.toJson("The username or password is invalid. Please try again.");
				} else{
					//errorMessage = gson.toJson("The username or password is invalid. Please try again.");
					System.out.println(message);

					message = gson.toJson("The username or password is invalid. Please try again.");
				}
			} catch (ConnectionException e) {
				//redirect to error page
			} catch (SystemException e) {
				//redirect to error page
			}
		
		return success;	
	}
	
	public String executeLogin(){
		log.debug("START ACTION");
		log.debug("Username"+username+password);
		LoginManager loginManager = new LoginManager();
		UserBean userInfo = null;
		Gson gson = new Gson();	
		try {
			userInfo = loginManager.retrieveUserInfo(username, password);
			if(userInfo.getFirstName() != null){
				session.put("userInfo", userInfo);
				message = gson.toJson("success");
			} else{
				//errorMessage = gson.toJson("The username or password is invalid. Please try again.");
				message = gson.toJson("error");
			}
		} catch (ConnectionException e) {
			//redirect to error page
		} catch (SystemException e) {
			//redirect to error page
		}
		
		return success;	
	}
	
	
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}




	public String getErrorMessage() {
		return errorMessage;
	}




	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
