package editor.modelo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import editor.modelo.ConnectionManager;

import editor.modelo.dao.MensajeDAO;
import editor.modelo.pojo.Mensaje;

public class MensajeDAOImpl implements MensajeDAO {
	
	private static final Logger LOG = Logger.getLogger(MensajeDAOImpl.class);
	
	private static MensajeDAOImpl instance = null;
	
	private MensajeDAOImpl() {
		super();
	}
	
	public static synchronized MensajeDAOImpl getInstance() {
		
		if (instance == null) {
			instance = new MensajeDAOImpl();
		}
		return instance;
	}
	
	private final String SQL_GET_ALL = "SELECT id, asunto, mensaje FROM mensajes ORDER BY id ASC LIMIT 500; ";
	private final String SQL_CREATE = "INSERT INTO mensajes (asunto, mensaje) VALUES (?, ?); ";
	
	//Clase para recoger los mensajes de la base de datos
	@Override
	public ArrayList<Mensaje> getAll() {
		
		ArrayList<Mensaje> msgs = new ArrayList<Mensaje>();
		
		//Conectar con la base de datos y ejecutar la SQL
		try(
			Connection con = ConnectionManager.getConnection();
			PreparedStatement pst = con.prepareStatement(SQL_GET_ALL);
			ResultSet rs = pst.executeQuery();
			){
			
			//Usar el mapper para guardar los datos recibidos en el arraylist
			LOG.debug(pst);
			while( rs.next() ) {
				
				msgs.add(mapper(rs));
			}		
		} catch (Exception e) {
			
			LOG.error(e);
		}//try
		
		return msgs;
	}//metodo getAll
	
	//Clase para guardar los mensajes en la base de datos
	@Override
	public Mensaje guardar(Mensaje msg) throws Exception {
		
		if (msg.getAsunto() == null) {
			throw new Exception("Tienes que escribir un asunto");
		}
		
		//Conectar con la base de datos y preparar la SQL
		try ( 
			Connection conexion = ConnectionManager.getConnection();
			PreparedStatement pst = conexion.prepareStatement(SQL_CREATE, PreparedStatement.RETURN_GENERATED_KEYS);
			){
			//Insertar datos en la SQL
			pst.setString(1, msg.getAsunto());
			pst.setString(2, msg.getMensaje());
			
			//Ejercutar comando SQL
			int affectedRows = pst.executeUpdate();
			
			//Comprobar el numero de entradas editadas en la base de datos
			LOG.debug(pst);
			if (affectedRows == 1) {
				
				//conseguir el ID
				try ( ResultSet rsKeys = pst.getGeneratedKeys(); ) {
					
					if (rsKeys.next()) {

						msg.setId(rsKeys.getInt(1));	
					}
				}//try2
			} else {

				throw new Exception ("No se pudo guardar " + msg.getAsunto());

			}//if
		} catch (SQLException e) {
			
				throw new SQLException("El asunto ya existe");
		}//try1
		
		return msg;		
	}//metodo guardar
	
	//Clase generica para recoger todos los datos al llamar a la base de datos
	private Mensaje mapper( ResultSet rs ) throws SQLException {
		
		Mensaje msg = new Mensaje();
		
		msg.setId(rs.getInt("id"));
		msg.setAsunto(rs.getString("asunto"));
		msg.setMensaje(rs.getString("mensaje"));
		
		return msg;
	}
}
