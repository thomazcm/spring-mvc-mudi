package br.com.alura.mvc.mudi.repository;

import java.util.List;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{
    List<Pedido> findByStatusAndUser_username(StatusPedido status, String username);

    // @Query ("select p from Pedido p join p.user u where u.username = :username")
    // List<Pedido> findByUser_username(@Param("username")String username);
    List<Pedido> findByUser_username(String username);

//    @Cacheable("pedidosHome")
    List<Pedido> findByStatus(StatusPedido status, Pageable paginacao);
}
