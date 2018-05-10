package br.edu.fatecsjc.creche.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="usr_usuario")
public @Data class Usuario implements UserDetails {
    @Id
    @Column(name="usr_id", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Getter @Setter
    @Column(name="usr_email", nullable=false, unique = true)
    private String email;

    @Getter @Setter
    @Column(name="usr_nome", nullable=false)
    private String nome;

    @Getter
    @Column(name="usr_password", nullable=false)
    private String password;

    @Column(name = "usr_data_cadastro")
    //@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    //@XmlJavaTypeAdapter(LocalDateTimeAttributeAdapter.class)
    private LocalDateTime dataCadastro;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "UAU_USUARIO_AUTORIZACAO",
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