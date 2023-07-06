package br.com.itau.aditamento.services;

import br.com.itau.aditamento.controllers.AditamentoRequestPagamento;
import br.com.itau.aditamento.controllers.AditamentoRequestParcelas;
import br.com.itau.aditamento.controllers.ContratoRequest;
import br.com.itau.aditamento.controllers.ContratoResponse;
import br.com.itau.aditamento.domain.Contrato;
import br.com.itau.aditamento.domain.ContratoMapper;
import br.com.itau.aditamento.integrations.JurosResponse;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
public class AditamentoServiceImpl implements AditamentoService {

    private final JurosService jurosService;

    public AditamentoServiceImpl(JurosService jurosService) {
        this.jurosService = jurosService;
    }

    @Override
    public ContratoResponse alterarQuantidadeParcelas(AditamentoRequestParcelas contratoRequest) {
        Contrato contrato = Contrato.validarDados(contratoRequest);
        JurosResponse jurosResponse = jurosService.consultarJuros();
        Contrato.Financeiro posCalculo = Contrato.financeiroPosCalculo(contrato, jurosResponse);

        return Mappers.getMapper(ContratoMapper.class).toResponse(contrato, posCalculo);
    }

    @Override
    public ContratoResponse alterarDiaPagamento(AditamentoRequestPagamento contratoRequest) {
        return null;
    }
}
