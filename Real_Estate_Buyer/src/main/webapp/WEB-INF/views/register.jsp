<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:url value="/buyer/register" var="registerBuyerUrl" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<link href="<c:url value='/static/css/lib/bootstrap.min.css' />" rel="stylesheet"></link>
<link href="<c:url value='/static/css/style/materialadmin.css' />" rel="stylesheet"></link>
<link href="<c:url value='/static/css/lib/font-awesome.min.css' />" rel="stylesheet"></link>
<link href="<c:url value='/static/css/lib/theme.css' />" rel="stylesheet"></link>
<link href="<c:url value='/static/css/style/main-style.css' />" rel="stylesheet"></link>
<script src="<c:url value='/static/js/script.js' />"></script>
</head>

<form:form id="registerBuyerFormId" action="${registerBuyerUrl}" commandName="registerBuyer" method="POST" >

<div class="container">
        <div class="row centered-form">
        <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
        	<div class="panel panel-default">
        		<div class="panel-heading">
			    		<h3 align="center" class="panel-title">Please Register to get more results!</h3>
			 			</div>
			 			<div class="panel-body">
			    			<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			                			<form:input path="firstName" type="text" name="first_name" id="rgFirstName" class="form-control required input-sm" placeholder="First Name"/>
			                			<div class="clear"></div>
										<div id="errorDivrgFirstName" class="errormsg_form display_none"></div>
			    					</div>
			    				</div>
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    						<form:input path="lastName" type="text" name="last_name" id="rgLastName" class="form-control input-sm required" placeholder="Last Name"/>
			    						<div class="clear"></div>
										<div id="errorDivrgrgLastName" class="errormsg_form display_none"></div>
			    					</div>
			    				</div>
			    			</div>

			    			<div class="form-group">
			    				<form:input path="email" type="email" name="email" id="rgEmail" class="form-control input-sm required" placeholder="Email Address"/>
			    				<div class="clear"></div>
								<div id="errorDivrgEmail" class="errormsg_form display_none"></div>
			    			</div>

			    			<div class="row">
			    				<div class="col-xs-6 col-sm-6 col-md-6">
			    					<div class="form-group">
			    						<form:input path="password" type="password" name="password" id="rgPassword" class="form-control input-sm required" placeholder="Password"/>
			    						<div class="clear"></div>
										<div id="errorDivrgPassword" class="errormsg_form display_none"></div>
			    					</div>
			    				</div>
			    			</div>
			    			
			    			<input type="submit" value="Register" class="btn btn-info btn-block">
			    	</div>
	    		</div>
    		</div>
    	</div>
    </div>
</form:form>    