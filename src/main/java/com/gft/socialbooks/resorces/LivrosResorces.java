package com.gft.socialbooks.resorces;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.gft.socialbooks.domain.Livro;
import com.gft.socialbooks.services.LivrosService;

@RestController
@RequestMapping("/livros")
public class LivrosResorces {
	@Autowired
	private LivrosService livrosservice;

	// GET
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Livro>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(livrosservice.listar());
	}

	// POST
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody Livro livro) {
		livrosservice.salvar(livro);
		return null;
	}

	// GET (ID)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Optional<Livro>> GET(@PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(livrosservice.buscar(id));
	}

	// DELETE
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
		this.livrosservice.delete(id);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	// PUT
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody Livro livro, @PathVariable("id") Long id) {
		livro.setId(id);
		livrosservice.update(livro);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
