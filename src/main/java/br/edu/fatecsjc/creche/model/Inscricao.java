package br.edu.fatecsjc.creche.model;

import br.edu.fatecsjc.creche.utils.LocalDateAttributeAdapter;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Entity
@Table(name = "ins_inscricao")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public @Data class Inscricao implements Serializable {
	@Id
	@Column(name = "ins_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "pss_id", referencedColumnName = "pss_id")
	private Pessoa pessoa;
	
	@Column(name="ins_data_cadastro")
	@JsonFormat(pattern = "dd/MM/yyyy")
	@XmlJavaTypeAdapter(LocalDateAttributeAdapter.class)
	private LocalDateTime dataCadastro;

	@OneToMany(mappedBy="inscricao", targetEntity=OpcaoInstituicao.class)
	private List<OpcaoInstituicao> opcaoInstituicaoList;
	
	@Column(name="ins_situacao")
	private SitucaoInscricao situcaoInscricao;
	
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
		return situcaoInscricao;
	}

	public void setSitucaoInscricao(SitucaoInscricao situcaoInscricao) {
		this.situcaoInscricao = situcaoInscricao;
	}
}
