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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Livros")
@RestController
@RequestMapping("/livros")
public class LivrosResorces {
	@Autowired
	private LivrosService livrosservice;

	// GET
	@ApiOperation("Lista livros")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Livro>> listar() {
		
		CacheControl cachecontrol = CacheControl.maxAge(20, TimeUnit.SECONDS);
		
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cachecontrol).body(livrosservice.listar());
	}

	// POST
	@ApiOperation("Criar livro")
	@RequestMapping( method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@ApiParam(name = "corpo", value="Criação de um novo livro")@Valid @RequestBody Livro livro) {
		livrosservice.salvar(livro);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	// GET (ID)
	@ApiOperation("Lista livros por ID")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> GET(@ApiParam("ID do livro") @PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(livrosservice.buscar(id));
	}

	// DELETE
	@ApiOperation("Deletar livro")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@ApiParam("ID do livro") @PathVariable("id") Long id) {
		this.livrosservice.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	// PUT
	@ApiOperation("Atualizar livro")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@ApiParam(name = "corpo", value="Atualização de um autor")@Valid @RequestBody Livro livro, @PathVariable("id") Long id) {
		livro.setId(id);
		livrosservice.update(livro);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	// ADICIONAR COMENTARIO
	@ApiOperation("Adicionar comentário")
	@RequestMapping(value = "/{id}/comentarios", method = RequestMethod.POST)
	public ResponseEntity<Void> adicionarComentario(@Valid @PathVariable("id") Long livroID,
			@RequestBody Comentario comentario) {
		livrosservice.salvarComentario(livroID, comentario);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		return ResponseEntity.created(uri).build();
	}

	// LISTAR COMENTARIO
	@ApiOperation("Lista comentários")
	@RequestMapping(value = "/{id}/comentarios", method = RequestMethod.GET)
	public ResponseEntity<List<Comentario>> listarComentario(@PathVariable("id") Long livroID) {
		List<Comentario> comentarios = livrosservice.listarComentarios(livroID);
		return ResponseEntity.status(HttpStatus.OK).body(comentarios);
	}

}
