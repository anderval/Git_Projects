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
	<%session.invalidate();%>

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

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">Receitas Médicas</h3>
			<hr>
			<div class="container text-center">

				<a href="login-med.jsp" class="btn btn-primary"> Medico</a>
			</div>
			<br>
			<div class="container text-center">

				<a href="login-farm.jsp" class="btn btn-primary"> Farmaceutico</a>
			</div>
			<br>
		</div>
	</div>
</body>
</html>
