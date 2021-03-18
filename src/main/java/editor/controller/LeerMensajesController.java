package editor.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import editor.modelo.dao.impl.MensajeDAOImpl;
import editor.modelo.pojo.Mensaje;

/**
 * Servlet implementation class MostrarMensajesController
 */
@WebServlet("/leer-mensajes")
public class LeerMensajesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(LeerMensajesController.class);
	private static MensajeDAOImpl dao = MensajeDAOImpl.getInstance();
	
	private static final String URL = "leerMensajes.jsp";
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Mensaje> msgs = new ArrayList<Mensaje>();
		
		try {
			//Va al metodo para recoger los mensajes
			msgs = dao.getAll();
		//Recoje errores al intentar contactar con la base de datos
		} catch (Exception e) {
			
			LOG.error(e);
			
		} finally {
			//Guarda los datos y los envia a la URL
			request.setAttribute("mensajes", msgs);
			LOG.debug("forward: " + URL);
			
			request.getRequestDispatcher(URL).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
