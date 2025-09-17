package com.InventarioBicicletas.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.InventarioBicicletas.entity.UsuarioEntity;
import com.InventarioBicicletas.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    public String register(String usuario, String contraseña) {
        UsuarioEntity user = new UsuarioEntity();
        user.setUsuario(usuario);
        user.setContraseña(encoder.encode(contraseña));
        usuarioRepository.save(user);
        return "Usuario registrado con éxito";
    }
}
