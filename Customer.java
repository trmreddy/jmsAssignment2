package com.ram.pubsub.assignment;

import java.io.Serializable;

public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;
	private long cardNumber;
	private String customerName;
	private String locationUsed;
	public long getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getLocationUsed() {
		return locationUsed;
	}
	public void setLocationUsed(String locationUsed) {
		this.locationUsed = locationUsed;
	}
}
