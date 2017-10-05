<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="application" />
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
</head>
<body>
    <div class="login-wraper">

        <div class="card">
            <img src="<c:url value='/static/img/logo.png' />" alt="logo" class="logo">
            <div class="login-head">
                <img src="<c:url value='/static/img/user-icon.png' />" width="48" height="48"><fmt:message key="msg.login.here" />
            </div>
            <div class="main-inner">
                <c:url var="loginUrl" value="/login" />
                <form class="form padding-top25" action="${loginUrl}" onsubmit="return emailIdLoginCheck();" method="post">
                    <span style="color: green;"> ${mailSuccessMsg} </span>
                    <div id="loginCheckMSG" class="error-msg">
                     <c:out value="${authfailed}"/>
                     <c:out value="${sessionExpMsg}"/>
                     <c:out value="${logout}"/>
                    </div>
                    <div class="form-group padding-top0">
                        <label class="managelabel">Email ID</label> <a href="javascript:void(0)" class="rem-link pull-right"
                            style="visibility: hidden; display: none;"><fmt:message key="msg.remind.me" /></a>
                        <div class="clearfix"></div>
                        <input type="text" class="form-control input-sm" id="emailId" autofocus="autofocus" name="emailId"
                            placeholder="Enter Email ID" required>
                    </div>
                    <div class="form-group">
                        <label class="managelabel">Password</label> <a href="javascript:void(0)" class="rem-link pull-right"
                            style="visibility: hidden; display: none;"><fmt:message key="msg.common.update" /><fmt:message key="msg_lbl_reset" /></a>
                        <div class="clearfix"></div>
                        <input type="password" class="form-control input-sm" id="password" name="password" placeholder="Enter Password" required>
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <div class="form-group">
                        <button type="submit" class="btn btn-block dark-btn text-normalcase">
                            <img src="<c:url value='/static/img/refresh-icon.png' />" width="18" height="20" class="refresh-icon"><fmt:message key="msg.login" />
                        </button>
                    </div>
                    <div class="form-group">
                        <a href="<c:url value="/forgotpassword" />" class="forgotlink"><fmt:message key="msg.forgot.pass" /></a>
                    </div>
             
                </form>
            </div>
        </div>
    </div>

    <!-- jQuery 2.1.4 -->
    <script src="<c:url value='/static/js/lib/jQuery-2.1.4.min.js' />"></script>
    <script src="<c:url value='/static/js/lib/bootstrap.min.js' />"></script>
    <script src="<c:url value='/static/js/lib/app.min.js' />"></script>
    <script src="<c:url value='/static/js/lib/AppForm.js' />"></script>
    <script src="<c:url value='/static/js/script/login.js' />"></script>
  
</body>
</html>
