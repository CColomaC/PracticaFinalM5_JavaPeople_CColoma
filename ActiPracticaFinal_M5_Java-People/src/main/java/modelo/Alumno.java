package modelo;

public class Alumno {
	
	private int id;
	private String nombres;
	private String apellidos;
	private String rut;
	private String genero;
	private String fono;
	private String curso;
	
	public Alumno() {
	}

	public Alumno(int id, String nombres, String apellidos, String rut, String genero, String fono, String curso) {
		this.id = id;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.rut = rut;
		this.genero = genero;
		this.fono = fono;
		this.curso = curso;
	}

	public Alumno(String nombres, String apellidos, String rut, String genero, String fono, String curso) {
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.rut = rut;
		this.genero = genero;
		this.fono = fono;
		this.curso = curso;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getFono() {
		return fono;
	}

	public void setFono(String fono) {
		this.fono = fono;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

}
