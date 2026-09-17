package com.flow.backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(
    name = "lotes",
    uniqueConstraints = {
        @UniqueConstraint(name = "uq_producto_lote", columnNames = {"id_producto","numero_lote"})
    },
    indexes = {
        @Index(name = "idx_lotes_producto_venc", columnList = "id_producto, fecha_vencimiento ASC")
    }
)
public class LoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lote", nullable = false)
    private Integer id;

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

    @OneToMany(mappedBy = "lote")
    @OnDelete(action = OnDeleteAction.RESTRICT)
    private List<ProductoEntity> productos;
}