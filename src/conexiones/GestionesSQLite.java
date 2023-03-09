package conexiones;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import clases.consulta;
import clases.medico;
import clases.paciente;

public class GestionesSQLite {

	public ArrayList<paciente> aPaciente;
	public ArrayList<medico> aMedico;
	public ArrayList<consulta> aConsulta;

	public void menuPrincipal() throws SQLException {
		Scanner in = new Scanner(System.in);
		boolean correcto;
		aPaciente = new ArrayList<paciente>();
		aMedico = new ArrayList<medico>();
		aConsulta = new ArrayList<consulta>();
		guardarPacientes(aPaciente);
		guardarMedicos(aMedico);
		guardarConsultas(aConsulta);
		int menu = 0;
		do {
			do {
				try {
					menu = 0;
					System.out.println("Elije una opcion:\n" + "1.Menu de listado de Pacientes\n"
							+ "2.Menu de Listado de Medicos\n" + "3.Menu de listado de Consultas\n"
							+ "4.Gestion Pacientes\n" + "5.Gestion Medicos\n" + "6.Gestion Consultas\n"
							+ "7.MetaDatos\n" + "8.Datos del hospital\n" + "9.salir");
					menu = Integer.parseInt(in.nextLine());
					correcto = true;
				} catch (Exception e) {
					System.out.println("Debe seleccionar numericamente la opcion");
					correcto = false;
				}
			} while (!correcto);
			switch (menu) {
			case 1:
				menuListadoPacientes();
				break;
			case 2:
				menuListadoMedicos();
				break;
			case 3:
				menuListadoConsultas();
				break;
			case 4:
				crudPacientes();
				break;
			case 5:
				crudMedicos();
				break;
			case 6:
				crudConsultas();
				break;
			case 7:
				listarMetadatos();
				break;
			case 8:
				datosHospital();
				break;
			case 9:
				System.out.println("Agur");
				break;
			default:
				break;
			}

		} while (menu != 9);
	}

	private void menuListadoPacientes() throws SQLException {
		Scanner in = new Scanner(System.in);
		boolean correcto;
		int menu = 0;
		do {
			do {
				try {
					menu = 0;
					System.out.println("Elije una opcion:\n" + "1.Listado completo de pacientes\n"
							+ "2.Listado de pacientes que tienen consulta pendiente\n"
							+ "3.Historico de consultas de un paciente\n4.salir");
					menu = Integer.parseInt(in.nextLine());
					correcto = true;
				} catch (Exception e) {
					System.out.println("Debe seleccionar numericamente la opcion");
					correcto = false;
				}
			} while (!correcto);
			switch (menu) {
			case 1:
				listadoPacientesCompleto();
				break;
			case 2:
				listadoPacientesSinCitaCompletada();
				break;
			case 3:
				listadoHistoricoPacientes();
				break;
			case 4:
				System.out.println("Agur");
			default:
				break;
			}
		} while (menu != 4);
	}

	private void menuListadoMedicos() throws SQLException {
		Scanner in = new Scanner(System.in);
		boolean correcto;
		int menu = 0;
		do {
			do {
				try {
					menu = 0;
					System.out.println("Elije una opcion:\n" + "1.Listado completo de medicos\n"
							+ "2.Listado de consultas y pacientes realidaza por medico\n" + "3.salir");
					menu = Integer.parseInt(in.nextLine());
					correcto = true;
				} catch (Exception e) {
					System.out.println("Debe seleccionar numericamente la opcion");
					correcto = false;
				}
			} while (!correcto);
			switch (menu) {
			case 1:
				listadoMedicosCompleto();
				break;
			case 2:
				listadoConsultasPacientesPorMedico();
				break;
			case 3:
				System.out.println("Agur");
			default:
				break;
			}
		} while (menu != 3);

	}

	private void menuListadoConsultas() throws SQLException {
		Scanner in = new Scanner(System.in);
		boolean correcto;
		int menu = 0;
		do {
			do {
				try {
					menu = 0;
					System.out.println("Elije una opcion:\n" + "1.Listado completo de consultas\n"
							+ "2.Listado de consultas por fecha\n" + "3.Listado de consultas por hora\n"
							+ "4.Listado de consultas por sala\n" + "5.Salir");
					menu = Integer.parseInt(in.nextLine());
					correcto = true;
				} catch (Exception e) {
					System.out.println("Debe seleccionar numericamente la opcion");
					correcto = false;
				}
			} while (!correcto);
			switch (menu) {
			case 1:
				listadoConsultasCompleto();
				break;
			case 2:
				listadoConsultasFecha();
				break;
			case 3:
				listadoConsultasHora();
				break;
			case 4:
				listadoConsultasSala();
				break;
			case 5:
				System.out.println("Agur");
			default:
				break;
			}
		} while (menu != 5);

	}

	private void listadoPacientesCompleto() throws SQLException {
		Connection miConexion = new ConexionBBDDSQLite().conectorSQLite();

		Statement miSentencia = miConexion.createStatement();

		ResultSet miResultSet = miSentencia.executeQuery("select * from paciente where baja = 0");
		while (miResultSet.next()) {
			System.out.println("id: " + miResultSet.getInt(1) + ", dni: " + miResultSet.getString(2) + ", nombre: "
					+ miResultSet.getString(3) + ", apellidos : " + miResultSet.getString(4) + ", direccion: "
					+ miResultSet.getString(5) + ", profesion: " + miResultSet.getString(6) + ", edad: "
					+ miResultSet.getString(7));
		}

		miConexion.close();

	}

