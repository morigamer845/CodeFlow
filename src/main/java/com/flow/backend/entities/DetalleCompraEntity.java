package com.flow.backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "detalle_compras")
public class DetalleCompraEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_compra", nullable = false)
    private Integer id;

    @Column(name = "cantidad", nullable = false, columnDefinition = "INT NOT NULL CHECK (cantidad > 0)")
    private Integer cantidad;

    @Column(name = "precio_unitario", nullable = false, precision = 12, scale = 2, columnDefinition = "NUMERIC(12, 2) NOT NULL CHECK (precio_unitario >= 0)")
    private BigDecimal precioUnitario;

    @Column(name = "subtotal", nullable = false, precision = 12, scale = 2, columnDefinition = "NUMERIC(12, 2) NOT NULL CHECK (subtotal >= 0)")
    private BigDecimal subtotal;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "id_compra", nullable = false)
    private CompraEntity compra;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_producto", nullable = false)
    private ProductoEntity productos;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_lote", nullable = false)
    private LoteEntity lote;
}