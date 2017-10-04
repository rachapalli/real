var lenderCheck = false;
var voucherCheck = false;

$(document).ready(function() {
	
});

/***************************************************************************
* Name                    : setformatValidation 
* Return type             : None 
* Input Parameter(s)      : None
* Purpose                 : This is used to set validation and formats for html elements.
* History Header          : Version            Date          Developer Name
* Added By                : 1.0                03 Aug, 2017  shravans
* **************************************************************************
*/
function setformatValidation(){
	 $("#layoutDivId").on('change', '.selectReq', function(event) {
		    var formId = $(this).closest("form").attr("id");
		    var objId = $(this).attr("id");
		    $("#" + formId + "	#errorDiv"+objId).text("");
		    $("#" + formId + "	#errorDiv"+objId).removeClass("display_block").addClass("display_none ");
	 });
	
	$("#layoutDivId").on('keydown', '.inputTextReq', function(event) {
		 var formId = $(this).closest("form").attr("id");
		 var objId = $(this).attr("id");
	     $("#" + formId + "	#errorDiv"+objId).text("");
	     $("#" + formId + "	#errorDiv"+objId).removeClass("display_block").addClass("display_none ");
	});
	
	 $('.multiselect-ui').multiselect({
	        includeSelectAllOption : true
  });
	 
	 $(".phnMask").mask("+1(999)-999-9999", {
	        placeholder : "+1(###)-###-####"
  });
}

/***************************************************************************
* Name                    : PrintDiv 
* Return type             : None 
* Input Parameter(s)      : id
* Purpose                 : This is used to Print div contents.
* History Header          : Version            Date          Developer Name
* Added By                : 1.0                03 Aug, 2017  shravans
* **************************************************************************
*/
function PrintDiv(id) {
    var data = document.getElementById(id).innerHTML;
    var myWindow = window.open();
    myWindow.document.write('<html><head><title></title>');
    myWindow.document.write('<link href="/CashBak-FI/static/css/style/printDiv.css" rel="stylesheet"></link>');
    myWindow.document.write('</head><body >');
    myWindow.document.write(data);
    myWindow.document.write('</body></html>');
    myWindow.document.close(); // necessary for IE >= 10

    myWindow.onload = function() { // necessary if the div contain images

        myWindow.focus(); // necessary for IE >= 10
        myWindow.print();
        myWindow.close();
    };
}

/***************************************************************************
 * Name 				: serchUserData 
 * Return type 			: None 
 * Input Parameter(s) 	: None
 * Purpose 				: This method is used to search user details.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	17 July, 2017 	shravans 
 * **************************************************************************
 */
function serchUserData(object) {
	var formId = $(object).closest("form").attr("id");
	var formData = $('form#' + formId).serialize();

	if (formId == "userSearchFormId") {
		loadWalletUserTable("userMainDataTableId", formData, "fetchwalletusers");
	} else if (formId == "voucherUserSearchFormId") {
		callForPGDataTable("allVoucherUserTableId", formData, "fetchassignedwalletusers");
	}
}


/***************************************************************************
 * Name 				: loadWalletUserTable 
 * Return type 			: None 
 * Input Parameter(s) 	: tableName, formData, URL
 * Purpose 				: This method is used to load wallet user data table.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	28 July, 2017 	shravans 
 * **************************************************************************
 */
function loadWalletUserTable(tableName, formData, url) {
		datatable = $("#" + tableName).DataTable({
		"bDestroy" : true,
		"processing" : false,
		"serverSide" : true,
		"scrollX" : true,
		
		// "scrollY" : "220px", // used to vertical scroll
		// "scrollCollapse" : true,
		"bLengthChange" : true, // used to disable Show field
		"aaSorting" : [],
		"bSort" : false,
		"bFilter" : false, // used to disable search field
		'columnDefs': [{
	            	   'targets': 0,
	            	   'className': 'dt-head-center dt-body-center',
	            	   'checkboxes': { 'selectRow': true}
               		  }],
        'select': {'style': 'multi'},
        'order': [[1, 'asc']],
		"ajax" : pipelineFn({
			url : document.URL.substring(0, document.URL.lastIndexOf("/")) + "/"+ url + '?' + formData,
			pages : 5
		// number of pages to cache
		}),
	});

	$('#' + tableName + ' tbody').on('click', 'tr', function() {
		if ($(this).hasClass('selected')) {
			$(this).removeClass('selected');
		} else {
			datatable.$('tr.selected').removeClass('selected');
			$(this).addClass('selected');
		}
	});
	
}

