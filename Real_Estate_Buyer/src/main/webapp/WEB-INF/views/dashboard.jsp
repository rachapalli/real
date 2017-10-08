<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:url value="/buyer/property/search" var="fetchdataUrl" />
<c:url value="/buyer/property/filter" var="filterdataUrl" />
<c:url value="/buyer/property/fetch" var="loaddataUrl" />
<c:url value="/buyer/property/sort" var="sortdataUrl" />
<c:url value="/buyer/contact/seller" var="contactSellerUrl" />


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div class="container">
	<div class="row">
		<div class="col-md-12 search-bar">
			<form id="buyerLookupForm">
				<div class="inner-box">
					<div class="row">
						<div class="col-sm-10">
							<div class="form-group border-primary floatlbl-padding">
								<input style="background-color: white;" type="text"
									placeholder=" Search for Properties, Locations"
									name="searchItem" id="searchItem" autofocus
									class="form-control input-sm validate  serchOnClickEnter height">

							</div>
						</div>
						<div class="col-sm-2">
							<div class="form-group border-primary floatlbl-padding">
								<button type="button" id="searchBtn"
									onclick="fetchResults(this,'${fetchdataUrl}')"
									class="md-btn btn ink-reaction btn ">
									<i class="fa fa-search"></i> Search
								</button>
							</div>
						</div>
					</div>

				</div>

				<div class="search-facet--no-margin row" id="price-filter" data-reactid="425">
					<div class="search-facet-box col-md-8" data-reactid="426">
						<div class="search-facet-box__inner" data-reactid="427">
							<label class="search-facet-box__heading" data-reactid="428">
								<i class="e-icon -icon-dollar -margin-right" data-reactid="429"></i>
								Price:
							</label>
							<label data-reactid="433">&nbsp;From&nbsp;</label>
							<span data-reactid="434"></span>
							<input type="text" class="e-input" placeholder="10000" data-reactid="435" id="lowPriceId">
							<label data-reactid="437">&nbsp; To &nbsp;</label>
							<span data-reactid="438"></span>
							<input class="e-input" type="text" placeholder="10000" data-reactid="439" id="highPriceId">

							<button type="button" class="fds-c-button fds-u-p0 no-label" data-reactid="441" onclick="filterProperties('${filterdataUrl}')">
								<i class="e-icon -icon-search" data-reactid="442">
								<span class="e-icon__alt" data-reactid="443" >Filter</span></i>
							</button>
						</div>
					</div>
					<div class="col-md-4 pull-right">
						<label for="sortby" class="fds-u-is-visually-hidden"> Sortby: </label>
						<select name="sortby" class="sort" onchange="sortProperties('${fetchdataUrl}', this)">
							<option value="price-asc" selected>Select</option>
							<option value="price-asc" id="low">low to high</option>
							<option value="price-desc" id="high">high to low</option>
						</select>
					</div>
				</div>
				<div class="row"  id="${loaddataUrl}">
					<div class="col-md-12" id="mainId"></div>
				</div>
				<div id="myModal" class="modal fade" role="dialog">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Send mail to Seller</h4>
							</div>
							<div class="modal-body row">
								<textarea class="col-md-12 textarea" rows="4" placeholder="Send mail to Seller" id="messageId" cols=""></textarea>
								<button class="col-md-2 btn btn-primary" onclick="sendMailToSeller('${contactSellerUrl}')">Submit</button>
							</div>
						</div>

					</div>
				</div>
				
				<div id="myModal1" class="modal fade" role="dialog">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">Property Details</h4>
							</div>
							<div class="modal-body row">
								<p class="row">
									<span class="col-md-3">Property Name</span><span class="col-md-1">:</span>
									<span class="col-md-3" id="propertyNameId"></span>
								</p>
								<p class="row">
									<span class="col-md-3">Street Address</span><span class="col-md-1">:</span>
									<span class="col-md-3" id="streetId"></span>
								</p>
								<p class="row">
									<span class="col-md-3">Place</span><span class="col-md-1">:</span>
									<span class="col-md-3" id="propertyLacationId"></span>
								</p>
								<p class="row">
									<span class="col-md-3">State</span><span class="col-md-1">:</span>
									<span class="col-md-3" id="stateId"></span>
								</p>
								<p class="row">
									<span class="col-md-3">Price</span><span class="col-md-1">:</span>
									<span class="col-md-3" id="priceId"></span>
								</p>
								<p class="row">
									<span class="col-md-3">Area</span><span class="col-md-1">:</span>
									<span class="col-md-3" id="areaId"></span>
								</p>
								<p class="row">
									<span class="col-md-3">postedBy</span><span class="col-md-1">:</span>
									<span class="col-md-3" id="postedById"></span>
								</p>
								<p class="row">
									<span class="col-md-3">Bed Rooms</span><span class="col-md-1">:</span>
									<span class="col-md-3" id="bedRoomsId"></span>
								</p>
								<p class="row">
									<span class="col-md-3">Bath Rooms</span><span class="col-md-1">:</span>
									<span class="col-md-3" id="bathRoomsId"></span>
								</p>
<!-- 								<button class="col-md-2 btn btn-primary">Submit</button>
 -->							</div>
						</div>

					</div>
				</div>
			</form>
		</div>
	</div>
</div>
