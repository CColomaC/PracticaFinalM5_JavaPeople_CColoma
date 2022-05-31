<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de alumnos</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.1/examples/offcanvas-navbar/">

	<!-- Incluye facilitador de Table Sort -->
	
    <!-- Bootstrap core CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>    
    
  </head>
<body class="bg-light">
    <!--  incluye el navbar -->

<div class="my-3 p-3 bg-body rounded shadow-sm">
	<h1>Alumnos</h1>	
	<table id="sortTable" class="table table-striped">
		<thead>
			<tr>
				<th class="col">ID</th>
				<th class="col">RUT</th>
				<th class="col">Nombre</th>
				<th class="col">Genero</th>
				<th class="col">Fono</th>
				<th class="col">Curso</th>
				<th class="col">Acciones</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="alumnos" items="${alumnos}">
				<tr>
					<td><c:out value="${alumnos.id}"></c:out></td>
					<td><c:out value="${alumnos.rut}"></c:out></td>
					<td><c:out value="${alumnos.nombres} ${alumnos.apellidos}"></c:out></td>
					<td><c:out value="${alumnos.genero}"></c:out></td>
					<td><c:out value="${alumnos.fono}"></c:out></td>
					<td><c:out value="${alumnos.curso}"></c:out></td>
					<td>
						<a href="JavaPeopleController?accion=consultar&amp;id=${alumnos.id}">Consultar</a>
						<a href="JavaPeopleController?accion=formNota&amp;id=${alumnos.id}">Agregar nota</a>
					</td>
				</tr>			
			</c:forEach>
		</tbody>
	</table>
</div>		
<a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-primary" role="button" >Volver</a>
	
	<!-- Llama las funciones de sort de DataTable -->

</body>
</html>