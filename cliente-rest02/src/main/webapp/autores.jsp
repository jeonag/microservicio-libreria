<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Autores</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> 


</head>
<body>

  

	<nav class="topNav navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="autores">LibreriaDistribuida</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="autores">Autores</a></li>
				<li><a href="libros">Libros</a></li>
			</ul>
		</div>
	</nav>

	<br>
	<br>
	
	<div class="container">
		<div class="row">
			<div class="col-xs-7">
				<div class="container">
					<a href="<%=request.getContextPath()%>/nuevo-formulario"
						class="btn btn-primary"> Crear Autor</a> <br>
				</div>
			</div>
		</div>
	</div>


	<div class="container">
		<h2>Listado de Autores</h2>
		<br>
		<p>Para ver los libro del autor dar clic en el Nombre*</p>
		<div class="table-responsive">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>#</th>
						<th>Nombre</th>
						<th>Genero</th>
						<th>Edad</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="listarAutores" items="${listarAutores }">
						<tr>
							<td>${listarAutores.id}</td>

							<td>
								<c:url value="libros-por-autor" var="librosURL">
									<c:param name="authorId" value="${listarAutores.id}"></c:param>
									<c:param name="nombreAutor" value="${listarAutores.name}"></c:param>
								</c:url> 
								<a href="${librosURL}">${listarAutores.name}</a>
							</td>
							
							<td>${listarAutores.genre}</td>
							<td>${listarAutores.age}</td>

							<td><c:url value="editar-formulario" var="librosURL">
									<c:param name="id_author" value="${listarAutores.id}"></c:param>
								</c:url> <a href="${librosURL}">Editar</a></td>

							<!--Eliminar  -->
							<td><c:url value="eliminar-autor" var="librosURL">
									<c:param name="id_author" value="${listarAutores.id}"></c:param>
								</c:url> <a href="${librosURL}">Eliminar</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>



</body>
</html>