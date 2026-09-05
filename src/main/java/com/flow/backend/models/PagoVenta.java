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
@Table(name = "pagos_ventas")
@Check(constraints = "monto > 0")
public class PagoVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago_venta")
    private Integer id;

    @Column(name = "monto", precision = 12, scale = 2, nullable = false)
    private BigDecimal monto;

    @Column(name = "referencia_transaccion", length = 100)
    private String referenciaTransaccion;

    @Column(name = "fecha_pago", nullable = false)
    private OffsetDateTime fecha_pago = OffsetDateTime.now();

    @ManyToOne
    @JoinColumn(name = "id_venta")
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "id_metodo_pago")
    private MetodoPago metodoPago;
}
