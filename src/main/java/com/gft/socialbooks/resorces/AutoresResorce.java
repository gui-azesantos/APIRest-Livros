package com.gft.socialbooks.resorces;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gft.socialbooks.domain.Autor;
import com.gft.socialbooks.services.AutoresServices;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Autores")
@RestController
@RequestMapping("/autores")
public class AutoresResorce {

	@Autowired
	private AutoresServices autoresservice;

	// GET
	@ApiOperation("Lista os autores")
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Autor>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(autoresservice.listar());
	}

	// POST
	@ApiOperation("Criar autor")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@ApiParam(name = "corpo", value="Criação de um novo autor")@Valid @RequestBody Autor autor) {
		autoresservice.salvar(autor);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	// GET (ID)
	@ApiOperation("Lista autor por ID")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> GET(@ApiParam("ID do livro") @PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(autoresservice.buscar(id));
	}

	// DELETE
	@ApiOperation("Deletar autor")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@ApiParam("ID do livro") @PathVariable("id") Long id) {
		this.autoresservice.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	// PUT
	@ApiOperation("Atualizar autor")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@ApiParam(name = "corpo", value="Atualização de um autor")@Valid @RequestBody Autor autor, @PathVariable("id") Long id) {
		autor.setId(id);
		autoresservice.update(autor);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