/***************************************************************************
 * Name 				: applyForLenderAccountType 
 * Return type 			: None 
 * Input Parameter(s) 	: object
 * Purpose 				: This method is used to apply for lender account type.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	11 Aug, 2017 	shravans 
 * **************************************************************************
 */
function applyForLenderAccountType(object){
	
	var checked = $(object).prop("checked");
	displayProgressBarPopup();
	$.ajax({
        type : "post",
        url :  document.URL.substring(0, document.URL.lastIndexOf("/")) + "/applyfor/lenderacc",
        datatype : "json",
//        async : false,
        data : "&checked=" + checked,
        success : function(resData) {
            if(resData){
            	if (resData.STATUS === "success" ) {
            		if(checked){
            			showSuccessPopup("Successfully applied and approved for Lender merchant account");
            		}else{
            			showSuccessPopup("Successfully applied and approved for removal of Lender merchant account");
            		}
        			
            	} else if(resData.STATUS === "failure" ){
            		showWarningPopup(resData.REASON);
            		if(checked === true){
            			$(object).prop('checked', false);
            		}else{
            			$(object).prop('checked', true);
            		}
            	}
            	hideProgressBarPopup();
            }else{
            	showWarningPopup("Some Error occured");
            	if(checked === true){
        			$(object).prop('checked', false);
        		}else{
        			$(object).prop('checked', true);
        		}
        		hideProgressBarPopup();
            }
        },
        error : function(e) {
            ajaxErrorOccur();
        }
    });
	
}


/***************************************************************************
 * Name 				: applyForVoucherAccountType 
 * Return type 			: None 
 * Input Parameter(s) 	: object
 * Purpose 				: This method is used to apply for voucher account type.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	11 Aug, 2017 	shravans 
 * **************************************************************************
 */
function applyForVoucherAccountType(objButton,actionUrl) {
	var checked = $(objButton).prop("checked");
	if(checked){
		loadVoucherCompanyTable(actionUrl);
	}else{
		applyForVoucherAccount(checked, []);
	}
	

}

/***************************************************************************
 * Name 				: loadVoucherCompanyTable 
 * Return type 			: None 
 * Input Parameter(s) 	: None
 * Purpose 				: This method is used to load Voucher Company Table.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	24 Aug, 2017 	shravans
 * **************************************************************************
 */
function loadVoucherCompanyTable(actionUrl) {
	var request = {};
	makeJsonAjaxCall({
		url : actionUrl,
		requestObj : request,
		onSuccess : function(req, status, res) {
			try {
				if(res.status){
					if(res.data && res.data.length > 0){
						for(var index = 0 ; index < res.data.length ; index++ ){
							$("select#selectvoucherCompanyDD").append(
									$("<option>").val(res.data[index].id).html(res.data[index].companyName));
						}
						$('select#selectvoucherCompanyDD').multiselect({
					        includeSelectAllOption : true
					    });
						$("#selectvoucherCompanyErrorDiv").text("");
						$("#voucherCompanyListDivId").modal("show");
					} else {
						var checked = $("#voucherSwitch").prop("checked");
	            		if(checked === true){
	            			$("#voucherSwitch").prop('checked', false);
	            		}else{
	            			$("#voucherSwitch").prop('checked', true);
	            		}
						showWarningPopup("There are no companies available to add");
					}
				}
			} catch (e) {
				console.log("Error " + e.stack);
			}
		},
		onError : function(req, status, error) {
			try {
				console.log("Error " + req);
			} catch (e) {
				console.log("Error " + e.stack);
			}
		}
	});
	
}

