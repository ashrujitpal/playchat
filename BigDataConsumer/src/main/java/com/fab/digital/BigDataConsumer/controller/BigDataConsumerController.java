package com.fab.digital.BigDataConsumer.controller;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fab.digital.BigDataConsumer.model.CustomerDetailsRequest;
import com.fab.digital.BigDataConsumer.model.CustomerDetailsResponse;
import com.fab.digital.BigDataConsumer.model.Offer;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
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
	
	/*//Commented by George on 17-Oct-2018
	@PostMapping("/getoffers")
	public CustomerDetailsResponse getOffersFromBigData(RequestEntity<CustomerDetailsRequest> request) {		
		
		System.out.println(request);
		
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
	*/
	
	@PostMapping("/getoffers")
	public CustomerDetailsResponse getOffersFromBigData(RequestEntity<Object> request) {
		
		System.out.println("Request body===================");
		System.out.println(request.getBody());
		
	
		System.out.println("==========Setting the request=============" );
		
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);	
		header.add("x-api-key", "5c9u3q3523");
		header.add("Proxy-Authorization", "Basic NTgyMzA6T2N0Z2VvQDIwMTg=");
		
		SimpleClientHttpRequestFactory clientHTTPReq = new SimpleClientHttpRequestFactory();
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.17.17.15", 8080));
		clientHTTPReq.setProxy(proxy);
		
				
		String kafkaProducerURL = "https://1mqrm0O6wb.execute-api.us-west-1.amazonaws.com";
		
		kafkaProducerURL += "";
		ResponseEntity<String> response = null;
		
		System.out.println("==========Setting the request=============");
		
		//HttpEntity<CustomerDetailsRequest> request1 = new HttpEntity<CustomerDetailsRequest>((CustomerDetailsRequest) request.getBody(),header);
		
		HttpEntity<Object> request1 = new HttpEntity<Object>(request.getBody(),header);
		
		System.out.println("==========Generated the request=============" + request1.getBody().toString());
		 
		
		try {
			
			System.out.println("==========Sending the request  =============");
			URI uri = new URI(kafkaProducerURL + "/integratedev/IntegrateFunction");
			
			System.out.println("==========Sending the request to =============");
			System.out.println( uri.toString());
			
			restTemplate = new RestTemplate(clientHTTPReq);
			response = restTemplate.exchange(new URI(kafkaProducerURL + "/integratedev/IntegrateFunction"), HttpMethod.POST, request1, String.class);
			
			System.out.println("==========Response Received =============");
			System.out.println(response.toString());
			
		} catch (Exception e) {
			
			System.out.println("==========Response Received With Exception=============");
			System.out.println(e.toString());
			//throw new RuntimeException();
		}	
		
		CustomerDetailsResponse detailsResponse = new CustomerDetailsResponse();
		JacksonJsonParser parser = new JacksonJsonParser();
		
		List<Object> ofr =  parser.parseList(response.getBody());
		
		detailsResponse.setOffers(ofr); //setAcknowledgement(response.getBody());				
		
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
		
		List<Object> offerList = new ArrayList<Object>();
		
		offerList.add(offer1);
		offerList.add(offer2);
		offerList.add(offer3);
		
		detailsResponse.setOffers(offerList);
		
		
		return detailsResponse;
	}
	
 
    /*@RequestMapping("/hello")
    public String hello() { return "Hello AppEngine Flex"; }*/
}