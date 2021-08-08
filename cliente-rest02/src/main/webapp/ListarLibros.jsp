<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Libros Autor</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>



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

	<br>
	<div class="container">
		<h2>Libros de ${nombreAutor} </h2>
		<br>
		
		<div class="table-responsive">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>#</th>
						<th>Autor</th>
						<th>ISBN</th>
						<th>Titulo</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="listarLibrosByAutor"
						items="${listarLibrosByAutor }">
						<tr>
							<td>${listarLibrosByAutor.id}</td>

							<td>${nombreAutor}</td>

							<td>${listarLibrosByAutor.isbn}</td>
							<td>${listarLibrosByAutor.title}</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>


</body>
</html>