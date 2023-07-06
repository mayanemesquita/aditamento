package br.com.itau.aditamento.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AditamentoRequestPagamento extends ContratoRequest {
    private AditamentoRequest aditamento;

    @Getter
    @Setter
    public static class AditamentoRequest {
        @JsonProperty("novo_dia_pagamento")
        public int novoDiaPagamento;
    }

}
