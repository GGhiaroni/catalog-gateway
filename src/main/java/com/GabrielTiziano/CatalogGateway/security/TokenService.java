package com.GabrielTiziano.CatalogGateway.security;

import com.GabrielTiziano.CatalogGateway.model.User;
import com.GabrielTiziano.CatalogGateway.model.enums.Role;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Component
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public static final String TOKEN_TYPE = "Bearer";

    public static final long EXPIRATION_SECONDS = 7200;

    public record GeneratedToken(String token, String type, long expiresIn) {}

    public GeneratedToken generateToken(User user) {
        try {
            Instant now = Instant.now();
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                    .withSubject(user.getEmail())
                    .withClaim("userId", user.getId())
                    .withClaim("name", user.getName())
                    .withClaim("roles", user.getRoles().stream().map(Role::name).toList())
                    .withIssuedAt(now)
                    .withExpiresAt(now.plusSeconds(EXPIRATION_SECONDS))
                    .withIssuer("API Catalog Gateway")
                    .sign(algorithm);

            return new GeneratedToken(token, TOKEN_TYPE, EXPIRATION_SECONDS);

        } catch (Exception e) {
            // TODO M3: trocar por TokenGenerationException
            throw new RuntimeException(e);
        }
    }

    public Optional<JWTUserData> verifyToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            DecodedJWT jwt = JWT.require(algorithm)
                    .build().
                    verify(token);

            return Optional.of(JWTUserData.builder()
                    .id(jwt.getClaim("userId").asString())
                    .name(jwt.getClaim("name").asString())
                    .roles(Set.copyOf(jwt.getClaim("roles").asList(String.class)))
                    .email(jwt.getSubject())
                    .build());

        } catch (RuntimeException e) {
            log.warn("Falha ao validar token JWT: {}", e.getMessage(), e);
            return Optional.empty();
        }
    }
}
