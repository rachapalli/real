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
									placeholder=" Search for Properties, Locations"
									name="searchItem" id="searchItem" autofocus
									class="form-control input-sm validate  serchOnClickEnter height">

							</div>
						</div>
						<div class="col-sm-1">
							<div class="form-group border-primary floatlbl-padding">
								<button type="button" id="searchBtn"
									onclick="fetchResults(this,'${fetchdataUrl}')"
									class="md-btn btn ink-reaction btn ">
									Search <i class="fa fa-search"></i>
								</button>
							</div>
						</div>
					</div>

				</div>

				<div class="search-facet--no-margin" id="price-filter"
					data-reactid="425">
					<div class="search-facet-box" data-reactid="426">
						<div class="search-facet-box__inner" data-reactid="427">
							<label class="search-facet-box__heading" data-reactid="428">
								<i class="e-icon -icon-dollar -margin-right" data-reactid="429"></i>
								Price
							</label> <label data-reactid="433">From</label> <span data-reactid="434"></span>
							<input type="text" class="e-input" placeholder="0000"
								data-reactid="435"> <label data-reactid="437">To</label><span
								data-reactid="438"></span><input type="text" placeholder="0000"
								data-reactid="439">

							<button type="button" class="fds-c-button fds-u-p0 no-label"
								data-reactid="441">
								<i class="e-icon -icon-search" data-reactid="442"> <span
									class="e-icon__alt" data-reactid="443">Filter</span></i>
						</div>
					</div>
				</div>


				<label for="sortby" class="fds-u-is-visually-hidden"> Price
					Sort by: </label> <select name="sortby">
					<option value="price-asc">low to high</option>
					<option value="price-desc">high to low</option>
				</select>
			</form>
		</div>
	</div>
</div>