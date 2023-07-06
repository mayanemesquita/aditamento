package br.com.itau.aditamento.services;

import br.com.itau.aditamento.integrations.JurosClient;
import br.com.itau.aditamento.integrations.JurosResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Log4j2
@Service
public class JurosService {
    private final JurosClient jurosClient;

    public JurosService(JurosClient jurosClient) {
        this.jurosClient = jurosClient;
    }


    public JurosResponse consultarJuros() {

        // jurosClient.calcularJuros(new JurosRequest());
        JurosResponse jurosResponse = new JurosResponse();
        jurosResponse.setPercentualJuros(new BigDecimal("1.93"));
        jurosResponse.setValorTotal(new BigDecimal("52000"));
        return jurosResponse;
    }
}