	private void listadoPacientesReducido() throws SQLException {
		Connection miConexion = new ConexionBBDDSQLite().conectorSQLite();

		Statement miSentencia = miConexion.createStatement();

		ResultSet miResultSet = miSentencia.executeQuery("SELECT * FROM paciente where baja = 0");
		while (miResultSet.next()) {
			System.out.println("id: " + miResultSet.getInt(1) + ", nombre: " + miResultSet.getString(3));
		}

		miConexion.close();
	}

	private void listadoPacientesSinCitaCompletada() throws SQLException {
		Connection miConexion = new ConexionBBDDSQLite().conectorSQLite();

		Statement miSentencia = miConexion.createStatement();

		ResultSet miResultSet = miSentencia.executeQuery(
				"SELECT p.dni, p.nombre FROM paciente p , consulta c where p.baja = 0 and  c.realizada=0");
		while (miResultSet.next()) {
			System.out.println("dni: " + miResultSet.getString(1) + ", nombre: " + miResultSet.getString(2));
		}

		miConexion.close();
	}

	private void listadoHistoricoPacientes() throws SQLException {
		Connection miConexion = new ConexionBBDDSQLite().conectorSQLite();
		Scanner in = new Scanner(System.in);
		int id = 0;
		boolean correcto;

		do {
			try {
				id = 0;
				listadoPacientesReducido();
				System.out.println("Selecciona el id de un paciente:");
				id = Integer.parseInt(in.nextLine());
				if (comprobarIdPaciente(id) == true) {
					correcto = true;
				} else {
					System.out.println("Debe elegir un id que de el listado que se le muestra");
					correcto = false;
				}
			} catch (Exception e) {
				System.out.println("Debe seleccionar el id numerico del paciente");
				correcto = false;
			}
		} while (!correcto);

		Statement miSentencia = miConexion.createStatement();

		ResultSet miResultSet = miSentencia.executeQuery(
				"SELECT c.* FROM paciente p , consulta c where p.baja = 0 and c.realizada= 1 and p.idPaciente = " + id);
		while (miResultSet.next()) {
			System.out.println("id: " + miResultSet.getInt(1) + ", sala: " + miResultSet.getInt(2) + ", fecha: "
					+ miResultSet.getString(3) + ", hora: " + miResultSet.getString(4) + ", id Medico: "
					+ miResultSet.getString(5) + ", id paciente: " + miResultSet.getString(6) + ",medicamentos: "
					+ miResultSet.getString(10) + ", coste: " + miResultSet.getDouble(7)
					+ ", analisis complementarios: " + miResultSet.getString(8));
		}
		miConexion.close();
	}

	private void listadoMedicosCompleto() throws SQLException {
		Connection miConexion = new ConexionBBDDSQLite().conectorSQLite();

		Statement miSentencia = miConexion.createStatement();

		ResultSet miResultSet = miSentencia.executeQuery("SELECT * FROM medico where baja = 0");
		while (miResultSet.next()) {
			System.out.println("id: " + miResultSet.getInt(1) + ", numColegiado: " + miResultSet.getString(2)
					+ ", nombre: " + miResultSet.getString(3) + ", apellidos: " + miResultSet.getString(4)
					+ ", fecha Nacimiento: " + miResultSet.getString(5) + ", fecha Contratacion: "
					+ miResultSet.getString(6) + ", especialidad: " + miResultSet.getString(7));
		}
		miConexion.close();
	}

	private void listadoMedicosReducido() throws SQLException {
		Connection miConexion = new ConexionBBDDSQLite().conectorSQLite();

		Statement miSentencia = miConexion.createStatement();

		ResultSet miResultSet = miSentencia.executeQuery("SELECT * FROM medico where baja = 0");
		while (miResultSet.next()) {
			System.out.println("id: " + miResultSet.getInt(1) + ", nombre: " + miResultSet.getString(3));
		}

		miConexion.close();
	}

	private void listadoConsultasPacientesPorMedico() throws SQLException {
		Connection miConexion = new ConexionBBDDSQLite().conectorSQLite();
		Scanner in = new Scanner(System.in);
		int id = 0;
		boolean correcto;
		do {
			try {
				id = 0;
				listadoMedicosReducido();
				System.out.println("Selecciona el id de un medico:");
				id = Integer.parseInt(in.nextLine());
				if (comprobarIdMedico(id) == true) {
					correcto = true;
				} else {
					System.out.println("Debe elegir un id que de el listado que se le muestra");
					correcto = false;
				}
			} catch (Exception e) {
				System.out.println("Debe seleccionar el id numerico del medico");
				correcto = false;
			}
		} while (!correcto);
		Statement miSentencia = miConexion.createStatement();

		ResultSet miResultSet = miSentencia.executeQuery(
				"SELECT c.* FROM paciente p , consulta c where p.baja = 0 and c.realizada = 1 and p.idPaciente = "
						+ id);
		while (miResultSet.next()) {
			System.out.println("id: " + miResultSet.getInt(1) + ", sala: " + miResultSet.getInt(2) + ", fecha: "
					+ miResultSet.getString(3) + ", hora: " + miResultSet.getString(4) + ", id Medico: "
					+ miResultSet.getString(5) + ", id paciente: " + miResultSet.getString(6) + ",medicamentos: "
					+ miResultSet.getString(10) + ", coste: " + miResultSet.getDouble(7)
					+ ", analisis complementarios: " + miResultSet.getString(8));
		}
		miConexion.close();
	}

