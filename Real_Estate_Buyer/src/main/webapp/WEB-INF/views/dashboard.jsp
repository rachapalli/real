<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:url value="/buyer/property/search" var="fetchdataUrl" />
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<div class="container">
		<div class="row">
			<div class="col-md-12 search-bar">
				<form id="buyerLookupForm">
					<div class="inner-box">
						<div class="row">
							<div class="col-sm-9">
								<div class="form-group border-primary floatlbl-padding">
									<input style="background-color: white;" type="text"
										placeholder="Search for Products, Brands and more"
										 name="searchItem" id="searchItem"   autofocus 
										class="form-control input-sm validate  serchOnClickEnter">
								
								</div>
							</div>
							<div class="col-sm-1">
								<div class="form-group border-primary floatlbl-padding">
									<button type="button" id="searchBtn"  onclick="fetchResults(this,'${fetchdataUrl}')" 
										class="md-btn btn ink-reaction btn-success">
										Search<i class="fa fa-search"></i>
									</button>
								</div>
							</div>
						</div>

					</div>
				</form>
			</div>
		</div>
	</div>
