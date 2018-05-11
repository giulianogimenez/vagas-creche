package br.edu.fatecsjc.creche.controller;

import br.edu.fatecsjc.creche.dto.UsuarioDTO;
import br.edu.fatecsjc.creche.model.Usuario;
import br.edu.fatecsjc.creche.repository.UsuarioRepository;
import br.edu.fatecsjc.creche.service.UsuarioService;
import br.edu.fatecsjc.creche.utils.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioRepository repository;
    @Autowired
    UsuarioService usuarioService;
    
    @RequestMapping(value = "/salvar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> cadastrarUsuario(@RequestBody UsuarioDTO usuarioDTO, UriComponentsBuilder ucBuilder) {
        Usuario usuario = usuarioService.criarUsuario(usuarioDTO.getEmail(), usuarioDTO.getNome(), usuarioDTO.getSenha());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/usuarios/busca/id/{id}").buildAndExpand(usuario.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //@JsonView(Views.SemId.class)
    @RequestMapping(value = "/busca/id/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public Usuario buscaPorId(@PathVariable("id") Long id) {
        return repository.findById(id);
    }

    //@JsonView(Views.SemId.class)
    @RequestMapping(value = "/busca/nome/{nome}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public Usuario buscaPorNome(@PathVariable("nome") String nome) {
        return repository.findByNome(nome);
    }

    //@JsonView(Views.SemId.class)
    @RequestMapping(value = "/busca/email/{email}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public Usuario buscaPorEmail(@PathVariable("email") String email) {
        return repository.findByEmail(email);
    }
    
    //@JsonView(Views.Basico.class)
    @RequestMapping(value = "/busca/todos/", method = RequestMethod.GET, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public List<Usuario> listarTodos() {
        return repository.findAll();
    }
}
