package dao;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import modelo.Alumno;

public interface AlumnoDAO {
	
	public List<Alumno> findAllAlumnos() 			throws SQLException, NamingException;
	public Alumno findAlumnoById(int alumnoId)  	throws SQLException, NamingException;
	public void createAlumno(Alumno alumno)			throws SQLException, NamingException;
	public void editAlumno(Alumno alumno)			throws SQLException, NamingException;
	public void deleteAlumno(int alumnoId)			throws SQLException, NamingException;
	public Alumno findLastCreatedAlumno()			throws SQLException, NamingException;

}
