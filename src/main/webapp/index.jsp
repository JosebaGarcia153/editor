<jsp:include page ="include/header.jsp">
	<jsp:param name="pagina" value="index" />
	<jsp:param name="titulo" value="Index" />
</jsp:include>

	<form action="enviar-mensaje" method="post">
	
		<div class="form-group">
			<label for="asunto">Asunto:</label> </br>
			<input type="text" name="asunto" id="asunto" autofocus placeholder="Asunto" required>
		</div>
		<div class="form-group">
			<label for="mensaje">Mensaje:</label> </br>
			<textarea rows="5" cols="40" name="mensaje" id="mensaje" placeholder="Escribir mensaje"></textarea>
		</div>
   
		<input type="submit" value="Enviar">
	</form>
	
	</br>
	<a href="leer-mensajes" role="button">Leer mensajes</a>
	
	
<%@include file="include/footer.jsp" %>