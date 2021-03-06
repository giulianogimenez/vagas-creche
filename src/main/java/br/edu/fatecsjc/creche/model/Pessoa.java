package br.edu.fatecsjc.creche.model;

import br.edu.fatecsjc.creche.utils.LocalDateAttributeAdapter;
import br.edu.fatecsjc.creche.utils.LocalDateTimeAttributeAdapter;
import br.edu.fatecsjc.creche.utils.Views;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Entity
@Table(name = "pss_pessoa")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public @Data class Pessoa {
	@Id
	@Column(name = "pss_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({Views.Basico.class, Views.Completo.class, Views.Padrao.class})
	private Long id;
	
	@Column(name = "pss_nome")
	@JsonView({Views.Basico.class, Views.Completo.class, Views.Padrao.class, Views.SemId.class})
	private String nome;
	
	@Column(name = "pss_data_nascimento")
	@JsonView({Views.Basico.class, Views.Completo.class, Views.Padrao.class, Views.SemId.class})
	@JsonFormat(pattern = "yyyy-MM-dd")
	@XmlJavaTypeAdapter(LocalDateAttributeAdapter.class)
	private LocalDate dataNascimento;
	
	@Column(name = "pss_data_cadastro")
	@JsonView({Views.Basico.class, Views.Completo.class, Views.Padrao.class, Views.SemId.class})
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@XmlJavaTypeAdapter(LocalDateTimeAttributeAdapter.class)
	private LocalDateTime dataCadastro;
	
	@OneToMany(targetEntity = Inscricao.class, mappedBy = "pessoa", fetch = FetchType.LAZY)
	@JsonView(Views.Completo.class)
	@XmlTransient
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
