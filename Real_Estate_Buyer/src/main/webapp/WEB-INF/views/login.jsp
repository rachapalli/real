<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<link href="<c:url value='/static/css/lib/bootstrap.min.css' />" rel="stylesheet"></link>
<link href="<c:url value='/static/css/style/main-style.css' />" rel="stylesheet"></link>
<link href="<c:url value='/static/css/lib/buttons.dataTables.min.css'/>" rel="stylesheet"></link>

<script src="<c:url value='/static/js/lib/jQuery-2.1.4.min.js' />"></script>
<script src="<c:url value='/static/js/lib/jquery-1.12.4.js' />"></script>
<script src="<c:url value='/static/js/script.js' />"></script>

<c:url var="loginUrl" value="/login" />
</head>

<body>
    <div class="login-wraper">

        <div class="card center-login">
            <div class="login-head">
                <%-- <img src="<c:url value='/static/img/user-icon.png' />" width="48" height="48"> --%>Login here
            </div>
            <div class="main-inner">
                <c:out value="${logout}" />
                
                <form class="form padding-top30"  action="<c:url value='j_spring_security_check' />" method='POST' onsubmit="return emailIdLoginCheck();" >
                    <span style="color: green;"> ${mailSuccessMsg} </span>
                     <div id="loginCheckMSG" class="error-msg">
                        <c:if test="${param.authfailed != null}">
                               Invalid Email ID and Password.
                        </c:if>
                     </div>
                    <c:if test="${param.logout != null}">
                        <div id="invalidLogoutMSG"  style="color: green;">
                            You have been logged out successfully.
                        </div>
                    </c:if>
                    <div class="form-group padding-top0">
                        <label class="managelabel">Email ID</label> <a href="javascript:void(0)" class="rem-link pull-right"
                            style="visibility: hidden; display: none;">Remind me</a>
                        <div class="clearfix"></div>
                       <!--  <input type="text" class="form-control input-sm" id="emailId" autofocus="autofocus" name="emailId" placeholder="Enter Email ID" required> -->
                      <input type="text" class="width280" name="j_username" placeholder="Enter Email ID" id="emailId" autofocus="autofocus" name="emailId">
                    </div>
                    <div class="form-group">
                        <label class="managelabel">Password</label> <a href="javascript:void(0)" class="rem-link pull-right"
                            style="visibility: hidden; display: none;">Reset</a>
                        <div class="clearfix"></div>
                      <!--   <input type="password" class="form-control input-sm" id="password" name="password" placeholder="Enter Password" required> -->
                      <input type="password" class="width280" name="j_password" id="password" name="password" placeholder="Enter Password" required>
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <div class="form-group">
                        <button type="submit" class="btn btn-block dark-btn text-normalcase">
                            Login
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
