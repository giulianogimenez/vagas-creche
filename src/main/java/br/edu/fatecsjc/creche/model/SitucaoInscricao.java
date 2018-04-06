package br.edu.fatecsjc.creche.model;


public enum SitucaoInscricao {
	LISTA_DE_ESPERA("Lista de Espera"), ENCAMINHADA("Encaminhada"), MATRICULADA("Matriculada"), CANCELADA("Cancelada");
	
	private String descricao;

	public String getDescricao() {
		return descricao;
	}
	
	private SitucaoInscricao(String descricao) {
		this.descricao = descricao;
	}
}
