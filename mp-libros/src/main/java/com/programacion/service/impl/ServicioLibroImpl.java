package com.programacion.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.eclipse.microprofile.opentracing.Traced;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.programacion.model.Author;
import com.programacion.model.Book;
import com.programacion.rest.authorService;
import com.programacion.service.ServicioLibro;

@Traced
@ApplicationScoped
public class ServicioLibroImpl implements ServicioLibro {

	@Inject
	EntityManager em;

	@Inject
	@RestClient
	authorService autorServicio;

	@Override
	public Book findById(Integer id) {
		Book book = em.find(Book.class, id);
		return book;
	}

	@Override
	public List<Book> listAll() {
		TypedQuery<Book> que = em.createQuery("SELECT b FROM Book b order by b.id", Book.class);
//		return que.getResultList();
		List<Book> libros = que.getResultList();
		List<Book> listaAux = new ArrayList<>();

		for (Book book : libros) {
			book.setAuthor(autorServicio.getByidAuthor(book.getAuthor_id()));
			listaAux.add(book);
		}
		return listaAux;
	}

	@Transactional
	@Override
	public Book create(Book book) {
		if (Objects.nonNull(book.getId())) {
			throw new IllegalStateException("Id null");
		}
		em.persist(book);
		return book;
	}

	@Transactional
	@Override
	public void updateBook(Book book) {
		Book entity = em.find(Book.class, book.getId());
		entity.setIsbn(book.getIsbn());
		entity.setTitle(book.getTitle());
		entity.setAuthor_id(book.getAuthor_id());
	}

	@Transactional
	@Override
	public void deleteById(Integer id) {
		Book entity = em.find(Book.class, id);
		em.remove(entity);
	}

	@Override
	public List<Book> listarbyAuthor(Integer idAuthor) {
		TypedQuery<Book> que = em.createQuery("SELECT b FROM Book b WHERE b.author_id = :idAuthor", Book.class)
				.setParameter("idAuthor", idAuthor);
		return que.getResultList();
	}

	@Override
	public List<Author> listarAuthor() {
		return autorServicio.getAllAuthors();
	}

}
