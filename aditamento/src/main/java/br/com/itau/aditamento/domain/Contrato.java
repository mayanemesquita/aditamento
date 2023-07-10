package br.com.itau.aditamento.domain;

import br.com.itau.aditamento.BusinessException;
import br.com.itau.aditamento.controllers.AditamentoRequestPagamento;
import br.com.itau.aditamento.controllers.AditamentoRequestParcelas;
import br.com.itau.aditamento.controllers.ContratoRequest;
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

    public static Contrato validarDados(AditamentoRequestParcelas contratoRequest) {
        validarContratoAtivo(contratoRequest.getAtivo());
        validarQuantidadeParcelas(contratoRequest);
        validarParcelasEmAtraso(contratoRequest.getParcelasEmAtraso());

        return Contrato.builder()
                .contratoId(contratoRequest.getContratoId())
                .nrCpfCnpjCliente(contratoRequest.getNrCpfCnpjCliente())
                .dataContratacao(contratoRequest.getDataContratacao())
                .ativo(contratoRequest.getAtivo())
                .parcelasEmAtraso(contratoRequest.getParcelasEmAtraso())
                .financeiro(Financeiro.buildFinanceiro(contratoRequest.getFinanceiro()))
                .aditamento(new Aditamento(contratoRequest.getAditamento().getNovaQuantidadeParcelas()))
                .build();
    }

    public static Contrato.Financeiro financeiroPosCalculo(Contrato contrato, JurosResponse jurosResponse) {
        return Financeiro.builder()
                .tipoCalculo("ADITAMENTO")
                .valorTotal(jurosResponse.getValorTotal())
                .dataCalculo(String.valueOf(LocalDate.now()))
                .diaPagamento(LocalDate.now().getDayOfMonth())
                .quantidadeParcelas(contrato.getAditamento().novaQuantidadeParcela)
                .valorParcelas(jurosResponse.getValorTotal().divide(BigDecimal.valueOf(contrato.getAditamento().getNovaQuantidadeParcela()), RoundingMode.FLOOR).setScale(2, RoundingMode.FLOOR))
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

        public static Financeiro buildFinanceiro(ContratoRequest.FinanceiroRequest financeiroRequest) {
            if (financeiroRequest.getDiaPagamento() != 0) {
                validar10Dias(financeiroRequest.getDiaPagamento());
            }

            return Financeiro.builder()
                    .tipoCalculo(financeiroRequest.getTipoCalculo())
                    .dataCalculo(financeiroRequest.getDataCalculo())
                    .quantidadeParcelas(financeiroRequest.getQuantidadeParcelas())
                    .valorParcelas(financeiroRequest.getValorParcelas())
                    .valorTotal(financeiroRequest.getValorTotal())
                    .percentualTaxaJuros(financeiroRequest.getPercentualTaxaJuros())
                    .build();
        }
    }

    @Getter
    @Setter
    public static class Aditamento {
        public int novaQuantidadeParcela;
        public int novaDataPagamento;

        public Aditamento(int novaQuantidadeParcela) {
            this.novaQuantidadeParcela = novaQuantidadeParcela;
        }
    }


    private static int recuperarUltimoDigitoContrato(Long contratoId) {
        return String.valueOf(contratoId).charAt(String.valueOf(contratoId).length() - 1);
    }

    public static void validarQuantidadeParcelas(AditamentoRequestParcelas contratoRequest) {
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

    public static void validar10Dias(int diaPagamento) {
        LocalDate pagamento = LocalDate.ofYearDay(2023, diaPagamento);
        LocalDate diaAtual = LocalDate.now();
        if (pagamento.isAfter(diaAtual.plusDays(10L))) {
            throw new RuntimeException();
        }
    }

}
