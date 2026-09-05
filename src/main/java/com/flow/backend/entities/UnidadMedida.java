package com.flow.backend.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "unidades_medida")
public class UnidadMedida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_unidad_medida")
    private Integer id;

    @Column(name = "nombre", length = 120, nullable = false, unique = true)
    private String nombre;

    @Column(name = "abreviatura", length = 10, nullable = false, unique = true)
    private String abreviatura;

    @Column(name = "activo", nullable = false)
    private Boolean activo = Boolean.TRUE;
}
