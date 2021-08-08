<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<!--   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<title>Editar Libro</title>
</head>
<body>

	<div class="container">
		<h2>Editar Libro</h2>
		<form action="editar-libro">

			<div class="form-group">
				<input type="hidden" name="id" value="${libro.id}" />
			</div>

			<div class="form-group">
				<label for="isbn">ISBN:</label> <input type="text"
					class="form-control" id="isbn" placeholder="Ingresa el nombre"
					name="isbn" value="${libro.isbn}">
			</div>
			<div class="form-group">
				<label for="titulo">Titulo:</label> <input type="text"
					class="form-control" id="titulo" placeholder="Ingresa el nombre"
					name="titulo" value="${libro.title}">
			</div>

			<!-- 			<div class="form-group"> -->
			<%-- 				<input type="hidden" name="author_id" value="${libro.author_id}" /> --%>
			<!-- 			</div> -->
			<div class="form-group">
				<label for="titulo">Id Autor:</label> <input type="text"
					class="form-control" id="author_id" placeholder="Ingresa el nombre"
					name="author_id" value="${libro.author_id}">
			</div>

			<button type="submit" class="btn btn-success">Guardar</button>
		</form>
	</div>

</body>
</html>