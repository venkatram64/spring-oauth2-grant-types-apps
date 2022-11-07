package com.venkat.gw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MySpringGatewayAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySpringGatewayAppApplication.class, args);
	}

}
