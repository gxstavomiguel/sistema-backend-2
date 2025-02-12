package com.example.sistema.repository;

import com.example.sistema.entity.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
}
