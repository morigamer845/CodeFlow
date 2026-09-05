package com.flow.backend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(
    name = "lotes",
    indexes = {
        @Index(name = "idx_lotes_vencimiento", columnList = "fecha_vencimiento")
    }
)
public class Lote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lote")
    private Integer id;

    @Column(name = "numero_lote", length = 80, nullable = false)
    private String numeroLote;

    @Column(name = "fecha_fabricacion")
    private LocalDate fechaFabricacion;

    @Column(name = "fecha_vencimiento", nullable = false)
    private LocalDate fechaVencimiento;

    @Column(name = "activo", nullable = false)
    private Boolean activo = Boolean.TRUE;

    @Column(name = "creado_en", nullable = false)
    private OffsetDateTime creadoEn = OffsetDateTime.now();

    @OneToMany
    @JoinColumn(name = "id_producto")
    private List<Producto> productos;
}
