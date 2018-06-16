package br.edu.fatecsjc.creche.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;

import br.edu.fatecsjc.creche.dto.InscricaoDTO;
import br.edu.fatecsjc.creche.model.Inscricao;
import br.edu.fatecsjc.creche.model.Pessoa;
import br.edu.fatecsjc.creche.model.SitucaoInscricao;
import br.edu.fatecsjc.creche.service.InscricaoService;
import br.edu.fatecsjc.creche.utils.Views;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/inscricao")
public class InscricaoController {
	@Autowired
	private InscricaoService inscricaoService;
	
	@ApiOperation(value = "Cadastra uma Inscrição e uma Pessoa")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Cadastrado com sucesso!") })
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> cadastrarInscricao(@RequestBody InscricaoDTO inscricaoDTO, UriComponentsBuilder ucBuilder) {
		Inscricao inscricao = inscricaoService.adicionarInscricao(inscricaoDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/inscricao/{id}").buildAndExpand(inscricao.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
	
	@ApiOperation(value = "Busca uma Inscrição pelo id", response = Inscricao.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Busca com sucesso!") })
	@JsonView(Views.SemId.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Inscricao> buscarPorId(@PathVariable("id") Long id) {
		return new ResponseEntity<Inscricao>(inscricaoService.buscarPorId(id), HttpStatus.OK);
    }
	
	@ApiOperation(value = "Busca uma lista de Inscrições por situação", response = Inscricao.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Busca com sucesso!") })
	@JsonView(Views.SemId.class)
    @RequestMapping(value = "/situacao/{situacao}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<Inscricao>> buscarListaDeEspera(@PathVariable("situacao") String situacao) {
		return new ResponseEntity<List<Inscricao>>(inscricaoService.buscarPorSitucaoInscricao(SitucaoInscricao.valueOf(situacao)), HttpStatus.OK);
    }
}
