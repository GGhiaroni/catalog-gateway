package com.GabrielTiziano.CatalogGateway.service;

import com.GabrielTiziano.CatalogGateway.dto.AdminUserCreateRequest;
import com.GabrielTiziano.CatalogGateway.dto.SignupRequest;
import com.GabrielTiziano.CatalogGateway.dto.UserResponse;
import com.GabrielTiziano.CatalogGateway.mapper.UserMapper;
import com.GabrielTiziano.CatalogGateway.model.User;
import com.GabrielTiziano.CatalogGateway.model.enums.Role;
import com.GabrielTiziano.CatalogGateway.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse register(SignupRequest signupRequest) {

        if (userRepository.existsByEmail(signupRequest.email())) {
            // TODO M3: trocar por EmailAlreadyExistsException
            throw new RuntimeException("E-mail já cadastrado no banco de dados.");
        }

        String hashedPassword = passwordEncoder.encode(signupRequest.password());

        User userEntity = UserMapper.toEntity(signupRequest, hashedPassword);
        userEntity.setRoles(Set.of(Role.CUSTOMER));

        return UserMapper.toResponse(userRepository.save(userEntity));
    }

    public UserResponse createWithRole(AdminUserCreateRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            // TODO M3: trocar por EmailAlreadyExistsException
            throw new RuntimeException("E-mail já cadastrado no banco de dados.");
        }

        String hashedPassword = passwordEncoder.encode(request.password());
        User userEntity = UserMapper.toEntity(request, hashedPassword);
        userEntity.setRoles(request.roles());

        return UserMapper.toResponse(userRepository.save(userEntity));
    }
}
