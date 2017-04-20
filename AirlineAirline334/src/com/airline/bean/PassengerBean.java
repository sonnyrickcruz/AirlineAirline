package com.airline.bean;

public class PassengerBean {
	private String prefix;
	private String passengerId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String birthday;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(String passengerId) {
		this.passengerId = passengerId;
	}

	@Override
	public String toString() {
		return "PassengerBean [prefix=" + prefix + ", passengerId=" + passengerId + ", firstName=" + firstName
				+ ", middleName=" + middleName + ", lastName=" + lastName + ", birthday=" + birthday + "]";
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

}