/***************************************************************************
 * Purpose 				: This event is used to clear error div for selectvoucherCompanyErrorDiv.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	25 Aug, 2017 	shravans
 * **************************************************************************
 */
 $(document).on('click','.multiselect', function(){
	 $("#selectvoucherCompanyErrorDiv").text("");
 });
 
 /***************************************************************************
  * Purpose 			: This is used on click of submit button on voucher company list.
  * History Header 		: Version 		Date 			Developer Name
  * Added By 			: 1.0 		 	25 Aug, 2017 	shravans
  * **************************************************************************
  */
 function submitSelectedVoucherCompanies(){
	var voucherCompanyIds = $('#selectvoucherCompanyDD').val();
	if(!voucherCompanyIds){
		$("#selectvoucherCompanyErrorDiv").text("Please select voucher company");
		$("#selectvoucherCompanyErrorDiv").show();
	}else{
		var checked = $("#voucherSwitch").prop("checked");
		applyForVoucherAccount(checked, voucherCompanyIds);
	}
 }
 
 /***************************************************************************
  * Name 				: applyForVoucherAccount 
  * Return type 		: None 
  * Input Parameter(s) 	: None
  * Purpose 			: This method is used to apply for voucher account.
  * History Header 		: Version 		Date 			Developer Name
  * Added By 			: 1.0 		 	24 Aug, 2017 	shravans
  * **************************************************************************
  */
 function applyForVoucherAccount(checked, voucherCompanyIds) {
	 displayProgressBarPopup();
		$.ajax({
	        type : "post",
	        url :  document.URL.substring(0, document.URL.lastIndexOf("/")) + "/applyfor/voucheracctype",
	        datatype : "json",
//	        async : false,
	        data : "checked=" + checked + "&voucherCompanyIds=" + voucherCompanyIds,
	        success : function(resData) {
	            if(resData){
	            	if (resData.STATUS === "success" ) {
	            		$("#voucherCompanyListDivId").modal("hide");
	            		if(checked){
	            			showSuccessPopup("Successfully applied for Voucher merchant account");	
	            		}else{
	            			showSuccessPopup("Successfully applied for removal of Voucher merchant account");
	            		}
	            		$("#sideMenuAppliedVchrCompLiId").show();
	            		$("input#voucherSwitch").prop('disabled', true);
            			
	            	} else if(resData.STATUS === "failure" ){
	            		showWarningPopup(resData.REASON);
	            		if(checked === true){
	            			$("#voucherSwitch").prop('checked', false);
	            		}else{
	            			$("#voucherSwitch").prop('checked', true);
	            		}
	            	}
	            	hideProgressBarPopup();
	            }else{
	            	showWarningPopup("Some Error occured");
	            	if(checked === true){
	            		$("#voucherSwitch").prop('checked', false);
	        		}else{
	        			$("#voucherSwitch").prop('checked', true);
	        		}
	        		hideProgressBarPopup();
	            }
	            $("#voucherCompanyListDivId").modal("hide");
	        },
	        error : function(e) {
	            ajaxErrorOccur();
	        }
	    });
 }
 
 /***************************************************************************
  * Purpose 			: This is used to change the value of voucher switch.
  * History Header 		: Version 		Date 			Developer Name
  * Added By 			: 1.0 		 	25 Aug, 2017 	shravans
  * **************************************************************************
  */
 $(document).on('click', '#voucherCompanyListCloseBtn', function() {
	var checked = $("#voucherSwitch").prop("checked");
	if (checked === true) {
		$("#voucherSwitch").prop('checked', false);
	} else {
		$("#voucherSwitch").prop('checked', true);
	}
});
 
 /***************************************************************************
  * Purpose 			: This is used to change the value of voucher switch.
  * History Header 		: Version 		Date 			Developer Name
  * Added By 			: 1.0 		 	29 Aug, 2017 	shravans
  * **************************************************************************
  */
 $(document).on('click', '#voucherCompanyListTopCloseBtn', function() {
	var checked = $("#voucherSwitch").prop("checked");
	if (checked === true) {
		$("#voucherSwitch").prop('checked', false);
	} else {
		$("#voucherSwitch").prop('checked', true);
	}
});
 
 
 /*******************************************************************************
  * Name 				: loadVoucherComDataTable 
  * Return type 		: None 
  * Input Parameter(s) 	: data
  * Purpose 			: This method is used to load Voucher Company Data Table. 
  * History Header 		: Version 		Date 			Developer Name
  * Added By 			: 1.0 		 	24 Aug, 2017 	shravans
  * **************************************************************************
  */
  function loadVoucherComDataTable(data) {
 	if (!data) {
 		data = "[]";
 		data = JSON.parse(data);
 	}
 	var CompanyListDataTable = $('#voucherCompanyListTableId').DataTable({
 		"bDestroy" : true,
 		"scrollX" : true,
 		data : data,
 		'columnDefs' : [ {
 			'targets' : 0,
 			'className' : 'dt-head-center dt-body-center',
 			'checkboxes' : {
 				'selectRow' : true
 			}
 		} ],
 		'select' : {
 			'style' : 'multi'
 		},
 		"order" : [],
 		"bSort" : false,
 		"columns" : [ {
 			"data" : "id"
 		}, {
 			"data" : "companyName"
 		}, {
 			"data" : "address"
 		}, {
 			"data" : "cityName"
 		}, {
 			"data" : "countryName"
 		} ]

 	});
 }
  
  /***************************************************************************
   * Name 					: applyForRemoveorAddVoucherCompany 
   * Return type 			: None 
   * Input Parameter(s) 	: elementObj
   * Purpose 				: This method is used to show pop up to remove voucher company from merchant.
   * History Header 		: Version 		Date 			Developer Name
   * Added By 				: 1.0 		 	31 Aug, 2017 	shravans
   * **************************************************************************
   */
