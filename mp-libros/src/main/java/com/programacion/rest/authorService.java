package com.programacion.rest;

import java.util.List; 

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import org.eclipse.microprofile.opentracing.Traced;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.programacion.model.Author;

@Path("/")
@Traced
@RegisterRestClient(configKey = "author")
public interface authorService {
	@GET
	List<Author> getAllAuthors();

	@GET
	@Path("/{id}")
	Author getByidAuthor(@PathParam("id") Integer id);
}
