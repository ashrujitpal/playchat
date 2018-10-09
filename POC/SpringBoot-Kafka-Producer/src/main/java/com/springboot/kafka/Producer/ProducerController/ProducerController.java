package com.springboot.kafka.Producer.ProducerController;

import javax.validation.Valid;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.kafka.Producer.models.ConfigProperties;
import com.springboot.kafka.Producer.models.BigDataRequest;
import com.springboot.kafka.Producer.models.JsonConfiguration;
import com.springboot.kafka.Producer.models.Sender;

@EnableKafka
@Import({ JsonConfiguration.class, ConfigProperties.class })
@RestController
@RequestMapping("/kafka")
public class ProducerController{

	@Autowired
	private Sender sender;
		
	@Autowired
	private ConfigProperties configProperties;
	
	@Autowired
	private ConcurrentKafkaListenerContainerFactory<String, Object> con;	
	
	ConsumerRecord<String,Object> objects = null;
	
	/* REQUEST */ 
	@RequestMapping("/kafkaProducer")
	public String kafkaProducer(@Valid @RequestBody BigDataRequest object) throws InterruptedException {
		System.out.println("*************************START***********************************************");
		System.out.println(" $$$$$ **REQUEST**  ProducerController class kafkaProducer()" );
		sender.sendMessage(this.configProperties.getTopic(),object);
		return "Successfully Sent";
	}
	

	/* RESPONSE from */ 

	@KafkaListener(topics = "bigDataResponseTopic")
	public void listen(ConsumerRecord<String,Object> object){		
		objects=object;
		System.out.println(" **RESPONSE** in ProducerController class    listen()  ");
		System.out.println("$$ Received: Value: " + object.value()+" Key : " + object.key()+" Offset : " + object.offset());
		System.out.println("***************************END*********************************************\n\n");
	}
	
	@RequestMapping("/kafkaProducerSuccess")
	public String kafkaSuccess(){	
		System.out.println(" **RESPONSE** in ProducerController class  kafkaSuccess()  ");
		listen(objects);
		return "Message Received Successfully !!!!!!!!!!!!!!!";
		
		
	}
	
}
