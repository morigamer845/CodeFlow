package com.flow.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(
    name = "existencias_lotes",
    uniqueConstraints = {
        @UniqueConstraint(name = "uq_inventario_lote", columnNames = {"id_inventario", "id_lote"})
    }
)
@Check(constraints = "stock_actual >= 0")
@Check(constraints = "stock_comprometido >= 0")
public class ExistenciasLote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_existencia_lote")
    private Integer id;

    @Column(name = "stock_actual", nullable = false)
    private Integer stockActual = 0;

    @Column(name = "stock_comprometido", nullable = false)
    private Integer stockComprometido = 0;

    @Column(name = "ubicacion_estante", length = 50)
    private String ubicacionEstante;

    @Column(name = "actualizado_en", nullable = false)
    private OffsetDateTime actualizadoEn = OffsetDateTime.now();

    @ManyToOne
    @JoinColumn(name = "id_inventario")
    private Inventario inventario;

    @ManyToOne
    @JoinColumn(name = "id_lote")
    private Lote lote;
}
