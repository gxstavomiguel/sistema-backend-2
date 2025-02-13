package com.example.sistema.controller;

import com.example.sistema.entity.Departamento;
import com.example.sistema.service.DepartamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/departamento")
public class DepartamentoController {

    private final DepartamentoService departamentoService;

    public DepartamentoController(DepartamentoService departamentoService){
        this.departamentoService = departamentoService;
    }

    @GetMapping("/listaDepartamento")
    public List<Map<String, Object>> listarDepartamentos(){
        List<Departamento> departamentos = departamentoService.findAll();
        return departamentos.stream()
                .map(departamento -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", departamento.getId());
                    map.put("nome", departamento.getNome());
                    return map;
                })
                .collect(Collectors.toList());
    }

    @PostMapping("/save")
    public ResponseEntity<Map<String, String>> save(@RequestBody Departamento departamento){
        try {
            String msg = departamentoService.save(departamento);
            Map<String, String> response = new HashMap<>();
            response.put("retorno", msg);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            Map<String, String> response = new HashMap<>();
            response.put("retorno", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, String>> update(@PathVariable Long id, @RequestBody Departamento departamento){
        try {
            String msg = departamentoService.update(departamento, id);
            Map<String, String> response = new HashMap();
            response.put("retorno", msg);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            Map<String, String> response = new HashMap<>();
            response.put("retorno", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id){
        try {
            String msg = departamentoService.deleteById(id);
            Map<String, String> response = new HashMap<>();
            response.put("retorno", msg);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Departamento>> findAll(){
        try {
            List<Departamento> lista = departamentoService.findAll();
            return new ResponseEntity<>(lista, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Optional<Departamento>> findById(@PathVariable Long id){
        try {
            Optional<Departamento> departamento = departamentoService.findById(id);
            return new ResponseEntity<>(departamento, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }





    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        e.printStackTrace();  // Isso pode ajudar a entender o erro
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
