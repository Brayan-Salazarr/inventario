package com.InventarioBicicletas.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.InventarioBicicletas.dto.BicicletaDTO;
import com.InventarioBicicletas.entity.BicicletasEntity;
import com.InventarioBicicletas.repository.BicicletasRepository;

@Service
public class BicicletaService {

    @Autowired
    private BicicletasRepository bicicletasRepository;
    
    public List<BicicletaDTO> listaBicicletas(){
        return bicicletasRepository.findAll()
                .stream()
                .map(bicicleta -> BicicletaDTO.builder()
                        .id(bicicleta.getId())
                        .marca(bicicleta.getMarca())
                        .modelo(bicicleta.getModelo())
                        .color(bicicleta.getColor())
                        .tipo(bicicleta.getTipo())
                        .precio(bicicleta.getPrecio())
                        .categoria(bicicleta.getCategoria())
                        .build())
                .collect(Collectors.toList());
    }

    public BicicletaDTO agregarBicicletas (BicicletaDTO bicicletaDTO){
        BicicletasEntity bicicleta = new BicicletasEntity();
        bicicleta.setMarca(bicicletaDTO.getMarca());
        bicicleta.setModelo(bicicletaDTO.getModelo());
        bicicleta.setColor(bicicletaDTO.getColor());
        bicicleta.setTipo(bicicletaDTO.getTipo());
        bicicleta.setPrecio(bicicletaDTO.getPrecio());
        bicicleta.setCategoria(bicicletaDTO.getCategoria());

        BicicletasEntity guardar = bicicletasRepository.save(bicicleta);

        return BicicletaDTO.builder()
                .id(guardar.getId())
                .marca(guardar.getMarca())
                .modelo(guardar.getModelo())
                .color(guardar.getColor())
                .tipo(guardar.getTipo())
                .precio(guardar.getPrecio())
                .categoria(guardar.getCategoria())
                .build();
    }
    
    public BicicletaDTO actualizarBicicleta(Long id, BicicletasEntity bicicletaEntity) {
        Optional<BicicletasEntity> listaBicicletas = bicicletasRepository.findById(id);

        if (listaBicicletas.isPresent()) {

            BicicletasEntity bicicletaEncontrada = listaBicicletas.get();
            bicicletaEncontrada.setMarca(bicicletaEntity.getMarca());
            bicicletaEncontrada.setModelo(bicicletaEntity.getModelo());
            bicicletaEncontrada.setColor(bicicletaEntity.getColor());
            bicicletaEncontrada.setTipo(bicicletaEntity.getTipo());
            bicicletaEncontrada.setPrecio(bicicletaEntity.getPrecio());
            bicicletaEncontrada.setCategoria(bicicletaEntity.getCategoria());
            bicicletaEncontrada.setStock(bicicletaEntity.getStock());

            BicicletasEntity bicicletaActualizado = bicicletasRepository.save(bicicletaEncontrada);

            return BicicletaDTO.builder()
                    .id(bicicletaActualizado.getId())
                    .marca(bicicletaActualizado.getMarca())
                    .modelo(bicicletaActualizado.getModelo())
                    .color(bicicletaActualizado.getColor())
                    .tipo(bicicletaActualizado.getTipo())
                    .precio(bicicletaActualizado.getPrecio())
                    .categoria(bicicletaActualizado.getCategoria())
                    .stock(bicicletaActualizado.getStock())
                    .build();
        } else {
            return null;
        }
    }

    public BicicletaDTO encontrarBicicleta(Long id) {
        return bicicletasRepository.findById(id)
                .map(bicicleta -> BicicletaDTO.builder()
                        .id(bicicleta.getId())
                        .marca(bicicleta.getMarca())
                        .modelo(bicicleta.getModelo())
                        .color(bicicleta.getColor())
                        .tipo(bicicleta.getTipo())
                        .precio(bicicleta.getPrecio())
                        .categoria(bicicleta.getCategoria())
                        .stock(bicicleta.getStock())
                        .build())
                .orElse(null);
    }

    public List<BicicletaDTO> encontrarBicicletaCate(String categoria) {
        return bicicletasRepository.findAllByCategoriaIgnoreCase(categoria)
                .stream()
                .map(bicicleta -> BicicletaDTO.builder()
                        .id(bicicleta.getId())
                        .marca(bicicleta.getMarca())
                        .modelo(bicicleta.getModelo())
                        .color(bicicleta.getColor())
                        .tipo(bicicleta.getTipo())
                        .precio(bicicleta.getPrecio())
                        .categoria(bicicleta.getCategoria())
                        .stock(bicicleta.getStock())
                        .build())
                .toList();
    }

    public List<BicicletaDTO> encontrarBicicletaTipo(String tipo) {
        return bicicletasRepository.findAllByCategoriaIgnoreCase(tipo)
                .stream()
                .map(bicicleta -> BicicletaDTO.builder()
                        .id(bicicleta.getId())
                        .marca(bicicleta.getMarca())
                        .modelo(bicicleta.getModelo())
                        .color(bicicleta.getColor())
                        .tipo(bicicleta.getTipo())
                        .precio(bicicleta.getPrecio())
                        .categoria(bicicleta.getCategoria())
                        .stock(bicicleta.getStock())
                        .build())
                .toList();
    }

    public BicicletaDTO eliminarBicicleta(Long id) {
        return bicicletasRepository.findById(id)
                .map(bicicleta -> {
                    bicicletasRepository.delete(bicicleta);
                    return BicicletaDTO.builder()
                            .id(bicicleta.getId())
                            .marca(bicicleta.getMarca())
                            .modelo(bicicleta.getModelo())
                            .color(bicicleta.getColor())
                            .tipo(bicicleta.getTipo())
                            .precio(bicicleta.getPrecio())
                            .categoria(bicicleta.getCategoria())
                            .stock(bicicleta.getStock())
                            .build();
                }).orElse(null);
    }

}
