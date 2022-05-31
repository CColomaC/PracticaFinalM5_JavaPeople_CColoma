<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
 <head>
    <meta charset="utf-8">
	<title>Registro de alumnos</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body class="bg-light">

<!-- alertas  -->
	<c:if test="${codigo == 0}">
	<div class="alert alert-danger" role="alert">
	  Seleccione su género.
	</div>
	</c:if>
	
	<c:if test="${codigo == 1}">
	<div class="alert alert-danger" role="alert">
	  Seleccione el curso.
	</div>
	</c:if>

<div class="my-3 p-3 bg-body rounded shadow-sm">
	<h1>Registro de alumno</h1>
	<div class="container">
	
		<h2>Datos del alumno</h2>
		<div class="col-mb-6">
			<form method="POST" action="JavaPeopleController?accion=addAlumno">
			  <div class="mb-3">
			    <label class="form-label">Nombres</label>
			    <input required type="text" class="form-control" id="nombres" name="nombres">
			  </div>
			  <div class="mb-3">
			    <label class="form-label">Apellidos</label>
			    <input required type="text" class="form-control" id="apellidos" name="apellidos">
			  </div>
			  <div class="mb-3">
			    <label class="form-label">RUT</label>
			    <input required type="text" class="form-control" id="rut" name="rut">
			  </div>
			  <div class="mb-3">
			    <label class="form-label">Género</label>
			    <select class="form-select" name="genero" id="genero">
			    	<option selected="selected" value="none">Seleccione su género</option>
			    	<option value="Masculino">Masculino</option>
			    	<option value="Femenino">Femenino</option>
			    	<option value="Otro">Otro</option>
			    </select>
			  </div>
			  <div class="mb-3">
			    <label class="form-label">Fono de contacto</label>
			    <input required type="text" class="form-control" id="fono" name="fono">
			  </div>
			  <div class="mb-3">
			    <label class="form-label">Curso</label>
			    <select class="form-select" name="curso" id="curso">
			    	<option selected="selected" value="none">Elija un curso</option>
			    	<option value="Curso 1">Curso 1</option>
			    	<option value="Curso 2">Curso 2</option>
			    	<option value="Curso 3">Curso 3</option>
			    </select>
			  </div>
			  <button type="submit" class="btn btn-success">Registrar</button> 
			  <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-primary" role="button" data-bs-toggle="button">Volver</a>
			</form>
		</div>
	</div>
</div>
</body>
</html>