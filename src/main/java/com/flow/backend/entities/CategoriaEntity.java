package com.flow.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "categorias")
public class CategoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria", nullable = false)
    private Integer id;

    @Column(name = "nombre", length = 150, nullable = false, unique = true)
    private  String nombre;

    @Column(name = "descripcion", length = 200)
    private  String descripcion;

    @Column(name = "activo", nullable = false)
    private Boolean activo = Boolean.TRUE;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private ProductoEntity producto;
}
