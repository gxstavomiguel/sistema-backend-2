package com.example.sistema.user;

public record RegisterDTO(String login, String password, String nome, String email, String telefone, UsuarioRole role) {
}
