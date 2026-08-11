package com.pdamjanovic.quizapp.config;

import com.pdamjanovic.quizapp.entity.Role;
import com.pdamjanovic.quizapp.entity.User;
import com.pdamjanovic.quizapp.repository.RoleRepository;
import com.pdamjanovic.quizapp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class DataSeeder implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.existsByUsername("admin")) {
            return;
        }

        Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                .orElseThrow(() -> new IllegalStateException("ROLE_ADMIN ne postoji u bazi"));

        User admin = new User();
        admin.setUsername("admin");
        admin.setEmail("admin@quizapp.com");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setEnabled(true);
        admin.setCreatedAt(LocalDateTime.now());

        Set<Role> roles = new HashSet<>();
        roles.add(adminRole);
        admin.setRoles(roles);

        userRepository.save(admin);

        System.out.println("Admin nalog je kreiran - username: admin, password: admin123");
    }
}
