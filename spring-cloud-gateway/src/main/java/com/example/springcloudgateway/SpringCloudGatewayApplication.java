package com.example.springcloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.factory.RequestRateLimiterGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class SpringCloudGatewayApplication {

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {

        return builder.routes()
                      .route(r -> r.path("/get")
                                   .filters(f -> f.addRequestHeader("X-Spring-One", "Awesome Event!"))
                                   .uri("http://httpbin.org:80"))
                      .route(r -> r.host("*.myhost.org")
                                   .filters(f -> f.addRequestHeader("You've-got", "Another Spam email"))
                                   .uri("http://httpbin.org:80"))
                      .route(r -> r.host("*.rewrite.org")
                                   .filters(f -> f.rewritePath("/foo/(?<segment>.*)", "/${segment}"))
                                   .uri("http://httpbin.org:80"))
                      .route(r -> r.path("/locate")
                                   .uri("lb://vehicle-location-service"))
                      .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudGatewayApplication.class, args);
    }
}
