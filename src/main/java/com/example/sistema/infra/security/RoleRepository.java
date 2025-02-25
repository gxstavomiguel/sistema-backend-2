package com.example.sistema.infra.security;

import com.example.sistema.user.UsuarioRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<UsuarioRole, Long> {
    UsuarioRole findByName(String name);
}
