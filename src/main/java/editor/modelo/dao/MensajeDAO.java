package editor.modelo.dao;

import java.util.ArrayList;

import editor.modelo.pojo.Mensaje;

public interface MensajeDAO {
	
	Mensaje guardar (Mensaje msg) throws Exception;
	public ArrayList<Mensaje> getAll();
}
