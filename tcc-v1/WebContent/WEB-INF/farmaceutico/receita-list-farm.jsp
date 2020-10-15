<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
int crf = (int) session.getAttribute("crf");
String nome = (String) session.getAttribute("nome");
String especialidade = (String) session.getAttribute("especialidade");

if(especialidade != null){
	response.sendRedirect("login-farm.jsp");
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

				<li><a href="<%=request.getContextPath()%>/list-farm"
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
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Nome</th>
						<th>CPF</th>
						<th>Itens</th>
						<th>CRM</th>
						<th>Finalizar</th>
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
							<td>
								<form action="edit" method="POST">
									<input type="hidden" name="id"
										value=<c:out value='${receita.id}' /> /> <input type="submit"
										name="id" value="Dar Baixar">
								</form>
							</td>
							<!--  
							<td><a href="edit?id=<c:out value='${user.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?id=<c:out value='${user.id}' />">Delete</a></td> -->
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>
