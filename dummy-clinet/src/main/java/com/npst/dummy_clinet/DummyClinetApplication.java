package com.npst.dummy_clinet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DummyClinetApplication {

	public static void main(String[] args) {
		SpringApplication.run(DummyClinetApplication.class, args);
	}

}
