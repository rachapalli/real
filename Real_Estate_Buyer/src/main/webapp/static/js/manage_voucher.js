var vcDocsCounter = 0;
var datatable;
var fileExistCheckstatus;
$(document).ready(function() {
	loadVoucherDetails();
	setformatValidation();
});

/***************************************************************************
 * Name 				: loadVoucherDetails 
 * Return type 			: None 
 * Input Parameter(s) 	: None
 * Purpose 				: This is used to load Voucher details.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	18 July, 2017 	shravans
 * **************************************************************************
 */
function loadVoucherDetails() {
	callForPGDataTable("voucherMainDataTableId", "", "fetch/VoucherData");
};

/***************************************************************************
 * Name 				: loadVoucherPanelBody 
 * Return type 			: None 
 * Input Parameter(s) 	: actionUrl
 * Purpose 				: This is used to load response data on screen body..
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	19 July, 2017 	shravans
 * **************************************************************************
 */
function loadVoucherPanelBody(actionUrl) {
	try {
		$("#voucherPanelBody").load(actionUrl, function(response, status, xhr) {
			if (status === "error") {
				$("#voucherPanelBody").html(response);
			}
			registerForNumericDecimal();
			loadDatePicker();
		});
	} catch (err) {
		console.log("Log" + err + "\nError" + err.printStackTrace);
	}
}

/***************************************************************************
 * Name 				: validateVoucherNextButton 
 * Return type 			: flag 
 * Input Parameter(s) 	: registrationDivId
 * Purpose 				: This is used to validate form on click of voucher NextBtn.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	19 July, 2017 	shravans
 * **************************************************************************
 */
function validateVoucherNextButton(registrationDivId) {
	var flag = true;
	$("#" + registrationDivId + " " + "input").each(function() {

		var attributeId = $(this).attr('id');
		var fieldValue = $(this).val();
		var valueLength = 0;
		if (fieldValue) {
			fieldValue = $(this).val().trim();
			valueLength = fieldValue.length;
		}
		var minLength = $(this).attr('min');
		var message = "";
		if (($(this).hasClass('required') && valueLength != 0)
				&& (valueLength < minLength)) {
			message = "Must be at least " + minLength
					+ " character long.";
			+".";
			flag = false;
		} else if (($(this).hasClass('required') && !fieldValue)) {
			message = "This field should not be blank";
			flag = false;
		} else if ((valueLength >= minLength)
				&& $(this).hasClass('email')) {
			var email = $(this).val();
			var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
			if (!regex.test(email)) {
				message = "Please enter a valid email id";
				flag = false;
			}
		} else if ($(this).hasClass('email')
				&& valueLength <= 0) {
			message = "Please enter a valid email id";
			flag = false;
		} else if (((valueLength >= 1) && (valueLength <= 15))
				&& $(this).hasClass('phone')) {
			var phone = $(this).val();
			var regex = /\(?([0-9]{3})\)?([ .-]?)([0-9]{3})\2([0-9]{4})/;
			if (!regex.test(phone)) {
				message = "Please enter a 10 digit contact number";
				flag = false;
			}
		}
		if (flag === false) {
			var errorDivId = 'errorDiv' + attributeId;
			$("#" + errorDivId).text(message).addClass(
					"display_block");
		}
	});
	$("#" + registrationDivId + " " + "select").each(function() {
		var attributeId = $(this).attr('id');
		var fieldValue = $(this).find('option:selected').val();
		var message = "";
		if (($(this).hasClass('required') && !fieldValue)) {
			message = "This field should not be blank";
			flag = false;
		}
		if (flag === false) {
			var errorDivId = 'errorDiv' + attributeId;
			$("#" + errorDivId).text(message).addClass("display_block");
		}
	});

	if (registrationDivId == "voucherMainInfoInnerDivId" && flag == true) {
		$("#liVoucherMainInfo").removeClass("active");
		$("#livoucherDocumentsInfo").addClass("active");

		$(".tab-content").find("#voucherMainInfoDivId").closest("div").removeClass("in active");
		$(".tab-content").find("#voucherDocumentInfoDivId").closest("div").addClass("in active");
	} else {
		return flag;
	}
}





/***************************************************************************
 * Name 				: editVoucherData 
 * Return type 			: None 
 * Input Parameter(s) 	: objMerchant,actionUrl
 * Purpose 				: This method is used to edit voucher.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	20 Jul, 2017 	shravans
 * **************************************************************************
 */
function editVoucherData(voucherObj){
	var voucherId = $(voucherObj).attr('voucherId');
	if(voucherId){
		loadPanelById(document.URL.substring(0, document.URL.lastIndexOf("/"))
				+ "/editvoucher", voucherId, "voucherPanelBody");
		registerForNumericDecimal();
		loadDatePicker();
//		callToVoucherDocs();
	}
}

