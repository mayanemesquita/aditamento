package br.com.itau.aditamento.services;

import br.com.itau.aditamento.controllers.AditamentoRequestPagamento;
import br.com.itau.aditamento.controllers.AditamentoRequestParcelas;
import br.com.itau.aditamento.controllers.ContratoRequest;
import br.com.itau.aditamento.controllers.ContratoResponse;
import br.com.itau.aditamento.domain.Contrato;


public interface AditamentoService {

     ContratoResponse alterarQuantidadeParcelas(AditamentoRequestParcelas contratoRequest);
     ContratoResponse alterarDiaPagamento(AditamentoRequestPagamento contratoRequest);
}
