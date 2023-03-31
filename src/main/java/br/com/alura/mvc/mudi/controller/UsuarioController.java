package br.com.alura.mvc.mudi.controller;

import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.repository.PedidoRepository;

@Controller
@RequestMapping("/usuario/pedido")
public class UsuarioController {
    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    public String home(Model model, Principal principal) {
        String username = principal.getName();
        List<Pedido> pedidos = pedidoRepository.findByUser_username(username);
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("secao", "pedidos");
        return "usuario/home";
    }

    @GetMapping("/{status}")
    public String porStatus(@PathVariable("status") String status, Model model,
            Principal principal) {

        var statusEnum = StatusPedido.valueOf(status.toUpperCase());
        var username = principal.getName();
        
        List<Pedido> pedidos = pedidoRepository.findByStatusAndUser_username(statusEnum, username);
        
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("status", status);
        model.addAttribute("secao", "pedidos");
        return "usuario/home";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String onError() {
        return "redirect:usuario//home";
    }
}
