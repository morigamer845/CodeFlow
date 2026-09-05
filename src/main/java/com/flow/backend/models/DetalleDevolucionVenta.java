package com.flow.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "detalle_devoluciones_venta")
@Check(constraints = "cantidad > 0")
@Check(constraints = "monto_reembolso >= 0")
public class DetalleDevolucionVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_datalle_devolucion")
    private Integer id;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "monto_reembolso", precision = 12, scale = 2, nullable = false)
    private BigDecimal montoReembolso;

    @Column(name = "motivo_especifico", length = 255, nullable = false)
    private String motivoEspecifico;

    @Column(name = "reingresa_a_stock", nullable = false)
    private Boolean reingresaAStock = Boolean.TRUE;

    @ManyToOne
    @JoinColumn(name = "id_devolucion_venta")
    private DevolucionVenta devolucionVenta;

    @ManyToOne
    @JoinColumn(name = "id_detalle_venta")
    private DetalleVenta detalleVenta;

    @OneToMany
    @JoinColumn(name = "id_existencia_lote")
    private List<ExistenciasLote> existenciasLotes;
}
