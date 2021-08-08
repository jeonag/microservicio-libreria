package com.programacion.rest;

import java.net.InetAddress;
import java.net.UnknownHostException;
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
import org.eclipse.microprofile.opentracing.Traced;

import com.programacion.model.Author;
import com.programacion.model.Book;
import com.programacion.service.ServicioLibro;

import io.opentracing.Tracer;

@Path("/libros")
@Traced(operationName = "Trace#libros")
@ApplicationScoped
public class RestLibro {

	@Inject
	ServicioLibro serivicioLibro;

	@Inject
	@ConfigProperty(name = "quarkus.http.port", defaultValue = "8080")
	private Integer appPort;

	@Inject
	Tracer tracer;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Book> listar() throws UnknownHostException {
		tracer.activeSpan().setTag("port", appPort);
		tracer.activeSpan().setTag("adrees", InetAddress.getLocalHost().getHostAddress());
		return serivicioLibro.listAll();
	}

	@GET
	@Path("autor")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Author> autores() throws UnknownHostException{
		tracer.activeSpan().setTag("port", appPort);
		tracer.activeSpan().setTag("ip", InetAddress.getLocalHost().getHostAddress());
		return serivicioLibro.listarAuthor();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Book listarById(@PathParam("id") Integer id)throws UnknownHostException {
		tracer.activeSpan().setTag("port", appPort);
		tracer.activeSpan().setTag("ip", InetAddress.getLocalHost().getHostAddress());
		return serivicioLibro.findById(id);
	}

	@GET
	@Path("/{id}/autor")
	@Produces(value = MediaType.APPLICATION_JSON)
	public List<Book> buscarbyAuthor(@PathParam("id") Integer id) throws UnknownHostException{
		tracer.activeSpan().setTag("puerto", appPort);
		tracer.activeSpan().setTag("ip", InetAddress.getLocalHost().getHostAddress());
		return serivicioLibro.listarbyAuthor(id);
	}

	@POST
	@Transactional
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Book book) throws UnknownHostException{

		tracer.activeSpan().setTag("port", appPort);
		tracer.activeSpan().setTag("ip", InetAddress.getLocalHost().getHostAddress());
		serivicioLibro.create(book);
		return Response.created(UriBuilder.fromResource(RestLibro.class).path(String.valueOf(book.getId())).build())
				.build();
	}

	@PUT
	@Path("/{id}")
	@Transactional
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") Integer id, Book book) throws UnknownHostException{
		tracer.activeSpan().setTag("port", appPort);
		tracer.activeSpan().setTag("ip", InetAddress.getLocalHost().getHostAddress());
		serivicioLibro.updateBook(book);
		return Response.noContent().build();
	}

	@DELETE
	@Transactional
	@Path("{id}")
	public void delete(@PathParam("id") Integer id) throws UnknownHostException{
		tracer.activeSpan().setTag("port", appPort);
		tracer.activeSpan().setTag("ip", InetAddress.getLocalHost().getHostAddress());
		Book book = serivicioLibro.findById(id);
		serivicioLibro.deleteById(book.getId());
	}

}
