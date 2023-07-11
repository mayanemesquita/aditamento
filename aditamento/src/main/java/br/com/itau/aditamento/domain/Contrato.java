package br.com.itau.aditamento.domain;

import br.com.itau.aditamento.BusinessException;
import br.com.itau.aditamento.controllers.ContratoParcelas;
import br.com.itau.aditamento.integrations.JurosResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Getter
@Builder
public class Contrato {

    private Long contratoId;
    private String nrCpfCnpjCliente;
    private int ultimoDigitoContrato;
    private String dataContratacao;
    private Boolean ativo;
    private Boolean parcelasEmAtraso;
    private Financeiro financeiro;
    private Aditamento aditamento;


    public static Contrato.Financeiro financeiroPosCalculoParcela(Contrato contrato, JurosResponse jurosResponse) {
        return Financeiro.builder()
                .tipoCalculo("ADITAMENTO")
                .valorTotal(jurosResponse.getValorTotal())
                .dataCalculo(String.valueOf(LocalDate.now()))
                .diaPagamento(contrato.getFinanceiro().getDiaPagamento())
                .quantidadeParcelas(contrato.getAditamento().getNovaQuantidadeParcelas())
                .valorParcelas(jurosResponse.getValorTotal().divide(BigDecimal.valueOf(contrato.getAditamento().getNovaQuantidadeParcelas()), RoundingMode.FLOOR).setScale(2, RoundingMode.FLOOR))
                .percentualTaxaJuros(jurosResponse.getPercentualJuros())
                .build();
    }

    public static Contrato.Financeiro financeiroPosCalculoPagamento(Contrato contrato, JurosResponse jurosResponse) {
        return Financeiro.builder()
                .tipoCalculo("ADITAMENTO")
                .valorTotal(jurosResponse.getValorTotal())
                .dataCalculo(String.valueOf(LocalDate.now()))
                .diaPagamento(contrato.getAditamento().getNovaDataPagamento())
                .quantidadeParcelas(contrato.getFinanceiro().getQuantidadeParcelas())
                .valorParcelas(jurosResponse.getValorTotal().divide(BigDecimal.valueOf(contrato.getFinanceiro().getQuantidadeParcelas()), RoundingMode.FLOOR).setScale(2, RoundingMode.FLOOR))
                .percentualTaxaJuros(jurosResponse.getPercentualJuros())
                .build();
    }

    @Getter
    @Builder
    public static class Financeiro {
        private String dataCalculo;
        private String tipoCalculo;
        private int quantidadeParcelas;
        private int diaPagamento;
        private BigDecimal valorTotal;
        private BigDecimal valorParcelas;
        private BigDecimal percentualTaxaJuros;

    }

    @Getter
    @Setter
    public static class Aditamento {
        private int novaQuantidadeParcelas;
        private int novaDataPagamento;
    }


    public static void validarQuantidadeParcelas(ContratoParcelas contratoRequest) {
        if (contratoRequest != null && contratoRequest.getAditamento() != null && contratoRequest.getFinanceiro() != null) {
            int quantidadeAditamento = contratoRequest.getAditamento().getNovaQuantidadeParcelas();
            int quantidadeFinanceiro = contratoRequest.getFinanceiro().getQuantidadeParcelas();

            if (quantidadeAditamento <= quantidadeFinanceiro) {
                throw new BusinessException("Quantidade de parcelas não pode ser menor que atual");
            }
        }
    }

    public static void validarContratoAtivo(Boolean ativo) {
        if (ativo.equals(Boolean.FALSE)) {
            throw new BusinessException("Contrato não está ativo");
        }
    }

    public static void validarParcelasEmAtraso(Boolean parcelasEmAtraso) {
        if (parcelasEmAtraso.equals(Boolean.TRUE)) {
            throw new BusinessException("Existe parcelas em atraso para esse contrato");
        }
    }

    public static void validar10Dias(int novaDataPagamento) {

        final int dia = LocalDate.now().getDayOfMonth() + 10;
        if (dia > novaDataPagamento) {
            throw new BusinessException("Dia de pagamento não pode estar mais que 10 dias adiante do dia atual de pagamento ");
        }
    }

}
