package br.com.itau.aditamento.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContratoPagamento extends ContratoRequest {
    private AditamentoPagamentoRequest aditamento;

    @Getter
    @Setter
    public static class AditamentoPagamentoRequest {
        @JsonProperty("nova_data_pagamento")
        public int novaDataPagamento;
    }

}
