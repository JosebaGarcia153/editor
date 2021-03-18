package editor.modelo.pojo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Mensaje {
	
	private int id;
	
	@NotNull(message = "El asunto no puede estar vacio")
	@Size(min = 3, max = 100, message = "El asunto debe tener entre 3 y 100 caracteres")
	private String asunto;
	
	@NotNull(message = "El mensaje no puede estar vacio")
	@Size(min = 3, max = 500, message = "El mensaje debe tener entre 3 y 500 caracteres")
	private String mensaje;
	
	public Mensaje () {
		this.id = 0;
		this.asunto = "";
		this.mensaje = "";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	@Override
	public String toString() {
		return "Mensaje [id=" + id + ", asunto=" + asunto + ", mensaje=" + mensaje + "]";
	}
}
