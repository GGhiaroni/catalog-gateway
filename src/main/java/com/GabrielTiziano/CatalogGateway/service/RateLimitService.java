package com.GabrielTiziano.CatalogGateway.service;

import com.GabrielTiziano.CatalogGateway.config.RateLimitProperties;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.BucketConfiguration;
import io.github.bucket4j.distributed.BucketProxy;
import io.github.bucket4j.redis.lettuce.cas.LettuceBasedProxyManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;


@Service
@RequiredArgsConstructor
public class RateLimitService {
    private final LettuceBasedProxyManager<String> proxyManager;

    public BucketProxy resolveBucket(String key, RateLimitProperties.Tier tier){
        BucketConfiguration configuration = createConfiguration(tier);
        return proxyManager.builder().build(key, () -> configuration);
        
    }

    private BucketConfiguration createConfiguration(RateLimitProperties.Tier tier) {
        Bandwidth bandwidth = Bandwidth.builder()
                .capacity(tier.capacity())
                .refillIntervally(tier.refillTokens(), Duration.ofSeconds(tier.refillPeriodSeconds()))
                .build();

        return BucketConfiguration.builder()
                .addLimit(bandwidth)
                .build();
    }
}
