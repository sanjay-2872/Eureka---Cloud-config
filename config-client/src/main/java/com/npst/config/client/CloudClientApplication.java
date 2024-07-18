package com.npst.config.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableDiscoveryClient
public class CloudClientApplication {

	private static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		context = SpringApplication.run(CloudClientApplication.class, args);
	}

	public static void restart(String profile) {

		Thread thread = new Thread(() -> {
			context.close();
			context = SpringApplication.run(CloudClientApplication.class, "--spring.profiles.active="+profile);
		});

		thread.setDaemon(false);
		thread.start();
	}

}
