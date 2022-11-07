package com.venkat.ml.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MyMethodLevelSecureResourceServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyMethodLevelSecureResourceServerApplication.class, args);
	}

}
