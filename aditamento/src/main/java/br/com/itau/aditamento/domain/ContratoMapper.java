package br.com.itau.aditamento.domain;

import br.com.itau.aditamento.controllers.ContratoPagamento;
import br.com.itau.aditamento.controllers.ContratoParcelas;
import br.com.itau.aditamento.controllers.ContratoResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ContratoMapper {

    @Mapping(target = "ultimoDigitoContrato", expression = "java(recuperarUltimoDigitoContrato(contratoRequest.getContratoId()))")
    Contrato fromRequest(ContratoParcelas contratoRequest);
    @Mapping(target = "ultimoDigitoContrato", expression = "java(recuperarUltimoDigitoContrato(contratoRequest.getContratoId()))")
    Contrato fromRequest(ContratoPagamento contratoRequest);

    @Mapping(target = "financeiro", ignore = true)
    ContratoResponse toResponse(Contrato contrato);

    ContratoResponse.FinanceiroResponse fromContrato(Contrato.Financeiro financeiro);

    ContratoResponse.FinanceiroResponse financeiroPosCalculo(Contrato.Financeiro financeiro);

    @AfterMapping
    default ContratoResponse toResponse(Contrato contrato, Contrato.Financeiro financeiro) {

        ContratoResponse contratoResponse = toResponse(contrato);

        ContratoResponse.FinanceiroResponse financeiroResponse = fromContrato(contrato.getFinanceiro());
        ContratoResponse.FinanceiroResponse financeiroResponseJuros = financeiroPosCalculo(financeiro);

        List<ContratoResponse.FinanceiroResponse> financeiroResponseList = new ArrayList<>();
        financeiroResponseList.add(financeiroResponse);
        financeiroResponseList.add(financeiroResponseJuros);
        contratoResponse.setFinanceiro(financeiroResponseList);

        return contratoResponse;

    }

    default int recuperarUltimoDigitoContrato(Long contratoId) {
        return Integer.parseInt(String.valueOf(String.valueOf(contratoId).charAt(String.valueOf(contratoId).length() - 1)));
    }

}
