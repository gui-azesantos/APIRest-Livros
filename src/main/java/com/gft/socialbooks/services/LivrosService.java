package com.gft.socialbooks.services;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gft.socialbooks.domain.Comentario;
import com.gft.socialbooks.domain.Livro;
import com.gft.socialbooks.repository.ComentariosRepository;
import com.gft.socialbooks.repository.LivrosRepository;
import com.gft.socialbooks.services.exception.LivroExistenteException;
import com.gft.socialbooks.services.exception.LivroNaoEncontradoException;

@Service
public class LivrosService {

	@Autowired
	private LivrosRepository repository;
	@Autowired
	private ComentariosRepository comentariosrepository;

	// LISTAR
	public List<Livro> listar() {
		return repository.findAll();
	}

	// BUSCAR
	public Livro buscar(Long id) {
		Livro livro = repository.findById(id).orElse(null);
		if (livro == null) {
			throw new LivroNaoEncontradoException("O livro não pôde ser encontrado.");
		}
		return livro;
	}

	// SALVAR
	public Livro salvar(Livro livro) {
		if (livro.getId() != null) {
			Livro livros = repository.findById(livro.getId()).orElse(null);
			if (livros != null) {
				throw new LivroExistenteException("O título já existe!");
			}
		}
		return repository.save(livro);
	}

	// DELETAR
	public void delete(Long id) {
		try {
			this.repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new LivroNaoEncontradoException("O Livro não pode ser encontrado");
		}
	}

	// ATUALIZAR
	public void update(Livro livro) {
		isExiste(livro);
		repository.save(livro);

	}

	// VERIFICAR EXISTENCIA
	private void isExiste(Livro livro) {
		buscar(livro.getId());
	}

	// SALVAR COMENTARIO
	public Comentario salvarComentario(Long livroId, Comentario comentario) {
		Livro livro = buscar(livroId);
		comentario.setLivro(livro);
		comentario.setData(new Date());

		return comentariosrepository.save(comentario);
	}
	//LISTAR COMENTARIOS
	public List<Comentario> listarComentarios (Long livroID){
		Livro livro = buscar(livroID);
		return livro.getComentarios();
	}

}
