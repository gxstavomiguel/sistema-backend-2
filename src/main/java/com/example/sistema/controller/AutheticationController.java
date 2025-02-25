package com.example.sistema.controller;

import com.example.sistema.entity.Usuario;
import com.example.sistema.infra.security.TokenService;
import com.example.sistema.repositories.UsuarioRepository;
import com.example.sistema.user.AuthenticationDTO;
import com.example.sistema.user.LoginResponseDTO;
import com.example.sistema.user.RegisterDTO;
import com.example.sistema.user.UsuarioRole;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AutheticationController {

    @Autowired
    TokenService tokenService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Validated RegisterDTO data){
        if(this.usuarioRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Usuario newUser = new Usuario(
                data.login(),          // login
                encryptedPassword,     // senha criptografada
                data.role(), // role
                data.nome(),           // nome
                data.email(),          // email
                data.telefone()       // telefone
                          // cargo
        );
        this.usuarioRepository.save(newUser);

        return ResponseEntity.ok().build();
    }

}
