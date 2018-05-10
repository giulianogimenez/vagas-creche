package br.edu.fatecsjc.creche.exception;

public class UsuarioException extends RuntimeException {
    private String errorMessage = "";

	public UsuarioException(String message) {
		super(message);
	}
	
	public UsuarioException() {
		super();
	}

	public void addErrorMessage(String msg) {
    	this.errorMessage += msg + ".\n";
    }
}
