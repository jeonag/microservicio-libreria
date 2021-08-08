package com.distribuida.service;

import java.util.List;

import com.distribuida.model.Autor;
import com.distribuida.model.Libro;

public interface AutorService {

	public void insertarAutor(Autor autor) throws Exception;

	void editarAutor(Autor autor, int id) throws Exception;

	void eliminarAutor(int idAutor) throws Exception;

	Autor buscarById(int idAutor) throws Exception;

	List<Autor> listarAutores() throws Exception;
	
	List<Libro> librosByAutor(int idAutor) throws Exception;

}
