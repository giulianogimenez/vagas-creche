package br.edu.fatecsjc.creche.service;

import br.edu.fatecsjc.creche.exception.UsuarioException;
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
        UsuarioException usuarioException = new UsuarioException();
        if (email == null || email.isEmpty()) {
            throw new UsuarioException.EmailNuloOuVazioException("Usuário deverá ter um e-mail válido");
        }
        usuario.setEmail(email);
        if (nome == null) {
        	usuarioException.
        }
        usuario.setNome(nome);
        if (senha == null) {
            throw new SenhaNuloOuVazioExption("Email nulo");
        }
        usuario.setPassword(senha);
        try {
            return usuarioRepository.save(usuario);
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
            throw new UsuarioException(e.getMessage());
        }
        
    }
}