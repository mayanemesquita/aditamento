package br.com.itau.aditamento.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ContratoParcelas extends ContratoRequest{

    private AditamentoParcelasRequest aditamento;

    @Getter
    @Setter
    public static class AditamentoParcelasRequest {
        @JsonProperty("nova_quantidade_parcelas")
        public int novaQuantidadeParcelas;
    }
}
