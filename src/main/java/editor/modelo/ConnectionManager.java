package editor.modelo;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionManager {
	
	//Clase que se encarga de guardar los datos de conexion
	//Los datos para conectar con la base de datos estan en META-INF -> context.xml
	public static Connection getConnection() throws SQLException, ClassNotFoundException, NamingException {
		
		Connection con = null;
		
		//Comprueba que tengamos el .jar de MySQL
		Class.forName("com.mysql.jdbc.Driver");
		
		InitialContext initCtx=new InitialContext();;
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource dataSource = (DataSource)envCtx.lookup("jdbc/super");
		
		//Establece conexion
		con = dataSource.getConnection(); 
		
		return con;
	};
	

}
