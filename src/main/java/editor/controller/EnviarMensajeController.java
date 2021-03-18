package editor.controller;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.log4j.Logger;

import editor.controller.Alert;
import editor.modelo.dao.impl.MensajeDAOImpl;
import editor.modelo.pojo.Mensaje;


/**
 * Servlet implementation class EnviarMensajeController
 */
@WebServlet("/enviar-mensaje")
public class EnviarMensajeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(EnviarMensajeController.class);
	private static MensajeDAOImpl dao = MensajeDAOImpl.getInstance();
	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private static Validator validator = factory.getValidator();
	
	private static final String URL = "index.jsp";
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Mensaje msg = new Mensaje();
		Alert alert = new Alert();
		
		try {
			//Recoge parametros del formulario
			String paramAsunto = request.getParameter("asunto");
			String paramMensaje = request.getParameter("mensaje");
			
			
			msg.setAsunto(paramAsunto);
			msg.setMensaje(paramMensaje);	
			
			//Validar pojo
			Set<ConstraintViolation<Mensaje>> violaciones = validator.validate(msg);
			
			//Comprueba si hay errores en el formulario
			if ( violaciones.isEmpty() ) {
				//Confirma que no hay errores y va al metodo para guardar el mensaje
				dao.guardar(msg);
				alert = new Alert("success", "Mensaje guardado.");
				
			} else {
				//Recoje los errores para mostrarlos al usuario
				String errores = "";
				
				for (ConstraintViolation<Mensaje> v : violaciones) {	
					errores += "<p><b>" + v.getPropertyPath() + "</b>: "  + v.getMessage() + "</p>";		
				}
				
				alert = new Alert("warning", errores);
			}//if
		//Recoje errores al intentar contactar con la base de datos	
		} catch (Exception e) {
			
			LOG.error(e);
			alert = new Alert("warning", e.getMessage());
			
		} finally {
			//Guarda los datos y los envia a la URL
			request.setAttribute("alert", alert);
			request.setAttribute("mensaje", msg);
			
			LOG.debug("forward: " + URL);	
			
			request.getRequestDispatcher(URL).forward(request, response);
		}//try			
	}//doPost
}
