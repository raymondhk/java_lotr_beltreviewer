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
					<li><a href="/dashboard">Home</a></li>
					<c:if test="${currentUser.permissionLevel <= 2}">
							<li><a href="/admin/ring">Ring Creator</a></li>
							<li class="active"><a href="/admin/guild">Person/Guild Creator</a></li>
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
								<h3 class="text-center">
									Welcome Iluvatar!
								</h3>
						</div>
				</div>
				<div class="row">
					<div class="col-md-2"></div>
					<div class="col-md-8">
						<p class="text-center">All Users:</p>
						<table class="table table-striped table-fixed">
							<thead>
								<tr>
									<th>Name</th>
									<th>Guild</th>
									<th>Age</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${users}" var="user">
									<tr>
										<td>${user.username}</td>
										<td>
										<c:forEach items="${user.guilds}" var="guild">
										<a href="/guild/${guild.id}">${guild.name}</a><br>	
										</c:forEach>
										</td>
										<td>${user.age}</td>
										<c:if test="${user.permissionLevel == 1}">
											<td>Super Admin!</td>
										</c:if>
										<c:if test="${user.permissionLevel > 1}">
											<td><a href="/admin/delete/${user.id}">Delete</a> | <a href="/admin/upgrade/${user.id}">Make Admin</a></td>
										</c:if>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div class="col-md-2">
					</div>
				</div>
				<div class="row">
					<div class="col-md-5">
						<h3 class="text-center">Add User to Guild:</h3>
						<p class="text-center red">${userGuildError}</p>
						<form action="/admin/addusertoguild" method="post">
							<div class="form-group">
								<label for="user">Pool of Users:</label>
								<select class="form-control" name="user_id" id="user">
										<option selected disabled>Select a User</option>
										<c:forEach items="${users}" var="u">
											<option value="${u.id}" label="${u.username}"/>
										</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label for="guilds">Guilds a User can Join:</label>
								<select class="form-control" name="guild_id" id="guilds">
										<option selected disabled>Select a Guild</option>
										<c:forEach items="${guilds}" var="guild">
											<option value="${guild.id}" label="${guild.name} (${guild.size})"/>
										</c:forEach>
								</select>
							</div>
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
							<input type="submit" class="btn btn-primary pull-right" value="Add">
						</form>

					</div>
					<div class="col-md-2"></div>
					<div class="col-md-5">
						<h3 class="text-center">Create a Guild:</h3>
						<p class="red"><form:errors path="guild.*"/></p>
						<form:form action="/admin/addguild" method="post" modelAttribute="guild">
							<form:input type="text" path="name" class="form-control text-right" placeholder="Guild Name"/><br>

							<form:input type="number" path="size" placeholder="Size" min="1" class="form-control guild-size pull-right text-center"/><br><br><br>
							<form:button class="btn btn-primary pull-right" type="submit">Add Guild</form:button>
						</form:form>
					</div>
				</div>
				<div class="row">
					<div class="col-md-3"></div>
					<div class="col-md-6">
					</div>
					<div class="col-md-3"></div>
				</div>
		</div>
</body>
</html>