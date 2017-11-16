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
		<div class="container">
				<div class="row">
						<div class="col-sm-6 col-md-4 col-md-offset-4">
								<p class="green text-center">${success}</p>
								<h1 class="text-center login-title">Login</h1>
								<div class="text-center">
									<c:if test="${logoutMessage != null}">
											<span class="text-center red"><c:out value="${logoutMessage}"></c:out></span>
									</c:if>
									<c:if test="${errorMessage != null}">
											<p class="red"><c:out value="${errorMessage}"></c:out></p>
									</c:if>
							</div>
								<div class="account-wall">
										<img class="profile-img" src="https://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/eu7opA4byxI/photo.jpg?sz=120"
												alt="">
										<form class="form-signin" action="/" method="post">
										<input type="text" name="username" class="form-control reg-input" placeholder="Email" required autofocus>
										<input type="password" name="password" class="form-control reg-input" placeholder="Password" id="passwordConfirmation" required>
										<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
										<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
										</form>
								</div>
						</div>
				</div>
				<div class="row">
					<div class="col-sm-6 col-md-4 col-md-offset-4">
							<h1 class="text-center login-title">Register</h1>
							<p class="text-center red">${emailError}</p>
							<p class="text-center red">${usernameError}</p>
							<p class="text-center red">${dupError}</p>
							<p class="text-center red"><form:errors path="user.*"/></p>
							<div class="account-wall">
									<img class="profile-img" src="https://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/eu7opA4byxI/photo.jpg?sz=120"
											alt="">
									<form:form class="form-signin" action="/register" method="post" modelAttribute="user">
										<form:input type="text" path="username" class="form-control reg-input" placeholder="Username"/>
										<form:input type="text" path="email" class="form-control reg-input" placeholder="Email"/>
										<form:input type="password" path="password" class="form-control reg-input" placeholder="Password" id="password"/>
										<form:input type="password" path="passwordConfirmation" class="form-control" placeholder="Password Confirmation" id="passwordConfirmation"/>
										<form:button class="btn btn-lg btn-primary btn-block" type="submit">Register</form:button>
									</form:form>
							</div>
					</div>
				</div>
		</div>
</body>
</html>