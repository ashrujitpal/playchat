package com.fab.digital.BigDataConsumer.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fab.digital.BigDataConsumer.model.CustomerDetailsRequest;
import com.fab.digital.BigDataConsumer.model.CustomerDetailsResponse;
import com.fab.digital.BigDataConsumer.model.Offer;
import java.net.URI;

@RestController
@RequestMapping("/bigdata")
//@PropertySource("external-config.properties")
public class BigDataConsumerController {
	
	@Autowired
    RestTemplate restTemplate;
	
	
	@GetMapping("/check/endpoint")
	public String checkEndpoint() {
		
		return "Check Done";
	}
	
	
	@PostMapping("/getoffers")
	public CustomerDetailsResponse getOffersFromBigData(RequestEntity<CustomerDetailsRequest> request) {		
		
		CustomerDetailsResponse detailsResponse = new CustomerDetailsResponse();
		
		String kafkaProducerURL = "http://ec2-18-235-150-53.compute-1.amazonaws.com:8077";
		
		kafkaProducerURL = kafkaProducerURL + "";
		ResponseEntity<String> response = null;
		
		HttpEntity<CustomerDetailsRequest> request1 = new HttpEntity<CustomerDetailsRequest>(request.getBody());
		
		try {
			response = restTemplate.exchange(new URI(kafkaProducerURL + "/kafka/kafkaProducer"), 
					HttpMethod.POST, request1, String.class);
		} catch (Exception e) {
			
			//throw new RuntimeException();
		}	
		
		detailsResponse.setAcknowledgement(response.getBody());				
		
		return detailsResponse;
	}
	
	@PostMapping("/getdummyoffers")
	public CustomerDetailsResponse getDummyOffersFromBigData(RequestEntity<CustomerDetailsRequest> request) {		
		
		CustomerDetailsResponse detailsResponse = new CustomerDetailsResponse();
		
		
		detailsResponse.setCustomerId("cust12345");
		
		Offer offer1 = new Offer();
		Offer offer2 = new Offer();
		Offer offer3 = new Offer();
		
		offer1.setOffername("Pizza Hut");
		offer1.setOfferDescription("Get 20% discount on Medium or Large Pizza on Gold card from FAB");
		
		offer2.setOffername("Dominos");
		offer2.setOfferDescription("Get 20% discount on Medium or Large Pizza on Gold card from FAB");
		
		offer3.setOffername("Malabar Gold");
		offer3.setOfferDescription("Get 20% discount on Making charge on the FAB debit or credit Card upon purchase of 1000 AED and above");
		
		List<Offer> offerList = new ArrayList<Offer>();
		
		offerList.add(offer1);
		offerList.add(offer2);
		offerList.add(offer3);
		
		detailsResponse.setOffers(offerList);
		
		
		return detailsResponse;
	}
	
 
    /*@RequestMapping("/hello")
    public String hello() { return "Hello AppEngine Flex"; }*/
}