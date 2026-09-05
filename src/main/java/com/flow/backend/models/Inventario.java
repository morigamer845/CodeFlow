package com.flow.backend.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "inventarios")
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inventario")
    private Integer id;

    @Column(name = "nombre_almacen", length = 100, nullable = false, unique = true)
    private String nombreAlmacen;

    @Column(name = "ubicacion", length = 150)
    private String ubicacion;

    @Column(name = "es_almacen_principal", nullable = false)
    private Boolean esAlmacenPrincipal = Boolean.FALSE;

    @Column(name = "activo", nullable = false)
    private Boolean activo = Boolean.TRUE;
}
