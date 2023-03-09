package conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBBDDSQLite {
Connection conexion = null;
	
public Connection conectorSQLite() {
    try {
        //Cargamos el driver        	
        Class.forName("org.sqlite.JDBC");
        String url = "jdbc:sqlite:C:\\Users\\raulg\\Downloads\\sqlite\\recuperacionSQLiteAD.db";
        //C:\\Users\\raulg\\Downloads\\sqlite\\proyecto
        //‪C:\Users\raulg\Documents\Clase\Este año\AD Recuperacion\HospitalesRecuperacion-1Trimestre\recuperacionSQLiteAD.db
        //Establecemos la conexión con la base de datos
        conexion = DriverManager.getConnection(url);
        System.out.println("Connection to SQLite has been established.");
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    }
    return conexion;
}

}
