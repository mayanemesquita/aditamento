package br.com.itau.aditamento;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
