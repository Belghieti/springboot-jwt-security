package com.example.securitydemo.security;

import com.example.securitydemo.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomUserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtFilter;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // ❌ Pas de CSRF (stateless)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 📴 Pas de session
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll() // ✅ Routes publiques
                        .anyRequest().authenticated() // 🔐 Les autres sont protégées
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) // ➕ Ajout du filtre JWT
                .build();
    }

}
/*
*
* | Élément                                                                  | Utilité                                          |
| ------------------------------------------------------------------------ | ------------------------------------------------ |
| `@EnableMethodSecurity`                                                  | Autorise les `@PreAuthorize("hasRole('ADMIN')")` |
| `passwordEncoder()`                                                      | Encode les mots de passe avec BCrypt             |
| `sessionCreationPolicy(STATELESS)`                                       | Désactive la session HTTP (tout passe par token) |
| `addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)` | Exécute notre filtre JWT AVANT celui de Spring   |

*
*
* */