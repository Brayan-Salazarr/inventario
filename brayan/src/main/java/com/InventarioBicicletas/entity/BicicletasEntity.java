package com.InventarioBicicletas.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "bicicletas")
@Data
public class BicicletasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String marca;

    private String modelo;

    private String color;

    private String tipo;

    private double precio;

    private String categoria;

    private int stock;
}
