import java.sql.SQLException;
import java.util.*;

import clases.consulta;
import clases.medico;
import clases.paciente;
import conexiones.*;

public class Main {
	static GestionesMySQL conexionMySQL;
	static GestionesSQLite conexionSQLite;
	static GestionesH2  conexionH2;
	static int menu;

	public static void main(String[] args) throws SQLException {
		Scanner in = new Scanner(System.in);
		boolean correcto;

		try {
			conexionMySQL = new GestionesMySQL();
			conexionSQLite = new GestionesSQLite();
			conexionH2 = new GestionesH2();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		do {
			do {
				try {
					menu = 0;
					System.out.println("A que hospital se desa conectar:\n" + "1.Hospital MySQL\n"
							+ "2.Hospital SQLite\n" + "3.Hospital H2\n" + "4.Salir");
					menu = Integer.parseInt(in.nextLine());
					correcto = true;
				} catch (Exception e) {
					System.out.println("Debe seleccionar numericamente el hospital");
					correcto = false;
				}
			} while (!correcto);
			switch (menu) {
			case 1:
				conexionMySQL.menuPrincipal();
				break;
			case 2:
				conexionSQLite.menuPrincipal();
				break;
			case 3:
				conexionH2.menuPrincipal();
				break;
			case 4:
				System.out.println("Gracias por usar nuestro programas");
				break;
			default:
				break;
			}
		} while (menu != 4);

	}

}
