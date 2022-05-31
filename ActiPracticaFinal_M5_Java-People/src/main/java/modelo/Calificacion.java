package modelo;

public class Calificacion {
	
	private int id_calificacion;
	private float nota;
	private int numeroEvaluacion;
	private int id_alumno;
	private int id_modulo;
	private Alumno alumno;
	private Modulo modulo;
	
	public Calificacion() {
	}

	public Calificacion(float nota, int numeroEvaluacion, int id_alumno, int id_modulo) {
		this.nota = nota;
		this.numeroEvaluacion = numeroEvaluacion;
		this.id_alumno = id_alumno;
		this.id_modulo = id_modulo;
	}

	public Calificacion(int id_calificacion, float nota, int numeroEvaluacion, Alumno alumno, Modulo modulo) {
		this.id_calificacion = id_calificacion;
		this.nota = nota;
		this.numeroEvaluacion = numeroEvaluacion;
		this.alumno = alumno;
		this.modulo = modulo;
	}

	public int getId_calificacion() {
		return id_calificacion;
	}

	public void setId_calificacion(int id_calificacion) {
		this.id_calificacion = id_calificacion;
	}

	public float getNota() {
		return nota;
	}

	public void setNota(float nota) {
		this.nota = nota;
	}

	public int getNumeroEvaluacion() {
		return numeroEvaluacion;
	}

	public void setNumeroEvaluacion(int numeroEvaluacion) {
		this.numeroEvaluacion = numeroEvaluacion;
	}

	public int getId_alumno() {
		return id_alumno;
	}

	public void setId_alumno(int id_alumno) {
		this.id_alumno = id_alumno;
	}

	public int getId_modulo() {
		return id_modulo;
	}

	public void setId_modulo(int id_modulo) {
		this.id_modulo = id_modulo;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Modulo getModulo() {
		return modulo;
	}

	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}
	
	

}