/***************************************************************************
 * Name 				: loadPanelById 
 * Return type 			: None 
 * Input Parameter(s) 	: actionUrl, objId, panelId
 * Purpose 				: This method is used for load edit page using object id..
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	20 Jul, 2017 	shravans
 * **************************************************************************
 */
function loadPanelById(actionUrl, objId, panelId) {
	try {
		$("#" + panelId).load(actionUrl + "?objId=" + objId, function(response, status, xhr) {
			if (status === "error") {
				$("#" + panelId).html(response);
			}
		});
		
	} catch (err) {
		console.log("Log" + err + "\nError" + err.printStackTrace);
	}
}


/***************************************************************************
 * Name 				: validateVoucherBackButton 
 * Return type 		: None 
 * Input Parameter(s) 	: registrationDivId
 * Purpose 			: This method is used to validate voucher back button.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	24 Jul, 2017 	shravans
 * **************************************************************************
 */
function validateVoucherBackButton(registrationDivId){
	 if (registrationDivId == "documentInformationDivId") {
		$("#livoucherDocumentsInfo").removeClass("active");
		$("#liVoucherMainInfo").addClass("active");
		$(".tab-content").find("#voucherDocumentInfoDivId").closest("div")
				.removeClass("in active");
		$(".tab-content").find("#voucherMainInfoDivId").closest("div").addClass("in active");
	}
}

/***************************************************************************
 * Name 				: serchVoucherData 
 * Return type 			: None 
 * Input Parameter(s) 	: None
 * Purpose 				: This method is used to load voucher details on basis of search
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	20 Jul, 2017 	shravans
 * **************************************************************************
 */
function serchVoucherData(){
	var formData = $('form#' + "voucherSearchFormId").serialize();
	callForPGDataTable("voucherMainDataTableId", formData, "fetch/VoucherData");
}


/***************************************************************************
 * Name 				: openRemoveVoucherPopup 
 * Return type 			: None 
 * Input Parameter(s) 	: object
 * Purpose 				: This method is used to open remove pop up for voucher.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	21 Jul, 2017 	shravans
 * **************************************************************************
 */
function openRemoveVoucherPopup(object){
	var voucherId = $(object).attr('voucherId');
	$("#voucherRemoveId").val(voucherId);
	$("#popupRemoveVoucherDivId").show();
}

/***************************************************************************
 * Name 				: deleteVoucherData 
 * Return type 			: None 
 * Input Parameter(s) 	: objMerchant,actionUrl
 * Purpose 				: This method is used to delete voucher data on basis of merchant id.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	01 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function deleteVoucherData(object, actionUrl){
	var voucherId = $("#voucherRemoveId").val();
	if(voucherId){
			var request = {};
			request.entityUniqueId = voucherId;
			makeJsonAjaxCall({
				url : actionUrl,
				requestObj : request,
				onSuccess : function(req, status, res) {
					try {
						if (res.status) {
							window.location = document.URL.substring(0, document.URL.lastIndexOf("/")) + "/managevoucher";
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
 * Name 				: callToVoucherDocs 
 * Return type 			: None 
 * Input Parameter(s) 	: None
 * Purpose 				: This method is used to make ajax call to get voucher docs data.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	24 Jul, 2017 	shravans
 * **************************************************************************
 */
function callToVoucherDocs(){
	if($("#voucherId").val() != 0){
    	$.ajax({
            type : "get",
            url :  document.URL.substring(0, document.URL.lastIndexOf("/")) + "/fetch/voucherdocuments",
            datatype : "json",
            async : false,
            data : "voucherId=" + $("#voucherId").val(),
            success : function(data) {
                if(data){
                	$("#docsExist").val("1");
                	var jsonData = JSON.parse(data);
                	loadVoucherDocsTable(jsonData);
                }else{
                	$("#docsExist").val("0");
                	loadVoucherDocsTable(data);
                }
            },
            error : function(e) {
                ajaxErrorOccur();
            }
        });
    }
    
}


