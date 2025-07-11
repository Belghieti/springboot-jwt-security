package com.example.securitydemo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    // ✅ Accessible sans authentification
    @GetMapping("/all")
    public String allAccess() {
        return "Contenu Public 📢";
    }

    // ✅ Accessible uniquement aux utilisateurs avec rôle USER
    @GetMapping("/user")
    @PreAuthorize("hasRole('MEMBRE')")
    public String userAccess() {
        return "Contenu pour Utilisateur 🧑";
    }

    // ✅ Accessible uniquement aux utilisateurs avec rôle ADMIN
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Contenu Admin 👑";
    }
}