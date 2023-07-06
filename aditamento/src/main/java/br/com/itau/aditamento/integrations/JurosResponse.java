package br.com.itau.aditamento.integrations;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@JsonIgnoreProperties
public class JurosResponse {
    @JsonProperty("percentual_juros")
    private BigDecimal percentualJuros;
    @JsonProperty("valor_total")
    private BigDecimal valorTotal;
}