	private void listadoConsultasCompleto() throws SQLException {
		Connection miConexion = new ConexionBBDDSQLite().conectorSQLite();
		Statement miSentencia = miConexion.createStatement();

		ResultSet miResultSet = miSentencia.executeQuery("SELECT * FROM consulta where realizada = 0");
		while (miResultSet.next()) {
			System.out.println("id: " + miResultSet.getInt(1) + ", sala: " + miResultSet.getInt(2) + ", fecha: "
					+ miResultSet.getString(3) + ", hora: " + miResultSet.getString(4) + ", id Medico: "
					+ miResultSet.getString(5) + ", id paciente: " + miResultSet.getString(6) + ",medicamentos: "
					+ miResultSet.getString(10) + ", coste: " + miResultSet.getDouble(7)
					+ ", analisis complementarios: " + miResultSet.getString(8));
		}
		miConexion.close();
	}

	private void listadoConsultasFecha() throws SQLException {
		Connection miConexion = new ConexionBBDDSQLite().conectorSQLite();
		Scanner in = new Scanner(System.in);
		String fecha = "";
		Date fechaConsulta = null;
		boolean correcto;

		do {
			System.out.println("Introduce la fecha en la que se atendera al paciente (con este formato: yyyy-mm-dd):");
			fecha = in.nextLine();
			try {
				fechaConsulta = comprobarFecha(fecha);
				System.out.println(fechaConsulta);
				correcto = true;
			} catch (Exception e) {
				correcto = false;
			}
		} while (!correcto);

		Statement miSentencia = miConexion.createStatement();

		try {
			ResultSet miResultSet = miSentencia
					.executeQuery("SELECT * FROM consulta where fecha = '" + fechaConsulta + "' and realizada = 0");
			while (miResultSet.next()) {
				System.out.println("id: " + miResultSet.getInt(1) + ", sala: " + miResultSet.getInt(2) + ", fecha: "
						+ miResultSet.getString(3) + ", hora: " + miResultSet.getString(4) + ", id Medico: "
						+ miResultSet.getString(5) + ", id paciente: " + miResultSet.getString(6) + ",medicamentos: "
						+ miResultSet.getString(10) + ", coste: " + miResultSet.getDouble(7)
						+ ", analisis complementarios: " + miResultSet.getString(8));
			}
		} catch (Exception e) {
			System.out.println("Ninguna consulta con esa fecha relazionada");
		}
		miConexion.close();
	}

	private void listadoConsultasHora() throws SQLException {
		Connection miConexion = new ConexionBBDDSQLite().conectorSQLite();
		Scanner in = new Scanner(System.in);
		String hora = "";
		Time horaConsulta = null;
		boolean correcto;

		do {
			System.out.println("Introduce la hora en la realizara la consulta (con formato de 24h)");
			hora = in.nextLine();
			try {
				horaConsulta = comprobarHora(hora);
				System.out.println(horaConsulta);
				correcto = true;
			} catch (Exception e) {
				correcto = false;
			}
		} while (!correcto);

		Statement miSentencia = miConexion.createStatement();
		try {
			ResultSet miResultSet = miSentencia
					.executeQuery("SELECT * FROM consulta where hora = '" + horaConsulta + "' and realizada = 0");
			while (miResultSet.next()) {
				System.out.println("id: " + miResultSet.getInt(1) + ", sala: " + miResultSet.getInt(2) + ", fecha: "
						+ miResultSet.getString(3) + ", hora: " + miResultSet.getString(4) + ", id Medico: "
						+ miResultSet.getString(5) + ", id paciente: " + miResultSet.getString(6) + ",medicamentos: "
						+ miResultSet.getString(10) + ", coste: " + miResultSet.getDouble(7)
						+ ", analisis complementarios: " + miResultSet.getString(8));
			}
		} catch (Exception e) {
			System.out.println("Ninguna consulta con esa hora relazionada");
		}
		miConexion.close();
	}

	private void listadoConsultasSala() throws SQLException {
		Connection miConexion = new ConexionBBDDSQLite().conectorSQLite();
		Scanner in = new Scanner(System.in);
		int sala = 0;
		boolean correcto;

		do {
			try {
				sala = 0;
				System.out.println("Elija el numero de sala para ver todas las consultas relazionada con esa sala: ");
				sala = Integer.parseInt(in.nextLine());
				correcto = true;
			} catch (Exception e) {
				System.out.println("Debe seleccionar numericamente la opcion");
				correcto = false;
			}
		} while (!correcto);

		Statement miSentencia = miConexion.createStatement();
		try {
			ResultSet miResultSet = miSentencia
					.executeQuery("SELECT * FROM consulta where sala = " + sala + " and realizada = 0");
			while (miResultSet.next()) {
				System.out.println("id: " + miResultSet.getInt(1) + ", sala: " + miResultSet.getInt(2) + ", fecha: "
						+ miResultSet.getString(3) + ", hora: " + miResultSet.getString(4) + ", id Medico: "
						+ miResultSet.getString(5) + ", id paciente: " + miResultSet.getString(6) + ",mediacamentos: "
						+ miResultSet.getString(10) + ", coste: " + miResultSet.getDouble(7)
						+ ", analisis complementarios: " + miResultSet.getString(8));
			}
		} catch (Exception e) {
			System.out.println("No hay ninguna consulta asociada a esa sala");
		}
		miConexion.close();
	}

	private void crudPacientes() throws SQLException {
		Scanner in = new Scanner(System.in);
		boolean correcto;
		int menu = 0;
		do {
			do {
				try {
					menu = 0;
					System.out.println("Que quiere hacer con el paciente:\n" + "1.Crear paciente\n"
							+ "2.Modificar paciente\n" + "3.Borrar paciente\n" + "4.Salir");
					menu = Integer.parseInt(in.nextLine());
					correcto = true;
				} catch (Exception e) {
					System.out.println("Debe seleccionar numericamente la opcion");
					correcto = false;
				}
			} while (!correcto);
			switch (menu) {
			case 1:
				crearPaciente();
				break;
			case 2:
				modificarPaciente();
				break;
			case 3:
				eliminarPaciente();
				break;
			case 4:
				System.out.println("Agur");
				break;
			default:
				break;
			}
		} while (menu != 4);

	}

