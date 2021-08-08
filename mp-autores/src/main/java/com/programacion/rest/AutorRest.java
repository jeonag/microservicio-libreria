package com.programacion.rest;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Produces;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.opentracing.Traced;

import com.programacion.model.Author;
import com.programacion.service.ServicioAutor;

import io.opentracing.Tracer;

@Path("/autores")
@Traced(operationName = "Trace#autores")
@ApplicationScoped
public class AutorRest {
	

	@Inject
	ServicioAutor servicioAutor;

	@Inject
	@ConfigProperty(name = "quarkus.http.port", defaultValue = "8080")
	private Integer appPort;

	@Inject
	@ConfigProperty(name = "configsource.consul.host", defaultValue = "127.0.0.1")
	private String consulHost;

	@Inject
	Tracer tracer;

	@Counted
	@Fallback(fallbackMethod = "getAuthor")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Author> listAll() {
		tracer.activeSpan().setTag("port", appPort);
		tracer.activeSpan().setTag("ip", consulHost);
		return servicioAutor.listAll();
	}


	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Author findById(@PathParam("id") Integer id) {
		tracer.activeSpan().setTag("port", appPort);
		tracer.activeSpan().setTag("ip", consulHost);
		return servicioAutor.findById(id);

	}

	@POST
	@Transactional
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Author author) {
		tracer.activeSpan().setTag("port", appPort);
		tracer.activeSpan().setTag("ip", consulHost);
		servicioAutor.create(author);
		return Response.created(UriBuilder.fromResource(AutorRest.class).path(String.valueOf(author.getId())).build())
				.build();

	}

	@PUT
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	public Response update(@PathParam("id") Integer id, Author autor) {
		tracer.activeSpan().setTag("port", appPort);
		tracer.activeSpan().setTag("ip", consulHost);
		servicioAutor.updateAuthor(autor);
		return Response.noContent().build();

	}

	@DELETE
	@Transactional
	@Path("{id}")
	public void delete(@PathParam("id") Integer id) {
		tracer.activeSpan().setTag("port", appPort);
		tracer.activeSpan().setTag("ip", consulHost);
		Author autor = servicioAutor.findById(id);
		servicioAutor.deleteById(autor.getId());
	}

	public List<Author> getAuthor() {
		List<Author> lista = new ArrayList<>();
		Author au = new Author();
		au.setId(1);
		au.setAge(50);
		au.setGenre("H");
		au.setName("juan");
		lista.add(au);
		return lista;
	}
}
