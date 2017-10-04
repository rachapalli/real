<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="application" />

<style>
.companyImage-upload>input {
	display: none;
}
body{
	background-color: #15a74c;
}
</style>

<header class="main-header">
    <!-- Logo -->
    <div class="logo">
        <div class="companyImage-upload">
            <label for="companyLogo"> <img class="logo-img" id="headerLogoImg"
            src="data:image/jpeg;base64,${image}" onError="this.src='<c:url value='/static/img/logo.png' />';"
            onmouseover="this.style.opacity=0.5;this.filters.alpha.opacity=50" onmouseout="this.style.opacity=1;this.filters.alpha.opacity=100"
            width="100" height="55">
            </label> <input type="file"  id="companyLogo" name="file" />
        </div> 


    </div>
    <!-- Header Navbar -->
    <nav class="manage-icons-right navbar navbar-static-top hidden-xs" role="navigation">
        <ul class="nav navbar-nav navbar-right">
            <!-- <li><a href="#"><i class="fa fa-question"></i> </a></li> -->
            <li class="dropdown"><a href="javascript:void(0);"  class="dropdown-toggle ink-reaction" data-toggle="dropdown">
                <i class="fa fa-cog"></i> </a>
             <ul class="dropdown-menu animation-dock profile-setting">
               <li><a href="updateempprofile" id="uploadEmpProfileLink" class="clearfix uploadEmpProfile"">
                        <span class="pull-left"><fmt:message key="msg.update.emp.profile" /></span> <i class="fa fa-user text-default-dark pull-right"></i>
                </a></li>
               <%--  <li><a href="#" id="uploadEmpImageLink" class="clearfix uploadProfilePic" data-toggle="modal" data-target="#myModalUpload">
                        <span class="pull-left"><fmt:message key="msg.upload.image" /></span> <i class="fa fa-user text-default-dark pull-right"></i>
                </a></li> --%>
                <li><a href="#" class="clearfix"> <span class="pull-left"><fmt:message key="msg.help" /> </span> <i
                        class="fa fa-question text-default-dark pull-right"></i>
                </a></li>
                <li><a href="changepassword" class="clearfix"> <span class="pull-left"><fmt:message key="msg.change.password" /></span>
                        <i class="fa fa-key text-default-dark pull-right"></i>
                </a></li>
                <li><a href="<c:url value="/logout" />" class="clearfix"> <span class="pull-left"><fmt:message key="msg.logout" /></span>
                        <i class="fa fa-sign-out text-default-dark pull-right"></i>
                </a></li>
            </ul>    
             </li>
        </ul>
         
    </nav>
</header>