	private void crearPaciente() throws SQLException {
		Connection miConexion = new ConexionBBDDSQLite().conectorSQLite();
		Scanner in = new Scanner(System.in);
		String dni = "", nombre = "", apellidos = "", direccion = "", profesion = "";
		int edad = 0;
		boolean baja = false, correcto;
		System.out.println("Introduzca el DNI del paciente");
		dni = in.nextLine();
		System.out.println("Introduce el nombre del paciente");
		nombre = in.nextLine();
		System.out.println("Introduce los apellidos del paciente");
		apellidos = in.nextLine();
		System.out.println("Introduce la direccion del paciente");
		direccion = in.nextLine();
		do {
			try {
				edad = 0;
				System.out.println("Introduce la edad del paciente");
				edad = Integer.parseInt(in.nextLine());
				correcto = true;
			} catch (Exception e) {
				System.out.println("La edad debe ser en numeros");
				correcto = false;
			}
		} while (!correcto);
		System.out.println("Introduce la profesion del paciente");
		profesion = in.nextLine();

		paciente pa = new paciente(dni, nombre, apellidos, direccion, edad, profesion, baja);

		Statement miSentencia = miConexion.createStatement();

		String sql = String.format("INSERT INTO paciente(dni,nombre,apellidos,direccion,edad,profesion,baja) VALUES('"
				+ pa.getDni() + "','" + pa.getNombre() + "','" + pa.getApellidos() + "','" + pa.getDireccion() + "',"
				+ pa.getEdad() + ",'" + pa.getDireccion() + "', 0 )");

		miSentencia.execute(sql);

		miConexion.close();

		aPaciente.clear();
		guardarPacientes(aPaciente);

	}

	private void modificarPaciente() throws SQLException {
		Connection miConexion = new ConexionBBDDSQLite().conectorSQLite();
		Scanner in = new Scanner(System.in);
		String dni = "", nombre = "", apellidos = "", direccion = "", profesion = "";
		int edad = 0, menu = 0;
		boolean correcto;
		do {
			try {
				menu = 0;
				listadoPacientesReducido();
				System.out.println("Selecciona el id de un paciente:");
				menu = Integer.parseInt(in.nextLine());
				if (comprobarIdPaciente(menu) == true) {
					correcto = true;
				} else {
					System.out.println("Debe elegir un id que de el listado que se le muestra");
					correcto = false;
				}
			} catch (Exception e) {
				System.out.println("Debe seleccionar el id numerico del paciente");
				correcto = false;
			}
		} while (!correcto);
		System.out.println("Introduzca el DNI del paciente");
		dni = in.nextLine();
		System.out.println("Introduce el nombre del paciente");
		nombre = in.nextLine();
		System.out.println("Introduce los apellidos del paciente");
		apellidos = in.nextLine();
		System.out.println("Introduce la direccion del paciente");
		direccion = in.nextLine();
		do {
			try {
				edad = 0;
				System.out.println("Introduce la edad del paciente");
				edad = Integer.parseInt(in.nextLine());
				correcto = true;
			} catch (Exception e) {
				System.out.println("La edad debe ser en numeros");
				correcto = false;
			}
		} while (!correcto);
		System.out.println("Introduce la profesion del paciente");
		profesion = in.nextLine();

		Statement miSentencia = miConexion.createStatement();

		String sql = String.format("UPDATE paciente SET `dni` = '" + dni + "'," + "`nombre` = '" + nombre + "',"
				+ "`apellidos` = '" + apellidos + "'," + "`direccion` = '" + direccion + "'," + "`edad` = " + edad + ","
				+ "`profesion` = '" + profesion + "' WHERE `idPaciente` = " + menu + ";");

		miSentencia.execute(sql);

		miConexion.close();

		aPaciente.clear();
		guardarPacientes(aPaciente);
	}

	private void eliminarPaciente() throws SQLException {
		Connection miConexion = new ConexionBBDDSQLite().conectorSQLite();
		Scanner in = new Scanner(System.in);
		int menu = 0;
		boolean correcto;
		do {
			try {
				menu = 0;
				listadoPacientesReducido();
				System.out.println("Selecciona el id de un paciente:");
				menu = Integer.parseInt(in.nextLine());
				if (comprobarIdPaciente(menu) == true) {
					correcto = true;
				} else {
					System.out.println("Debe elegir un id que de el listado que se le muestra");
					correcto = false;
				}
			} catch (Exception e) {
				System.out.println("Debe seleccionar el id numerico del paciente");
				correcto = false;
			}
		} while (!correcto);

		Statement miSentencia = miConexion.createStatement();

		String sql = String.format("UPDATE paciente SET baja= 1 WHERE `idPaciente` = " + menu + ";");

		miSentencia.execute(sql);

		miConexion.close();

		aPaciente.clear();
		guardarPacientes(aPaciente);
	}

