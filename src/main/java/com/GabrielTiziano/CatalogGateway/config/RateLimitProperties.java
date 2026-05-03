package com.GabrielTiziano.CatalogGateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "catalog.rate-limit")
public record RateLimitProperties(
        boolean enabled,
        Tier anonymous,
        Tier authEndpoints,
        Tier customer,
        Tier manager,
        Tier admin
) {
    public record Tier(
            int capacity,
            int refillTokens,
            int refillPeriodSeconds
    ) {}
}
