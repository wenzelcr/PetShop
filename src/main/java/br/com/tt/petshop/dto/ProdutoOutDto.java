package br.com.tt.petshop.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProdutoOutDto {

    private Long id;
    private BigDecimal valor;
    private String descricao;
    private LocalDate criacao;
    private LocalDate alteracao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
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

    public LocalDate getCriacao() {
        return criacao;
    }

    public void setCriacao(LocalDate criacao) {
        this.criacao = criacao;
    }

    public LocalDate getAlteracao() {
        return alteracao;
    }

    public void setAlteracao(LocalDate alteracao) {
        this.alteracao = alteracao;
    }
}
