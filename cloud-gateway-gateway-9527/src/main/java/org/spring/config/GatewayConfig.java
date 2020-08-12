package org.spring.config;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator createRoutes(RouteLocatorBuilder builder){
        // route(路由的id,没有固定要求是唯一的,断言url)
        return builder.routes().route("route",
                r -> r.path("/v1/payments/**")
                        .uri("lb://cloud-provider-service"))
                .build();

    }
}
