package com.springboot.kafka.Producer.models;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class SenderConfig {
	
    @Bean
    public Properties producerConfigs() {
       
        Properties props = new Properties();
	      props.put("bootstrap.servers", "ec2-18-235-150-53.compute-1.amazonaws.com:9092");
	      props.put("acks", "all");
	      props.put("retries", 0);
	      props.put("batch.size", 16384);
	      props.put("linger.ms",0);
	      props.put("buffer.memory", 33554432);	  
	      props.put("key.serializer", 
	         "org.apache.kafka.common.serialization.StringSerializer");
	      props.put("value.serializer", JsonSerializer.class);
		System.out.println(" $$$$ In SenderConfig class producerConfigs()" );

        return props;
    }

    @Bean
    public Producer<String,BigDataRequest> producerFactory() {
		System.out.println(" $$$ in SenderConfig class    Producer()  ");
        return new KafkaProducer<String, BigDataRequest>(producerConfigs());
    }

    @Bean
    public Sender sender() {
		System.out.println(" $$$ in SenderConfig class    sender()  ");
        return new Sender();
    }

}
