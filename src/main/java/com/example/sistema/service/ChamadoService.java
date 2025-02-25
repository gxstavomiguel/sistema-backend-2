package com.example.sistema.service;

import com.example.sistema.entity.Chamado;
import com.example.sistema.repositories.ChamadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
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

    public Map<String, Integer> countChamadosPorDepartamento() {
        List<Object[]> resultado = chamadoRepository.qtdChamadosByDepartamentos();

        Map<String, Integer> chamadosPorDepartamento = new HashMap<>();
        for (Object[] row : resultado) {
            String departamentoNome = (String) row[0];
            Long quantidadeChamados = (Long) row[1];
            chamadosPorDepartamento.put(departamentoNome, quantidadeChamados.intValue());
        }

        return chamadosPorDepartamento;
    }

}
