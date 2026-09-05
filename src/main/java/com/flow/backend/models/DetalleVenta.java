package com.flow.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "detalle_ventas")
@Check(constraints = "cantidad > 0")
@Check(constraints = "precio_unitario >= 0")
@Check(constraints = "descuento_unitario >= 0")
@Check(constraints = "subtotal >= 0")
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_venta")
    private Integer id;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "precio_unitario", precision = 12, scale = 2, nullable = false)
    private BigDecimal precioUnitario;

    @Column(name = "descuento_unitario", precision = 12, scale = 2, nullable = false)
    private BigDecimal descuentoUnitario = BigDecimal.valueOf(0.0);

    @Column(name = "subtotal", precision = 12, scale = 2, nullable = false)
    private BigDecimal subtotal;

    @ManyToOne
    @JoinColumn(name = "id_venta")
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id_existencia_lote")
    private ExistenciasLote existenciasLote;
}
