package br.com.alura.mvc.mudi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/oferta")
public class OfertaController {

    @GetMapping
    public String getFormularioOferta(Model model) {
        model.addAttribute("secao", "ofertas");
        return "oferta/home";
    }

}
