<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head> 
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<!--   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> -->
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> 



<title>Libros</title>
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
					<a href="<%=request.getContextPath()%>/form-libro"
					
						class="btn btn-primary"> Crear Libro</a> <br>
				</div>
			</div>
		</div>
	</div>

	
	<div class="container">
		<h2>Listado de Libros</h2>
		<br>
		<br>
		<div class="table-responsive">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>#</th>
						<th>Autor</th>
						<th>ISBN</th>
						<th>Title</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="listarLibros" items="${listarLibros }">
						<tr>
							<td>${listarLibros.id}</td>
							<td>${listarLibros.author.name}</td>	
							<td>${listarLibros.isbn}</td>
							<td>${listarLibros.title}</td>
							<td><c:url value="form-edit-libro" var="librosURL">
									<c:param name="id" value="${listarLibros.id}"></c:param>
								</c:url> <a href="${librosURL}">Editar</a></td>


							<td><c:url value="eliminar-libro" var="librosURL">
									<c:param name="id" value="${listarLibros.id}"></c:param>
								</c:url> <a href="${librosURL}">Eliminar</a></td>


						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>


</body>
</html>