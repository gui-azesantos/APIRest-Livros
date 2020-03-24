package com.gft.socialbooks.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gft.socialbooks.domain.Autor;
import com.gft.socialbooks.repository.AutoresRepository;
import com.gft.socialbooks.services.exception.AutorExistenteException;
import com.gft.socialbooks.services.exception.AutorNaoEncontradoException;

@Service
public class AutoresServices {

	@Autowired
	private AutoresRepository repository;

	// LISTAR
	public List<Autor> listar() {
		return repository.findAll();
	}

	// BUSCAR
	public Autor buscar(Long id) {
		Autor autor= repository.findById(id).orElse(null);
		if (autor == null) {
			throw new AutorNaoEncontradoException("O livro não pôde ser encontrado.");
		}
		return autor ;
	}

	// SALVAR
	public Autor salvar(Autor autor) {
		if (autor.getId() != null) {
			Autor autores = repository.findById(autor.getId()).orElse(null);
			if (autores != null) {
				throw new AutorExistenteException("O autor já existe!");
			}
		}
		return repository.save(autor);
	}

	// DELETAR
	public void delete(Long id) {
		try {
			this.repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new AutorNaoEncontradoException("O Livro não pode ser encontrado");
		}
	}

	// ATUALIZAR
	public void update(Autor autor) {
		isExiste(autor);
		repository.save(autor);

	}

	// VERIFICAR EXISTENCIA
	private void isExiste(Autor autor) {
		buscar(autor.getId());
	}
}