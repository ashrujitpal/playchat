package com.fab.digital.BigDataConsumer.model;

import java.util.List;

public class CustomerDetailsResponse {
	
	private String customerId;
	private List<Offer> offers;
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public List<Offer> getOffers() {
		return offers;
	}
	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}
	
}
