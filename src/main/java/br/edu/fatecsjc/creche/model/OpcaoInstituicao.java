package br.edu.fatecsjc.creche.model;

import lombok.Data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonView;

import br.edu.fatecsjc.creche.utils.Views;

@Entity
@Table(name="opi_opcao_instituicao")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public @Data class OpcaoInstituicao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="opi_id")
	@JsonView({Views.Basico.class, Views.Completo.class, Views.Padrao.class})
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "int_id",  referencedColumnName = "int_id")
	@JsonView({Views.Completo.class, Views.Padrao.class, Views.SemId.class})
	private Instituicao instituicao;
	
	@ManyToOne
	@JoinColumn(name = "ins_id", referencedColumnName = "ins_id")
	@JsonView({Views.Completo.class, Views.Padrao.class, Views.SemId.class})
	private Inscricao inscricao;
	
	@Column(name="opi_posicao")
	@JsonView({Views.Basico.class, Views.Completo.class, Views.Padrao.class, Views.SemId.class})
	private Integer posicao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public Inscricao getInscricao() {
		return inscricao;
	}

	public void setInscricao(Inscricao inscricao) {
		this.inscricao = inscricao;
	}

	public Integer getPosicao() {
		return posicao;
	}

	public void setPosicao(Integer posicao) {
		this.posicao = posicao;
	}
}
