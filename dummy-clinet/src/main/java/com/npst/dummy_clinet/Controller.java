package com.npst.dummy_clinet;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dummy/")
public class Controller {


    @Value("${eureka.client.service-url.defaultZone}")
    private String eurekaIp;

    @GetMapping("/say")
    public String getMethodName() {
        return new String("HII I am from Dummy MS eureka ip :"+eurekaIp);
    }
}
