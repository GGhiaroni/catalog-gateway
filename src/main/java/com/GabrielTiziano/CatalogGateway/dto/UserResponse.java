package com.GabrielTiziano.CatalogGateway.dto;

import lombok.Builder;

import java.time.Instant;
import java.util.Set;

@Builder
public record UserResponse(
        String id,
        String email,
        String name,
        Set<String> roles,
        boolean active,
        Instant createdAt
) {
}
