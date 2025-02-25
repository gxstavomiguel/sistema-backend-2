package com.example.sistema.infra.security;

import com.example.sistema.entity.Usuario;
import com.example.sistema.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    private RoleRepository roleRepository;

    private UsuarioRepository usuarioRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AdminUserConfig(UsuarioRepository usuarioRepository, BCryptPasswordEncoder bCryptPasswordEncoder, RoleRepository roleRepository) {
        this.usuarioRepository = usuarioRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {



        var roleAdmin = roleRepository.findByName("admin");

        var userAdmin = usuarioRepository.findByLogin("admin");

        userAdmin.ifPresentOrElse(
                user -> {System.out.println("Já existe");
                    },
                () -> {
                    var user = new Usuario(
                            "admin",
                            bCryptPasswordEncoder.encode("admin"),
                            roleAdmin,
                            "Gustavo Miguel",
                            "gustavomiguel@gmail.com",
                            "12345678"
                    );
                    usuarioRepository.save(user);

                }
        );
    }
}
