package br.edu.fatecsjc.creche.exception;

public class EmailNuloOuVazioException extends RuntimeException {
    public EmailNuloOuVazioException(String message) {
        super(message);
    }
}
