package br.edu.fatecsjc.creche.controller;

import br.edu.fatecsjc.creche.model.Pessoa;
import br.edu.fatecsjc.creche.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaController {

    @Autowired
    PessoaRepository repository;

    @RequestMapping(value = "/salvar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> cadastrarPessoa(@RequestBody Pessoa pessoa, UriComponentsBuilder ucBuilder) {
        pessoa = repository.saveAndFlush(pessoa);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/pessoas/busca/id/{id}").buildAndExpand(pessoa.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/busca/id/{id}", method = RequestMethod.GET)
    public Optional<Pessoa> buscaPorId(@PathVariable("id") Long id) {
        Optional<Pessoa> p = repository.findById(id);
        return p;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Pessoa> listarTodos() {
        return repository.findAll();
    }
}
