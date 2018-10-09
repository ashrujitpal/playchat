package com.fab.digital.BigDataConsumer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/google-vision")
public class GoogleVisionServiceController {
	
	@GetMapping("/check/endpoint")
	public String checkEndpoint() {
		
		return "Check Done";
	}
	
	
	
		

}

