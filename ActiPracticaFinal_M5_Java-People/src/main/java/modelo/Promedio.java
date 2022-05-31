package modelo;

public class Promedio {
	
	private float promedio;
	private Modulo modulo;
	private Alumno alumno;
	
	public Promedio(float promedio, Modulo modulo, Alumno alumno) {
		this.promedio = promedio;
		this.modulo = modulo;
		this.alumno = alumno;
	}
	
	public float getPromedio() {
		return promedio;
	}
		public void setPromedio(float promedio) {
		this.promedio = promedio;
	}
	public Modulo getModulo() {
		return modulo;
	}
	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}
	public Alumno getAlumno() {
		return alumno;
	}
	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
	
}
