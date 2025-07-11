package com.example.securitydemo.repository;

import com.example.securitydemo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
 Optional<Role>  findRoleByName(String name);
}
