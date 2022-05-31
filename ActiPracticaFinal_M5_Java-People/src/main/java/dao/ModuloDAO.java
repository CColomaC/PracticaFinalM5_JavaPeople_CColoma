package dao;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import modelo.Modulo;

public interface ModuloDAO {

	public List<Modulo> findAllModulos() 			throws SQLException, NamingException;
	public Modulo findModuloById(int moduloId)  	throws SQLException, NamingException;
	public void createModulo(Modulo modulo)			throws SQLException, NamingException;
	public void editModulo(Modulo modulo)			throws SQLException, NamingException;
	public void deleteModulo(int moduloId)			throws SQLException, NamingException;
	public Modulo findLastCreatedModulo()			throws SQLException, NamingException;
}