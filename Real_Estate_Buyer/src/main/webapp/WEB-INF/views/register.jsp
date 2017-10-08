<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<c:url value="/buyer/save" var="registerBuyerUrl" />
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title></title>
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	<link href="<c:url value='/static/css/lib/bootstrap.min.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/style/materialadmin.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/style/main-style.css' />" rel="stylesheet"></link>
	
	<script src="<c:url value='/static/js/lib/jQuery-2.1.4.min.js' />"></script>
	<script src="<c:url value='/static/js/lib/jquery-1.12.4.js' />"></script>
	<script src="<c:url value='/static/js/script.js' />"></script>
</head>

<form:form id="registerBuyerFormId" action="${registerBuyerUrl}" commandName="registerBuyer" method="POST" onclick="return validateRegisterForm('registerBuyerFormId', this)">

<div class="container">
        <div class="row centered-form">
        <div class="col-xs-12 col-sm-8 col-md-5 col-sm-offset-2 col-md-offset-4">
        	<div class="panel panel-default">
        		<div class="login-head">
		    		<h3 align="center" class="panel-title">Please Register to get more results!</h3>
	 			</div>
		 			<div class="panel-body">
		    			<div class="row">
		    				<div class="col-xs-6 col-sm-6 col-md-6">
		    					<div class="form-group">
		                			<form:input path="firstName" type="text" name="first_name" id="rgFirstName" class="form-control required input-sm" placeholder="First Name" onkeydown="clearData()"/>
		                			<div class="clear"></div>
									<div id="errorDivrgFirstName" class="invalid-error-msg display_none"></div>
		    					</div>
		    				</div>
		    				<div class="col-xs-6 col-sm-6 col-md-6">
		    					<div class="form-group">
		    						<form:input path="lastName" type="text" name="last_name" id="rgLastName" class="form-control input-sm required" placeholder="Last Name" onkeydown="clearData()"/>
		    						<div class="clear"></div>
									<div id="errorDivrgLastName" class="invalid-error-msg display_none"></div>
		    					</div>
		    				</div>
		    			</div>
		    			<div class="row">
							<div class="col-xs-6 col-sm-6 col-md-6">
				    			<div class="form-group">
				    				<form:input path="email" type="email" name="email" id="rgEmail" class="form-control input-sm required" placeholder="Email Address" onkeydown="clearData()"/>
				    				<div class="clear"></div>
									<div id="errorDivrgEmail" class="invalid-error-msg display_none"></div>
				    			</div>
							</div>
							<div class="col-xs-6 col-sm-6 col-md-6">
				    			<div class="form-group">
				    				<form:input path="phone" type="text" name="phone" id="rgPhone" class="form-control input-sm required" placeholder="Enter Phone" onkeydown="clearData()"/>
				    				<div class="clear"></div>
									<div id="errorDivrgPhone" class="invalid-error-msg display_none"></div>
				    			</div>
							</div>
						</div>
		    			<div class="row">
		    				<div class="col-xs-6 col-sm-6 col-md-6">
		    					<div class="form-group">
		    						<form:input path="password" type="password" name="password" id="rgPassword" class="form-control input-sm required" placeholder="Password" onkeydown="clearData()"/>
		    						<div class="clear"></div>
									<div id="errorDivrgPassword" class="invalid-error-msg display_none"></div>
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