	private void crudMedicos() throws SQLException {
		Scanner in = new Scanner(System.in);
		boolean correcto;
		int menu = 0;
		do {
			do {
				try {
					menu = 0;
					System.out.println("Que quiere hacer con el medico:\n" + "1.Crear medico\n" + "2.Modificar medico\n"
							+ "3.Borrar medico\n" + "4.Salir");
					menu = Integer.parseInt(in.nextLine());
					correcto = true;
				} catch (Exception e) {
					System.out.println("Debe seleccionar numericamente la opcion");
					correcto = false;
				}
			} while (!correcto);
			switch (menu) {
			case 1:
				crearMedico();
				break;
			case 2:
				modificarMedico();
				break;
			case 3:
				eliminarMedico();
				break;
			case 4:
				System.out.println("Agur");
				break;
			default:
				break;
			}
		} while (menu != 4);

	}

	private void crearMedico() throws SQLException {
		Connection miConexion = new ConexionBBDDSQLite().conectorSQLite();
		Scanner in = new Scanner(System.in);
		String dni = "", nombre = "", apellidos = "", direccion = "", especialidad = "", fecha = "";
		Date fechaNaci = null, fechaContrata = null;
		int numcolegiado = 0, edad = 0;
		boolean baja = false, correcto;
		do {
			try {
				numcolegiado = 0;
				System.out.println("Introduce el numero de colegiado del medico:");
				numcolegiado = Integer.parseInt(in.nextLine());
				correcto = true;
			} catch (Exception e) {
				System.out.println("Debe ser numerico");
				correcto = false;
			}
		} while (!correcto);
		System.out.println("Introduce el dni del medico");
		dni = in.nextLine();
		System.out.println("Introduce el nombre del medico");
		nombre = in.nextLine();
		System.out.println("Introduce los apellidos del medico");
		apellidos = in.nextLine();
		do {
			System.out.println("Introduce la fecha de nacimiento del medico (con este formato: yyyy-mm-dd):");
			fecha = in.nextLine();
			try {
				fechaNaci = comprobarFecha(fecha);
				correcto = true;
			} catch (Exception e) {
				correcto = false;
			}
		} while (!correcto);
		do {
			System.out.println("Introduce la fecha de contratacion del medico (con este formato: yyyy-mm-dd):");
			fecha = in.nextLine();
			try {
				fechaContrata = comprobarFecha(fecha);
				correcto = true;
			} catch (Exception e) {
				correcto = false;
			}
		} while (!correcto);
		System.out.println("Introduce la especialidad del medico:");
		especialidad = in.nextLine();

		medico med = new medico(numcolegiado, dni, nombre, apellidos, fechaNaci, fechaContrata, especialidad, baja);

		Statement miSentencia = miConexion.createStatement();

		String sql = String.format(
				"INSERT INTO medico(numColegiado,dni,nombre,apellidos,fechaNaci,fechaContratacion,especialidad,baja) "
						+ "VALUES(" + med.getNumColegiado() + ",'" + med.getDni() + "','" + med.getNombre() + "','"
						+ med.getApellidos() + "','" + med.getFecahNaci() + "','" + med.getFechaContrata() + "','"
						+ med.getEspecialidad() + "', 0 )");

		miSentencia.execute(sql);

		miConexion.close();

		aMedico.clear();
		guardarMedicos(aMedico);

	}

	private void modificarMedico() throws SQLException {
		Connection miConexion = new ConexionBBDDSQLite().conectorSQLite();
		Scanner in = new Scanner(System.in);
		String dni = "", nombre = "", apellidos = "", direccion = "", especialidad = "", fecha = "";
		Date fechaNaci = null, fechaContrata = null;
		int numcolegiado = 0, edad = 0, menu = 0;
		boolean correcto;
		do {
			try {
				menu = 0;
				listadoMedicosReducido();
				System.out.println("Selecciona el id de un medico:");
				menu = Integer.parseInt(in.nextLine());
				if (comprobarIdMedico(menu) == true) {
					correcto = true;
				} else {
					System.out.println("Debe elegir un id que de el listado que se le muestra");
					correcto = false;
				}
			} catch (Exception e) {
				System.out.println("Debe seleccionar el id numerico del medico");
				correcto = false;
			}
		} while (!correcto);
		do {
			try {
				numcolegiado = 0;
				System.out.println("Introduce el numero de colegiado del medico:");
				numcolegiado = Integer.parseInt(in.nextLine());
				correcto = true;
			} catch (Exception e) {
				System.out.println("Debe ser numerico");
				correcto = false;
			}
		} while (!correcto);
		System.out.println("Introduce el dni del medico");
		dni = in.nextLine();
		System.out.println("Introduce el nombre del medico");
		nombre = in.nextLine();
		System.out.println("Introduce los apellidos del medico");
		apellidos = in.nextLine();
		do {
			System.out.println("Introduce la fecha de nacimiento del medico (con este formato: yyyy-mm-dd):");
			fecha = in.nextLine();
			try {
				fechaNaci = comprobarFecha(fecha);
				System.out.println(fechaNaci);
				correcto = true;
			} catch (Exception e) {
				correcto = false;
			}
		} while (!correcto);
		do {
			System.out.println("Introduce la fecha de contratacion del medico (con este formato: yyyy-mm-dd):");
			fecha = in.nextLine();
			try {
				fechaContrata = comprobarFecha(fecha);
				correcto = true;
			} catch (Exception e) {
				correcto = false;
			}
		} while (!correcto);
		System.out.println("Introduce la especialidad del medico:");
		especialidad = in.nextLine();

		Statement miSentencia = miConexion.createStatement();

		String sql = String.format("UPDATE medico SET numColegiado = " + numcolegiado + ",dni = '" + dni
				+ "', nombre =  '" + nombre + "', " + "apellidos = '" + apellidos + "', fechaNaci = '" + fechaNaci
				+ "', fechaContratacion ='" + fechaContrata + "'," + "especialidad = '" + especialidad
				+ "' where idmedico = " + menu + "");

		miSentencia.execute(sql);

		miConexion.close();

		aMedico.clear();
		guardarMedicos(aMedico);
	}

