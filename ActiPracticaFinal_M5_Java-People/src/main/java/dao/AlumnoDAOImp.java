package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import modelo.Alumno;

public class AlumnoDAOImp implements AlumnoDAO {

	@Override
	public List<Alumno> findAllAlumnos() throws SQLException, NamingException {
		try(
				Connection conn = DBUtils.getConexion();
				Statement st = conn.createStatement();
			) {
			ResultSet rs = st.executeQuery("SELECT * FROM alumno");
			List<Alumno> alumnos = new ArrayList<>();
			while(rs.next()) {
				int id 					= rs.getInt("id_alumno");
				String nombres 			= rs.getString("nombres");
				String apellidos 		= rs.getString("apellidos");
				String rut				= rs.getString("rut");
				String genero			= rs.getString("genero");
				String fono 			= rs.getString("fono");
				String curso			= rs.getString("curso");
				
				Alumno alumno = new Alumno(id,nombres,apellidos,rut,genero,fono,curso);
				alumnos.add(alumno);
			}
			return alumnos;
		}
	}

	@Override
	public Alumno findAlumnoById(int alumnoId) throws SQLException, NamingException {
		try(
				Connection conn = DBUtils.getConexion();
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM alumno WHERE id_alumno = ?");
			) {
			ps.setInt(1, alumnoId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				int id 					= rs.getInt("id_alumno");
				String nombres 			= rs.getString("nombres");
				String apellidos 		= rs.getString("apellidos");
				String rut				= rs.getString("rut");
				String genero			= rs.getString("genero");
				String fono 			= rs.getString("fono");
				String curso			= rs.getString("curso");
				
				return new Alumno(id,nombres,apellidos,rut,genero,fono,curso);		
			}
		}
		return null;
	}

	@Override
	public void createAlumno(Alumno alumno) throws SQLException, NamingException {
		try(
				Connection conn = DBUtils.getConexion();
				PreparedStatement ps = conn.prepareStatement("INSERT INTO alumno(rut,nombres,apellidos, genero, fono, curso) VALUES (?,?,?,?,?,?)");

			) {
			
			ps.setString(1, alumno.getRut());
			ps.setString(2, alumno.getNombres());
			ps.setString(3, alumno.getApellidos());
			ps.setString(4, alumno.getGenero());
			ps.setString(5, alumno.getFono());
			ps.setString(6, alumno.getCurso());
			ps.executeUpdate();
		}

	}

	@Override
	public void editAlumno(Alumno alumno) throws SQLException, NamingException {

	}

	@Override
	public void deleteAlumno(int alumnoId) throws SQLException, NamingException {
	
	}

	@Override
	public Alumno findLastCreatedAlumno() throws SQLException, NamingException {

		return null;
	}

}