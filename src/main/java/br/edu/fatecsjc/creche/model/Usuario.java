package br.edu.fatecsjc.creche.model;

import br.edu.fatecsjc.creche.utils.LocalDateTimeAttributeAdapter;
import br.edu.fatecsjc.creche.utils.Views;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="usr_usuario")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public @Data class Usuario implements UserDetails {
    @Id
    @Column(name="usr_id", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    @JsonView({Views.Basico.class, Views.Completo.class, Views.Padrao.class})
    private Long id;

    @Getter @Setter
    @Column(name="usr_email", nullable=false, unique = true)
    @JsonView({Views.Basico.class, Views.Completo.class, Views.Padrao.class, Views.SemId.class})
    private String email;

    @Getter @Setter
    @Column(name="usr_nome", nullable=false)
    @JsonView({Views.Basico.class, Views.Completo.class, Views.Padrao.class, Views.SemId.class})
    private String nome;

    @Getter
    @Column(name="usr_password", nullable=false)
    @JsonView(Views.Completo.class)
    private String password;

    @Column(name = "usr_data_cadastro")
    @XmlJavaTypeAdapter(LocalDateTimeAttributeAdapter.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonView({Views.Basico.class, Views.Completo.class, Views.Padrao.class, Views.SemId.class})
    private LocalDateTime dataCadastro;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "uau_usuario_autorizacao",
            joinColumns = { @JoinColumn(name = "usr_id") },
            inverseJoinColumns = { @JoinColumn(name = "aut_id") })
    private List<Autorizacao> autorizacoes;

    public void setPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] bytes = md.digest(password.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            this.password = sb.toString();
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.autorizacoes;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}