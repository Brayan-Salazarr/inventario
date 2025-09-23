package com.InventarioBicicletas.controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.InventarioBicicletas.dto.UsuarioDTO;
import com.InventarioBicicletas.entity.UsuarioEntity;
import com.InventarioBicicletas.security.JwtUtil;
import com.InventarioBicicletas.services.UsuarioService;

@RestController
@RequestMapping("/login")
public class UsuarioControlle {
    
    private final UsuarioService authService;
    private final JwtUtil jwt;
    public UsuarioControlle(UsuarioService authService,JwtUtil jwt) {
        this.authService = authService;
        this.jwt = jwt;
    }

    @PostMapping("/registro")
    public ResponseEntity<String> register(@RequestBody UsuarioDTO req) {
        return ResponseEntity.ok(authService.register(req.getUsuario(), req.getContraseña()));
    }
    
 @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioEntity request) {
        UsuarioEntity user = authService.login(request.getUsuario());

        // Generar token JWT
      String token = jwt.generarToken(user.getUsuario());

        return ResponseEntity.ok(
                Map.of(
                        "id", user.getId(),
                        "usuario", user.getUsuario(),
                        "token", token
                )
        );
    }
}
