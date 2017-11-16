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
	<title>Dashboard</title>
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
					<li class="active"><a href="/dashboard">Home</a></li>
					<c:if test="${currentUser.permissionLevel <= 2}">
							<li><a href="/admin/ring">Ring Creator</a></li>
							<li><a href="/admin/guild">Person/Guild Creator</a></li>
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
						<div class="col-md-12">
								<h3>
									Welcome ${currentUser.username}
								</h3>
						</div>
				</div>
				<div class="row">
					<div class="col-md-4">
						<c:if test="${currentUser.permissionLevel == 1}">
							<p>Welcome Ring Master, create rings and send them out into the world!</p>
						</c:if>
							<p>Welcome to your awesome magical ring finder, put the ring on, only good things will happen. Maybe it'll make you live forever, go invisible, turn your inherent hunger for riches or power into an insatiable curse that eventuall dooms your entire species</p>							
					</div>
					<div class="col-md-4 text-center">
						<img src="/img/ring.jpg" width=50%>
					</div>
					<div class="col-md-4">
						<form action="/addRing" method="post">
							<div class="form-group">
								<label for="rings">Rings You Can Add:</label>
								<select class="form-control" name="rings" id="rings">
									  <option selected disabled>Select a Ring</option>
									  <c:forEach items="${availableRings}" var="aRing">
										  <option value="${aRing.id}" label="${aRing.name}"/>
									  </c:forEach>
								</select>
							</div>
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							<input type="submit" class="btn btn-primary pull-right" value="Add">
						</form>
					</div>
				</div>
				<div class="row">
					<div class="col-md-3"></div>
					<div class="col-md-6 myRings">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>Rings you have found</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${currentUser.rings}" var="boundRing">
									<tr>
										<td>${boundRing.name}</td>
										<td><a href="/unbind/${boundRing.id}">Unbind Ring</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						
					</div>
					<div class="col-md-3"></div>
				</div>
				<c:if test="${currentUser.permissionLevel == 1}">
				<div class="row">
					<div class="col-md-3"></div>
					<div class="col-md-6 myRings">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>Rings you have created</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${adminRings}" var="adminRing">
									<tr>
										<td>${adminRing.name}</td>
										<td><a href="#">Destroy the Ring</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						
					</div>
					<div class="col-md-3"></div>
				</div>
				</c:if>
		</div>
</body>
</html>