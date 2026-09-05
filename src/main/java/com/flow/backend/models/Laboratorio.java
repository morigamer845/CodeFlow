package com.flow.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "laboratorios")
public class Laboratorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_laboratorio")
    private Integer id;

    @Column(name = "nombre", length = 120, nullable = false, unique = true)
    private String nombre;

    @Column(name = "pais_origen", length = 80)
    private String paisOrigen;

    @Column(name = "nombre_contacto", length = 120)
    private String nombreContacto;

    @Column(name = "telefono", length = 25)
    private String telefono;

    @Column(name = "email", length = 360, nullable = false, unique = true)
    private String email;

    @Column(name = "activo", nullable = false)
    private Boolean activo = Boolean.TRUE;
}
