package com.example.securitydemo;

import com.example.securitydemo.model.Role;
import com.example.securitydemo.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SecuritydemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecuritydemoApplication.class, args);
    }

    @Bean
    CommandLineRunner init(RoleRepository roleRepository) {
        return args -> {
            if (roleRepository.findRoleByName("ROLE_ADMIN").isEmpty()) {
                roleRepository.save(new Role("ROLE_ADMIN"));
            }
            if (roleRepository.findRoleByName("ROLE_MEMBRE").isEmpty()) {
                roleRepository.save(new Role("ROLE_MEMBRE"));
            }

        };
    }
}