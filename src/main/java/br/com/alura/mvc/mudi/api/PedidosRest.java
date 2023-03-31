package br.com.alura.mvc.mudi.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;

@RestController
@RequestMapping("/api/pedidos")
public class PedidosRest {

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping("aguardando")
    public ResponseEntity<List<Pedido>> getPedidosAguardandoOfertas() {
        
        Sort porId = Sort.by("id").descending();
        PageRequest paginacao = PageRequest.of(0, 5, porId);
        
        List<Pedido> pedidos = pedidoRepository.findByStatus(StatusPedido.AGUARDANDO, paginacao);
        return ResponseEntity.ok().body(pedidos);
    }

}
