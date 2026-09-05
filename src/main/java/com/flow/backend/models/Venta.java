package com.flow.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(
    name = "ventas",
    indexes = {
        @Index(name = "idx_ventas_fecha", columnList = "fecha_venta DESC"),
        @Index(name = "idx_ventas_cliente", columnList = "id_cliente")
    }
)
@Check(constraints = "subtotal_sin_iva >= 0")
@Check(constraints = "subtotal_con_iva >= 0")
@Check(constraints = "descuento >= 0")
@Check(constraints = "total >= 0")
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Integer id;

    @Column(name = "numero_factura", length = 60, nullable = false, unique = true)
    private String numeroFactura;

    @Column(name = "fecha_venta", nullable = false)
    private OffsetDateTime fechaVenta = OffsetDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoVenta estado = EstadoVenta.PAGADA;

    @Column(name = "subtotal_sin_iva", precision = 12, scale = 2, nullable = false)
    private BigDecimal subtotalSinIva = BigDecimal.valueOf(0.0);

    @Column(name = "subtotal_con_iva", precision = 12, scale = 2, nullable = false)
    private BigDecimal subtotalConIva = BigDecimal.valueOf(0.0);

    @Column(name = "descuento", precision = 12, scale = 2, nullable = false)
    private BigDecimal descuento = BigDecimal.valueOf(0.0);

    @Column(name = "total", precision = 12, scale = 2, nullable = false)
    private BigDecimal total;

    @Column(name = "observaciones", length = 255)
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToMany
    @JoinColumn(name = "id_inventario_origen")
    private List<Inventario> inventarios;
}
