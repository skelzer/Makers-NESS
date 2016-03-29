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
						<!-- 
						<hr />
						<a class="button" href="registro"><span>Agregar</span></a>
						<a class="button" href="lista"><span>Listar</span></a>
						-->
						<hr />
						<h2>Bypass</h2>
						<span class="avatar avatar2"><img src="<c:url value="/resources/css/images/bypass.png" />" alt="" /></span>
						
						<form method="get" action="add">
							<div class="field">
								<input type="text" name="name" id="name" placeholder="Nombre" />
							</div>
							<div class="field">
								<input type="text" name="num" id="num" placeholder="Num. Serie" />
							</div>
							
							<ul class="actions">
								<input type="submit" class="button" value="Registrar" />
							</ul>
						</form>
					
						
						
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



