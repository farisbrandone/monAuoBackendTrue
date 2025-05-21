package com.example.monauto.dao;

import com.example.monauto.entity.Role;
import com.example.monauto.enumFile.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByRoleName(RoleUser roleName);
}
