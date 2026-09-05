package com.flow.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "devoluciones_venta")
@Check(constraints = "monto_total_reembolso >= 0")
public class DevolucionVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_devolucion_venta")
    private Integer id;

    @Column(name = "numero_devolucion", length = 60, nullable = false, unique = true)
    private String numeroDevolucion;

    @Column(name = "fecha_devolucion", nullable = false)
    private OffsetDateTime fechaDevolucion = OffsetDateTime.now();

    @Column(name = "monto_total_reembolso", precision = 12, scale = 2, nullable = false)
    private BigDecimal montoTotalReembolso = BigDecimal.valueOf(0.0);

    @Column(name = "motivo_general", length = 255, nullable = false)
    private String motivoGeneral;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoDevolucion estado = EstadoDevolucion.APROBADA;


    @ManyToOne
    @JoinColumn(name = "id_venta")
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "id_usuario_autoriza")
    private Usuario usuario;
}
