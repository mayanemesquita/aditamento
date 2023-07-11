package br.com.itau.aditamento.services;

import br.com.itau.aditamento.controllers.ContratoPagamento;
import br.com.itau.aditamento.controllers.ContratoParcelas;
import br.com.itau.aditamento.controllers.ContratoResponse;


public interface AditamentoService {

     ContratoResponse alterarQuantidadeParcelas(ContratoParcelas contratoRequest);
     ContratoResponse alterarDiaPagamento(ContratoPagamento contratoRequest);
}
