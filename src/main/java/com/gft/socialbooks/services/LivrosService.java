package com.gft.socialbooks.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gft.socialbooks.domain.Livro;
import com.gft.socialbooks.repository.LivrosRepository;
import com.gft.socialbooks.services.exception.LivroExistenteException;
import com.gft.socialbooks.services.exception.LivroNaoEncontradoException;

@Service
public class LivrosService {

	@Autowired
	private LivrosRepository repository;

	// LISTAR
	public List<Livro> listar() {
		return repository.findAll();
	}

	// BUSCAR
	public Optional<Livro> buscar(Long id) {
		Optional<Livro> livro = repository.findById(id);
		if (livro == null) {
			throw new LivroNaoEncontradoException("O Título não foi encontrado!");
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
}
