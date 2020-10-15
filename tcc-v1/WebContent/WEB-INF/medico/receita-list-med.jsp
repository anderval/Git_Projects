<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
int crm = (int) session.getAttribute("crm");
String nome = (String) session.getAttribute("nome");
String especialidade = (String) session.getAttribute("especialidade");

if(especialidade == null){
	response.sendRedirect("login-med.jsp");
}else {

}
%>
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

			<ul class="navbar-nav">
				<li><a href="pesquisa-receita-med.jsp"
					class="nav-link">Receitas Farmaceutico</a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">Receitas Médicas</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Gerar
					Nova Receita</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Nome</th>
						<th>CPF</th>
						<th>Itens</th>
						<th>CRM</th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="receita" items="${listReceita}">

						<tr>
							<td><c:out value="${receita.nomePaciente}" /></td>
							<td><c:out value="${receita.cpfPaciente}" /></td>
							<td><c:out value="${receita.itensReceita}" /></td>
							<td><c:out value="${receita.crm}" /></td>

						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>
