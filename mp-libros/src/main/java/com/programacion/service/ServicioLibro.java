package com.programacion.service;

import java.util.List;

import com.programacion.model.Author;
import com.programacion.model.Book; 

public interface ServicioLibro {
	public Book findById(Integer id);

	public List<Book> listAll();

	public Book create(Book book);

	public void updateBook(Book book);

	public void deleteById(Integer id);

	public List<Author> listarAuthor();

	public List<Book> listarbyAuthor(Integer idAuthor);

}
