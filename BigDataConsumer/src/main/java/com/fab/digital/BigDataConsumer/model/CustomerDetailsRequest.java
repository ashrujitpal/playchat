package com.fab.digital.BigDataConsumer.model;

import java.util.List;

public class CustomerDetailsRequest {
	
	private List<Customer> Customer;
	
	public List<Customer> getCustomer() {
		return Customer;
	}
	
	public void setCustomer(List<Customer> customer) {
		this.Customer = customer;
		
	} 

}
