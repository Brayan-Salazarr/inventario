package com.InventarioBicicletas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.InventarioBicicletas.dto.BicicletaDTO;
import com.InventarioBicicletas.entity.BicicletasEntity;
import com.InventarioBicicletas.services.BicicletaService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/inventarioBic")
public class BicicletasControlle {
    @Autowired
    private BicicletaService bicicletaService;

    @GetMapping()
    public ResponseEntity<List<BicicletaDTO>> listarBicicletas(){
        List<BicicletaDTO> bicicletas = bicicletaService.listaBicicletas();
        return ResponseEntity.ok(bicicletas);
    }

    @PostMapping()
    public ResponseEntity<BicicletaDTO> crearBicicleta(@RequestBody BicicletaDTO bicicletaDTO){
        BicicletaDTO guardar = bicicletaService.agregarBicicletas(bicicletaDTO);
        return ResponseEntity.ok(guardar);
    }
        
     @PutMapping("/{id}")
    public ResponseEntity<BicicletaDTO> actualizarBicicleta(@PathVariable Long id,
            @RequestBody BicicletasEntity bicicletaEntity) {
        BicicletaDTO bicicletaActualizada = bicicletaService.actualizarBicicleta(id, bicicletaEntity);
        return ResponseEntity.ok(bicicletaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BicicletaDTO> eliminarBicicleta(@PathVariable Long id) {
        BicicletaDTO eliminado = bicicletaService.eliminarBicicleta(id);

        if (id != null) {
            return ResponseEntity.ok(eliminado);
        } else {
            return null;
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<BicicletaDTO> encontrarBicicleta(@PathVariable Long id) {
        BicicletaDTO bicicleta = bicicletaService.encontrarBicicleta(id);

        if (id != null) {
            return ResponseEntity.ok(bicicleta);
        } else {
            return null;
        }
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<BicicletaDTO>> encontrarBicicletaTipo(@PathVariable String tipo) {
       List<BicicletaDTO> bicicletaTipo = bicicletaService.encontrarBicicletaTipo(tipo);

        if (tipo != null) {
            return ResponseEntity.ok(bicicletaTipo);
        } else {
            return null;
        }
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<BicicletaDTO>> encontrarBicicletaCate(@PathVariable String categoria) {
       List<BicicletaDTO> bicicletaCate = bicicletaService.encontrarBicicletaCate(categoria);

        if (categoria != null) {
            return ResponseEntity.ok(bicicletaCate);
        } else {
            return null;
        }
    }
    
}
