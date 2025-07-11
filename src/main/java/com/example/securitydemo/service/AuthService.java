package com.example.securitydemo.service;

import com.example.securitydemo.dto.AuthRequest;
import com.example.securitydemo.dto.AuthResponse;
import com.example.securitydemo.dto.RegisterRequest;
import com.example.securitydemo.model.Role;
import com.example.securitydemo.model.User;
import com.example.securitydemo.repository.RoleRepository;
import com.example.securitydemo.repository.UserRepository;
import com.example.securitydemo.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final CustomUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    public String register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return "❌ Email déjà utilisé.";
        }

        Role userRole = roleRepository.findRoleByName(request.getNameRole().toUpperCase())
                .orElseThrow(()-> new RuntimeException("Role not found"));
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(List.of(userRole));
        userRepository.save(user);
        return "✅ Utilisateur enregistré avec succès.";
    }

    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = jwtUtil.generateToken(userDetails);

        return new AuthResponse(token);
    }
}
