package com.example.sistema.service;

import com.example.sistema.entity.Departamento;
import com.example.sistema.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class DepartamentoService {


    private final DepartamentoRepository departamentoRepository;

    @Autowired
    public DepartamentoService(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    public String save(Departamento departamento) {
        departamentoRepository.save(departamento);
        return "Departamento salvo com sucesso";
    }

    public String update(Departamento departamento, Long id) {
        departamento.setId(id);
        departamentoRepository.save(departamento);
        return "Departamento atualizado com sucesso";
    }

    public List<Departamento> findAll() {
        return departamentoRepository.findAll();
    }

    public Optional<Departamento> findById(Long id) {
        return departamentoRepository.findById(id);
    }

    public String deleteById(Long id) {
        departamentoRepository.deleteById(id);
        return "Departamento deletado com sucesso";
    }
}
