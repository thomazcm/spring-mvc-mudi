package br.com.alura.mvc.mudi.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Oferta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Pedido pedido;
    
    private BigDecimal valor;
    private LocalDate dataEntrega;
    private String comentario;
    
    public Oferta() {
    }

    public Oferta(String valor, String dataEntrega, String comentario) {
        this.valor = new BigDecimal(valor);
        this.dataEntrega = LocalDate.parse(dataEntrega, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.comentario = comentario;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Long getId() {
        return id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public String getComentario() {
        return comentario;
    }


}
