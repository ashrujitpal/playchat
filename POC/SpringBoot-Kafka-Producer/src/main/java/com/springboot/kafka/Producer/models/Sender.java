package com.springboot.kafka.Producer.models;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;

public class Sender {
	
	    @Autowired
	    private Producer<String,BigDataRequest> producer;    
	    
	    public void sendMessage(String topic,BigDataRequest object) throws InterruptedException { 
	    	
			 try {
	 	         producer.send(new ProducerRecord<String, BigDataRequest>(topic, object));
				 System.out.println(" $$$ Sender class Producer()  !!!");	
	 	         System.out.println("Message sent successfully !!!");	 	        
	      
				}
			 finally {
				producer.flush();
				//producer.close();
				}

		}
	 
}

