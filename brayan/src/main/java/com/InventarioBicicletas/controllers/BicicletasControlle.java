package com.InventarioBicicletas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.InventarioBicicletas.dto.BicicletaDTO;
import com.InventarioBicicletas.entity.BicicletasEntity;
import com.InventarioBicicletas.security.JwtUtil;
import com.InventarioBicicletas.services.BicicletaService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;



@RestController
@RequestMapping("/inventarioBic")
public class BicicletasControlle {
    @Autowired
    private BicicletaService bicicletaService;

    private boolean validarToken(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return false;
        }
        String token = authHeader.replace("Bearer ", "");
        return JwtUtil.validarToken(token);
    }

    //  Listar bicicletas 
    @GetMapping()
    public ResponseEntity<?> listarBicicletas(@RequestHeader("Authorization") String authHeader) {
        if (!validarToken(authHeader)) {
            return ResponseEntity.status(401).body("Token inválido o expirado");
        }
        List<BicicletaDTO> bicicletas = bicicletaService.listaBicicletas();
        return ResponseEntity.ok(bicicletas);
    }
    //  Crear bicicleta
    @PostMapping()
    public ResponseEntity<?> crearBicicleta(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody BicicletaDTO bicicletaDTO) {
        if (!validarToken(authHeader)) {
            return ResponseEntity.status(401).body("Token inválido o expirado");
        }
        BicicletaDTO guardar = bicicletaService.agregarBicicletas(bicicletaDTO);
        return ResponseEntity.ok(guardar);
    }

    // Actualizar bicicleta
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarBicicleta(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Long id,
            @RequestBody BicicletasEntity bicicletaEntity) {
        if (!validarToken(authHeader)) {
            return ResponseEntity.status(401).body("Token inválido o expirado");
        }

        BicicletaDTO bicicletaActualizada = bicicletaService.actualizarBicicleta(id, bicicletaEntity);
        if (bicicletaActualizada != null) {
            return ResponseEntity.ok(bicicletaActualizada);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //  Eliminar bicicleta
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarBicicleta(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Long id) {
        if (!validarToken(authHeader)) {
            return ResponseEntity.status(401).body("Token inválido o expirado");
        }

        BicicletaDTO eliminado = bicicletaService.eliminarBicicleta(id);
        if (eliminado != null) {
            return ResponseEntity.ok(eliminado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //  Buscar por ID
    @GetMapping("/id/{id}")
    public ResponseEntity<?> encontrarBicicleta(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Long id) {
        if (!validarToken(authHeader)) {
            return ResponseEntity.status(401).body("Token inválido o expirado");
        }

        BicicletaDTO bicicleta = bicicletaService.encontrarBicicleta(id);
        if (bicicleta != null) {
            return ResponseEntity.ok(bicicleta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //  Buscar por tipo
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<?> encontrarBicicletaTipo(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable String tipo) {
        if (!validarToken(authHeader)) {
            return ResponseEntity.status(401).body("Token inválido o expirado");
        }

        List<BicicletaDTO> bicicletaTipo = bicicletaService.encontrarBicicletaTipo(tipo);
        if (bicicletaTipo != null && !bicicletaTipo.isEmpty()) {
            return ResponseEntity.ok(bicicletaTipo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //  Buscar por categoría
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<?> encontrarBicicletaCate(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable String categoria) {
        if (!validarToken(authHeader)) {
            return ResponseEntity.status(401).body("Token inválido o expirado");
        }

        List<BicicletaDTO> bicicletaCate = bicicletaService.encontrarBicicletaCate(categoria);
        if (bicicletaCate != null && !bicicletaCate.isEmpty()) {
            return ResponseEntity.ok(bicicletaCate);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}
