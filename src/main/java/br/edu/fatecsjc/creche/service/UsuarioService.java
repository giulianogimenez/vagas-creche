package br.edu.fatecsjc.creche.service;

import br.edu.fatecsjc.creche.exception.EmailExistenteException;
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
        }
        usuario.setEmail(email);
        if (nome == null) {
            throw new NomeNuloOuVazioeption("Nome nulo");
        }
        usuario.setNome(nome);
        if (senha == null) {
            throw new SenhaNuloOuVazioeption("Email nulo");
        }
        usuario.setPassword(senha);
        try {
            return usuarioRepository.save(usuario);
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
            throw new EmailExistenteException(e.getMessage());
        }
        
    }
}