<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<link href="<c:url value='/static/css/lib/bootstrap.min.css' />" rel="stylesheet"></link>
<link href="<c:url value='/static/css/style/cashbak.css' />" rel="stylesheet"></link>
<link href="<c:url value='/static/css/style/materialadmin.css' />" rel="stylesheet"></link>
<link href="<c:url value='/static/css/lib/font-awesome.min.css' />" rel="stylesheet"></link>
<link href="<c:url value='/static/css/lib/theme.css' />" rel="stylesheet"></link>
<link href="<c:url value='/static/css/style/main-style.css' />" rel="stylesheet"></link>
<script src="<c:url value='/static/js/libs/jQuery-2.1.4.min.js' />"></script>
<script src="<c:url value='/static/js/libs/jquery-1.12.4.js' />"></script>
<script src="<c:url value='/static/js/libs/bootstrap.min.js' />"></script>
<link href="<c:url value='/static/css/lib/bootstrap-dialog.css' />" rel="stylesheet"></link>
<link href="<c:url value='/static/css/lib/jquery.tagger.css' />" rel="stylesheet"></link>
<!-- Start AutoComplete  TextField  Dropdown  Functionality implementation -->
<link href="<c:url value='/static/css/lib/jquery-ui.css'/>" rel="stylesheet"></link>

<script src="<c:url value='/static/js/libs/jquery-ui.js' />"></script>
<!-- End AutoComplete  TextField  Dropdown  Functionality implementation -->
<link href="<c:url value='/static/css/lib/bootstrap-glyphicons.css'/>" rel="stylesheet"></link>
<link href="<c:url value='/static/css/lib/dataTables.bootstrap.css'/>" rel="stylesheet"></link>

<link href="<c:url value='/static/css/lib/loader1.css'/>" rel="stylesheet"></link>
<link href="<c:url value='/static/css/lib/loader2.css'/>" rel="stylesheet"></link>
<link href="<c:url value='/static/css/lib/buttons.dataTables.min.css'/>" rel="stylesheet"></link>
<link href="<c:url value='/static/css/lib/jquery-editable-select.css'/>" rel="stylesheet"></link>
<c:url var="loginUrl" value="/login" />
</head>

<body>
    <div class="login-wraper">

        <div class="card">
            <img src="<c:url value='/static/img/logo.png' />" alt="logo" class="logo">
            <div class="login-head">
                <img src="<c:url value='/static/img/user-icon.png' />" width="48" height="48">Login here
            </div>
            <div class="main-inner">
                <c:out value="${logout}" />
                
                <form class="form padding-top25" action="${loginUrl}"  onsubmit="return emailIdLoginCheck();" method="post">
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
                        <input type="text" class="form-control input-sm" id="emailId" autofocus="autofocus" name="emailId" placeholder="Enter Email ID" required>
                    </div>
                    <div class="form-group">
                        <label class="managelabel">Password</label> <a href="javascript:void(0)" class="rem-link pull-right"
                            style="visibility: hidden; display: none;">Reset</a>
                        <div class="clearfix"></div>
                        <input type="password" class="form-control input-sm" id="password" name="password" placeholder="Enter Password" required>
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <div class="form-group">
                        <button type="submit" class="btn btn-block dark-btn text-normalcase">
                            <img src="<c:url value='/static/img/refresh-icon.png' />" width="18" height="20" class="refresh-icon">Login
                        </button>
                    </div>
                    <div class="form-group">
                        <a href="<c:url value="/forgotPassword" />" class="forgotlink">Forgot Password</a>
                    </div>
                     <div class="form-group">
                        <a href="<c:url value="nonsecure/uploadDoc" />" class="forgotlink">Upload</a>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- jQuery 2.1.4 -->
    <script src="<c:url value='/static/js/libs/jQuery-2.1.4.min.js' />"></script>
    <script src="<c:url value='/static/js/libs/bootstrap.min.js' />"></script>
    <script src="<c:url value='/static/js/libs/app.min.js' />"></script>
    <script src="<c:url value='/static/js/libs/AppForm.js' />"></script>
     <script src="<c:url value='/static/js/login.js' />"></script>
    <!-- Bootstrap 3.3.5 -->
    <!-- <script src="js/bootstrap.min.js"></script>  -->
    <!-- <!-- Application Java Script -->    
    <!-- <script src="js/app.min.js"></script>  -->
    <!-- <script src="js/AppForm.js"></script> -->
</body>
</html>
