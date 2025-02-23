package com.example.sistema.repository;

import com.example.sistema.entity.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Long> {

    @Query("SELECT c.departamento.nome, COUNT(c) FROM Chamado c GROUP BY c.departamento")
    List<Object[]> qtdChamadosByDepartamentos();
}
