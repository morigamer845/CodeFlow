package com.flow.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "compras")
@Check(constraints = "subtotal >= 0")
@Check(constraints = "impuesto >= 0")
@Check(constraints = "total >= 0")
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compra")
    private Integer id;

    @Column(name = "numero_factura_proveedor", length = 80, nullable = false)
    private String numeroFacturaProveedor;

    @Column(name = "fecha_emision", nullable = false)
    private LocalDate fechaEmision;

    @Column(name = "fecha_recepcion")
    private LocalDate fechaRecepcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoCompra estado = EstadoCompra.RECIBIDO;

    @Column(name = "subtotal", precision = 12, scale = 2, nullable = false)
    private BigDecimal subtotal;

    @Column(name = "impuesto", precision = 12, scale = 2, nullable = false)
    private BigDecimal impuesto;

    @Column(name = "total", precision = 12, scale = 2, nullable = false)
    private BigDecimal total;

    @Column(name = "observaciones", length = 255)
    private String observaciones;

    @Column(name = "creado_en", nullable = false)
    private OffsetDateTime creadoEn = OffsetDateTime.now();

    @ManyToOne
    @JoinColumn(name = "id_proveedor")
    private Proveedor proveedor;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_inventario_destino")
    private Inventario inventario;
}
