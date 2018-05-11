package br.edu.fatecsjc.creche.controller;

import br.edu.fatecsjc.creche.model.Instituicao;
import br.edu.fatecsjc.creche.repository.InstituicaoRepository;
import br.edu.fatecsjc.creche.service.InstituicaoService;
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
@RequestMapping(value = "/instituicoes")
public class InstituicaoController {

    @Autowired
    InstituicaoRepository repository;
    @Autowired
    InstituicaoService instituicaoService;
    
    @RequestMapping(value = "/salvar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> cadastrarInstituicao(@RequestBody Instituicao instituicao, UriComponentsBuilder ucBuilder) {
        instituicao = instituicaoService.criarInstituicao(instituicao.getNome());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/instituicoes/busca/id/{id}").buildAndExpand(instituicao.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @JsonView(Views.SemId.class)
    @RequestMapping(value = "/busca/id/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public Instituicao buscaPorId(@PathVariable("id") Long id) {
        return repository.findById(id);
    }

    @JsonView(Views.SemId.class)
    @RequestMapping(value = "/busca/nome/{nome}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public Instituicao buscaPorNome(@PathVariable("nome") String nome) {
        return repository.findByNome(nome);
    }

    @JsonView(Views.Basico.class)
    @RequestMapping(value = "/busca/todos/", method = RequestMethod.GET, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public List<Instituicao> listarTodos() {
        return repository.findAll();
    }
}
