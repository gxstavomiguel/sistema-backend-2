package com.example.sistema.controller;

import com.example.sistema.entity.Usuario;
import com.example.sistema.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UsuarioControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private UsuarioController usuarioController;

    @Mock
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();
    }

    @Test
    public void testSaveUsuario() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNome("Gustavo");
        usuario.setEmail("gustavo@miguel.com");
        usuario.setSenha("123456");
        usuario.setTelefone("123456789");
        usuario.setTipo(Usuario.TipoUsuario.ADMIN);
        usuario.setCargo("Funcionário");

        when(usuarioService.save(any(Usuario.class)))
                .thenReturn("Usuário salvo com sucesso");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        String jsonRequest = objectMapper.writeValueAsString(usuario);

        mockMvc.perform(post("/api/usuario/save")
                .contentType("application/json")
                .content("{ \"nome\": \"Gustavo\", \"email\": \"gustavo@miguel.com\", \"senha\": \"123456\", \"telefone\": \"123456789\", \"cargo\": \"Funcionário\", \"tipo\": \"ADMIN\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.retorno").value("Usuário salvo com sucesso"));
    }

    @Test
    public void testFindAll() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Gustavo");
        usuario.setEmail("gustavo@miguel.com");
        usuario.setSenha("123456");
        usuario.setTelefone("123456789");
        usuario.setCargo("Funcionario");
        usuario.setTipo(Usuario.TipoUsuario.ADMIN);

        when(usuarioService.findAll()).thenReturn(Collections.singletonList(usuario));

        mockMvc.perform(get("/api/usuario/findAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.usuarios[0].nome").value("Gustavo"));
    }


    @Test
    public void testFindById() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("Gustavo");
        usuario.setEmail("gustavo@miguel.com");
        usuario.setSenha("123456");
        usuario.setTelefone("123456789");
        usuario.setCargo("Funcionario");
        usuario.setTipo(Usuario.TipoUsuario.ADMIN);

        when(usuarioService.findById(anyLong())).thenReturn(Optional.of(usuario));

        mockMvc.perform(get("/api/usuario/findById/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Gustavo"));
    }

    @Test
    public void testUpdate() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNome("Gustavo Miguel da Silva");
        usuario.setEmail("gustavo@miguelsilva.com");
        usuario.setSenha("novasenha1234");
        usuario.setTelefone("987654321");
        usuario.setCargo("Gerente");
        usuario.setTipo(Usuario.TipoUsuario.ADMIN);

        when(usuarioService.update(any(Usuario.class), anyLong())).thenReturn("Usuário atualizado com sucesso");

        mockMvc.perform(put("/api/usuario/update/1")
                        .contentType("application/json")
                        .content("{ \"nome\": \"Gustavo Miguel da Silva\", \"email\": \"gustavo@miguelsilva.com\", \"senha\": \"novasenha1234\", \"telefone\": \"987654321\", \"cargo\": \"Gerente\", \"tipo\": \"ADMIN\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.retorno").value("Usuário atualizado com sucesso"));
    }

    @Test
    public void testDelete() throws Exception {
        when(usuarioService.delete(anyLong())).thenReturn("Usuário deletado com sucesso");

        mockMvc.perform(delete("/api/usuario/delete/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.retorno").value("Usuário deletado com sucesso"));
    }
}