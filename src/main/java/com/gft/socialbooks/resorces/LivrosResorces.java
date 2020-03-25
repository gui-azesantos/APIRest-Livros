package com.gft.socialbooks.resorces;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gft.socialbooks.domain.Comentario;
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
		
		CacheControl cachecontrol = CacheControl.maxAge(20, TimeUnit.SECONDS);
		
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cachecontrol).body(livrosservice.listar());
	}

	// POST
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody Livro livro) {
		livrosservice.salvar(livro);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	// GET (ID)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> GET(@PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(livrosservice.buscar(id));
	}

	// DELETE
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
		this.livrosservice.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	// PUT
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@Valid @RequestBody Livro livro, @PathVariable("id") Long id) {
		livro.setId(id);
		livrosservice.update(livro);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	// ADICIONAR COMENTARIO
	@RequestMapping(value = "/{id}/comentarios", method = RequestMethod.POST)
	public ResponseEntity<Void> adicionarComentario(@Valid @PathVariable("id") Long livroID,
			@RequestBody Comentario comentario) {
		livrosservice.salvarComentario(livroID, comentario);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		return ResponseEntity.created(uri).build();
	}

	// LISTAR COMENTARIO
	@RequestMapping(value = "/{id}/comentarios", method = RequestMethod.GET)
	public ResponseEntity<List<Comentario>> listarComentario(@PathVariable("id") Long livroID) {
		List<Comentario> comentarios = livrosservice.listarComentarios(livroID);
		return ResponseEntity.status(HttpStatus.OK).body(comentarios);
	}

}
