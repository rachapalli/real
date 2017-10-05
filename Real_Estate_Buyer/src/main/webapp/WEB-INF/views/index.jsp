<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:url value="/register" var="registerUrl" />
<c:url value="/login" var="loginUrl" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to Real Estate Web site</title>
</head>

<form:form id="registerFormId" action="${registerUrl}" method="POST">

	<input type="submit" name="Register Buyers" value="Register Buyers">

</form:form>
<form:form id="loginFormId" action="${loginUrl}" method="POST">

	<input type="submit" name="login" value="Log in">

</form:form>


</html>