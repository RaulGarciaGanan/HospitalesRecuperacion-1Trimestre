import java.sql.SQLException;
import java.util.*;

import conexiones.*;

public class Main {
	static GestionesMySQL conexionMySQL;
	static int menu;

	public static void main(String[] args) throws SQLException {
		try {
			conexionMySQL = new GestionesMySQL();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Scanner in = new Scanner(System.in);
		do {
			menu = 0;
			System.out.println("A que hospital se desa conectar:\n" + "1.Hospital MySQL\n" + "2.Hospital SQLite\n"
					+ "3.Hospital cualtoque\n" + "4.Salir");
			menu = in.nextInt();
			switch (menu) {
			case 1:
				conexionMySQL.menuPrincipal();
				break;
			case 2:
				break;
			case 3:
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
