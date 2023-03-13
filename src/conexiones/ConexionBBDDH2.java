package conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBBDDH2 {
Connection conexion = null;
	
public Connection conectorH2() {
    try {
        //Cargamos el driver        	
        Class.forName("org.h2.Driver");
        String url = "jdbc:h2:~/test";
        conexion = DriverManager.getConnection(url,"sa","");
        System.out.println("Connection to SQLite has been established.");
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    }
    return conexion;
}

}
