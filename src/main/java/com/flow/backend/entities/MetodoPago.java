package com.flow.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "metodos_pago")
public class MetodoPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_metodo_pago")
    private Integer id;

    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    @Column(name = "requiere_referencia", nullable = false)
    private Boolean requiereReferencia = Boolean.FALSE;

    @Column(name = "activo", nullable = false)
    private Boolean activo = Boolean.TRUE;
}
