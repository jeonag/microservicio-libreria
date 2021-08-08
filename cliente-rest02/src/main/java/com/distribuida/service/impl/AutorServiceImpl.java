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

import com.distribuida.model.Autor;
import com.distribuida.model.Libro;
import com.distribuida.service.AutorService;
import com.fasterxml.jackson.databind.ObjectMapper; 

@ApplicationScoped
public class AutorServiceImpl implements AutorService {
	 
	private static final String URL_API = "http://127.0.0.1/mp-autores/autores"; 
	private CloseableHttpClient client = HttpClients.createDefault(); 

	@Override
	public void insertarAutor(Autor autor) throws Exception {

		HttpPost post = new HttpPost(URL_API);
		ObjectMapper objectMapper = new ObjectMapper();
	
		String json = objectMapper.writeValueAsString(new Autor(autor.getAge(),autor.getGenre(),autor.getName()));
		System.out.println(json);

		StringEntity entity = new StringEntity(json); 
		post.setEntity(entity); 
		post.setHeader("Content-type", "application/json"); 
	}

	@Override
	public void editarAutor(Autor autor, int id) throws Exception {
		
		HttpPut update = new HttpPut(URL_API + "/" + id);
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(autor);
		System.out.println(json);

		StringEntity entity = new StringEntity(json);
		update.setEntity(entity);
		update.setHeader("Accept", "application/json");
		update.setHeader("Content-type", "application/json");

		client.execute(update);		
	}

	@Override
	public void eliminarAutor(int idAutor) throws Exception {
		HttpDelete delete = new HttpDelete(URL_API + "/" + idAutor);
		delete.addHeader("Accept", "application/json");
		client.execute(delete);
	}

	@Override
	public Autor buscarById(int idAutor) throws Exception {
		HttpGet get = new HttpGet(URL_API + "/" + idAutor);

		get.addHeader("Accept", "application/json");
		String texto = client.execute(get, response -> EntityUtils.toString(response.getEntity()));
		System.out.println(texto);

		ObjectMapper mapper = new ObjectMapper();

		Autor autor = mapper.readValue(texto, Autor.class);
		return autor;
	}

	@Override
	public List<Autor> listarAutores() throws Exception {
		HttpGet get = new HttpGet(URL_API);
		get.addHeader("Accept", "application/json");
		String texto = client.execute(get, response -> EntityUtils.toString(response.getEntity()));
		ObjectMapper mapper = new ObjectMapper();

		List<Autor> autor = Arrays.asList(mapper.readValue(texto, Autor[].class));
//		System.out.println(autor);
		return autor;
	}

	@Override
	public List<Libro> librosByAutor(int idAutor) throws Exception {
		HttpGet get = new HttpGet("http://localhost:8081/autores" + "/" + idAutor + "/libros");
		get.addHeader("Accept", "application/json");
		String texto = client.execute(get, response -> EntityUtils.toString(response.getEntity()));
		System.out.println(texto);
		ObjectMapper mapper = new ObjectMapper();

		List<Libro> librosByAutor = Arrays.asList(mapper.readValue(texto, Libro[].class));
//		System.out.println(librosByAutor);
		return librosByAutor;
	}
	
	

}
