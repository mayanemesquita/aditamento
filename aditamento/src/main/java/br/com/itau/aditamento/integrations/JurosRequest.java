package br.com.itau.aditamento.integrations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties
public class JurosRequest {

    @JsonProperty("definir_data_contratacao")
    private String dataContratacao;
    @JsonProperty("definir_criterio_calculo")
    private String criterioCalculo;
    @JsonProperty("definir_quantidade_parcelas")
    private String quantidadeParcelas;
    @JsonProperty("definir_valor_contratacao")
    private String valorParcelas;
}
