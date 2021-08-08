package com.programacion.service.impl;

import java.util.List;
import java.util.Objects;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.opentracing.Traced;

import com.programacion.model.Author;
import com.programacion.service.ServicioAutor;

@Traced
@ApplicationScoped
public class ServicioAutorImpl implements ServicioAutor {

	@Inject
	EntityManager em;

	@Inject
	@ConfigProperty(name = "quarkus.http.port", defaultValue = "8080")
	private Integer appPort;

	public Author findById(Integer id) {
		Author author = em.find(Author.class, id);
		em.detach(author);
		return author;
	}

	@Override
	public List<Author> listAll() {
		TypedQuery<Author> que = em.createQuery("SELECT a FROM Author a order by a.id", Author.class);
		return que.getResultList();
	}

	@Transactional
	@Override
	public Author create(Author author) {
		if (Objects.nonNull(author.getId())) {
			throw new IllegalStateException("Id null");
		}
		em.persist(author);
		return author;
	}

	@Transactional
	@Override
	public void updateAuthor(Author aut) {
		Author entity = em.find(Author.class, aut.getId());
		entity.setAge(aut.getAge());
		entity.setGenre(aut.getGenre());
		entity.setName(aut.getName());
	}

	@Transactional
	@Override
	public void deleteById(Integer id) {
		Author entity = em.find(Author.class, id);
		em.remove(entity);
	}

}