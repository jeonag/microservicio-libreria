<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head> 
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<!--   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> -->
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> 


<title>Insertar Libro</title>
</head>
<body>
	
	<div class="container">
		<h2>Insertar Libro</h2>
		<form action="ingresar-libro">
			<div class="form-group">
				<label for="isbn">ISBN:</label> <input type="text"
					class="form-control" id="isbn" placeholder="Ingresa el ISBN del libro"
					name="isbn">
			</div>
			<div class="form-group">
				<label for="titulo">Titulo:</label> <input type="text"
					class="form-control" id="titulo" placeholder="Ingresa el titulo dle libro"
					name="titulo">
			</div>
			<div class="form-group">
				<label for="authorId">Id Autor:</label> <input type="text"
					class="form-control" id="authorId" placeholder="Ingresa el ID del Autor"
					name="authorId">
			</div>
			 
			<button type="submit" class="btn btn-success">Guardar</button>
		</form>
	</div>

</body>
</html>
