package br.com.itau.aditamento.integrations;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "calculo-juros", url = "https://juros-api.itau.br")
public interface JurosClient {
    @PostMapping(value = "/calculo-juros", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    JurosResponse calcularJuros(JurosRequest jurosRequest);
}
