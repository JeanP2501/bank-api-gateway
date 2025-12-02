package com.bank.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * API Gateway Application.
 * Main entry point for all microservices
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

    /**
     * Main method to start API Gateway.
     * @param args command line arguments
     */
	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

}
