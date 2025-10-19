package TDD_CompraEntradas_tp6.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import TDD_CompraEntradas_tp6.demo.entities.TipoEntrada;
import TDD_CompraEntradas_tp6.demo.entities.TipoPersona;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_pedido")
    private Long id;

    @Enumerated(EnumType.STRING) // Guarda "PENDIENTE" o "COMPLETADO" en texto
    @Column(name="tipo_persona", nullable=false)
    TipoPersona tipoPersona;

    @Enumerated(EnumType.STRING) // Guarda "PENDIENTE" o "COMPLETADO" en texto
    @Column(name="tipo_entrada", nullable=false)
    TipoEntrada tipoEntrada;
    double precio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pedido", nullable = false)
    Pedido pedido;
}