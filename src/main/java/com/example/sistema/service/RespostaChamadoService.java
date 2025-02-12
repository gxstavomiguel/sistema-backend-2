package com.example.sistema.service;

import com.example.sistema.entity.RespostaChamado;
import com.example.sistema.repository.RespostaChamadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RespostaChamadoService {

    RespostaChamadoRepository respostaChamadoRepository;

    public String save(RespostaChamado respostaChamado) {
        respostaChamadoRepository.save(respostaChamado);
        return "Resposta do chamado salva com sucesso";
    }

    public String update(RespostaChamado respostaChamado, Long id) {
        respostaChamado.setId(id);
        respostaChamadoRepository.save(respostaChamado);
        return "Nova Resposta do chamado salva com sucesso";
    }

    public List<RespostaChamado> findAll() {
        return respostaChamadoRepository.findAll();
    }

    public Optional<RespostaChamado> findById(Long id) {
        return respostaChamadoRepository.findById(id);
    }

    public String deleteById(Long id) {
        respostaChamadoRepository.deleteById(id);
        return "Resposta do chamado deletada com sucesso";
    }

}
