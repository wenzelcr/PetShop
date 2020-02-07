package br.com.tt.petshop.api.handler;

import java.time.LocalDateTime;

public class ApiError {
    private String codigo;
    private String descricao;
    private LocalDateTime horario;

    public ApiError(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.horario =  LocalDateTime.now();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getHorario() {
        return horario;
    }
}
