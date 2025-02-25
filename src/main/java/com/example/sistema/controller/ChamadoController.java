package com.example.sistema.controller;

import com.example.sistema.entity.Chamado;
import com.example.sistema.service.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/chamado/")
public class ChamadoController {

    @Autowired
    ChamadoService chamadoService;

    public ChamadoController(ChamadoService chamadoService) {
        this.chamadoService = chamadoService;
    }

    @PostMapping("/save")
    public ResponseEntity<Map<String, String>> save(@RequestBody Chamado chamado) {
        try {
            String msg = chamadoService.save(chamado);
            Map<String, String> response = new HashMap<>();
            response.put("retorno", msg);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("retorno", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<Object> findAll() {
        try {
            List<Chamado> lista = chamadoService.findAll();
            return ResponseEntity.ok(Collections.singletonMap("chamados", lista));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findById")
    public ResponseEntity<Optional<Chamado>> findById(@PathVariable Long id) {
        try {
            Optional<Chamado> chamadoPorId = chamadoService.findById(id);
            return new ResponseEntity<>(chamadoPorId, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, Chamado chamado) {
        try {
            String msg = chamadoService.update(chamado, id);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            chamadoService.delete(id);
            String msg = "Chamado deletado com sucesso!";
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/qtdchamadosbydepartamento")
    public ResponseEntity<Map<String, Integer>> countChamadosPorDepartamento() {
        Map<String, Integer> chamadosPorDepartamento = chamadoService.countChamadosPorDepartamento();
        return new ResponseEntity<>(chamadosPorDepartamento, HttpStatus.OK);
    }

}
