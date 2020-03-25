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

@RestController
@RequestMapping("/autores")
public class AutoresResorce {

	@Autowired
	private AutoresServices autoresservice;

	// GET
	@RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<Autor>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(autoresservice.listar());
	}

	// POST
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody Autor autor) {
		autoresservice.salvar(autor);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	// GET (ID)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> GET(@PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(autoresservice.buscar(id));
	}

	// DELETE
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
		this.autoresservice.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	// PUT
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@Valid @RequestBody Autor autor, @PathVariable("id") Long id) {
		autor.setId(id);
		autoresservice.update(autor);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