	private void eliminarMedico() throws SQLException {
		Connection miConexion = new ConexionBBDDSQLite().conectorSQLite();
		Scanner in = new Scanner(System.in);
		int menu = 0;
		boolean correcto;
		do {
			try {
				menu = 0;
				listadoMedicosReducido();
				System.out.println("Selecciona el id de un medico:");
				menu = Integer.parseInt(in.nextLine());
				if (comprobarIdMedico(menu) == true) {
					correcto = true;
				} else {
					System.out.println("Debe elegir un id que de el listado que se le muestra");
					correcto = false;
				}
			} catch (Exception e) {
				System.out.println("Debe seleccionar el id numerico del medico");
				correcto = false;
			}
		} while (!correcto);

		Statement miSentencia = miConexion.createStatement();

		String sql = String.format("UPDATE medico SET baja=1 WHERE `idmedico` = " + menu + ";");

		miSentencia.execute(sql);

		miConexion.close();

		aMedico.clear();
		guardarMedicos(aMedico);

	}

	private void crudConsultas() throws SQLException {
		Scanner in = new Scanner(System.in);
		boolean correcto;
		int menu = 0;
		do {
			do {
				try {
					menu = 0;
					System.out.println("Que quiere hacer con la consulta:\n" + "1.Crear consulta\n"
							+ "2.Modificar consulta\n" + "3.Borrar consulta\n" + "4.Salir");
					menu = Integer.parseInt(in.nextLine());
					correcto = true;
				} catch (Exception e) {
					System.out.println("Debe seleccionar numericamente la opcion");
					correcto = false;
				}
			} while (!correcto);
			switch (menu) {
			case 1:
				crearConsulta();
				break;
			case 2:
				modificarConsulta();
				break;
			case 3:
				eliminarConsulta();
				break;
			case 4:
				System.out.println("Agur");
				break;
			default:
				break;
			}
		} while (menu != 4);

	}

	private void crearConsulta() throws SQLException {
		Connection miConexion = new ConexionBBDDSQLite().conectorSQLite();
		Scanner in = new Scanner(System.in);
		int sala = 0, idMedico = 0, idPaciente = 0, menu = 0;
		Double coste = 0.0;
		String analisis = "", fecha = "", hora = "", medicamentos = "";
		Date fechaConsulta = null;
		Time horaConsulta = null;
		boolean correcto;
		do {
			try {
				sala = 0;
				System.out.println("Introduce la sala de la consulta:");
				sala = Integer.parseInt(in.nextLine());
				correcto = true;
			} catch (Exception e) {
				System.out.println("Debe ser numerico");
				correcto = false;
			}
		} while (!correcto);
		do {
			System.out.println("Introduce la fecha en la que se atendera al paciente (con este formato: yyyy-mm-dd):");
			fecha = in.nextLine();
			try {
				fechaConsulta = comprobarFecha(fecha);
				System.out.println(fechaConsulta);
				correcto = true;
			} catch (Exception e) {
				correcto = false;
			}
		} while (!correcto);
		do {
			System.out.println("Introduce la hora en la realizara la consulta (con formato de 24h)");
			hora = in.nextLine();
			try {
				horaConsulta = comprobarHora(hora);
				System.out.println(horaConsulta);
				correcto = true;
			} catch (Exception e) {
				correcto = false;
			}
			/*
			 * codigo posiblemente util en el futuro se relaciona con con comprobar hora if
			 * (comprobarHora(hora) == false) {
			 * System.out.println("Hora mal introducida, debe tener un formato de 24 horas"
			 * ); correcto = false; } else { horaConsulta = Time.valueOf(hora); correcto =
			 * true; }
			 */
		} while (!correcto);
		do {
			try {
				idMedico = 0;
				listadoMedicosReducido();
				System.out.println("Selecciona el id de un medico:");
				idMedico = Integer.parseInt(in.nextLine());
				correcto = true;
			} catch (Exception e) {
				System.out.println("Debe seleccionar el id numerico del medico");
				correcto = false;
			}
		} while (!correcto);
		do {
			try {
				idPaciente = 0;
				listadoPacientesReducido();
				System.out.println("Selecciona el id del paciente:");
				idPaciente = Integer.parseInt(in.nextLine());
				correcto = true;
			} catch (Exception e) {
				System.out.println("Debe seleccionar el id numerico del medico");
				correcto = false;
			}
		} while (!correcto);
		System.out.println("Medicamentos que necesita el paciente: ");
		medicamentos = in.nextLine();
		do {
			try {
				coste = 0.0;
				System.out.println("Introduce el coste de la consulta:");
				coste = Double.parseDouble(in.nextLine());
				correcto = true;
			} catch (Exception e) {
				System.out.println("Debe ser numerico y tener decimales");
				correcto = false;
			}
		} while (!correcto);
		System.out.println("Analisis complementarios para el paciente");
		analisis = in.nextLine();

		consulta cont = new consulta(sala, fechaConsulta, horaConsulta, idMedico, idPaciente, medicamentos, coste,
				analisis);

		Statement miSentencia = miConexion.createStatement();

		String sql = String.format(
				"INSERT INTO consulta (sala,fecha,hora,medicoInterviniente,paciente,coste,analisisComplementarios,realizada,medicamentos) VALUES ("
						+ cont.getSala() + ",'" + cont.getFecha() + "','" + cont.getHora() + "'," + cont.getIdMedico()
						+ "," + cont.getIdPaciente() + "," + cont.getCoste() + ",'" + cont.getAnalisisComplement()
						+ "', 0 ,'" + cont.getMedicamentos() + "')");

		miSentencia.execute(sql);

		miConexion.close();

		aConsulta.clear();
		guardarConsultas(aConsulta);

	}

