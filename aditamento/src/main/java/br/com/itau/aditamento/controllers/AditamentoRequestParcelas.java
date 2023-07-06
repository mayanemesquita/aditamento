package br.com.itau.aditamento.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class AditamentoRequestParcelas extends ContratoRequest{

    private AditamentoRequest aditamento;

    @Getter
    @Setter
    public static class AditamentoRequest {
        @JsonProperty("nova_quantidade_parcelas")
        public int novaQuantidadeParcelas;
    }
}
