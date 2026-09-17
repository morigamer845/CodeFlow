package com.flow.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(
    name = "productos",
    indexes = {
        @Index(name = "idx_productos_busqueda", columnList = "nombre_comercial, sku")
    }
)
public class ProductoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto", nullable = false)
    private Integer id;

    @Column(name = "codigo_barras", nullable = false, length = 60, unique = true)
    private String codigoBarras;

    @Column(name = "sku", nullable = false, length = 60, unique = true)
    private String sku;

    @Column(name = "nombre_comercial", nullable = false, length = 150)
    private String nombreComercial;

    @Column(name = "nombre_generico", length = 150)
    private String nombreGenerico;

    @Column(name = "laboratorio", length = 100)
    private String laboratorio;

    @Column(name = "presentacion", length = 80)
    private String presentacion;

    @Column(name = "precio_compra", nullable = false, precision = 12, scale = 2, columnDefinition = "NUMERIC(12, 2) NOT NULL DEFAULT 0.0 CHECK (precio_compra >= 0)")
    private BigDecimal precioCompra = BigDecimal.valueOf(0.0);

    @Column(name = "precio_venta", nullable = false, precision = 12, scale = 2, columnDefinition = "NUMERIC(12, 2) NOT NULL CHECK (precio_venta >= precio_compra)")
    private BigDecimal precioVenta;

    @Column(name = "stock_min", nullable = false, columnDefinition = "INT NOT NULL DEFAULT 5 CHECK (stock_minimo >= 0)")
    private Integer stockMin = 5;

    @Column(name = "requiere_receta", nullable = false)
    private Boolean requiereReceta = Boolean.FALSE;

    @Column(name = "activo", nullable = false)
    private Boolean activo = Boolean.TRUE;

    @Column(name = "creado_en", nullable = false)
    private OffsetDateTime creadoEn = OffsetDateTime.now();

    @ManyToOne
    @JoinColumn(name = "lote_id", nullable = false)
    private LoteEntity lote;

    @OneToMany(mappedBy = "producto")
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private List<CategoriaEntity> categorias;
}