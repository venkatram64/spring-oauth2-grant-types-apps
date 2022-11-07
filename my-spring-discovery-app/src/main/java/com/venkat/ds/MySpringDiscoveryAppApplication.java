package com.venkat.ds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MySpringDiscoveryAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySpringDiscoveryAppApplication.class, args);
	}

}
