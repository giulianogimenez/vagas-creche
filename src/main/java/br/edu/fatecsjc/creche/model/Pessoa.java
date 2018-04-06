package br.edu.fatecsjc.creche.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pss_pessoa")
public class Pessoa {
	@Id
	@Column(name = "pss_id")
	@GeneratedValue
	private Long id;
	
	@Column(name = "pss_nome")
	private String nome;
	
	@Column(name = "pss_data_nascimento")
	private LocalDate dataNascimento;
	
	@Column(name = "pss_data_cadastro")
	private LocalDateTime dataCadastro;
	
	@OneToMany(targetEntity = Inscricao.class, mappedBy = "pessoa", fetch = FetchType.LAZY)
	private List<Inscricao> inscricaoList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public List<Inscricao> getInscricaoList() {
		return inscricaoList;
	}

	public void setInscricaoList(List<Inscricao> inscricaoList) {
		this.inscricaoList = inscricaoList;
	}
}
