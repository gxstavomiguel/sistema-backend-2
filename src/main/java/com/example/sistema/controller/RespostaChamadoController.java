package com.example.sistema.controller;

import com.example.sistema.entity.RespostaChamado;
import com.example.sistema.entity.Usuario;
import com.example.sistema.service.RespostaChamadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/respostaChamado")
public class RespostaChamadoController {

    RespostaChamadoService respostaChamadoService;

    public RespostaChamadoController(RespostaChamadoService respostaChamadoService) {
        this.respostaChamadoService= respostaChamadoService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody RespostaChamado respostaChamado){
        try {
            String msg = respostaChamadoService.save(respostaChamado);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<RespostaChamado>> findAll(){
        try {
            List<RespostaChamado> lista = respostaChamadoService.findAll();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Optional<RespostaChamado>> findById(@PathVariable Long id){
        try {
            Optional<RespostaChamado> usuario = respostaChamadoService.findById(id);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@RequestBody RespostaChamado respostaChamado, @PathVariable Long id){
        try {
            String msg = respostaChamadoService.update(respostaChamado, id);
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        try {
            respostaChamadoService.deleteById(id);
            String msg = "Usuário Deletado com sucesso";
            return new ResponseEntity<>(msg, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
