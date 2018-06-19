package br.edu.fatecsjc.creche.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;

import br.edu.fatecsjc.creche.utils.LocalDateAttributeAdapter;
import br.edu.fatecsjc.creche.utils.Views;
import lombok.Data;

@Entity
@Table(name = "ins_inscricao")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public @Data class Inscricao implements Serializable {
	@Id
	@Column(name = "ins_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({Views.Basico.class, Views.Completo.class, Views.Padrao.class})
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "pss_id", referencedColumnName = "pss_id")
	@JsonView({Views.Basico.class, Views.Completo.class, Views.Padrao.class, Views.SemId.class})
	private Pessoa pessoa;
	
	@Column(name="ins_data_cadastro")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@XmlJavaTypeAdapter(LocalDateAttributeAdapter.class)
	@JsonView({Views.Basico.class, Views.Completo.class, Views.Padrao.class, Views.SemId.class})
	private LocalDateTime dataCadastro;

	@OneToMany(mappedBy="inscricao", targetEntity=OpcaoInstituicao.class)
	@JsonView({Views.Basico.class, Views.Completo.class, Views.Padrao.class, Views.SemId.class})
	private List<OpcaoInstituicao> opcaoInstituicaoList;
	
	@Column(name="ins_situacao")
	@JsonView({Views.Basico.class, Views.Completo.class, Views.Padrao.class, Views.SemId.class})
	private SitucaoInscricao situacaoInscricao;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public List<OpcaoInstituicao> getOpcaoInstituicaoList() {
		return opcaoInstituicaoList;
	}

	public void setOpcaoInstituicaoList(List<OpcaoInstituicao> opcaoInstituicaoList) {
		this.opcaoInstituicaoList = opcaoInstituicaoList;
	}

	public SitucaoInscricao getSitucaoInscricao() {
		return situacaoInscricao;
	}

	public void setSitucaoInscricao(SitucaoInscricao situcaoInscricao) {
		this.situacaoInscricao = situcaoInscricao;
	}
}
