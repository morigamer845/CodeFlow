package com.flow.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer id;

    @Column(name = "identificacion_fiscal", length = 30, unique = true)
    private String identificacionFiscal;

    @Column(name = "nombre_completo", length = 150, nullable = false)
    private String nombreCompleto;

    @Column(name = "email", length = 360)
    private String email;

    @Column(name = "telefono", length = 25)
    private String telefono;

    @Column(name = "direccion", length = 255)
    private String direccion;

    @Column(name = "activo", nullable = false)
    private Boolean activo = Boolean.TRUE;

    @Column(name = "creado_en", nullable = false)
    private OffsetDateTime creadoEn = OffsetDateTime.now();
}
