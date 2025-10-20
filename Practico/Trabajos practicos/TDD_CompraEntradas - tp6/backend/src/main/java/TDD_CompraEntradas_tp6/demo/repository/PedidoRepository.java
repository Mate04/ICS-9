package TDD_CompraEntradas_tp6.demo.repository;

import TDD_CompraEntradas_tp6.demo.entities.EstadoPedido;
import TDD_CompraEntradas_tp6.demo.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {}
