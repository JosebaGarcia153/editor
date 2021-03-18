<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!doctype html>
<html lang="es">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <!-- Todas las rutas relativas comienzan por el href indicado -->
    <!--  ${pageContext.request.contextPath} == http://localhost:8080/editor -->
    <base href="${pageContext.request.contextPath}/" />
    
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

    
    <!-- Libreria de CKEditor -->
	<script src="https://cdn.ckeditor.com/ckeditor5/26.0.0/classic/ckeditor.js"></script>
    
    <title>Text Editor</title>
	
</head>
<body>
	<main role="main" class="container pt-5 mt-5">
	
		<jsp:include page="alerts.jsp"></jsp:include>