package com.example.sistema.controller;

import com.example.sistema.entity.Usuario;
import com.example.sistema.infra.security.TokenService;
import com.example.sistema.repositories.UsuarioRepository;
import com.example.sistema.user.AuthenticationDTO;
import com.example.sistema.user.LoginResponseDTO;
import com.example.sistema.user.RegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("auth")
public class AutheticationController {

    @Autowired
    TokenService tokenService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    private JwtEncoder jwtEncoder;

    public void AuthenticationController(JwtEncoder jwtEncoder,
                                         UsuarioRepository usuarioRepository,
                                         BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.jwtEncoder = jwtEncoder;
        this.usuarioRepository = usuarioRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AuthenticationDTO authenticationDTO) {
        var user = usuarioRepository.findByLogin(authenticationDTO.login());

        if (user.isEmpty() || !user.get().isLoginCorrect(authenticationDTO, bCryptPasswordEncoder)) {
            throw new BadCredentialsException("Login ou senha inválido");
        }

        var now = Instant.now();
        var expiresIn = 300l;

        var claims = JwtClaimsSet.builder()
                .issuer("mybackend")
                .subject(user.get().getId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok(new LoginResponseDTO(jwtValue, expiresIn));

    }


//    @PostMapping("/login")
//    public ResponseEntity login(@RequestBody @Validated AuthenticationDTO data){
//        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
//        var auth = this.authenticationManager.authenticate(usernamePassword);
//        var token = tokenService.generateToken((Usuario) auth.getPrincipal());
//        return ResponseEntity.ok(new LoginResponseDTO(token));
//    }

//    @PostMapping("/register")
//    public ResponseEntity register(@RequestBody @Validated RegisterDTO data) {
//        if (this.usuarioRepository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();
//
//        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
//        Usuario newUser = new Usuario(
//                data.login(),          // login
//                encryptedPassword,     // senha criptografada
//                data.role(), // role
//                data.nome(),           // nome
//                data.email(),          // email
//                data.telefone()       // telefone
//                // cargo
//        );
//        this.usuarioRepository.save(newUser);
//
//        return ResponseEntity.ok().build();
//    }

}
