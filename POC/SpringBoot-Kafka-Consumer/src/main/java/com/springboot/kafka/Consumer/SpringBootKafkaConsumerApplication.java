package com.springboot.kafka.Consumer;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.client.RestTemplate;
import com.springboot.kafka.Consumer.models.ConfigProperties;
import com.springboot.kafka.Consumer.models.BigDataRequest;
import com.springboot.kafka.Consumer.models.JsonConfiguration;
import com.springboot.kafka.Consumer.models.Sender;

@SpringBootApplication
@EnableKafka
@Import({ JsonConfiguration.class, ConfigProperties.class })
public class SpringBootKafkaConsumerApplication {
	
	public static void main(String[] args) throws BeansException, InterruptedException {
		
		SpringApplication.run(SpringBootKafkaConsumerApplication.class, args);
		
	}
	
	@Bean
	public Listener listener() {
		return new Listener();
	}
	
	public static class Listener {		
		
		@Autowired
		private Sender sender;
		
		@Autowired
		private ConfigProperties configProperties;
		
		@Autowired
		private RestTemplate restTemplate;		
		
		@KafkaListener(topics = "bigDataRequestTopic")
		public void listen(BigDataRequest object) {
			
			System.out.println("Received: Value: " + object);		
			
			try {
				
				Map<String, Object> jsonValues = new HashMap<String, Object>();
			    jsonValues.put("ReferenceId", object.getRefId());
			    jsonValues.put("ErrorCode", "1245");
			    jsonValues.put("Error_Desc" , "Success");			    
			    Object objectreturn=jsonValues;			    
				sender.sendMessage(this.configProperties.getResponseTopic(),objectreturn);				
				String msg=	restTemplate.getForObject(this.configProperties.getRestURL(),String.class);
				System.out.println("In Consumer second msg : "+msg);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
	
	
}
