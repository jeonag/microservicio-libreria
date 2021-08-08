package com.distribuida.servlets;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.distribuida.model.Autor;
import com.distribuida.model.Libro;
import com.distribuida.service.AutorService;
import com.distribuida.service.LibroService;

@WebServlet({"/","/nuevo-formulario","/editar-formulario","/ingresar-autor","/eliminar-autor","/editar-autor","/buscarPorIdLista","/autores","/libros-por-autor"})
public class AutorServlet extends HttpServlet {
	private static final long serialVersionUID = 2L;

	@Inject
	private AutorService servicio;
	
	@Inject
	private LibroService servicioLibro;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		String action = req.getServletPath();
		System.out.println("\n actionAutor->"+ action);

		try {
			switch (action) {
			case "/nuevo-formulario":
				insertarForm(req, resp); 
				break;
			case "/editar-formulario":
				editarForm(req, resp);
				break;
			case "/ingresar-autor":
				insertarAutor(req, resp); 
				break;
			case "/eliminar-autor":
				eliminarAutor(req, resp);
				break;
			case "/editar-autor":
				editarAutor(req, resp);
				break;
			case "/buscarPorIdLista":
				buscarById(req, resp);
				break;
			case "/libros-por-autor":
				librosByAutor(req, resp);
				break; 
			case "/autores":
				listarAutores(req, resp);
				break; 
//			default:
//				listarAutores(req, resp);
//				break;
			}
		} catch (Exception e) { 
		}
	}

	public void insertarForm(HttpServletRequest req, HttpServletResponse resp) throws IOException {

 
		try {
			req.getRequestDispatcher("inserForm.jsp").forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void editarForm(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			
			int id = Integer.parseInt(req.getParameter("id_author"));
			Autor autor = servicio.buscarById(id);
 
			RequestDispatcher dispatcher = req.getRequestDispatcher("EditarAutor.jsp");
			req.setAttribute("autor", autor);
			dispatcher.forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void insertarAutor(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	
		try {
			System.out.println("servlet [insertarAutor]");
			
			int age = Integer.parseInt(req.getParameter("age"));
			String genre = req.getParameter("genre");
			String name = req.getParameter("name");
			servicio.insertarAutor(new Autor(age, genre, name));

			
		} catch (Exception e) {

			e.printStackTrace();
		}
//		req.setAttribute("idPersona", personaInsertada.getId());
		resp.sendRedirect("autores");
	}

	public void editarAutor(HttpServletRequest req, HttpServletResponse resp) throws IOException {		
	System.out.println("servlet [editarAutor]");
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			int edad = Integer.parseInt(req.getParameter("edad"));
			String genero = req.getParameter("genero");
			String nombre = req.getParameter("nombre"); 
			servicio.editarAutor(new Autor(id,edad, genero, nombre), id);
			resp.sendRedirect("autores");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void eliminarAutor(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		int id = Integer.parseInt(req.getParameter("id_author"));

		try {
			servicio.eliminarAutor(id);
			resp.sendRedirect("autores");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void buscarById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("/autores");
		try {
			int idAutor = Integer.parseInt(req.getParameter("idAutor")); 
			Autor datosAutor = servicio.buscarById(idAutor);
			req.setAttribute("listarAutores", datosAutor);
//			req.getRequestDispatcher("autores.jsp").forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void listarAutores(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("servlet [listarAutores]");
		try {
			List<Autor> datos = servicio.listarAutores();
			req.setAttribute("listarAutores", datos);
			req.getRequestDispatcher("autores.jsp").forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void librosByAutor(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("servlet [librosByAutor]");
		int idAutor = Integer.parseInt(req.getParameter("authorId"));
		String nombreAutor = req.getParameter("nombreAutor");
		try {
			List<Libro> datos = servicioLibro.librosByAutor(idAutor);
			System.out.println(datos);
			req.setAttribute("listarLibrosByAutor", datos);
			req.setAttribute("nombreAutor", nombreAutor);
			req.getRequestDispatcher("ListarLibros.jsp").forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
