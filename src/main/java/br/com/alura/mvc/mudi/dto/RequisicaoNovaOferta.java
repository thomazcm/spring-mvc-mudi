package br.com.alura.mvc.mudi.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import br.com.alura.mvc.mudi.model.Oferta;

public class RequisicaoNovaOferta {

    private Long pedidoId;

    @NotNull
    @Pattern(regexp = "^\\d+(\\.\\d+{2})?$")
    private String valor;

    @NotNull
    @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4}$")
    private String dataEntrega;
    private String comentario;

    public Oferta toOferta() {
        return new Oferta(valor, dataEntrega, comentario);
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public String getValor() {
        return valor;
    }

    public String getDataEntrega() {
        return dataEntrega;
    }

    public String getComentario() {
        return comentario;
    }


}
