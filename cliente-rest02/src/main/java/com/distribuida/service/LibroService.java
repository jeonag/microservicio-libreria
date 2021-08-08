package com.distribuida.service;

import java.util.List;

import com.distribuida.model.Libro;

public interface LibroService {
	
	void insertarLibro(Libro libro) throws Exception;

	void editarLibro(Libro libro, int id) throws Exception;

	void eliminarLibro(int idLibro) throws Exception;

	Libro buscarById(int idLibro) throws Exception;

	List<Libro> listarLibros() throws Exception;
	
	List<Libro> librosByAutor(int idAutor) throws Exception;

}
