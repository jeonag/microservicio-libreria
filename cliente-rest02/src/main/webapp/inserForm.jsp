<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<!--   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> -->
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> 

<title>Insertar Autor</title>
</head>
<body>
 
	<div class="container">
		<h2>Insertar Autor</h2>
		<form action="ingresar-autor">
			<div class="form-group">
				<label for="nombre">Nombre:</label> <input type="text"
					class="form-control" id=nombre placeholder="Ingresa el nombre"
					name="name">
			</div>
			<div class="form-group">
				<label for="genero">Genero:</label> <input type="text"
					class="form-control" id="genero" placeholder="Ingresa el genero"
					name="genre">
			</div>
			
			<div class="form-group">
				<label for="edad">Edad:</label> <input type="text"
					class="form-control" id="edad" placeholder="Ingresa edad"
					name="age">
			</div>
			 
			<button type="submit" class="btn btn-success">Guardar</button>
		</form>
	</div>




	<!-- 	</div> -->
</body>
</html>
