package com.InventarioBicicletas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.InventarioBicicletas.entity.BicicletasEntity;

@Repository
public interface BicicletasRepository extends JpaRepository<BicicletasEntity, Long> {
    List<BicicletasEntity> findAllByCategoriaIgnoreCase(String categoria);
    List<BicicletasEntity> findAllByTipoIgnoreCase(String tipo);
}
