package io.getarrays.start_up_admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
//@EnableEurekaClient
public class StartUpAdminApplication{

//	@Bean
//	public RestTemplate restTemplate(){
//		return new RestTemplate();
//	}


	public static void main(String[] args) {
		SpringApplication.run(StartUpAdminApplication.class, args);
	}


}

