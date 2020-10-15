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
<meta http-equiv="refresh"
	content="5; URL='<%=request.getContextPath()%>/new'" />
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

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->
		<input type="hidden"
			value="<c:out value='${crm}' />"
			class="form-control" name="crm" required="required" readonly>

		<div class="container">
			<h1 class="text-center">Receita finalizada Gerada com Sucesso!</h1>

			<h3 class="text-center">Redirecionando...</h3>
		</div>
		<br>
	</div>
	</div>
</body>
</html>
