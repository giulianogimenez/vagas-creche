package br.edu.fatecsjc.creche.model;

import br.edu.fatecsjc.creche.utils.LocalDateTimeAttributeAdapter;
import br.edu.fatecsjc.creche.utils.Views;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

@Entity
@Table(name="usr_usuario")
public @Data class Usuario {
    @Id
    @Column(name="usr_id", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Getter @Setter
    @Column(name="usr_email", nullable=false)
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
}