package com.fab.digital.BigDataConsumer.controller;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fab.digital.BigDataConsumer.model.CustomerDetailsRequest;

@RestController
@RequestMapping("/bigdata")
public class BigDataConsumerController {
	
	@GetMapping("/check/endpoint")
	public String checkEndpoint() {
		
		return "Check Done";
	}
	
	@GetMapping("/getoffers")
	public String getOffersFromBigData(RequestEntity<CustomerDetailsRequest> request) {
		
		
		
		return "Check Done";
	}
 
    /*@RequestMapping("/hello")
    public String hello() { return "Hello AppEngine Flex"; }*/
}