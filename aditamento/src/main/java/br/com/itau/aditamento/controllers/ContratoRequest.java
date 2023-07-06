package br.com.itau.aditamento.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ContratoRequest {

    @JsonProperty("id_contrato")
    private Long contratoId;
    @JsonProperty("numero_cpf_cnpj_cliente")
    private String nrCpfCnpjCliente;
    @JsonProperty("data_contratacao")
    private String dataContratacao;
    private Boolean ativo;
    @JsonProperty("parcelas_em_atraso")
    private Boolean parcelasEmAtraso;
    private ContratoRequest.FinanceiroRequest financeiro;

    @Getter
    @Setter
    public static class FinanceiroRequest {
        @JsonProperty("data_calculo")
        private String dataCalculo;
        @JsonProperty("tipo_calculo")
        private String tipoCalculo;
        @JsonProperty("quantidade_parcelas")
        private int quantidadeParcelas;
        @JsonProperty("dia_pagamento")
        private int diaPagamento;
        @JsonProperty("valor_total")
        private BigDecimal valorTotal;
        @JsonProperty("valor_parcelas")
        private BigDecimal valorParcelas;
        @JsonProperty("percentual_taxa_juros")
        private BigDecimal percentualTaxaJuros;
    }

}
