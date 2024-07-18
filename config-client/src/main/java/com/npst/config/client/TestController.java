package com.npst.config.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Arrays;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private Environment env;

	@Autowired
	private TestConfig testConfig;
	
	@GetMapping("properties/status")
    public String props() {
		String prop = env.getProperty("apple");
        return "Using Environment.getProperty() : "+prop + " ,Using @Value annotation : " + testConfig.getProp1();

    }

	@GetMapping("Change/profile/{profileName}")
	public String setNewProfile(@PathVariable String profileName) {
		System.out.println(Arrays.toString(env.getActiveProfiles()));
		CloudClientApplication.restart(profileName);
		return "Success";
	}


	@Value("${server.port}")
	private String serverPort;

	@GetMapping("/say")
	public String getMethodName() {
		return new String("HII I am from Payment MS "+serverPort);
	}


}
