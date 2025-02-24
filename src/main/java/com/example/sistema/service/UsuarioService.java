package com.example.sistema.service;

import com.example.sistema.entity.Usuario;
import com.example.sistema.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public String save(Usuario usuario) {
        usuarioRepository.save(usuario);
        return "Usuario salvo com sucesso";
    }

    public String update(Usuario usuario, Long id) {
        usuario.setId(id);
        usuarioRepository.save(usuario);
        return "Usuario atualizado com sucesso";
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    public String delete(Long id) {
        usuarioRepository.deleteById(id);
        return "Usuario deletado com sucesso";
    }

}