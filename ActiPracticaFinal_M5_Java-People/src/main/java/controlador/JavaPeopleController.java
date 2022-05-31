package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Modulo;
import modelo.Calificacion;
import modelo.Alumno;
import modelo.Promedio;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import dao.ModuloDAO;
import dao.ModuloDAOImp;
import dao.CalificacionDAO;
import dao.CalificacionDAOImp;
import dao.AlumnoDAO;
import dao.AlumnoDAOImp;

public class JavaPeopleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private AlumnoDAO alumnoDAO;
	private ModuloDAO moduloDAO;
	private CalificacionDAO calificacionDAO;
	
	@Override
	public void init() throws ServletException{
		super.init();
		this.alumnoDAO = new AlumnoDAOImp();
		this.moduloDAO = new ModuloDAOImp();
		this.calificacionDAO = new CalificacionDAOImp(alumnoDAO, moduloDAO);
		
	}
	
    public JavaPeopleController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		
		switch(accion) {
		case "listar":
			List<Alumno> alumnos = null;
			try {
				alumnos = alumnoDAO.findAllAlumnos();
			} catch (SQLException | NamingException e) {
				e.printStackTrace();
				response.sendError(500);
				return;
			}
			request.setAttribute("alumnos", alumnos);
			request.getRequestDispatcher("/WEB-INF/jsp/vista/lista-alumnos.jsp").forward(request, response);
			break;
			
		case "formulario":
			request.getRequestDispatcher("/WEB-INF/jsp/vista/form-alumnos.jsp").forward(request, response);
			break;
			
		case "formNota":
			int idAlumno = Integer.parseInt(request.getParameter("id"));
			
			Alumno alumno = null;
			List<Modulo> modulos = null;
			try {
				alumno = alumnoDAO.findAlumnoById(idAlumno);
				modulos = moduloDAO.findAllModulos();
			} catch (SQLException | NamingException e) {
				e.printStackTrace();
				response.sendError(500);
				return;
			}
			request.setAttribute("modulos", modulos);
			request.setAttribute("alumno", alumno);
			request.getRequestDispatcher("/WEB-INF/jsp/vista/form-calificaciones.jsp").forward(request, response);
			break;
			
		case "consultar":
			idAlumno = Integer.parseInt(request.getParameter("id"));
			List<Calificacion> calificaciones = null;
			List<Promedio> promedios = null;
			try {
				calificaciones = calificacionDAO.findAllCalificacionesById(idAlumno);
				promedios = calificacionDAO.findAverageCalificacionById(idAlumno);
			} catch (SQLException | NamingException e) {
				e.printStackTrace();
				response.sendError(500);
				return;
			}
			request.setAttribute("promedios", promedios);
			request.setAttribute("calificaciones", calificaciones);
			request.getRequestDispatcher("/WEB-INF/jsp/vista/lista-calificaciones.jsp").forward(request, response);
			break;
			
		case "prepareEditNota":
			
			// toma el id de la calificacion a editar
			int idNota = Integer.parseInt(request.getParameter("idNota"));
			Calificacion calificacion = null;
			try {
				// busca la calificacion a editar segun su id
				calificacion = calificacionDAO.findCalificacionById(idNota);
			} catch (SQLException | NamingException e) {
				e.printStackTrace();
				response.sendError(500);
				return;
			}	
			request.setAttribute("calificacion", calificacion);
			request.getRequestDispatcher("/WEB-INF/jsp/vista/form-editar-calificaciones.jsp").forward(request, response);
			break;
			
		default:
			response.sendError(500);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		
		switch(accion) {
		case "addAlumno":
				String nombres			= request.getParameter("nombres");
				String apellidos 		= request.getParameter("apellidos");
				String rut 				= request.getParameter("rut");
				String genero 			= request.getParameter("genero");
				String fono 			= request.getParameter("fono");
				String curso 			= request.getParameter("curso");
				
				// si el usuario no cambio las opciones por default, redirecciona de vuelta y muestra el mensaje de error correspondiente
				if(genero.equals("none")) {
					request.setAttribute("codigo", 0);
					request.getRequestDispatcher("/WEB-INF/jsp/vista/form-alumnos.jsp").forward(request, response);
					break;
				}
				
				if(curso.equals("none")) {
					request.setAttribute("codigo", 1);
					request.getRequestDispatcher("/WEB-INF/jsp/vista/form-alumnos.jsp").forward(request, response);
					break;
				}
				
				// si todo esta en orden, pasa a la creacion del alumno, cuando lo haya hecho, redirecciona al indice con un mensaje de exito
				Alumno alumno = new Alumno(nombres,apellidos,rut,genero,fono,curso);
				try {
					alumnoDAO.createAlumno(alumno);
					request.setAttribute("codigo", 1);
					request.getRequestDispatcher("index.jsp").forward(request, response);
					
				} catch (SQLException | NamingException e) {
					// ante un error, redirecciona al indice y muestra una alerta
					request.setAttribute("codigo", 0);
					request.getRequestDispatcher("index.jsp").forward(request, response);
					e.printStackTrace();
				}
				break;
				
		case "addNota":
				int idAlumno			= Integer.parseInt(request.getParameter("idAlumno")); 
				int idModulo			= Integer.parseInt(request.getParameter("modulo"));
				float nota				= Float.parseFloat(request.getParameter("nota"));
				
				// prepara objeto calificacion para asignacion o creacion de instancia
				Calificacion calificacion = null;
				
				// utiliza los id de alumno y modulo para hacer un query
				// buscará si hay alguna calificacion registrada con los id de alumno y modulo enviados
				// si los hay, tomará el primero de la lista, ordenado por "numero evaluacion" de forma descendiente y lo retornara en forma de objeto Calificacion
				try {
					 calificacion = calificacionDAO.findCalificacionByForeignIds(idModulo, idAlumno);

				} catch (SQLException | NamingException e) {
					e.printStackTrace();
					response.sendError(500);
					return;
				}
				
				// si no encuentra ningun registro con los datos proporcionados, crea uno nuevo con el "numeroEvaluacion" en 0
				if (calificacion == null) {
					calificacion = new Calificacion(nota,0,idAlumno,idModulo);
				}
				
				// reemplaza la nota del registro encontrado con la nota impuesta por el usuario
				 calificacion.setNota(nota);
				
				// independiente de si ha encontrado un registro o no, ya con el objeto calificacion creado, le suma 1 a "numeroEvaluacion"
				// una suerte de autoincremental que depende de que dos campos ID sean iguales para hacer el incremento
				calificacion.setNumeroEvaluacion(calificacion.getNumeroEvaluacion()+1);
				
				// terminado todo este proceso, envia el objeto para ser registrado en la base de datos
				try {
					calificacionDAO.createCalificacion(calificacion);
					request.setAttribute("codigo", 2);
					request.setAttribute("nota", nota);
					request.getRequestDispatcher("index.jsp").forward(request, response);
				} catch (SQLException | NamingException e) {
					e.printStackTrace();
					response.sendError(500);
					return;
				}
					break;
					
		case "editNota":
				// toma la id de la calificacion a editar y la nueva calificacion que se le pondra
				int idNota 		= Integer.parseInt(request.getParameter("idNota"));
				nota			= Float.parseFloat(request.getParameter("nota"));
				
				try {
					// busca la calificacion por id proporcionado
					calificacion = calificacionDAO.findCalificacionById(idNota);
					
					// setea la nueva nota sobre la calificacion encontrada
					calificacion.setNota(nota);
					
					// manda el query de edicion junto a la calificacion con la nota asignada
					calificacionDAO.editCalificacion(calificacion);
					request.setAttribute("codigo", 3);
					request.getRequestDispatcher("index.jsp").forward(request, response);
					
				} catch (SQLException | NamingException e) {
					e.printStackTrace();
					response.sendError(500);
					return;
				}
				
				break;
			
		default:
				response.sendError(500);
				break;
		}
	}
}