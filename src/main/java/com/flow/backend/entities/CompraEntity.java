package com.flow.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "compras")
public class CompraEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compra", nullable = false)
    private Integer id;

    @Column(name = "numero_factura_proveedor", nullable = false, length = 80)
    private String numeroFacturaProveedor;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDate fechaEmision;

    @Column(name = "total", nullable = false, precision = 12, scale = 2, columnDefinition = "NOT NULL DEFAULT 0.00 CHECK (total >= 0)")
    private BigDecimal total = BigDecimal.valueOf(0.0);

    @Enumerated(value = EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoCompra estado = EstadoCompra.RECIBIDO;

    @Column(name = "creado_en", nullable = false)
    private OffsetDateTime creadoEn = OffsetDateTime.now();

    @Column(name = "id_usuario", nullable = false, length = 360)
    private String idUsuario;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_proveedor", nullable = false)
    ProveedorEntity proveedor;
}