function applyForRemoveorAddVoucherCompany(elementObj) {
	$("#voucherCompanySwitchId").val($(elementObj).attr('id'))
	var checked = $(elementObj).prop("checked");
	if (checked === true) {
		var voucherCompanyId = $(elementObj).attr('voucherCompId');
		applyToAddVoucherCompany(voucherCompanyId);
	} else {
		$("#removedVoucherCompId").val($(elementObj).attr('voucherCompId'))
		$('#popupRemoveVoucherCompanyDivId').show();
	}
	
}

/***************************************************************************
 * Name 				: applyToAddVoucherCompany 
 * Return type 			: None 
 * Input Parameter(s) 	: voucherCompanyId
 * Purpose 				: This method is used to apply to add voucher company.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	31 Aug, 2017 	shravans
 * **************************************************************************
 */
function applyToAddVoucherCompany(voucherCompanyId){

	if(voucherCompanyId){
			var request = {};
			request.voucherCompanyId = voucherCompanyId;
			makeJsonAjaxCall({
				url :  document.URL.substring(0, document.URL.lastIndexOf("/")) + "/apply/toget/vouchercompany",
				requestObj : request,
				onSuccess : function(req, status, res) {
					try {
						if (res.status) {
							var voucherCompanyStatusTdId = 'voucherCompanyStatusTd' + $("#voucherCompanySwitchId").val().substr(3);
							$("#" + voucherCompanyStatusTdId).html('Pending');
						}
					} catch (e) {
						console.log("Error " + e.stack);
					}
				},
				onError : function(req, status, error) {
					try {
						console.log("Error " + req);
					} catch (e) {
						console.log("Error " + e.stack);
					}
				}
			});
	}

}

/***************************************************************************
 * Name 				: removeSelectedVoucherComp 
 * Return type 			: None 
 * Input Parameter(s) 	: elementObj, url
 * Purpose 				: This method is used to remove voucher company from merchant.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	31 Aug, 2017 	shravans
 * **************************************************************************
 */
function removeSelectedVoucherComp(elementObj, actionUrl){
	var voucherCompanyId = $("#removedVoucherCompId").val();
	if(voucherCompanyId){
			var request = {};
			request.voucherCompanyId = voucherCompanyId;
			makeJsonAjaxCall({
				url : actionUrl,
				requestObj : request,
				onSuccess : function(req, status, res) {
					try {
						if (res.status) {
							var voucherCompanyStatusTdId = 'voucherCompanyStatusTd' + $("#voucherCompanySwitchId").val().substr(3);
							$("#" + voucherCompanyStatusTdId).html('Removed');
							$(elementObj).closest('.pop_up').hide();
						}
					} catch (e) {
						console.log("Error " + e.stack);
					}
				},
				onError : function(req, status, error) {
					try {
						console.log("Error " + req);
					} catch (e) {
						console.log("Error " + e.stack);
					}
				}
			});
	}
}

