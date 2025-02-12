package com.example.sistema.controller;

import com.example.sistema.entity.Chamado;
import com.example.sistema.service.ChamadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/chamado/")
public class ChamadoController {

    ChamadoService chamadoService;

    public ChamadoController(ChamadoService chamadoService) {
        this.chamadoService = chamadoService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(Chamado chamado) {
        try {
            String msg = chamadoService.save(chamado);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Chamado>> findAll(){
        try {
            List<Chamado> lista = chamadoService.findAll();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findById")
    public ResponseEntity<Optional<Chamado>> findById(@PathVariable Long id){
        try {
            Optional<Chamado> chamadoPorId = chamadoService.findById(id);
            return new ResponseEntity<>(chamadoPorId, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, Chamado chamado){
        try {
            String msg = chamadoService.update(chamado, id);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        try {
            chamadoService.delete(id);
            String msg = "Chamado deletado com sucesso!";
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }














}
