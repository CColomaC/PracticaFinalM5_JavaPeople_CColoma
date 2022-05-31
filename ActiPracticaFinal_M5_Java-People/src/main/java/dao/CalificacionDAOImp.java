package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import modelo.Modulo;
import modelo.Calificacion;
import modelo.Alumno;
import modelo.Promedio;

public class CalificacionDAOImp implements CalificacionDAO {
	
	private AlumnoDAO alumnoDAO;
	private ModuloDAO moduloDAO;
	
	
	public CalificacionDAOImp(AlumnoDAO alumnoDAO, ModuloDAO moduloDAO) {
		this.alumnoDAO = alumnoDAO;
		this.moduloDAO = moduloDAO;
	}

	@Override
	public List<Calificacion> findAllCalificacionesById(int alumnoId)  throws SQLException, NamingException {
		try(
				Connection conn = DBUtils.getConexion();
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM calificacion WHERE id_alumno = ?");
			) {
			ps.setInt(1, alumnoId);
			ResultSet rs = ps.executeQuery();
			List<Calificacion> calificaciones = new ArrayList<>();
			while(rs.next()) {
				int id					= rs.getInt("id_calificacion");
				float nota 				= rs.getFloat("nota");
				int numeroEvaluacion 	= rs.getInt("numeroEvaluacion");
				int id_alumno	 		= rs.getInt("id_alumno");
				int id_modulo 			= rs.getInt("id_modulo");
				
				Alumno alumno = alumnoDAO.findAlumnoById(id_alumno);
				Modulo modulo = moduloDAO.findModuloById(id_modulo);
				
				Calificacion calificacion = new Calificacion(id,nota,numeroEvaluacion,alumno,modulo);
				calificaciones.add(calificacion);
			}
			return calificaciones;
		}
	}

	@Override
	public Calificacion findCalificacionByForeignIds(int moduloId,int alumnoId) throws SQLException, NamingException {
		try(
				Connection conn = DBUtils.getConexion();
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM calificacion WHERE id_modulo = ? AND id_alumno = ? ORDER BY numeroevaluacion DESC LIMIT 1");
			) {
			ps.setInt(1, moduloId);
			ps.setInt(2, alumnoId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				float nota 				= rs.getFloat("nota");
				int numeroEvaluacion 	= rs.getInt("numeroevaluacion");
				int idAlumno 			= rs.getInt("id_alumno");
				int idModulo 			= rs.getInt("id_modulo");
				return new Calificacion(nota,numeroEvaluacion,idAlumno,idModulo);
			}			
		}
		return null;
	}

	@Override
	public void createCalificacion(Calificacion calificacion) throws SQLException, NamingException {
		try(
				Connection conn = DBUtils.getConexion();
				PreparedStatement ps = conn.prepareStatement("INSERT INTO calificacion(numeroevaluacion, nota, id_alumno, id_modulo) VALUES (?,?,?,?)");

			) {
			ps.setInt(1, calificacion.getNumeroEvaluacion());
			ps.setFloat(2, calificacion.getNota());
			ps.setInt(3, calificacion.getId_alumno());
			ps.setInt(4, calificacion.getId_modulo());
			ps.executeUpdate();
		}

	}

	@Override
	public void editCalificacion(Calificacion calificacion) throws SQLException, NamingException {
		try(
				Connection conn = DBUtils.getConexion();
				PreparedStatement ps = conn.prepareStatement("UPDATE calificacion SET nota = ? WHERE id_calificacion = ?");
			) {
			ps.setFloat(1, calificacion.getNota());
			ps.setInt(2, calificacion.getId_calificacion());
			ps.executeUpdate();
		}

	}

	@Override
	public void deleteCalificacion(int calificacionId) throws SQLException, NamingException {

	}

	@Override
	public Calificacion findLastCreatedCalificacion() throws SQLException, NamingException {
		return null;
	}

	@Override
	public Calificacion findCalificacionById(int calificacionId) throws SQLException, NamingException {
		try(
				Connection conn = DBUtils.getConexion();
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM calificacion WHERE id_calificacion = ?");
			) {
			ps.setInt(1, calificacionId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				int id					= rs.getInt("id_calificacion");
				float nota 				= rs.getFloat("nota");
				int numeroEvaluacion 	= rs.getInt("numeroEvaluacion");
				int id_alumno	 		= rs.getInt("id_alumno");
				int id_modulo 			= rs.getInt("id_modulo");
				
				Alumno alumno = alumnoDAO.findAlumnoById(id_alumno);
				Modulo modulo = moduloDAO.findModuloById(id_modulo);
				
				return new Calificacion(id,nota,numeroEvaluacion,alumno,modulo);
				
				
				
			}			
		}
		return null;
	}

	@Override
	public List<Promedio> findAverageCalificacionById(int alumnoId) throws SQLException, NamingException {
		try(
				Connection conn = DBUtils.getConexion();
				PreparedStatement ps = conn.prepareStatement("SELECT id_modulo, id_alumno, AVG(nota) FROM calificacion WHERE id_alumno = ? GROUP BY id_modulo, id_alumno");
			) {
			ps.setInt(1, alumnoId);
			ResultSet rs = ps.executeQuery();
			List<Promedio> promedios = new ArrayList<>(); 
			while(rs.next()) {
				float average 				= rs.getFloat("avg");
				int id_modulo 				= rs.getInt("id_modulo");
				int id_alumno 				= rs.getInt("id_alumno");
				
				Alumno alumno = alumnoDAO.findAlumnoById(id_alumno);
				Modulo modulo = moduloDAO.findModuloById(id_modulo);
				
				Promedio promedio = new Promedio(average,modulo,alumno);
				
				promedios.add(promedio); 
				
			}	
			return promedios;
		}
	}

}