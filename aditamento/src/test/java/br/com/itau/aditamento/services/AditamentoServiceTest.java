package br.com.itau.aditamento.services;

import br.com.itau.aditamento.controllers.ContratoPagamento;
import br.com.itau.aditamento.controllers.ContratoParcelas;
import br.com.itau.aditamento.controllers.ContratoResponse;
import br.com.itau.aditamento.domain.Contrato;
import br.com.itau.aditamento.domain.ContratoMapper;
import br.com.itau.aditamento.integrations.JurosResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static br.com.itau.aditamento.domain.Contrato.financeiroPosCalculoParcela;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class AditamentoServiceTest {

    @Mock
    JurosService jurosService;
    @Mock
    AditamentoService aditamentoService;


    @Test
    void alterarQuantidadeParcelas() {

       when(jurosService.consultarJuros()).thenReturn(new JurosResponse());
       when(financeiroPosCalculoParcela(Contrato.builder().build(), new JurosResponse())).thenReturn();

        ContratoResponse contrato = aditamentoService.alterarQuantidadeParcelas(new ContratoParcelas());

        assertNotNull(contrato);
    }

    @Test
    void alterarDiaPagamento() {
    }


    public static ContratoPagamento contratoPagamento(){
       ContratoPagamento contratoPagamento = new ContratoPagamento();
       contratoPagamento.setContratoId(123644L);
       return contratoPagamento;
    }

}