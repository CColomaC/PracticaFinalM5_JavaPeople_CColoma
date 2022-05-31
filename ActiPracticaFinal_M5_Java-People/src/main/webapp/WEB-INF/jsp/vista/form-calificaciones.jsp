<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="utf-8">
	<title>Calificaciones</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body class="bg-light">


<div class="my-3 p-3 bg-body rounded shadow-sm">
	<h1>Calificaciones</h1>
	<div class="container">
	
		<h2>Agregar calificaciones</h2>
		<div class="col-mb-6">
			<form method="POST" action="JavaPeopleController?accion=addNota">
			  <div class="mb-3">
			    <label class="form-label">Alumno</label>
			    <input type="hidden" name="idAlumno" value="${alumno.id}">
			    <input disabled type="text" class="form-control" id="nombre" name="nombre" value="${alumno.nombres} ${alumno.apellidos}">
			  </div>
			  <div class="mb-3">
			    <label class="form-label">Curso</label>
			    <input disabled type="text" class="form-control" id="curso" name="curso" value="${alumno.curso}">
			  </div>
			  <div class="mb-3">
			    <label class="form-label">Módulo</label>
			    <select class="form-select" name="modulo" id="modulo">
			    	<option selected="selected" value="none">Elija un módulo</option>
					<c:forEach var="modulos" items="${modulos}">					
						<option value="${modulos.id_modulo}">${modulos.nombre}</option>						
					</c:forEach>
			    </select>
			  </div>
			  <div class="col-sm-1 mb-3">
			    <label class="form-label">Nota</label>
			    <input type="text" class="form-control" id="nota" name="nota" placeholder="7.0">
			  </div>
			  <button type="submit" class="btn btn-success">Registrar</button> 
			  <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-primary" role="button" data-bs-toggle="button">Volver</a>
			</form>

		</div>
	</div>
</div>
</body>
</html>