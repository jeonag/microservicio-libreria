package com.programacion.service;

import java.util.List;
import com.programacion.model.Author;

public interface ServicioAutor {

	public Author findById(Integer id);

	public List<Author> listAll();

	public Author create(Author author);

	public void updateAuthor(Author aut);

	public void deleteById(Integer id);

}
