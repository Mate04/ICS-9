package TDD_CompraEntradas_tp6.demo.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "PEDIDO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pedido {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_pedido")
        private long id;


        @Column(nullable = false)
        private LocalDateTime fechaEmision;

        @Column(nullable = false)
        private double montoTotal;

        @OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
        private List<DetallePedido> detallesPedidos;

        @Enumerated(EnumType.STRING)
        @Column(name = "metodo_pago")
        private MetodoPago tipoPago;

        @Column(name = "estado_pedido")
        @Enumerated(EnumType.STRING)
        private EstadoPedido estado;

        @PrePersist
        void prePersist() {
                if (fechaEmision == null) fechaEmision = LocalDateTime.now();
                if (estado == null) estado = EstadoPedido.CREADO;
        }

        public void AddDetallePedido(DetallePedido detallePedido){
                if (detallesPedidos == null) this.detallesPedidos = new ArrayList<>();
                this.detallesPedidos.add(detallePedido);
                detallePedido.setPedido(this);
        }

}
