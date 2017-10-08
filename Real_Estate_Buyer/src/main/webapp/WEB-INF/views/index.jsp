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
<!-- your webpage info goes here -->
<title>Real Estate Web site</title>

<meta name="author" content="RealEstate" />
<meta name="description" content="" />
<link href="<c:url value='/static/css/lib/bootstrap.min.css' />" rel="stylesheet"></link>
<link href="<c:url value='/static/css/style/materialadmin.css' />" rel="stylesheet"></link>
<link href="<c:url value='/static/css/lib/font-awesome.min.css' />" rel="stylesheet"></link>
<link href="<c:url value='/static/css/lib/theme.css' />" rel="stylesheet"></link>
<link href="<c:url value='/static/css/style/main-style.css' />" rel="stylesheet"></link>
<script src="<c:url value='/static/js/lib/jQuery-2.1.4.min.js' />"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


</head>
	<body class="body-bg">
		<!-- webpage content goes here in the body -->
		<div id="page">
			<div id="logo">
				<h1>My Real Estate Web site</h1>
			</div>
			<div id="nav" class="pull-right btns">
				<form:form id="registerFormId" action="${registerUrl}" method="POST">
					<input type="submit" align="right" name="Register Buyers" value="Register Buyers" class="btn btn-warning">
				</form:form>
				<form:form id="loginFormId" action="${loginUrl}" method="POST">
					<input type="submit" name="login" align="right" value="Log in" class="btn btn-info">
				</form:form>
			</div>
			<div id="content">
				<p>Please register to get access for searching properties</p>
			</div>
			<div id="footer">
				<p>Web page made by Umamaheswara Rao</p>
			</div>
		</div>
	</body>
</html>