package com.npst.api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudGatewayRouting {

    @Bean
    public RouteLocator configureRoute(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("testId", r->r.path("/test/**").uri("lb://client-app")) //static routing
                .route("dummyId", r->r.path("/dummy/**").uri("lb://dummy-clinet")) //dynamic routing
                .build();
    }
}