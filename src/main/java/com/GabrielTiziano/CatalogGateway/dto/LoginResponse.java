package com.GabrielTiziano.CatalogGateway.dto;

import java.util.Set;

public record LoginResponse(
        String token,
        String tokenType,
        long expiresIn,
        String userEmail,
        String userId,
        Set<String> userRoles
) {
}
