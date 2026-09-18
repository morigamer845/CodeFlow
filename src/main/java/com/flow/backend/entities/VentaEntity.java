package com.flow.backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    name = "ventas",
    indexes = {
        @Index(name = "idx_ventas_fecha", columnList = "fecha_venta DESC")
    }
)
public class VentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta", nullable = false)
    private Integer id;

    @Column(name = "numero_factura", nullable = false, length = 60, unique = true)
    private String numeroFactura;

    @Column(name = "fecha_venta", nullable = false)
    private OffsetDateTime fechaVenta = OffsetDateTime.now();

    @Enumerated(value = EnumType.STRING)
    @Column(name = "metodo_pago", nullable = false)
    private MetodoPago metodoPago = MetodoPago.EFECTIVO;

    @Column(name = "total", nullable = false, precision = 12, scale = 2, columnDefinition = "NUMERIC(12, 2) NOT NULL CHECK (total >= 0)")
    private BigDecimal total;

    @Column(name = "estado", nullable = false)
    private EstadoVenta estado = EstadoVenta.PAGADA;

    @Column(name = "id_usuario", nullable = false, length = 360)
    private String idUsuario;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "id_cliente")
    private ClienteEntity cliente;

    @OneToMany(mappedBy = "venta" ,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleVentaEntity> detalleVentas;
}