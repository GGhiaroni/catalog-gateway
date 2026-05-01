package com.GabrielTiziano.CatalogGateway.config;

import com.GabrielTiziano.CatalogGateway.model.User;
import com.GabrielTiziano.CatalogGateway.model.enums.Role;
import com.GabrielTiziano.CatalogGateway.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Profile("dev")
@RequiredArgsConstructor
@Slf4j
public class AdminSeed implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${catalog.admin.seed.email}")
    private String adminEmail;

    @Value("${catalog.admin.seed.password}")
    private String adminPassword;

    @Value("${catalog.admin.seed.name}")
    private String adminName;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.existsByEmail(adminEmail)) {
            log.info("AdminSeed: usuário admin já existe (email={}), nenhuma ação tomada.", adminEmail);
            return;
        }

        User admin = User.builder()
                .name(adminName)
                .email(adminEmail)
                .passwordHash(passwordEncoder.encode(adminPassword))
                .roles(Set.of(Role.ADMIN))
                .build();

        userRepository.save(admin);
        log.info("AdminSeed: usuário admin criado com sucesso (email={}).", adminEmail);
    }
}
