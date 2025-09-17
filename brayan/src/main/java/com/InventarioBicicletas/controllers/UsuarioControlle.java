package com.InventarioBicicletas.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.InventarioBicicletas.dto.UsuarioDTO;
import com.InventarioBicicletas.services.UsuarioService;

@RestController
@RequestMapping("/login")
public class UsuarioControlle {
    
    private final UsuarioService authService;

    public UsuarioControlle(UsuarioService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth")
    public ResponseEntity<?> register(@RequestBody UsuarioDTO req) {
        return ResponseEntity.ok(authService.register(req.getUsuario(), req.getContraseña()));
    }
}
