package com.flow.backend.entities;

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
    name = "productos",
    indexes = {
        @Index(name = "idx_productos_cod_barras", columnList = "codigo_barras"),
        @Index(name = "idx_productos_sku", columnList = "sku")
    }
)
@Check(constraints = "stock_min >= 0")
@Check(constraints = "stock_max >= stock_min")
@Check(constraints = "precio_compra >= 0")
@Check(constraints = "precio_venta >= precio_compra")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer id;

    @Column(name = "codigo_barras", length = 60, nullable = false, unique = true)
    private String codigoBarras;

    @Column(name = "sku", length = 60, nullable = false, unique = true)
    private String sku;

    @Column(name = "nombre_comercial", length = 150, nullable = false)
    private String nombreComercial;

    @Column(name = "nombre_generico", length = 150)
    private String nombreGenerico;

    @Column(name = "descripcion", length = 200)
    private  String descripcion;

    @Column(name = "concentracion", length = 100)
    private  String concentracion;

    @Column(name = "requiere_receta", nullable = false)
    private Boolean requiereReceta = Boolean.FALSE;

    @Column(name = "es_controlado", nullable = false)
    private Boolean esControlado = Boolean.FALSE;

    @Column(name = "temperatura_min", precision = 4, scale = 1)
    private BigDecimal temperaturaMin;

    @Column(name = "temperatura_max", precision = 4, scale = 1)
    private BigDecimal temperaturaMax;

    @Column(name = "stock_min", nullable = false)
    private Integer stockMin = 5;

    @Column(name = "stock_max", nullable = false)
    private Integer stockMax = 25;

    @Column(name = "precio_compra", precision = 12, scale = 2, nullable = false)
    private BigDecimal precioCompra = BigDecimal.valueOf(0.0);

    @Column(name = "precio_venta", precision = 12, scale = 2, nullable = false)
    private BigDecimal precioVenta;

    @Column(name = "activo", nullable = false)
    private Boolean activo = Boolean.TRUE;

    @Column(name = "creado_en", nullable = false)
    private OffsetDateTime creadoEn = OffsetDateTime.now();

    @Column(name = "actualizado_en", nullable = false)
    private OffsetDateTime actualizadoEn = OffsetDateTime.now();


    @OneToMany
    @JoinColumn(name = "id_categoria")
    private List<Categoria> categorias;

    @ManyToOne
    @JoinColumn(name = "id_laboratorio")
    private Laboratorio laboratorio;

    @ManyToOne
    @JoinColumn(name = "id_presentacion")
    private Presentacion presentacion;

    @ManyToOne
    @JoinColumn(name = "id_unidad_medida")
    private UnidadMedida unidadMedida;
}
