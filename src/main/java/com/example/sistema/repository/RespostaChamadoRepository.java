package com.example.sistema.repository;

import com.example.sistema.entity.RespostaChamado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespostaChamadoRepository extends JpaRepository<RespostaChamado, Long> {
}
