package com.GabrielTiziano.CatalogGateway.mapper;

import com.GabrielTiziano.CatalogGateway.dto.AdminUserCreateRequest;
import com.GabrielTiziano.CatalogGateway.dto.SignupRequest;
import com.GabrielTiziano.CatalogGateway.dto.UserResponse;
import com.GabrielTiziano.CatalogGateway.model.User;
import com.GabrielTiziano.CatalogGateway.model.enums.Role;

import java.util.stream.Collectors;

public class UserMapper {
    private UserMapper() {
    }

    public static User toEntity(SignupRequest signupRequest, String passwordHash) {
        return User.builder()
                .name(signupRequest.name())
                .email(signupRequest.email())
                .passwordHash(passwordHash)
                .build();
    }

    public static User toEntity(AdminUserCreateRequest request, String passwordHash) {
        return User.builder()
                .name(request.name())
                .email(request.email())
                .passwordHash(passwordHash)
                .build();
    }

    public static UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .roles(user.getRoles().stream().map(Role::name).collect(Collectors.toSet()))
                .createdAt(user.getCreatedAt())
                .active(user.getActive())
                .build();
    }
}
