package com.GabrielTiziano.CatalogGateway.dto;

import java.time.Instant;
import java.util.Set;

public record UserResponse(
        String id,
        String email,
        String name,
        Set<String> roles,
        boolean active,
        Instant createdAt
) {
}
