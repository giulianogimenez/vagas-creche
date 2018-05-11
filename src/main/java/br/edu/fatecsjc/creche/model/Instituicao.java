package br.edu.fatecsjc.creche.model;

import br.edu.fatecsjc.creche.utils.LocalDateAttributeAdapter;
import br.edu.fatecsjc.creche.utils.Views;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import java.time.LocalDateTime;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Entity
@Table(name = "int_instituicao")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public  @Data class Instituicao {
	@Id
	@Column(name = "int_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonView({Views.Basico.class, Views.Completo.class, Views.Padrao.class})
	private Long id;
	
	@Column(name="int_nome")
	@JsonView({Views.Basico.class, Views.Completo.class, Views.Padrao.class, Views.SemId.class})
	private String nome;
	
	@Column(name="int_data_cadastro")
	@JsonFormat(pattern = "dd/MM/yyyy")
	@XmlJavaTypeAdapter(LocalDateAttributeAdapter.class)
	private LocalDateTime dataCadastro;

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

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Instituicao other = (Instituicao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
