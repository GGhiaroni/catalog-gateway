package com.GabrielTiziano.CatalogGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.GabrielTiziano.CatalogGateway.config.RateLimitProperties;

@SpringBootApplication
@EnableConfigurationProperties(RateLimitProperties.class)
public class CatalogGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogGatewayApplication.class, args);
	}

}
