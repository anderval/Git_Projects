<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>SCRM - TCC</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: Cadetblue">
			<div>
				<a href="index.jsp" class="navbar-brand"> SCRM - TCC Sistemas de
					Informação - UNIP </a>
			</div>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<form action="loginMed" method="post">

					<caption>
						<h2>Login Médico</h2>
					</caption>


					<fieldset class="form-group">
						<label>CRM</label> <input type="text"
							value="<c:out value='${usuario.indentificador}' />"
							class="form-control" name="username" required="required"
							maxlength="11">
					</fieldset>

					<fieldset class="form-group">
						<label>Senha</label> <input type="text"
							value="<c:out value='${usuario.senha}' />" class="form-control"
							name="password" required="required" maxlength="11">
					</fieldset>


					<button type="submit" class="btn btn-success">Entrar</button>
					
				</form>
			</div>
		</div>
	</div>
</body>
</html>
