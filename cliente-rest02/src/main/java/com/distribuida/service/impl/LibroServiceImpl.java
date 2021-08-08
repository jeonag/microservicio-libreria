package com.distribuida.service.impl;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.distribuida.model.Libro;
import com.distribuida.service.LibroService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ApplicationScoped
public class LibroServiceImpl implements LibroService {

	private static final String URL_API = "http://127.0.0.1/mp-libros/libros";
//	private static final String URL_API = "http://localhost:8080/rest02/libros";

	private CloseableHttpClient client = HttpClients.createDefault();

	@Override
	public void insertarLibro(Libro libro) throws Exception { 

		HttpPost post = new HttpPost(URL_API);
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(libro);
		System.out.println(json);

		StringEntity entity = new StringEntity(json);
		post.setEntity(entity);
		post.setHeader("Accept", "application/json");
		post.setHeader("Content-type", "application/json");

		client.execute(post);

	}

	@Override
	public void editarLibro(Libro libro, int id) throws Exception {
		HttpPut update = new HttpPut(URL_API + "/" + id);

		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(libro);
		System.out.println(json);

		StringEntity entity = new StringEntity(json);
		update.setEntity(entity);
		update.setHeader("Accept", "application/json");
		update.setHeader("Content-type", "application/json");

		client.execute(update);

	}

	@Override
	public void eliminarLibro(int idLibro) throws Exception {
		HttpDelete delete = new HttpDelete(URL_API + "/" + idLibro);
		delete.addHeader("Accept", "application/json");
		client.execute(delete);

	}

	@Override
	public Libro buscarById(int idLibro) throws Exception {
		HttpGet get = new HttpGet(URL_API + "/" + idLibro);

		get.addHeader("Accept", "application/json");
		String texto = client.execute(get, response -> EntityUtils.toString(response.getEntity()));
		System.out.println(texto);

		ObjectMapper mapper = new ObjectMapper();

		Libro libro = mapper.readValue(texto, Libro.class);
		return libro;
	}

	@Override
	public List<Libro> listarLibros() throws Exception {
		HttpGet get = new HttpGet(URL_API);
		get.addHeader("Accept", "application/json");
		String texto = client.execute(get, response -> EntityUtils.toString(response.getEntity()));
		ObjectMapper mapper = new ObjectMapper();

		List<Libro> libros = Arrays.asList(mapper.readValue(texto, Libro[].class));
//		System.out.println(autor);
		return libros;
	}
	
	@Override
	public List<Libro> librosByAutor(int idAutor) throws Exception {
		HttpGet get = new HttpGet(URL_API + "/" + idAutor + "/autor");
		
		get.addHeader("Accept", "application/json");
		String texto = client.execute(get, response -> EntityUtils.toString(response.getEntity()));
		System.out.println(texto);
		ObjectMapper mapper = new ObjectMapper();

		List<Libro> librosByAutor = Arrays.asList(mapper.readValue(texto, Libro[].class));
//		System.out.println(librosByAutor);
		return librosByAutor;
	}

}
