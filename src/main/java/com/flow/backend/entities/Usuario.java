package com.flow.backend.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer id;

    @Column(name = "nombre", length = 120, nullable = false)
    private String nombre;

    @Column(name = "apellido", length = 120, nullable = false)
    private String apellido;

    @Column(name = "email", length = 360, nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", length = 255, nullable = false)
    private String passwordHash;

    @Column(name = "telefono", length = 25)
    private String telefono;

    @Column(name = "activo", nullable = false)
    private Boolean activo = Boolean.TRUE;

    @Column(name = "creado_en", nullable = false)
    private OffsetDateTime creadoEn = OffsetDateTime.now();

    @Column(name = "actualizado_en", nullable = false)
    private OffsetDateTime actualizadoEn = OffsetDateTime.now();

    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Rol rol;
}