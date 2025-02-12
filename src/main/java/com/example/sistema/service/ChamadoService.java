package com.example.sistema.service;

import com.example.sistema.entity.Chamado;
import com.example.sistema.repository.ChamadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    ChamadoRepository chamadoRepository;

    public String save(Chamado chamado) {
        chamadoRepository.save(chamado);
        return "Chamado salvo com sucesso!";
    }

    public String update(Chamado chamado, Long id) {
        chamado.setId(id);
        chamadoRepository.save(chamado);
        return "Chamado atualizado com sucesso!";
    }

    public List<Chamado> findAll() {
        return chamadoRepository.findAll();
    }

    public Optional<Chamado> findById(Long id) {
        return chamadoRepository.findById(id);
    }

    public String delete(Long id) {
        chamadoRepository.deleteById(id);
        return "Chamado deletado com sucesso!";
    }


}