/***************************************************************************
 * Name 				: hidePopupRemoveVoucherCompany 
 * Return type 			: None 
 * Input Parameter(s) 	: objId
 * Purpose 				: This method is used to hide popup RemoveVoucherCompany .
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	31 Aug, 2017 	shravans
 * **************************************************************************
 */
function hidePopupRemoveVoucherCompany(objId) {
	var voucherSwitchId = $("#voucherCompanySwitchId").val();
	var checked = $("#" + voucherSwitchId).prop("checked");
	if (checked === true) {
		$("#" + voucherSwitchId).prop('checked', false);
	} else {
		$("#" + voucherSwitchId).prop('checked', true);
	}
	
	$(objId).closest('.pop_up').hide();
}


/***************************************************************************
 * Name 				: loadDropDownToAddVoucherComp
 * Return type 			: None 
 * Input Parameter(s) 	: None
 * Purpose 				: This method is used to load Voucher Company Drop Down.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	04 Sep, 2017 	shravans
 * **************************************************************************
 */
function loadDropDownToAddVoucherComp(actionUrl) {
	var request = {};
	makeJsonAjaxCall({
		url : actionUrl,
		requestObj : request,
		onSuccess : function(req, status, res) {
			try {
				if(res.status){
					if(res.data && res.data.length > 0){
						for(var index = 0 ; index < res.data.length ; index++ ){
							$("select#selectvoucherCompanyDDtoAdd").append(
									$("<option>").val(res.data[index].id).html(res.data[index].companyName));
						}
						$('select#selectvoucherCompanyDDtoAdd').multiselect({
					        includeSelectAllOption : true
					    });
						$("#selectvoucherCompanytoAddErrorDiv").text("");
						$("#addVoucherCompanyListDivId").modal("show");
					}else{
						showWarningPopup("There are no companies available to add");
					}
					
				}
			} catch (e) {
				console.log("Error " + e.stack);
			}
		},
		onError : function(req, status, error) {
			try {
				console.log("Error " + req);
			} catch (e) {
				console.log("Error " + e.stack);
			}
		}
	});
}

/***************************************************************************
 * Name 				: addSelectedVoucherCompanies
 * Return type 			: None 
 * Input Parameter(s) 	: None
 * Purpose 				: This is used on click of submit button on voucher company list.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	25 Aug, 2017 	shravans
 * **************************************************************************
 */
function addSelectedVoucherCompanies(actionUrl){
	var voucherCompanyIds = $('#selectvoucherCompanyDDtoAdd').val();
	if(!voucherCompanyIds){
		$("#selectvoucherCompanytoAddErrorDiv").text("Please select voucher company");
		$("#selectvoucherCompanytoAddErrorDiv").show();
	}else{
		addVoucherCompaniesToMerchant(voucherCompanyIds, actionUrl);
	}
}

/***************************************************************************
 * Name 				: addVoucherCompaniesToMerchant 
 * Return type 			: None 
 * Input Parameter(s) 	: None
 * Purpose 				: This method is used to apply for voucher account.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	04 Sep, 2017 	shravans
 * **************************************************************************
 */
function addVoucherCompaniesToMerchant(voucherCompanyIds, actionUrl) {
	 
	var request = {};
	request.voucherCompanyIds = voucherCompanyIds;
	displayProgressBarPopup();
	makeJsonAjaxCall({
		url : actionUrl,
		requestObj : request,
		onSuccess : function(req, status, res) {
			try {
				if(res.status){
					window.location = document.URL.substring(0, document.URL.lastIndexOf("/")) + "/vouchercompanystatus";
				}
			} catch (e) {
				console.log("Error " + e.stack);
			}
			hideProgressBarPopup();
		},
		onError : function(req, status, error) {
			try {
				console.log("Error " + req);
			} catch (e) {
				console.log("Error " + e.stack);
			}
			hideProgressBarPopup();
		}
	});
	
}


