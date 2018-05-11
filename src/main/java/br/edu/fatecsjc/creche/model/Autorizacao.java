package br.edu.fatecsjc.creche.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import br.edu.fatecsjc.creche.utils.Views;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "aut_autorizacao")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public @Data class Autorizacao implements GrantedAuthority {

    private static final long serialVersionUID = -7578937961979778761L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "aut_id")
    @JsonView({Views.Basico.class, Views.Completo.class, Views.Padrao.class})
    private Long id;

    @Column(name = "aut_nome", unique=true, length = 20, nullable = false)
    @JsonView({Views.Basico.class, Views.Completo.class, Views.Padrao.class, Views.SemId.class})
    private String nome;

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

    @Override
    public String getAuthority() {
        return this.nome;
    }

    public void setAuthority(String authority) {
        this.nome = authority;
    }

}