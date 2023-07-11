package br.com.itau.aditamento.controllers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContratoResponse {
    @JsonProperty("id_contrato")
    private Long contratoId;
    @JsonProperty("ultimo_digito_contrato")
    private int ultimoDigitoContrato;
    @JsonProperty("numero_cpf_cnpj_cliente")
    private String nrCpfCnpjCliente;
    @JsonProperty("data_contratacao")
    private String dataContratacao;
    private Boolean ativo;
    @JsonProperty("parcelas_em_atraso")
    private Boolean parcelasEmAtraso;
    private List<FinanceiroResponse> financeiro;

    @Getter
    @Setter
    public static class FinanceiroResponse {
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
