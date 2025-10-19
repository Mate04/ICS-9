package TDD_CompraEntradas_tp6.demo.repository;

import TDD_CompraEntradas_tp6.demo.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    Pedido updateById(long id, Pedido pedido);

}
