package br.com.itau.aditamento.controllers;


import br.com.itau.aditamento.services.AditamentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/aditamento")
public class AditamentoController {

    private final AditamentoService aditamentoService;

    public AditamentoController(AditamentoService aditamentoService) {
        this.aditamentoService = aditamentoService;
    }

    @PostMapping("/altera-quantidade-parcelas")
    public ResponseEntity<ContratoResponse> alterarQuantidadeParcelas(@RequestHeader(value = "itau-pos-venda-teste", required = true) UUID itauPosVenda, @Valid @RequestBody AditamentoRequestParcelas contratoRequest) {
        return new ResponseEntity<>(aditamentoService.alterarQuantidadeParcelas(contratoRequest), HttpStatus.OK);
    }

    @PostMapping("/altera-dia-pagamento")
    public ResponseEntity<ContratoResponse> alterarDiaPagamento(@RequestHeader(value = "itau-pos-venda-teste", required = true) UUID itauPosVenda, @Valid @RequestBody AditamentoRequestPagamento contratoRequest) {
        return new ResponseEntity<>(aditamentoService.alterarDiaPagamento(contratoRequest), HttpStatus.OK);
    }


}
