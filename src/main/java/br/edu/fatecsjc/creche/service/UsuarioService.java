package br.edu.fatecsjc.creche.service;

import br.edu.fatecsjc.creche.exception.UsuarioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import br.edu.fatecsjc.creche.model.Usuario;
import br.edu.fatecsjc.creche.repository.UsuarioRepository;

import java.time.LocalDateTime;

import javax.validation.ConstraintViolationException;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Usuario usuario = usuarioRepository.findByEmail(username);
        if (usuario == null) {
            return null;
        }
        return usuario;
    }
    
    public Usuario criarUsuario(String email, String nome, String senha) {
        Usuario usuario = new Usuario();
        StringBuilder errorsMsgs = new StringBuilder();
        if (email == null || email.isEmpty()) {
        	errorsMsgs.append("E-mail deverá ser obrigatório.\n");
        } else if (!this.isEmailValido(email)) {
        	errorsMsgs.append("E-mail inserido é inválido.\n");
        } else {
            usuario.setEmail(email);
        }
        if (nome == null || nome.isEmpty()) {
        	errorsMsgs.append("O nome deverá ser obrigatório.\n");
        } else {
            usuario.setNome(nome);
        }
        if (senha == null || senha.isEmpty()) {
        	errorsMsgs.append("Senha deverá ser obrigatório.\n");
        } else if (!this.isSenhaValida(senha)) {
        	errorsMsgs.append("A senha não é válida.\n");
        } else {
            usuario.setPassword(senha);
        }
        if(!errorsMsgs.toString().isEmpty())
        	throw new UsuarioException(errorsMsgs.toString());
        try {
        	usuario.setDataCadastro(LocalDateTime.now());
            return usuarioRepository.saveAndFlush(usuario);
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
            throw new UsuarioException(e.getMessage());
        }
        
    }

    private boolean isSenhaValida(String senha) {
        if(senha.contains(" ")) {
        	return false;
        }
        if(senha.length() < 6) {
        	return false;
        }
        return true;
    }

    private boolean isEmailValido(String email) {
        if(email.contains("@") && email.contains(".")) {
        	return true;
        }
        return false;
    }
}