package br.com.tt.petshop.model;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="Produto")
public class Produto {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "valor")
    private BigDecimal valor;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "criacao")
    @CreatedDate
    private LocalDate criacao;
    @Column(name = "alteracao")
    @LastModifiedDate
    private LocalDate alteracao;

    public BigDecimal getValor() {
        return valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
