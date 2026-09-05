package com.flow.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "proveedores")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    private Integer id;

    @Column(name = "ruc_identificacion", nullable = false, unique = true)
    private String rucIdentificacion;

    @Column(name = "razon_social", length = 150, nullable = false)
    private String razonSocial;

    @Column(name = "nombre_comercial", length = 150)
    private String nombreComercial;

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
