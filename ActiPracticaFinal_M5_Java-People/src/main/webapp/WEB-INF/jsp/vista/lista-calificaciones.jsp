<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listado de Calificaciones</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.1/examples/offcanvas-navbar/">

	<!-- Incluye facilitador de Table Sort -->

    <!-- Bootstrap core CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>    
    
  </head>
<body class="bg-light">
    <!--  incluye el navbar -->

<div class="my-3 p-3 bg-body rounded shadow-sm">
	<h1>Listado de Calificaciones</h1>	
	<table id="sortTable" class="table table-striped">
		<thead>
			<tr>				
				<th class="col">ID</th>
				<th class="col">Nombre</th>
				<th class="col">Módulo</th>
				<th class="col">Nota</th>
				<th class="col">Número de evaluación</th>
				<th class="col">Acciones</th>
				
			</tr>
		</thead>
		<tbody>
			<c:forEach var="calificaciones" items="${calificaciones}">
				<tr>
					<td><c:out value="${calificaciones.id_calificacion}"></c:out></td>
					<td><c:out value="${calificaciones.alumno.nombres} ${calificaciones.alumno.apellidos}"></c:out></td>
					<td><c:out value="${calificaciones.modulo.nombre}"></c:out></td>
					<td><c:out value="${calificaciones.nota}"></c:out></td>
					<td><c:out value="${calificaciones.numeroEvaluacion}"></c:out></td>
					<td>
						<a href="JavaPeopleController?accion=prepareEditNota&amp;idNota=${calificaciones.id_calificacion}">Modificar</a>
					</td>
				</tr>			
			</c:forEach>
		</tbody>
	</table>
</div>

<div class="my-3 p-3 bg-body rounded shadow-sm">
	<h1>Promedios</h1>	
	<table class="table table-striped">
		<thead>
			<tr>				
				<th class="col">Módulo</th>
				<th class="col">Promedio</th>
				
			</tr>
		</thead>
		<tbody>
			<c:forEach var="promedios" items="${promedios}">
				<tr>
					<td><c:out value="${promedios.modulo.nombre}"></c:out></td>
					<td><c:out value="${promedios.promedio} "></c:out></td>
				</tr>			
			</c:forEach>
		</tbody>
	</table>
</div>
		
<a href="JavaPeopleController?accion=listar" class="btn btn-primary" role="button" >Volver</a>
	
	<!-- Llama las funciones de sort de DataTable -->

</body>
</html>