/***************************************************************************
 * Name 				: loadVoucherDocsTable 
 * Return type 			: None 
 * Input Parameter(s) 	: registrationDivId
 * Purpose 				: This method is used to load Voucher Documents Table.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	24 Jul, 2017 	shravans
 * **************************************************************************
 */
 function loadVoucherDocsTable(data) {
	if(!data){
	    data = "[]";
	    data = JSON.parse(data);
	}
	var dataTable = $('#voucherDocumentDataTableId').DataTable({
	"bDestroy" : true,
	"scrollX" : true,
	data : data,
	"order": [],
	"bSort": true,
	"columns" : [
	       {
	           "data" : function(data, type, row, meta) {
	        	var actionDiv = "<div class='text_center views-section'><i class='fa fa-eye' aria-hidden='true' docId ='"
				+ data.id
				+ "' onclick='viewVoucherDocuments(this)'> </i>   <i class='fa fa-trash' aria-hidden='true' docId ='"
				+ data.id + "' onclick='openDeleteVoucherDocumentPopup(this)'></i></div>";
	        	return actionDiv;
	           }
	       },
	       {
	    	   "data" : "createDate"
	       },
	       {
	    	   "data" : "documentTypeName"
	       },
	       {
	    	   "data" : "documentName"
	       },
	       {
	    	   "data" : function(data, type, row, meta) {
	    		   if(data.userName){
	    			   return data.userName;
	    		   }else{
	    			   return "";
	    		   }
	    	   }
           }
       ]
	
	});
}
 
 /***************************************************************************
  * Name 				: viewVoucherDocuments 
  * Return type 		: None 
  * Input Parameter(s) 	: objIcon
  * Purpose 			: This method is used to view voucher doc after save.
  * History Header 		: Version 		Date 			Developer Name
  * Added By 			: 1.0 		 	24 Jul, 2017 	shravans
  * **************************************************************************
  */
 function viewVoucherDocuments(objIcon){
 	var id = $(objIcon).attr('docId');
 	window.open(document.URL.substring(0, document.URL.lastIndexOf("/")) + "/view/voucherdocument" + "?id=" + id);
 }
 
 /***************************************************************************
  * Name 				: openDeleteVoucherDocumentPopup 
  * Return type 		: None 
  * Input Parameter(s) 	: objIcon
  * Purpose 			: This method is used to open remove pop up.
  * History Header 		: Version 		Date 			Developer Name
  * Added By 			: 1.0 		 	24 Jul, 2017 	shravans
  * **************************************************************************
  */
 function openDeleteVoucherDocumentPopup(objIcon){
 	$('#popupRemoveVoucherDocDivId').show();
 	$("#voucherDocId").val($(objIcon).attr('docId'));
 }
 
 /***************************************************************************
  * Name 				: deleteVoucherDocument 
  * Return type 		: None 
  * Input Parameter(s) 	: objBtn,actionUrl
  * Purpose 			: This method is used to delete merchant documents.
  * History Header 		: Version 		Date 			Developer Name
  * Added By 			: 1.0 		 	24 Jul, 2017 	shravans
  * **************************************************************************
  */
 function deleteVoucherDocument(objBtn,actionUrl){
 	if($("#voucherDocId").val()){
 			var request = {};
 			request.documentId = $("#voucherDocId").val();
 			makeJsonAjaxCall({
 				url : actionUrl,
 				requestObj : request,
 				onSuccess : function(req, status, res) {
 					try {
 						if (res.status) {
 							$(objBtn).closest('.pop_up').hide();
 							//docTable.row('.selected').remove().draw(false);
 							callToVoucherDocs();
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
  * Name 				: validateVoucherFormSubmit 
  * Return type 		: None 
  * Input Parameter(s) 	: formId ,objForm
  * Purpose 			: This is used to validate voucher form on submit button.
  * History Header 		: Version 		Date 			Developer Name
  * Added By 			: 1.0 		 	25 Jul, 2017 	shravans
  * **************************************************************************
  */
 function validateVoucherFormSubmit(formId, objForm) {
	 var isDocsExist = $("#docsExist").val();
	 var flag = true;
	 var formCheck = validateFormSubmit(formId, objForm);
	 if(isDocsExist === "0"){
		 $("#" + formId + " " + ".docTypeClass").each(function() {
			 if(validateVoucherDocuments($(this))){
				 flag = false;
			 }
		 });
		 
		 $("#" + formId + " " + ".fileClass").each(function() {
			 if(validateVoucherDocuments($(this))){
				 flag = false;
			 }
		 });
	 }
	 
	if(formCheck == false || flag == false){
		return false;
	}else{
		displayProgressBarPopup();
		return true;
	}
}
 
 /***************************************************************************
  * Name 				: validateVoucherDocuments 
  * Return type 		: boolean 
  * Input Parameter(s) 	: object
  * Purpose 			: This is used to validate voucher documents on submit button.
  * History Header 		: Version 		Date 			Developer Name
  * Added By 			: 1.0 		 	25 Jul, 2017 	shravans
  * **************************************************************************
  */ 
function validateVoucherDocuments(object) {
	var docTypeId = $(object).attr("id");
	var fileType = $(object).val();
	if (!fileType) {
		$("#errorDiv" + docTypeId).text("This field should not be blank")
				.removeClass("display_none ");
		$("#errorDiv" + docTypeId).addClass("display_block")
		return true;
	} else {
		return false;
	}
}

 /***************************************************************************
  * Name 				: addVoucherDocumentSection 
  * Return type 		: None 
  * Input Parameter(s) 	: innerDivId,outerDivId
  * Purpose 			: This method is used to clone add voucher document section.
  * History Header 		: Version 		Date 			Developer Name
  * Added By 			: 1.0 		 	25 Jul, 2017 	shravans
  * **************************************************************************
  */
 function addVoucherDocumentSection(innerDivId,outerDivId){
	 vcDocsCounter = vcDocsCounter + 1; 
     var divClone = $("#"+innerDivId).clone();
     divClone.find("#vcDocumentTypeId").val("");
     divClone.find("#vcRequiredDocumentId").val("");

     divClone.find(".docTypeClass").attr("id", "vcDocumentTypeId" + vcDocsCounter);
     divClone.find(".docTypeClass").attr("name", "documentBeans[" + vcDocsCounter +"].documentTypeId");
     
     divClone.find(".fileClass").attr("id", "vcRequiredDocumentId" + vcDocsCounter);
     divClone.find(".fileClass").attr("name", "documentBeans[" + vcDocsCounter +"].document");
     
     divClone.find(".docTypeErrorDiv").attr("id", "errorDivvcDocumentTypeId" + vcDocsCounter);
     divClone.find(".docFileErrorDiv").attr("id", "errorDivvcRequiredDocumentId" + vcDocsCounter);
     
     $("#"+outerDivId).append(divClone);
 }
 
 
 
 /***************************************************************************
  * Name 				: viewVoucherPdf 
  * Return type 		: None 
  * Input Parameter(s) 	: object
  * Purpose 			: This method is used to view voucher pdf on click of pdf btn.
  * History Header 		: Version 		Date 			Developer Name
  * Added By 			: 1.0 		 	09 Aug, 2017 	shravans
  * **************************************************************************
  */
 function viewVoucherPdf(object){
 	if($(object).attr('voucherId')){
 		var voucherId = $(object).attr('voucherId');
 		var voucherName =  $(object).attr('voucherName');
 		checkFileExistForVoucher(voucherId);
 		if (fileExistCheckstatus) {
 	        fileViewPopup(voucherId, voucherName);
 	    }
 	}
 	
 }
 
 /***************************************************************************
  * Name 				: checkFileExistForVoucher 
  * Return type 		: None 
  * Input Parameter(s) 	: voucherId
  * Purpose 			: This method is used to check whether file exists or not.
  * History Header 		: Version 		Date 			Developer Name
  * Added By 			: 1.0 		 	09 Aug, 2017 	shravans
  * **************************************************************************
  */
 function checkFileExistForVoucher(voucherId) {

	$.ajax({
		type : "post",
		url : document.URL.substring(0, document.URL.lastIndexOf("/"))
				+ "/fileexistcheck/forvoucher",
		datatype : "json",
		async : false,
		data : "voucherId=" + voucherId,
		success : function(response) {
			fileExistCheckstatus = response;
		},
		error : function(e) {
			ajaxErrorOccur();
		}
	});

	if (!fileExistCheckstatus) {
		BootstrapDialog.show({
			title : 'Information',
			type : BootstrapDialog.TYPE_DANGER,
			message : "Unable to access  requested  file, file is not available in the disk"
		});
		event.preventDefault();
	}
}
 
 /***************************************************************************
  * Name 				: fileViewPopup 
  * Return type 		: None 
  * Input Parameter(s) 	: voucherId
  * Purpose 			: This method is used to show voucher pdf file.
  * History Header 		: Version 		Date 			Developer Name
  * Added By 			: 1.0 		 	09 Aug, 2017 	shravans
  * **************************************************************************
  */
 function fileViewPopup(voucherId, voucherName) {

	var urld = "get/filepopup/forvoucher?voucherId=" + voucherId;

	BootstrapDialog.show({
		title : voucherName,
		modal : true,
		margin : 0,
		padding : 0,
		size : BootstrapDialog.SIZE_WIDE,
		type : BootstrapDialog.TYPE_SUCCESS,
		message : function(dialog) {
			var object = "<center><object data='{FileName}' type='application/pdf'  overflow='auto' width='850px' height='700px'>";
			object += "</object></center>";
			object = object.replace(/{FileName}/g, urld);
			return object;

		}
	});
}