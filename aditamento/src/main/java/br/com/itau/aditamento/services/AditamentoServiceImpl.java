package br.com.itau.aditamento.services;

import br.com.itau.aditamento.controllers.ContratoPagamento;
import br.com.itau.aditamento.controllers.ContratoParcelas;
import br.com.itau.aditamento.controllers.ContratoResponse;
import br.com.itau.aditamento.domain.Contrato;
import br.com.itau.aditamento.domain.ContratoMapper;
import br.com.itau.aditamento.integrations.JurosResponse;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.itau.aditamento.domain.Contrato.*;

@Service
public class AditamentoServiceImpl implements AditamentoService {

    private final JurosService jurosService;

    @Autowired
    public AditamentoServiceImpl(JurosService jurosService) {
        this.jurosService = jurosService;
    }

    @Override
    public ContratoResponse alterarQuantidadeParcelas(ContratoParcelas contratoRequest) {
        validarContratoAtivo(contratoRequest.getAtivo());
        validarQuantidadeParcelas(contratoRequest);

        Contrato contrato = Mappers.getMapper(ContratoMapper.class).fromRequest(contratoRequest);
        JurosResponse jurosResponse = jurosService.consultarJuros();
        Contrato.Financeiro posCalculo = financeiroPosCalculoParcela(contrato, jurosResponse);

        return Mappers.getMapper(ContratoMapper.class).toResponse(contrato, posCalculo);
    }

    @Override
    public ContratoResponse alterarDiaPagamento(ContratoPagamento contratoRequest) {
        validarContratoAtivo(contratoRequest.getAtivo());
        validar10Dias(contratoRequest.getAditamento().getNovaDataPagamento());
        validarParcelasEmAtraso(contratoRequest.getParcelasEmAtraso());

        Contrato contrato = Mappers.getMapper(ContratoMapper.class).fromRequest(contratoRequest);
        JurosResponse jurosResponse = jurosService.consultarJuros();
        Contrato.Financeiro posCalculo = financeiroPosCalculoPagamento(contrato, jurosResponse);

        return Mappers.getMapper(ContratoMapper.class).toResponse(contrato, posCalculo);
    }
}
