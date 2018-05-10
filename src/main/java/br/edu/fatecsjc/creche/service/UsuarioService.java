package br.edu.fatecsjc.creche.service;

import br.edu.fatecsjc.creche.exception.EmailExistenteException;
import br.edu.fatecsjc.creche.exception.EmailInvalidoException;
import br.edu.fatecsjc.creche.exception.EmailNuloOuVazioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import br.edu.fatecsjc.creche.model.Usuario;
import br.edu.fatecsjc.creche.repository.UsuarioRepository;

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
        if (email == null || email.isEmpty()) {
            throw new EmailNuloOuVazioException("Email nulo");
        } else if (!this.isEmailValido(email)) {
            throw new EmailInvalidoException("Email invalido");
        }
        usuario.setEmail(email);
        if (nome == null) {
            throw new NomeNuloOuVazioeption("Nome nulo");
        }
        usuario.setNome(nome);
        if (senha == null) {
            throw new SenhaNuloOuVazioeption("Email nulo");
        } else if (!this.isSenhaValida(senha)) {
            throw new SenhaInvalidaException("Senha invalida");
        }
        usuario.setPassword(senha);
        try {
            return usuarioRepository.save(usuario);
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
            throw new EmailExistenteException(e.getMessage());
        }
        
    }

    private boolean isSenhaValida(String senha) {
        return true;
    }

    private boolean isEmailValido(String email) {
        return true;
    }
}