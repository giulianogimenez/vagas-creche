package br.edu.fatecsjc.creche.model;


public enum SitucaoInscricao {
	LISTA_DE_ESPERA(1, "Lista de Espera"),

	ENCAMINHADA(2, "Encaminhada"),

	MATRICULADA(3, "Matriculada"),

	CANCELADA(4, "Cancelada");
	
	private int id;
	private String descricao;
	
	private SitucaoInscricao(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}
	
	public int getId() {
		return this.id;
	}
}
