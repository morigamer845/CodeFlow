package com.flow.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "detalle_compras")
@Check(constraints = "catidad > 0")
@Check(constraints = "precio_unitario >= 0")
@Check(constraints = "subtotal >= 0")
public class DetalleCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_compra")
    private Integer id;

    @Column(name = "catidad", nullable = false)
    private Integer catidad;

    @Column(name = "precio_unitario", precision = 12, scale = 2, nullable = false)
    private BigDecimal precioUnitario;

    @Column(name = "subtotal", precision = 12, scale = 2, nullable = false)
    private BigDecimal subtotal;


    @ManyToOne
    @JoinColumn(name = "id_compra")
    private Compra compra;

    @OneToMany
    @JoinColumn(name = "id_producto")
    private List<Producto> productos;

    @OneToMany
    @JoinColumn(name = "id_lote")
    private List<Lote> lotes;
}
