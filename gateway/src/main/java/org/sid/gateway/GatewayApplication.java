package org.sid.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	/* @Bean  CONFIGURATION STATIQUE
	RouteLocator routeLocator(RouteLocatorBuilder builder){
		return builder.routes()
				.route("r1", r -> r.path("/customers/**").uri("lb://CUSTOMER-SERVICE"))//ici je suis pas obliger de connaire toute uri jai besoin sauf de nom de MicroService car il est deja enregistrer dan Discovery EUREKA
				.route("r2", r -> r.path("/products/**").uri("lb://INVENTORY-SERVICE"))
				.build();
	}*/

	//CONFIGURATION DYNAMIQUE
	// je dis a Gitway  que je connais pas les microService et chaque fois tu recois une rqt regarde url et prendre nom de micro service  et router la rqt vers bon microservices
	@Bean
	DiscoveryClientRouteDefinitionLocator definitionLocator(
			ReactiveDiscoveryClient rdc, DiscoveryLocatorProperties properties){
		return new  DiscoveryClientRouteDefinitionLocator(rdc,properties);
	}
}
