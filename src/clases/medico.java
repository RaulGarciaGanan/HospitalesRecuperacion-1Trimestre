package clases;

import java.sql.*;

public class medico {
	private int id;
	private int numColegiado;
	private String dni;
	private String nombre;
	private String apellidos;
	private Date fecahNaci;
	private Date fechaContrata;
	private String especialidad;
	private boolean baja;

	public medico(int id, int numColegiado, String dni, String nombre, String apellidos, Date fecahNaci,
			Date fechaContrata, String especialidad, boolean baja) {
		super();
		this.id = id;
		this.numColegiado = numColegiado;
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fecahNaci = fecahNaci;
		this.fechaContrata = fechaContrata;
		this.especialidad = especialidad;
		this.baja = baja;
	}

	public medico(int numColegiado, String dni, String nombre, String apellidos, Date fecahNaci, Date fechaContrata,
			String especialidad, boolean baja) {
		super();
		this.numColegiado = numColegiado;
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fecahNaci = fecahNaci;
		this.fechaContrata = fechaContrata;
		this.especialidad = especialidad;
		this.baja = baja;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumColegiado() {
		return numColegiado;
	}

	public void setNumColegiado(int numColegiado) {
		this.numColegiado = numColegiado;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Date getFecahNaci() {
		return fecahNaci;
	}

	public void setFecahNaci(Date fecahNaci) {
		this.fecahNaci = fecahNaci;
	}

	public Date getFechaContrata() {
		return fechaContrata;
	}

	public void setFechaContrata(Date fechaContrata) {
		this.fechaContrata = fechaContrata;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public boolean isBaja() {
		return baja;
	}

	public void setBaja(boolean baja) {
		this.baja = baja;
	}

}
