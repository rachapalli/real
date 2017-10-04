<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="application" />

<!-- Left side column. contains the LOGO and side bar -->

<aside class="main-sidebar">
  <%--   <!-- Side bar: style can be found in sidebar.less -->
    	<div id="content-1" class="sidebar content mCustomScrollbar">
	        <!-- Side bar Menu -->
	        <ul class="header-nav-profile">
	            <li>
                <div class="profile-box">
                    <div class="profile-img">
                        <img class="empProfileImage" id="empProfileImage" src="data:image/jpeg;base64,${image}" onError="this.src='<c:url value='/static/img/user-small.jpg' />';">
                        <a href="#" id="uploadEmpImageLink" class="uploadProfilePic" data-toggle="modal" data-target="#myModalUpload"> <span class=""><fmt:message
                            key="msg.change" /></span></a>
                    </div>
                    <span class="profile-info">${sessionScope.loggedUser.firstName} ${sessionScope.loggedUser.lastName}</span>
                </div>
                    <input type="hidden" id="loggedUser" value="${sessionScope.loggedUser.firstName} ${sessionScope.loggedUser.lastName}">
                    
                      <c:choose>
	                    <c:when test="${sessionScope.notReqCityState != 'notReqCityState'}">
	                         <span class="storeid" style="display: none;" id="loggedUserStoreId"><c:out
	                                value="${sessionScope.epmStoreId}"></c:out></span>
	                         <span class="storeid" id="loggedUserStoreName"><fmt:message key="msg.common.store" /># :  <c:out
	                                value="${sessionScope.empStoreName}"></c:out></span>
	                        <span class="storeid"><fmt:message key="msg.city" /> <c:out value="${sessionScope.storeCity}"></c:out></span>
	                        <span class="storeid"><fmt:message key="msg.state" /> <c:out value="${sessionScope.storeState}"></c:out></span>
	                    </c:when>
	                    <c:otherwise>
						<span style="display: none;" class="storeid"
							id="loggedUserStoreId"><c:out
								value="${sessionScope.epmStoreId}"></c:out></span>
						<span style="display: none;" class="storeid"
							id="loggedUserStoreName"><c:out
								value="${sessionScope.epmStoreName}"></c:out></span>		
					</c:otherwise>
	                </c:choose>
	                <!--End Drop Down - menu --></li>
	            <!-- End Drop Down -->
	        </ul>
	        <div id="sidebar-menu-div">
	            <ul class="sidebar-menu" id="sidebar-menu">
	                <li><a href="dashboard?empStoreId=${sessionScope.epmStoreId}&&tabName=dashboardTab" id="dashboardTab" ><img
	                        src="<c:url value='/static/img/user-pc.png' />" width="26" height="22" class="manage-icon"><span><fmt:message
	                                key="msg.customer.lookup" /></span> </a></li>
	                <li><a href='viewloanapproval?tabName=viewloanApprovalTab' id="viewloanApprovalTab"><img
	                        src="<c:url value='/static/img/user-pc.png' />" width="26" height="22" class="manage-icon"><span><fmt:message
	                                key="msg.loan.approval" /></span> </a></li>
	
	                <li><a href="viewloandeclined?tabName=viewloanDeclinedTab" id="viewloanDeclinedTab"><img src="<c:url value='/static/img/user-pc.png' />" width="26"
	                        height="22" class="manage-icon"><span><fmt:message key="msg.loan.declined" /></span> </a></li>
	
	                <li><a href="viewchangeappstatus?tabName=viewChangeAppStatusTab" id="viewChangeAppStatusTab"><img src="<c:url value='/static/img/user-pc.png' />" width="26"
	                        height="22" class="manage-icon"><span><fmt:message key="msg.change.app.status" /></span> </a></li>
	                <li><a href="todolist?tabName=toDoListTab" id="toDoListTab"><img
	                        src="<c:url value='/static/img/user-pc.png' />" width="26" height="22" class="manage-icon"><span><fmt:message
	                                key="msg.todo.list" /></span> </a></li>
                                    
                    <li><a href="custblockdet?tabName=custBlockDetTab" id="custBlockDetTab"><img
                            src="<c:url value='/static/img/user-pc.png' />" width="26" height="22" class="manage-icon"><span><fmt:message
                                    key="msg.cust.block.det" /></span> </a></li>                
                    <li><a href="deletedapps?tabName=deletedAppsTab" id="deletedAppsTab"><img src="<c:url value='/static/img/dollar-bag.png' />"
                            width="26" height="22" class="manage-icon"><span><fmt:message key="msg.deleted.apps" /></span> </a></li>
                                    
                    <li><a href="callpromiselist?tabName=callpromiseListTab" id="callpromiseListTab"><img
                            src="<c:url value='/static/img/user-pc.png' />" width="26" height="22" class="manage-icon"><span><fmt:message
                                    key="msg.callpromise.list" /></span> </a></li>               
                                    
	                <li><a href="#"><img src="<c:url value='/static/img/dashbaord.png' />" width="26" height="22" class="manage-icon"><span>Dashboard</span>
	            </a></li>
	                <c:if test="${sessionScope.empRoleId != 1}">
	                    <li><a href="viewassignedleads?tabName=viewAssignedLeadsTab" id="viewAssignedLeadsTab"><img
	                            src="<c:url value='/static/img/dollar-bag.png' />" width="26" height="22" class="manage-icon"><span><fmt:message
	                                    key="msg.assigned.leads" /></span> <i></i></a></li>
	                </c:if>
	                <li><a href="viewpaymenthistory?tabName=viewPaymentHistoryTab" id="viewPaymentHistoryTab"><img src="<c:url value='/static/img/dollar-bag.png' />" width="26"
	                        height="22" class="manage-icon"><span><fmt:message key="msg_lbl_loan_history" /></span> </a></li>
	               
	                <li><a href="viewpaymentcompletedapp?tabName=viewPaymentCompletedAppTab" id="viewPaymentCompletedAppTab"><img src="<c:url value='/static/img/dollar-bag.png' />"
	                        width="26" height="22" class="manage-icon"><span><fmt:message key="msg.payment.completed.app" /></span> </a></li>
                    <li class="treeview"><a href="#"><img src="<c:url value='/static/img/dollar-bag.png' />" width="26" height="22"
	                        class="manage-icon"><span><fmt:message key="msg.manage.payment" /></span> <i class="fa fa-angle-down fa-lg pull-right"></i></a>
	                    <ul class="treeview-menu">
	                        <li><a href='paymenttocustomer?tabName=paymentToCustomerTab_T'  id="paymentToCustomerTab_T"> <fmt:message key="msg.payment.to.customer" /></a></li>
	                        <li><a href="paymentfromcustomer?tabName=paymentFromCustomerTab_T"  id="paymentFromCustomerTab_T"> <fmt:message key="msg.payment.from.customer" /></a></li>
	                    </ul></li>
	                    
	                <li><a href="viewloanoutstanding?tabName=viewLoanOutstandingTab" id="viewLoanOutstandingTab"><img src="<c:url value='/static/img/dollar-bag.png' />" width="26"
	                        height="22" class="manage-icon"><span><fmt:message key="msg.payment.loanOutstanding" /></span> </a></li>
	
					 <li><a href="viewscheduleoutstanding?tabName=viewScheduleOutstandingTab" id="viewScheduleOutstandingTab"><img src="<c:url value='/static/img/dollar-bag.png' />" width="26"
	                        height="22" class="manage-icon"><span><fmt:message key="msg.payment.scheduleOutstanding" /></span> </a></li>
	
	                <c:if test="${fn:contains(permissions, roleManage)}">
	                    <li class="treeview"><a href="#"><img src="<c:url value='/static/img/user-pc.png' />" width="26" height="22"
	                            class="manage-icon"><span><fmt:message key="msg.manage.role" /></span> <i class="fa fa-angle-down fa-lg pull-right"></i></a>
	                        <ul class="treeview-menu">
	                            <c:if test="${fn:contains(permissions, viewRole)}">
	                                <li><a href='viewrole?status=view&tabName=viewRoleTab_T'   id="viewRoleTab_T" ><fmt:message key="msg.view.role" /></a></li>
	                            </c:if>
	                            <c:if test="${fn:contains(permissions, addRole)}">
	                                <li><a href="addrole?tabName=addRoleTab_T"   id="addRoleTab_T" ><fmt:message key="msg.add.role" /></a></li>
	                            </c:if>
	                        </ul></li>
	                </c:if>
	
	                <c:if test="${fn:contains(permissions, storeManage)}">
	                    <li class="treeview"><a href="#"><img src="<c:url value='/static/img/user-pc.png' />" width="26" height="22"
	                            class="manage-icon"><span><fmt:message key="msg.manage.store" /></span> <i
	                            class="fa fa-angle-down fa-lg pull-right"></i></a>
	                        <ul class="treeview-menu">
	                            <c:if test="${fn:contains(permissions, viewStore)}">
	                                <li><a href="viewstore?tabName=viewStoreTab_T"  id="viewStoreTab_T" ><fmt:message key="msg.view.store" /></a></li>
	                            </c:if>
	                            <c:if test="${fn:contains(permissions, addStore)}">
	                                <li><a href="createstore?tabName=createStoreTab_T"  id="createStoreTab_T" ><fmt:message key="msg.add.store" /></a></li>
	                            </c:if>
	                        </ul></li>
	                </c:if>
	                <c:if test="${fn:contains(permissions, empManage)}">
	                    <li class="treeview"><a href="#"><img src="<c:url value='/static/img/user-pc.png' />" width="26" height="22"
	                            class="manage-icon"><span><fmt:message key="msg.manage.employee" /></span> <i
	                            class="fa fa-angle-down fa-lg pull-right"></i></a>
	                        <ul class="treeview-menu">
	                            <c:if test="${fn:contains(permissions, viewEmployee)}">
	                                <li><a href="viewemployee?tabName=viewEmployeeTab_T"   id="viewEmployeeTab_T"   ><fmt:message key="msg.view.employee" /></a></li>
	                            </c:if>
	                            <c:if test="${fn:contains(permissions, addEmployee)}">
	                                <li><a href="employeeform?tabName=employeeFormTab_T"  id="employeeFormTab_T"   ><fmt:message key="msg.add.employee" /></a></li>
	                            </c:if>
	                        </ul></li>
	                </c:if>
	
	                <c:if test="${fn:contains(permissions, loanManage)}">
	                    <li class="treeview"><a href="#"><img src="<c:url value='/static/img/dollar-bag.png' />" width="26" height="22"
	                            class="manage-icon"><span><fmt:message key="msg.manage.loan" /></span> <i class="fa fa-angle-down fa-lg pull-right"></i></a>
	                        <ul class="treeview-menu">
	                            <li><a href="applyloan?tabName=applyLoanTab_T"   id="applyLoanTab_T"><fmt:message key="msg.apply.loan" /></a></li>
	                        </ul></li>
	                </c:if>
	                <c:if test="${sessionScope.empRoleId == 1}">
	                    <li><a href="viewadminpanel?tabName=viewadminpanelTab" id="viewadminpanelTab" ><img src="<c:url value='/static/img/user-pc.png' />"
	                            width="26" height="22" class="manage-icon"><span><fmt:message key="msg.admin.panel" /></span> <i></i></a></li>
	                </c:if>
	                <li class="treeview"><a href="#"><img src="<c:url value='/static/img/dollar-bag.png' />" width="26" height="22"
	                        class="manage-icon"><span><fmt:message key="msg.view.report" /></span> <i class="fa fa-angle-down fa-lg pull-right"></i></a>
	                    <ul class="treeview-menu">
	                        <li><a href="viewreport?reportType=appVolumeReport"><fmt:message key="msg.app.volume.report" /></a></li>
	                        <li><a href="viewreport?reportType=loanVolumeReport"><fmt:message key="msg.loan.volume.report" /></a></li>
	                        <li><a href="viewreport?reportType=convRateReport"><fmt:message key="msg.conv.rate.report" /></a></li>
	                        <li><a href="viewreport?reportType=riskGradTrendReportByAppLoanVol"><fmt:message key="msg.app.loan.vol.risk.grad.trend.report" /></a></li>
	                        <li><a href="viewreport?reportType=riskGradTrendReport"><fmt:message key="msg.risk.grad.trend.report" /></a></li>
	                        <li><a href="viewreport?reportType=firstPayDefaultsReport"><fmt:message key="msg.first.pay.defaults.report" /></a></li>
	                        <li><a href="viewreport?reportType=riskScoreKSChart"><fmt:message key="msg.risk.score.ks.chart" /></a></li>
	                        <li><a href="viewreport?reportType=actualvsExpectedBadRatesbyRiskScore"><fmt:message key="msg.actualvs.Expected.Bad.Ratesby.RiskScore" /></a></li>   
	                        <li><a href="viewreport?reportType=staticPool"><fmt:message key="msg.static.pool" /></a></li>       
	                        <li><a href="viewreport?reportType=sampleCollections"><fmt:message key="msg.sample.collections" /></a></li>
	                        <li><a href="viewreport?reportType=appsAndLoanByMarketing"><fmt:message key="msg.app.loan.byMarketing" /></a></li>
	                        <li><a href="viewreport?reportType=transactionReport"><fmt:message key="msg.transaction.report" /></a></li>
	                        <li><a href="viewreport?reportType=portfolioAnalysisReport"><fmt:message key="msg.portfolio.analysis.report" /></a></li>
	                        <li><a href="viewreport?reportType=loanBalanceAnalysis"><fmt:message key="msg.loan.balance.analysis" /></a></li>
	                    </ul></li>
	            </ul>
	        </div>
	        <!-- /.sidebar-menu -->
        </div>
    <!-- /.sidebar --> --%>
</aside>
