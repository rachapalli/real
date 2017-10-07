<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:url value="/buyer/register" var="registerUrl" />
<c:url value="/login" var="loginUrl" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to Real Estate Web site</title>

<!-- your webpage info goes here -->

<title>Real Estate Web site</title>

<meta name="author" content="your name" />
<meta name="description" content="" />

<link rel="stylesheet" href="/static/css/style/main-style.css"
	type="text/css" />

</head>
	<body>
		<!-- webpage content goes here in the body -->
		<div id="page">
			<div id="logo">
				<h1>My Real Estate Web site</h1>
			</div>
			<div id="nav">
				<form:form id="registerFormId" action="${registerUrl}" method="POST">
					<input type="submit" align="right" name="Register Buyers" value="Register Buyers">
				</form:form>
				<form:form id="loginFormId" action="${loginUrl}" method="POST">
					<input type="submit" name="login" align="right" value="Log in">
				</form:form>
			</div>
			<div id="content">
				<p>Test comment</p>
				<p>Test comment</p>
			</div>
			<div id="footer">
				<p>Web page made by Umamaheswara Rao</p>
			</div>
		</div>
	</body>
</html>