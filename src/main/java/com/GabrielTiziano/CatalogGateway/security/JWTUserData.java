package com.GabrielTiziano.CatalogGateway.security;

import lombok.Builder;

import java.util.Set;

@Builder
public record JWTUserData(
        String id,
        String name,
        String email,
        Set<String> roles
) {
}
