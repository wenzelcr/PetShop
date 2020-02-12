package br.com.tt.petshop.dto;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProdutoInDto {

    @Min(message = "O valor minimo deve ser 1", value = 1)
    private BigDecimal valor;

    @NotNull(message = "Deve ser inserida uma descricao do produto.")
    private String descricao;

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
}
