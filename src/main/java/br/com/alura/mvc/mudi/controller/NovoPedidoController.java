package br.com.alura.mvc.mudi.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.alura.mvc.mudi.dto.RequisicaoNovoPedido;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;
import br.com.alura.mvc.mudi.repository.UserRepository;

@Controller
@RequestMapping("pedido")
public class NovoPedidoController {
    private PedidoRepository pedidoRepository;
    private UserRepository userRepository;
    
    @Autowired
    public NovoPedidoController(PedidoRepository pedidoRepository, UserRepository userRepository) {
        this.pedidoRepository = pedidoRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("formulario")
    public String formulario(RequisicaoNovoPedido requisicaoNovoPedido) {
        return "pedido/formulario";
    }
    
    @PostMapping("novo")
    public String novo(@Valid RequisicaoNovoPedido requisicaoNovoPedido, BindingResult result) {
        if(result.hasErrors()) {
            return "pedido/formulario";
        }
        
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        
        var user = userRepository.findById(username).get();
        Pedido pedido = requisicaoNovoPedido.toPedido(user);
        pedidoRepository.save(pedido);
        return "redirect:/usuario/pedido";
    }
}
