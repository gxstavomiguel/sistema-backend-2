package com.example.sistema.controller;

import com.example.sistema.entity.Departamento;
import com.example.sistema.service.DepartamentoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DepartamentoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DepartamentoService departamentoService;

    @InjectMocks
    private DepartamentoController departamentoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(departamentoController).build();
    }

    @Test
    void testSaveDepartamento() throws Exception {
        Departamento departamento = new Departamento();
        departamento.setNome("TI");
        departamento.setDescricao("Departamento de Tecnologia");

        when(departamentoService.save(any(Departamento.class)))
                .thenReturn("Departamento salvo com sucesso!");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        String jsonRequest = objectMapper.writeValueAsString(departamento);

        mockMvc.perform(post("/api/departamento/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.retorno").value("Departamento salvo com sucesso!"));

        verify(departamentoService, times(1)).save(any(Departamento.class));
    }

}
