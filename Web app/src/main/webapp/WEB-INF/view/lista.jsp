<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>


<html>
	<head>
		<title>Nesswater - Artik</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
		<link rel="icon" href="<c:url value="/resources/css/images/iconos/favicon.gif" />" type="image/gif" sizes="16x16"> 
		<noscript><link href="<c:url value="/resources/css/noscript.css" />" rel="stylesheet"></noscript>
	</head>
	<body class="is-loading">

		<!-- Wrapper -->
			<div id="wrapper">

				<!-- Main -->
					<section id="main">
						<header>
							<span class="avatar"><img src="<c:url value="/resources/css/images/avatar.jpg" />" alt="" /></span>
							<h1>Nesswater</h1>
							<p>Proyecto Artik</p>
						</header>
						<hr />
						<a class="button" href="registro"><span>Agregar</span></a>
						<a class="button" href="lista"><span>Listar</span></a>
							<a class="button" href="borrar"><span>Eliminar</span></a>
						
						<hr />
						
						<div>
							<table>
							<c:forEach var="row" items="${lista}" varStatus="status">
								<tr>
									<td>
										<em>ID:</em> ${row.id} /
									</td>
									<!--  
									<td>
										&nbsp;Nombre: ${row.nombre}
									<td>
									-->
									<td>
										&nbsp;<em>Modelo:</em> ${row.nombre} /
									<td>
									
									<td>
										&nbsp;<em>Número Serie:</em> ${row.numeroSerie} /
									<td>
									<!--  
									<td>
										&nbsp;<em>Fecha Inicio:</em> ${row.TInicio} /
									<td>
									-->
									<td>
										<c:choose>	
											<c:when test="${row.tipoEstado.id==0}">
												&nbsp;<em>Estado:</em> <img src="<c:url value="/resources/css/images/iconos/frio.png" />" alt="" style="height: 20px; margin-bottom: -5px;" /> /
											</c:when>
											<c:when test="${row.tipoEstado.id==1}">
												&nbsp;<em>Estado:</em> <img src="<c:url value="/resources/css/images/iconos/caliente.png" />" alt="" style="height: 20px; margin-bottom: -5px;" /> /
											</c:when>
											<c:when test="${row.tipoEstado.id==2}">
												&nbsp;<em>Estado:</em> <img src="<c:url value="/resources/css/images/iconos/recirculando.png" />" alt="" style="height: 20px; margin-bottom: -5px;" /> /
											</c:when>
											<c:when test="${row.tipoEstado.id==3}">
												&nbsp;<em>Estado:</em> <img src="<c:url value="/resources/css/images/iconos/error.png" />" alt="" style="height: 20px; margin-bottom: -5px;" /> /
											</c:when>
										</c:choose>
									</td>
					
									
									<td>
										<c:choose>	
											<c:when test="${row.finCiclo==0}">
												&nbsp;<em>Fin Ciclo:</em> <img src="<c:url value="/resources/css/images/iconos/off.png" />" alt="" style="height: 20px; margin-bottom: -5px;" /> 
											</c:when>
											<c:when test="${row.finCiclo==1}">
												&nbsp;<em>Fin Ciclo:</em> <img src="<c:url value="/resources/css/images/iconos/on.png" />" alt="" style="height: 20px; margin-bottom: -5px;" /> 
											</c:when>
											
										</c:choose>
									</td>
								</tr>
							</c:forEach>
							</table>
								${msg}
						</div>
						
					</section>

				<!-- Footer -->
					<footer id="footer">
						<ul class="copyright">
							<li><img src="<c:url value="/resources/css/images/metrica.png" />" width="171" height="37"></li>
							
						</ul>
					</footer>

			</div>

		<!-- Scripts -->
	
			<script>
				if ('addEventListener' in window) {
					window.addEventListener('load', function() { document.body.className = document.body.className.replace(/\bis-loading\b/, ''); });
					document.body.className += (navigator.userAgent.match(/(MSIE|rv:11\.0)/) ? ' is-ie' : '');
				}
			</script>
			
	

	</body>
</html>


