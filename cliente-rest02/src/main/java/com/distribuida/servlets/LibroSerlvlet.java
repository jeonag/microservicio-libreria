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

import com.distribuida.model.Libro;
import com.distribuida.service.LibroService;

@WebServlet({ "/libros", "/form-libro", "/form-edit-libro", "/ingresar-libro", "/eliminar-libro", "/editar-libro" })
public class LibroSerlvlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private LibroService servicio;
 

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		String action = req.getServletPath();
		System.out.println("actionLibro->" + action);
		System.out.println("req.getContextPath()->" + req.getContextPath());

		try {
			switch (action) {
			case "/form-libro":
				insertarForm(req, resp);
				break;
			case "/form-edit-libro":
				editarForm(req, resp);
				break;
			case "/ingresar-libro":
				insertarLibro(req, resp);

				break;
			case "/eliminar-libro":
				eliminarLibro(req, resp);
				break;
			case "/editar-libro":
				editarLibro(req, resp);
				break; 
			default:
				listarLibros(req, resp);
				break;
			}
		} catch (Exception e) { 
		}
	}

	public void insertarForm(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("InsertarForm -> libro");

		try {
			req.getRequestDispatcher("/libros/InsertarLibro.jsp").forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void editarForm(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {

			int id = Integer.parseInt(req.getParameter("id"));
			Libro libro = servicio.buscarById(id); 
			RequestDispatcher dispatcher = req.getRequestDispatcher("/libros/EditarLibro.jsp");
			req.setAttribute("libro", libro);
			dispatcher.forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public void insertarLibro(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		try {
			System.out.println("servlet [insertarLibro]");

			String isbn = req.getParameter("isbn");
			String titulo = req.getParameter("titulo");
			int authorId = Integer.parseInt(req.getParameter("authorId"));
			
			servicio.insertarLibro(new Libro(isbn, titulo, authorId));
			
			resp.sendRedirect("libros");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void editarLibro(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("servlet [editarLibro]");
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			String isbn = req.getParameter("isbn");
			String titulo = req.getParameter("titulo");
			int author_id = Integer.parseInt(req.getParameter("author_id"));
			
			servicio.editarLibro(new Libro(id,author_id, isbn, titulo), id);
			resp.sendRedirect("libros");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void eliminarLibro(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			servicio.eliminarLibro(id);
			resp.sendRedirect("libros");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void buscarById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			
			System.out.println("servletLibro-> [buscarById]");
			int idAutor = Integer.parseInt(req.getParameter("idAutor"));
			Libro datosLibro = servicio.buscarById(idAutor);
			req.setAttribute("listarLibros", datosLibro); 

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void listarLibros(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		try {
			System.out.println("servletLibro -> [listarLibros]");
			List<Libro> datos = servicio.listarLibros();
			
			req.setAttribute("listarLibros", datos);
			req.getRequestDispatcher("/libros/libros.jsp").forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