	private void modificarConsulta() throws SQLException {
		Connection miConexion = new ConexionBBDDSQLite().conectorSQLite();
		Scanner in = new Scanner(System.in);
		int sala = 0, idMedico = 0, idPaciente = 0, menu = 0;
		Double coste = 0.0;
		String analisis = "", fecha = "", hora = "", medicamentos = "";
		Date fechaConsulta = null;
		Time horaConsulta = null;
		boolean correcto;
		do {
			try {
				menu = 0;
				listadoConsultasCompleto();
				System.out.println("Selecciona el id de una consulta:");
				menu = Integer.parseInt(in.nextLine());
				if (comprobarIdConsulta(menu) == true) {
					correcto = true;
				} else {
					System.out.println("Debe elegir un id que de el listado que se le muestra");
					correcto = false;
				}
			} catch (Exception e) {
				System.out.println("Debe seleccionar el id numerico de la consulta");
				correcto = false;
			}
		} while (!correcto);
		do {
			try {
				sala = 0;
				System.out.println("Introduce la sala de la consulta:");
				sala = Integer.parseInt(in.nextLine());
				correcto = true;
			} catch (Exception e) {
				System.out.println("Debe ser numerico");
				correcto = false;
			}
		} while (!correcto);
		do {
			System.out.println("Introduce la fecha en la que se atendera al paciente (con este formato: yyyy-mm-dd):");
			fecha = in.nextLine();
			try {
				fechaConsulta = comprobarFecha(fecha);
				System.out.println(fechaConsulta);
				correcto = true;
			} catch (Exception e) {
				correcto = false;
			}
		} while (!correcto);
		do {
			System.out.println("Introduce la hora en la realizara la consulta (con formato de 24h)");
			hora = in.nextLine();
			try {
				horaConsulta = comprobarHora(hora);
				System.out.println(horaConsulta);
				correcto = true;
			} catch (Exception e) {
				correcto = false;
			}
		} while (!correcto);
		System.out.println("Medicamentos que necesita el paciente: ");
		medicamentos = in.nextLine();
		do {
			try {
				idMedico = 0;
				listadoMedicosReducido();
				System.out.println("Selecciona el id de un medico:");
				idMedico = Integer.parseInt(in.nextLine());
				correcto = true;
			} catch (Exception e) {
				System.out.println("Debe seleccionar el id numerico del medico");
				correcto = false;
			}
		} while (!correcto);
		do {
			try {
				idPaciente = 0;
				listadoPacientesReducido();
				System.out.println("Selecciona el id del paciente:");
				idPaciente = Integer.parseInt(in.nextLine());
				correcto = true;
			} catch (Exception e) {
				System.out.println("Debe seleccionar el id numerico del medico");
				correcto = false;
			}
		} while (!correcto);
		do {
			try {
				coste = 0.0;
				System.out.println("Introduce el coste de la consulta:");
				coste = Double.parseDouble(in.nextLine());
				correcto = true;
			} catch (Exception e) {
				System.out.println("Debe ser numerico y tener decimales");
				correcto = false;
			}
		} while (!correcto);
		System.out.println("Analisis complementarios para el paciente");
		analisis = in.nextLine();
		Statement miSentencia = miConexion.createStatement();

		String sql = String.format("UPDATE consulta SET sala = " + sala + ", fecha = '" + fechaConsulta + "', hora =  '"
				+ hora + "', " + "medicoInterviniente = " + idMedico + ", paciente = " + idPaciente + ", coste ="
				+ coste + "," + "analisisComplementarios = '" + analisis + "', medicamentos = '" + medicamentos
				+ "' where idconsulta = " + menu + "");

		miSentencia.execute(sql);

		miConexion.close();

		aConsulta.clear();
		guardarConsultas(aConsulta);

	}

	private void eliminarConsulta() throws SQLException {
		Connection miConexion = new ConexionBBDDSQLite().conectorSQLite();
		Scanner in = new Scanner(System.in);
		int menu = 0;
		boolean correcto;
		do {
			try {
				menu = 0;
				listadoConsultasCompleto();
				System.out.println("Selecciona el id de una consulta:");
				menu = Integer.parseInt(in.nextLine());
				if (comprobarIdConsulta(menu) == true) {
					correcto = true;
				} else {
					System.out.println("Debe elegir un id que de el listado que se le muestra");
					correcto = false;
				}
			} catch (Exception e) {
				System.out.println("Debe seleccionar el id numerico de la consulta");
				correcto = false;
			}
		} while (!correcto);
		Statement miSentencia = miConexion.createStatement();

		String sql = String.format("UPDATE consulta SET realizada = 1 where idconsulta = " + menu + "");

		miSentencia.execute(sql);
		miConexion.close();

		aConsulta.clear();
		guardarConsultas(aConsulta);
	}

	public Date comprobarFecha(String fecha) throws SQLException {
		// String startDate="01-02-2013";
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = null;
		try {
			date = sdf1.parse(fecha);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("Fecha mal introducida");

		}
		java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
		return sqlStartDate;

	}

