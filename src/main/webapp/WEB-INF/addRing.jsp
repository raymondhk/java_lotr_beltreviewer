<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Login and Registration</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link rel="stylesheet" type="text/css" href="/css/style.css">
</head>

<body>
		<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				  <div class="navbar-header">
					<a class="navbar-brand" href="/dashboard">Ring Collector</a>
				  </div>
				  <ul class="nav navbar-nav">
					<li><a href="/dashboard">Home</a></li>
					<c:if test="${currentUser.permissionLevel <= 2}">
							<li class="active"><a href="/ring">Ring Creator</a></li>
							<li><a href="#">Person/Team Creator</a></li>
					</c:if>
				  </ul>
				<form id="logoutForm" method="POST" action="/logout" class="navbar-form navbar-right">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
					<input type="submit" class="btn btn-default" value="Logout!" />
				</form>
			</div>
		</nav>
		<div class="container">
				<div class="row header">
						<div class="col-md-12"></div>
				</div>
				<div class="row">
						<div class="col-md-3"></div>
						<br>
						<br>
						<br>
						<br>
						<br>
						<div class="col-md-6">
							<h2>Forge Ring:</h2>
							<form:form action="/ring" method="post" modelAttribute="ring">
								<div class="form-group">
									Name: <form:input type="text" path="name" class="form-control"/>
								</div>
									<input type="submit" class="btn btn-danger">
							</form:form>
					</div>
					<div class="col-md-3"></div>
				</div>
</body>
</html>