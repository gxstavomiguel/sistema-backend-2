package com.example.sistema.controller;

import com.example.sistema.entity.Usuario;
import com.example.sistema.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("api/usuario/")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/save")
    public ResponseEntity<Map<String, String>> save(@RequestBody Usuario usuario){
        try {
            String msg = usuarioService.save(usuario);
            Map<String, String> response = new HashMap<>();
            response.put("retorno", msg);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            Map<String, String> response = new HashMap<>();
            response.put("retorno", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<Object> findAll(){
        try {
            List<Usuario> lista = usuarioService.findAll();
            return  ResponseEntity.ok(Collections.singletonMap("usuarios", lista));
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findByEmail")
    public ResponseEntity<Object> findByEmail(@RequestBody String email){
        try {
            Object retornoEmail = usuarioService.findByEmail(email);
            return  ResponseEntity.ok(Collections.singletonMap("Email do usuário", retornoEmail));
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Optional<Usuario>> findById(@PathVariable Long id){
        try {
            Optional<Usuario> usuario = usuarioService.findById(id);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String,String>> update(@RequestBody Usuario usuario, @PathVariable Long id){
        try {
            String msg = usuarioService.update(usuario, id);
            Map<String, String> response = new HashMap<>();
            response.put("retorno", msg);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id){
        try {
            String msg = usuarioService.delete(id);
            Map<String, String> response = new HashMap<>();
            response.put("retorno", msg);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}