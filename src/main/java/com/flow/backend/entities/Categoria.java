package com.flow.backend.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer id;

    @Column(name = "nombre", length = 120, nullable = false, unique = true)
    private  String nombre;

    @Column(name = "descripcion", length = 200)
    private  String descripcion;

    @Column(name = "activo", nullable = false)
    private Boolean activo = Boolean.TRUE;
}
