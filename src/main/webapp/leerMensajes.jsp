<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<jsp:include page ="include/header.jsp">
	<jsp:param name="pagina" value="leer" />
	<jsp:param name="titulo" value="Leer Mensajes" />
</jsp:include>
	
	<c:forEach items="${mensajes}" var="c">
		<label for="asunto">Asunto ${c.id}:</label> </br>
		${c.asunto}
		</br>
		<label for="asunto">Mensaje:</label> </br>
		${c.mensaje}
	</c:forEach>
	</br>
	<a href="index.jsp" role="button">Mandar nuevos mensajes</a>
	
<%@include file="include/footer.jsp" %>