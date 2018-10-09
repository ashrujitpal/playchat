package com.springboot.kafka.Consumer.models;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "kafka")
public class ConfigProperties {

	private String brokerAddress;

	private String responseTopic;
	
	public String getResponseTopic() {
		return responseTopic;
	}

	public void setResponseTopic(String responseTopic) {
		this.responseTopic = responseTopic;
	}

	private String restURL;
	
	public String getRestURL() {
		return restURL;
	}

	public void setRestURL(String restURL) {
		this.restURL = restURL;
	}

	public String getBrokerAddress() {
		return this.brokerAddress;
	}

	public void setBrokerAddress(String brokerAddress) {
		this.brokerAddress = brokerAddress;
	}

	

}
