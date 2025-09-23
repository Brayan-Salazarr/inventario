package com.InventarioBicicletas.services;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.InventarioBicicletas.entity.UsuarioEntity;
import com.InventarioBicicletas.repository.UsuarioRepository;
import com.InventarioBicicletas.security.JwtUtil;

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

    public Optional<String> login(String usuario, String contraseña) {
        Optional<UsuarioEntity> userOptional = usuarioRepository.findByUsuario(usuario);

        if (userOptional.isPresent()) {
            UsuarioEntity user = userOptional.get();
            // Verify password using BCrypt.
            if (encoder.matches(contraseña, user.getContraseña())) {
                // If authentication is successful, generate and return the JWT.
                String token = JwtUtil.generarToken(usuario);
                return Optional.of(token);
            }
        }
        
        // If authentication fails, return an empty Optional.
        return Optional.empty();
    }
    public UsuarioEntity login(String usuario) {
        return usuarioRepository.findByUsuario(usuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
}
