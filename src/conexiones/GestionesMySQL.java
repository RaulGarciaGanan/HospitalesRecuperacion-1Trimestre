package conexiones;

import clases.*;

import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class GestionesMySQL {
	Connection miConexion = new ConexionBBDDMYSQL().conectorMySQL();

	public void menuPrincipal() throws SQLException {
		Scanner in = new Scanner(System.in);
		int menu;
		do {
			menu = 0;
			System.out.println("Elije una opcion:\n" + "1.Listado de Pacientes\n" + "2.Listado de Medicos\n"
					+ "3.Listado de Consultas\n" + "4.Gestion Pacientes\n" + "5.Gestion Medicos\n"
					+ "6.Gestion Consultas\n" + "7.MetaDatos\n" + "8.Datos del hospital\n" + "9.salir");
			menu = in.nextInt();
			switch (menu) {
			case 1:
				listadoPacientes();
				break;
			case 2:
				listadoMedicos();
				break;
			case 3:
				listadoConsultas();
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

	public void listadoPacientes() throws SQLException {
		Statement miSentencia = miConexion.createStatement();

		ResultSet miResultSet = miSentencia.executeQuery("SELECT * FROM paciente where baja = false");
		while (miResultSet.next()) {
			System.out.println("id: " + miResultSet.getInt(1) + ", nombre: " + miResultSet.getString(2)
					+ ", apellidos: " + miResultSet.getString(3) + ", direccion: " + miResultSet.getString(4)
					+ ", profesion: " + miResultSet.getString(5) + ", edad: " + miResultSet.getString(6));
		}

	}

	private void listadoMedicos() throws SQLException {
		Statement miSentencia = miConexion.createStatement();

		ResultSet miResultSet = miSentencia.executeQuery("SELECT * FROM medico where baja = false");
		while (miResultSet.next()) {
			System.out.println("id: " + miResultSet.getInt(1) + ", numColegiado: " + miResultSet.getString(2)
					+ ", nombre: " + miResultSet.getString(3) + ", apellidos: " + miResultSet.getString(4)
					+ ", fecha Nacimiento: " + miResultSet.getString(5) + ", fecha Contratacion: " + miResultSet.getString(6)
					+", especialidad: " + miResultSet.getString(7));
		}
	}

	private void listadoConsultas() throws SQLException {
		Statement miSentencia = miConexion.createStatement();

		ResultSet miResultSet = miSentencia.executeQuery("SELECT * FROM consulta where baja = false");
		while (miResultSet.next()) {
			System.out.println("id: " + miResultSet.getInt(1) + ", nombre: " + miResultSet.getString(2)
					+ ", apellidos: " + miResultSet.getString(3) + ", direccion: " + miResultSet.getString(4)
					+ ", profesion: " + miResultSet.getString(5) + ", edad: " + miResultSet.getString(6));
		}
	}

	private void crudPacientes() throws SQLException {
		Scanner in = new Scanner(System.in);
		int menu;
		do {
			menu = 0;
			System.out.println("Que quiere hacer con el paciente:\n" + "1.Crear paciente\n" + "2.Modificar paciente\n"
					+ "3.Borrar paciente\n" + "4.Salir");
			menu = in.nextInt();
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
				+ pa.getEdad() + ",'" + pa.getDireccion() + "'," + pa.isBaja() + ")");

		miSentencia.execute(sql);

	}

	private void modificarPaciente() throws SQLException {
		Scanner in = new Scanner(System.in);
		String dni = "", nombre = "", apellidos = "", direccion = "", profesion = "";
		int edad = 0, menu = 0;
		boolean correcto;
		do {
			try {
				menu = 0;
				listadoPacientes();
				System.out.println("Selecciona el id de un paciente:");
				menu = Integer.parseInt(in.nextLine());
				correcto = true;
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

		String sql = String.format("UPDATE paciente SET" + "`dni` = '" + dni + "'," + "`nombre` = '" + nombre + "',"
				+ "`apellidos` = '" + apellidos + "'," + "`direccion` = '" + direccion + "'," + "`edad` = " + edad + ","
				+ "`profesion` = '" + profesion + "' WHERE `idPaciente` = " + menu + ";");

		miSentencia.execute(sql);
	}

	private void eliminarPaciente() throws SQLException {
		Scanner in = new Scanner(System.in);
		int menu = 0;
		boolean correcto;
		do {
			try {
				menu = 0;
				listadoPacientes();
				System.out.println("Selecciona el id de un paciente:");
				menu = Integer.parseInt(in.nextLine());
				correcto = true;
			} catch (Exception e) {
				System.out.println("Debe seleccionar el id numerico del paciente");
				correcto = false;
			}
		} while (!correcto);

		Statement miSentencia = miConexion.createStatement();

		String sql = String.format("UPDATE paciente SET baja=true WHERE `idPaciente` = " + menu + ";");

		miSentencia.execute(sql);
	}

	private void crudMedicos() throws SQLException {
		Scanner in = new Scanner(System.in);
		int menu;
		do {
			menu = 0;
			System.out.println("Que quiere hacer con el medico:\n" + "1.Crear medico\n" + "2.Modificar medico\n"
					+ "3.Borrar medico\n" + "4.Salir");
			menu = in.nextInt();
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
						+ med.getEspecialidad() + "'," + med.isBaja() + ")");

		miSentencia.execute(sql);

	}

	private void modificarMedico() {
		Scanner in = new Scanner(System.in);
		String dni = "", nombre = "", apellidos = "", direccion = "", especialidad = "", fecha = "";
		Date fechaNaci = null, fechaContrata = null;
		int numcolegiado = 0, edad = 0;
		boolean baja = false, correcto;
	}

	private void eliminarMedico() {

	}

	private void crudConsultas() {
		Scanner in = new Scanner(System.in);
		int menu;
		do {
			menu = 0;
			System.out.println("Que quiere hacer con la consulta:\n" + "1.Crear consulta\n" + "2.Modificar consulta\n"
					+ "3.Borrar consulta\n" + "4.Salir");
			menu = in.nextInt();
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
				System.out.println("4");
				break;
			default:
				break;
			}
		} while (menu != 4);

	}

	private void crearConsulta() {

	}

	private void modificarConsulta() {

	}

	private void eliminarConsulta() {

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
		System.out.println(sqlStartDate);
		return sqlStartDate;

	}

	private void datosHospital() {

	}

	private void listarMetadatos() {

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

	}

}
