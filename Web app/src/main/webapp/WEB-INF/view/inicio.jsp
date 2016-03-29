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
						
						
						<!--
						
						<hr />
						<h2>Extra Stuff!</h2>
						<form method="post" action="#">
							<div class="field">
								<input type="text" name="name" id="name" placeholder="Name" />
							</div>
							<div class="field">
								<input type="email" name="email" id="email" placeholder="Email" />
							</div>
							<div class="field">
								<div class="select-wrapper">
									<select name="department" id="department">
										<option value="">Department</option>
										<option value="sales">Sales</option>
										<option value="tech">Tech Support</option>
										<option value="null">/dev/null</option>
									</select>
								</div>
							</div>
							<div class="field">
								<textarea name="message" id="message" placeholder="Message" rows="4"></textarea>
							</div>
							<div class="field">
								<input type="checkbox" id="human" name="human" /><label for="human">I'm a human</label>
							</div>
							<div class="field">
								<label>But are you a robot?</label>
								<input type="radio" id="robot_yes" name="robot" /><label for="robot_yes">Yes</label>
								<input type="radio" id="robot_no" name="robot" /><label for="robot_no">No</label>
							</div>
							<ul class="actions">
								<li><a href="#" class="button">Get Started</a></li>
							</ul>
						</form>
						<hr />
						
						<footer>
							<ul class="icons">
								<li><a href="#" class="fa-twitter">Twitter</a></li>
								<li><a href="#" class="fa-instagram">Instagram</a></li>
								<li><a href="#" class="fa-facebook">Facebook</a></li>
							</ul>
						</footer>
						-->
						
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



