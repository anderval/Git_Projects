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
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<form action="update" method="post">

					<caption>
						<h2>Dar Baixa na Receita</h2>
					</caption>

					<input readonly type="text" name="id"
						value="<c:out value='${receita.id}' />" />


					<fieldset class="form-group">
						<label>CPF Paciente</label> <input type="text"
							value="<c:out value='${receita.cpfPaciente}' />"
							class="form-control" name="cpf" required="required" readonly>
					</fieldset>

					<fieldset class="form-group">
						<label>Nome Paciente</label> <input type="text"
							value="<c:out value='${receita.nomePaciente}' />"
							class="form-control" name="nome" readonly>
					</fieldset>

					<fieldset class="form-group">
						<label>Receita Itens</label> <input type="text"
							value="<c:out value='${receita.itensReceita}' />"
							class="form-control" name="itens" required="required">
						</textarea>
					</fieldset>

					<fieldset class="form-group">
						<label>CRM Médico</label> <input type="text"
							value="<c:out value='${receita.crm}' />" class="form-control"
							name="crm" readonly>
					</fieldset>
					<fieldset class="form-group">
						<label>CRF Farmaceutico</label> <input type="text"
							value="<c:out value='${crf}'  />"
							class="form-control" name="crf" readonly>
					</fieldset>

					<fieldset class="form-group">
						<label>IdHospital</label> <input type="text"
							value="<c:out value='${receita.idHospital}' />"
							class="form-control" name="idHospital" readonly>

						<button type="submit" class="btn btn-success">Dar Baixa</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
