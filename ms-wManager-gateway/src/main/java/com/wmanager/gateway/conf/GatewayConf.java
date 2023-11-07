package com.wmanager.gateway.conf;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConf {
	
	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("auth", a -> a
						.path("/auth/**")
						.uri("http://127.0.0.1:8081/auth/")
						)
				.build();
	}

}
