package com.flow.backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "proveedores")
public class ProveedorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor", nullable = false)
    private Integer id;

    @Column(name = "ruc_identificacion", length = 30, nullable = false, unique = true)
    private String rucIdentificacion;

    @Column(name = "razon_social", nullable = false, length = 150)
    private String razonSocial;

    @Column(name = "telefono", length = 25)
    private String telefono;

    @Column(name = "email", length = 360)
    private String email;

    @Column(name = "activo", nullable = false)
    private Boolean activo = Boolean.TRUE;

    @OneToMany(mappedBy = "proveedor")
    private List<CompraEntity> compras;
}