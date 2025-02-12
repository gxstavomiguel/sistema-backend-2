package com.example.sistema.repository;

import com.example.sistema.entity.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> {
}
