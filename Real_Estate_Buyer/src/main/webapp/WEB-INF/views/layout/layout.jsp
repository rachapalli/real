<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<fmt:setBundle basename="application" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

<link href="<c:url value='/static/css/lib/bootstrap.min.css' />" rel="stylesheet"></link>
<link href="<c:url value='/static/css/style/materialadmin.css' />" rel="stylesheet"></link>
<link href="<c:url value='/static/css/lib/font-awesome.min.css' />" rel="stylesheet"></link>
<link href="<c:url value='/static/css/lib/theme.css' />" rel="stylesheet"></link>
<link href="<c:url value='/static/css/style/main-style.css' />" rel="stylesheet"></link>
<script src="<c:url value='/static/js/lib/jQuery-2.1.4.min.js' />"></script>
<script src="<c:url value='/static/js/lib/jquery-1.12.4.js' />"></script>
<script src="<c:url value='/static/js/lib/bootstrap.min.js' />"></script>
<link href="<c:url value='/static/css/lib/bootstrap-dialog.css' />" rel="stylesheet"></link>
<link href="<c:url value='/static/css/lib/jquery.tagger.css' />" rel="stylesheet"></link>
<!-- Start AutoComplete  TextField  Dropdown  Functionality implementation -->
<link href="<c:url value='/static/css/lib/jquery-ui.css'/>" rel="stylesheet"></link>

<script src="<c:url value='/static/js/lib/jquery-ui.js' />"></script>
<!-- End AutoComplete  TextField  Dropdown  Functionality implementation -->
<link href="<c:url value='/static/css/lib/bootstrap-glyphicons.css'/>" rel="stylesheet"></link>
<link href="<c:url value='/static/css/lib/dataTables.bootstrap.css'/>" rel="stylesheet"></link>

<script src="<c:url value='/static/js/lib/bootstrap-multiselect.js' />"></script>
<link href="<c:url value='/static/css/lib/bootstrap-multiselect.css'/>" rel="stylesheet"></link>

<link href="<c:url value='/static/css/lib/loader1.css'/>" rel="stylesheet"></link>
<link href="<c:url value='/static/css/lib/loader2.css'/>" rel="stylesheet"></link>
<link href="<c:url value='/static/css/lib/buttons.dataTables.min.css'/>" rel="stylesheet"></link>
<script src="<c:url value='/static/js/lib/jquery.mCustomScrollbar.concat.min.js' />"></script>
<link href="<c:url value='/static/css/lib/jquery.mCustomScrollbar.css'/>" rel="stylesheet"></link>
    <!-- Application Java Script -->

    <script src="<c:url value='/static/js/lib/app.min.js' />"></script>
    <script src="<c:url value='/static/js/lib/AppForm.js' />"></script>

    <script src="<c:url value='/static/js/lib/jquery.validate.js' />"></script>
    <script src="<c:url value='/static/js/lib/additional-methods.js' />"></script>
    <script src="<c:url value='/static/js/lib/jquery.dataTables.js' />"></script>
    <script src="<c:url value='/static/js/lib/jquery.dataTables.min.js' />"></script>
    <script src="<c:url value='/static/js/lib/dataTables.tableTools.js' />"></script>
    <script src="<c:url value='/static/js/lib/dataTables.bootstrap.js' />"></script>
    <script src="<c:url value='/static/js/lib/dataTables.buttons.min.js' />"></script>

    <script src="<c:url value='/static/js/lib/jquery.maskedinput.js' />"></script>
    <script src="<c:url value='/static/js/lib/jquery.i18n.properties.js' />"></script>
    <script src="<c:url value='/static/js/lib/bootstrap-dialog.js' />"></script>
    <script src="<c:url value='/static/js/lib/jquery-ui.multidatespicker.js'/>"></script>

    <script src="<c:url value='/static/js/lib/jquery.tagger.js' />"></script>
    <script src="<c:url value='/static/js/lib/date.js' />"></script>
    <script src="<c:url value='/static/js/lib/loader1.js' />"></script>

    <script src="<c:url value='/static/js/lib/jquery-editable-select.js' />"></script>
    
    <script src="<c:url value='/static/js/lib/buttons.flash.min.js' />"></script>
    <script src="<c:url value='/static/js/lib/jszip.min.js' />"></script>
    <script src="<c:url value='/static/js/lib/pdfmake.min.js' />"></script>
    <script src="<c:url value='/static/js/lib/vfs_fonts.js' />"></script>
    <script src="<c:url value='/static/js/lib/buttons.html5.min.js' />"></script>
    <script src="<c:url value='/static/js/lib/buttons.print.min.js' />"></script>

    <script src="<c:url value='/static/js/lib/canvasjs.min.js' />"></script>

    <!-- Adding Js files for Charts -->
    <script src="<c:url value='/static/js/lib/loader.js' />"></script>
    <script src="<c:url value='/static/js/lib/highcharts.js' />"></script>
    <script src="<c:url value='/static/js/lib/exporting.js' />"></script>
    <script src="<c:url value='/static/js/general_ajax_call.js' />"></script>
    <script src="<c:url value='/static/js/script.js' />"></script>
    <!-- Application Java Script -->
</head>

<body class="hold-transition skin-blue sidebar ">
    <input type="hidden" name="baseUrl" id="baseUrl" value="${request.contextPath}" />
    <div class="wrapper">
        <tiles:insertAttribute name="header" />
        <tiles:insertAttribute name="menu" />
        <tiles:insertAttribute name="body" />
        <tiles:insertAttribute name="footer" />
    </div>
    
    <div id="face" class="fixed-wrapper">
        <div class="absOut">
            <img src='/CashBak-FI/static/img/loader.gif' width='50px;' height='50px;' alt='' />
            <h2>
                <span id="loaderPopupMsg">......</span>
            </h2>
        </div>
    </div>
</body>

</html>