	public Time comprobarHora(String hora) {
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm");
		java.util.Date time = null;
		try {
			time = sdf1.parse(hora);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.println("Fecha mal introducida");

		}
		java.sql.Time sqlStartDate = new java.sql.Time(time.getTime());
		return sqlStartDate;

		/*
		 * codigo viejo posiblemente util en el futuro // Regex to check valid time in
		 * 24-hour format. String regex = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
		 * 
		 * // Compile the ReGex Pattern p = Pattern.compile(regex);
		 * 
		 * // If the time is empty // return false if (time == null) { return false; }
		 * 
		 * // Pattern class contains matcher() method // to find matching between given
		 * time // and regular expression. Matcher m = p.matcher(time);
		 * 
		 * // Return if the time // matched the ReGex return m.matches();
		 */
	}

	public void guardarPacientes(ArrayList<paciente> aPaciente) throws SQLException {
		Connection miConexion = new ConexionBBDDSQLite().conectorSQLite();
		Statement miSentencia = miConexion.createStatement();

		ResultSet miResultSet = miSentencia.executeQuery("SELECT * FROM paciente where baja = 0");
		while (miResultSet.next()) {
			paciente p = new paciente(miResultSet.getInt(1), miResultSet.getString(2), miResultSet.getString(3),
					miResultSet.getString(4), miResultSet.getString(5), miResultSet.getInt(6), miResultSet.getString(7),
					miResultSet.getBoolean(8));
			aPaciente.add(p);
		}
		miConexion.close();

	}

	public void guardarMedicos(ArrayList<medico> aMedicos) throws SQLException {
		Connection miConexion = new ConexionBBDDSQLite().conectorSQLite();
		Statement miSentencia = miConexion.createStatement();

		ResultSet miResultSet = miSentencia.executeQuery("SELECT * FROM medico where baja = 0");
		while (miResultSet.next()) {
			medico m = new medico(miResultSet.getInt(1), miResultSet.getInt(2), miResultSet.getString(3),
					miResultSet.getString(4), miResultSet.getString(5), miResultSet.getDate(6), miResultSet.getDate(7),
					miResultSet.getString(8), miResultSet.getBoolean(9));
			aMedicos.add(m);
		}
		miConexion.close();

	}

	public void guardarConsultas(ArrayList<consulta> aConsultas) throws SQLException {
		Connection miConexion = new ConexionBBDDSQLite().conectorSQLite();
		Statement miSentencia = miConexion.createStatement();

		ResultSet miResultSet = miSentencia.executeQuery("SELECT * FROM consulta where realizada = 0");
		while (miResultSet.next()) {
			consulta c = new consulta(miResultSet.getInt(1), miResultSet.getInt(2), miResultSet.getDate(3),
					miResultSet.getTime(4), miResultSet.getInt(5), miResultSet.getInt(6), miResultSet.getString(10),
					miResultSet.getDouble(7), miResultSet.getString(8), miResultSet.getBoolean(9));
			aConsultas.add(c);
		}
		miConexion.close();
	}

	public boolean comprobarIdPaciente(Integer id) {
		for (int i = 0; i < aPaciente.size(); i++) {
			if (aPaciente.get(i).getId() == id) {
				return true;
			}
		}
		return false;
	}

	public boolean comprobarIdMedico(Integer id) {
		for (int i = 0; i < aMedico.size(); i++) {
			if (aMedico.get(i).getId() == id) {
				return true;
			}
		}
		return false;
	}

	public boolean comprobarIdConsulta(Integer id) {
		for (int i = 0; i < aConsulta.size(); i++) {
			if (aConsulta.get(i).getId() == id) {
				return true;
			}
		}
		return false;
	}

	private void datosHospital() {

	}

	private void listarMetadatos() throws SQLException {
		Connection miConexion = new ConexionBBDDSQLite().conectorSQLite();
		try {
			// Obtención de metadatos
			DatabaseMetaData datosBBDD = miConexion.getMetaData();

			// Mostramos información de la BBDD
			System.out.println("Gestor de BBDD: " + datosBBDD.getDatabaseProductName());
			System.out.println("Versión del Gestor: " + datosBBDD.getDatabaseProductVersion());
			System.out.println("Nombre del driver: " + datosBBDD.getDriverName());
			System.out.println("Versión del driver: " + datosBBDD.getDriverVersion());
			System.out.println("URL de la BBDD: " + datosBBDD.getURL());
			System.out.println("Nombre del usuario: " + datosBBDD.getUserName());

			// Mostramos información de las tablas, columnas, ...

			ResultSet miMetaResult = datosBBDD.getTables("ejemplo", null, null, new String[] { "table" });

			// ResultSet miMetaResult = datosBBDD.getTables(null,null,"empl%",null);
			System.out.println("\n----Lista de tablas----");
			while (miMetaResult.next()) {
				System.out.println("\nNombre de tabla: " + miMetaResult.getString("TABLE_NAME") + " - Esquema: "
						+ miMetaResult.getString(2));

				ResultSet columnsResult = datosBBDD.getColumns(null, null, miMetaResult.getString(3), null);
				while (columnsResult.next()) {
					System.out.format("\tColumna: %-15s  Tipo de dato: %7s   Nullable: %1s\n",
							columnsResult.getString(4), columnsResult.getString("TYPE_NAME"),
							columnsResult.getInt("NULLABLE"));
				}

				ResultSet primaryResult = datosBBDD.getPrimaryKeys(null, null, miMetaResult.getString(3));
				while (primaryResult.next()) {
					System.out.println("Clave primaria: " + primaryResult.getObject(4).toString());
				}

			}

			miMetaResult = datosBBDD.getColumns(null, null, "EMP%", null);
			// miMetaResult = datosBBDD.getColumns(null,null,"empl%",null);
			System.out.println("\n----Lista de columnas----");
			while (miMetaResult.next()) {
				System.out.println(miMetaResult.getString(4));

			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		miConexion.close();

	}
}
