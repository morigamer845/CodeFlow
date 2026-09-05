package com.flow.backend.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(
    name = "movimientos_inventario",
    indexes = {
        @Index(name = "idx_movimientos_lote_fecha", columnList = "id_lote, fecha_movimiento DESC")
    }
)
@Check(constraints = "cantidad > 0")
public class MovimientoInventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimiento")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimiento", nullable = false)
    private TipoMovimientoInventario tipoMovimiento;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "stock_anterior", nullable = false)
    private Integer stockAnterior;

    @Column(name = "stock_posterior", nullable = false)
    private Integer stockPosterior;

    @Column(name = "documento_referencia", length = 80)
    private String documentoReferencia;

    @Column(name = "motivo", length = 255)
    private String motivo;

    @Column(name = "fecha_movimiento", nullable = false)
    private OffsetDateTime fechaMovimiento = OffsetDateTime.now();

    @ManyToOne
    @JoinColumn(name = "id_inventario")
    private Inventario inventario;

    @ManyToOne
    @JoinColumn(name = "id_lote")
    private Lote lote;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
