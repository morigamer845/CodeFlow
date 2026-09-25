package com.flow.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(
        name = "lotes",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_producto_lote", columnNames = {"id_producto", "numero_lote"})
        },
        indexes = {
                @Index(name = "idx_lotes_producto_venc", columnList = "id_producto, fecha_vencimiento ASC")
        }
)
public class LoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lote", nullable = false)
    private Long id;

    @Column(name = "numero_lote", nullable = false, length = 80)
    private String numeroLote;

    @Column(name = "fecha_vencimiento", nullable = false)
    private LocalDate fechaVencimiento;

    @Column(name = "stock_actual", nullable = false, columnDefinition = "INT NOT NULL DEFAULT 0 CHECK (stock_actual >= 0)")
    private Integer stockActual = 0;

    @Column(name = "activo", nullable = false)
    private Boolean activo = Boolean.TRUE;

    @Column(name = "creado_en", nullable = false)
    private OffsetDateTime creadoEn = OffsetDateTime.now();

    // ÚNICA relación con ProductoEntity en toda la clase
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_producto", nullable = false)
    private ProductoEntity producto;
}