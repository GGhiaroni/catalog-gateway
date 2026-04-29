package com.GabrielTiziano.CatalogGateway.controller;

import com.GabrielTiziano.CatalogGateway.dto.LoginRequest;
import com.GabrielTiziano.CatalogGateway.dto.LoginResponse;
import com.GabrielTiziano.CatalogGateway.dto.SignupRequest;
import com.GabrielTiziano.CatalogGateway.dto.UserResponse;
import com.GabrielTiziano.CatalogGateway.mapper.UserMapper;
import com.GabrielTiziano.CatalogGateway.model.User;
import com.GabrielTiziano.CatalogGateway.model.enums.Role;
import com.GabrielTiziano.CatalogGateway.security.TokenService;
import com.GabrielTiziano.CatalogGateway.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/cataloggateway/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody SignupRequest signupRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(signupRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest){
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                loginRequest.email(),
                loginRequest.password()
        );

        Authentication authentication = authenticationManager.authenticate(authToken);

        User user = (User) authentication.getPrincipal();

        TokenService.GeneratedToken token = tokenService.generateToken(user);

        return ResponseEntity.ok(new LoginResponse(
                token.token(),
                token.type(),
                token.expiresIn(),
                user.getEmail(),
                user.getId(),
                user.getRoles().stream().map(Role::name).collect(Collectors.toSet()))
        );
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> me(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(UserMapper.toResponse(user));
    }
}
