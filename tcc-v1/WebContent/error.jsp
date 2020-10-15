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
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: Cadetblue">
			<div>
				<a href="index.jsp" class="navbar-brand"> SCRM
					- TCC Sistemas de Informação - UNIP </a>
			</div>
		</nav>
	</header>
	<br>
	
	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
		<h1 class="text-center">Erro! Você tentou uma ação irregular.</h1>
	
			<h3 class="text-center">Volte para a tela inicial!</h3>
			<hr>
			<div class="container text-center">

				<a href="pesquisa-receita-farm.jsp" class="btn btn-warning">Inicio</a>
			</div>

		</div>
	</div>
</body>
</html>
