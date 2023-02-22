package clases;

import java.sql.*;

public class consulta {
	private int id;
	private int sala;
	private Date fecha;
	private Time hora;
	private int idMedico;
	private int idPaciente;
	private double coste;
	private String analisisComplement;
	private boolean realizada;

	public consulta(int id, int sala, Date fecha, Time hora, int idMedico, int idPaciente, double coste,
			String analisisComplement, boolean realizada) {
		super();
		this.id = id;
		this.sala = sala;
		this.fecha = fecha;
		this.hora = hora;
		this.idMedico = idMedico;
		this.idPaciente = idPaciente;
		this.coste = coste;
		this.analisisComplement = analisisComplement;
		this.realizada = realizada;
	}

	public consulta(int sala, Date fecha, Time hora, int idMedico, int idPaciente, double coste,
			String analisisComplement) {
		this.sala = sala;
		this.fecha = fecha;
		this.hora = hora;
		this.idMedico = idMedico;
		this.idPaciente = idPaciente;
		this.coste = coste;
		this.analisisComplement = analisisComplement;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSala() {
		return sala;
	}

	public void setSala(int sala) {
		this.sala = sala;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public int getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(int idMedico) {
		this.idMedico = idMedico;
	}

	public int getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}

	public double getCoste() {
		return coste;
	}

	public void setCoste(double coste) {
		this.coste = coste;
	}

	public String getAnalisisComplement() {
		return analisisComplement;
	}

	public void setAnalisisComplement(String analisisComplement) {
		this.analisisComplement = analisisComplement;
	}

	public boolean isRealizada() {
		return realizada;
	}

	public void setRealizada(boolean realizada) {
		this.realizada = realizada;
	}

}
