package br.edu.fatecsjc.creche.exception;

public class EmailExistenteException extends RuntimeException {
    public EmailExistenteException(String message) {
        super(message);
    }
}
