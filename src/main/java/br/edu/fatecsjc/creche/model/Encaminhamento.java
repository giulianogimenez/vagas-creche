package br.edu.fatecsjc.creche.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name = "enc_encaminhamento")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public @Data class Encaminhamento implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "enc_id")
    @JsonView({Views.Basico.class, Views.Completo.class, Views.Padrao.class})
    private Long id;
    
    @OneToOne
	@JoinColumn(name = "opi_id", referencedColumnName = "opi_id")
	@JsonView({Views.Basico.class, Views.Completo.class, Views.Padrao.class, Views.SemId.class})
    private OpcaoInstituicao opcaoInstituicao;
    
    @Column(name = "enc_data_encaminhamento")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@XmlJavaTypeAdapter(LocalDateAttributeAdapter.class)
	@JsonView({Views.Basico.class, Views.Completo.class, Views.Padrao.class, Views.SemId.class})
    private LocalDate dataEncaminhamento;

}
