var counter = 0;
var documentId = null;
var roleId = null;
var voucherCompanyId = null;
var merchantType = null;
var emailExistFlag = null;
$(document).ready(function() {
	counter = 0;
	setHeight();
	$(window).resize(function() {
		setHeight();
	});
	
	/*date picker js start*/
	
	$(".datetimepicker" ).datetimepicker({
		format: 'm/d/Y',
		timepicker: false,
		yearEnd: 2115,
		closeOnDateSelect: true,
		scrollMonth : false,
		scrollInput : false,
		minDate: '0', /* To disable the date before current date */
		onSelectDate:function(dateText, inputField) {}
	});
	
	$(".datepickerwithbackdatesonly" ).datetimepicker({
		format: 'm/d/Y',
		timepicker: false,
		yearEnd: 2115,
		closeOnDateSelect: true,
		scrollMonth : false,
		scrollInput : false,
		maxDate: 0, /* To disable the date after current date */
		onSelectDate:function(dateText, inputField) {}
	});
	
	$(".datepickerwithalldate" ).datetimepicker({
		format: 'm/d/Y',
		timepicker: false,
		yearEnd: 2115,
		closeOnDateSelect: true,
		scrollMonth : false,
		scrollInput : false,
		onSelectDate:function(dateText, inputField) {}
	});
	
	registerForNumericDecimal();
	loadFunctions();
	//loadMerchantDetails();
	//loadMerchantverificationDetails();
	//loadVoucherCompanyDetails();
	loadAccountverificationDetails();
	loadDatePicker();
	clearData();
	//disableRegistrationDiv();
	//loadDocumentsTable();
	//loadRoleTable();
	viewUserAccounts();
	 $(".phnMask").mask("+1(999)-999-9999", {
	        placeholder : "+1(###)-###-####"
   });
	// loadEmployeeDataTable();
	 /*active class for menubar.....................................................................*/	 
	 var href = $("#menu").find('a');
		
		for(var i = 0; i<href.length; i++){
			if(href[i].href == window.location.href ){
				$(href).parent().removeClass('active');
				$(href[i]).parent().addClass("active");
				/*
				if((href[i]).parent().parent():has('collapse')){
					alert(hello);
//					$(this).parent().parent().addClass('in');
				};*/
			}
			
		};
		
		/*$('.drop-down').click(function(){
			$('.collapse').removeClass('in');
		});*/

});

$(document).on('click','.side-menubar-collapse', function(){
	$('#wrapper').toggleClass('side-menu-hide');
	$('#sidebar-wrapper').toggleClass('side-menu-show');
 });

/***************************************************************************
 * Name 				: setHeight 
 * Return type 			: None 
 * Input Parameter(s) 	: None
 * Purpose 				: This is used to window height.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	22 Jun, 2017 	Prabina Rout
 * **************************************************************************
 */
function setHeight() {
    var win_height = $(window).height()
    $("#page-content-wrapper").css("min-height",win_height-65+"px");
};

/***************************************************************************
 * Name 				: validateForm 
 * Return type 			: None 
 * Input Parameter(s) 	: None
 * Purpose 				: This is used to validate login form.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	22 Jun, 2017 	Prabina Rout
 * **************************************************************************
 */
function validateLoginForm() {
	var userName = $("#loginUserId").val(); 
	var password = $("#loginUserPassId").val();
	var msg = "";
	if(!userName && !password) {
		msg = "Please enter user name and password.";
	} else if(!userName) {
		msg = "Please enter user name.";
	}  else if(!password) {
		msg = "Please enter password.";
	}
	if(msg) {
		$("#loginCredsError").text(msg);
		$(".error").hide();
		$(".msg").hide();
		$("#loginCredsError").show();
		return false;
	} else {
		return true;
	}
}

/***************************************************************************
 * Name 				: sendTempPassword 
 * Return type 			: None 
 * Input Parameter(s) 	: actionUrl
 * Purpose 				: This is used to send temporary login password to User.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	22 Jun, 2017 	Prabina Rout
 * **************************************************************************
 */
function sendTempPassword(actionUrl) {
	var request = {};
	request.emailId = $("#forgotEmailId").val();
	makeJsonAjaxCall({
		url : actionUrl,
		requestObj : request,
		onSuccess : function(req, status, res) {
			try {
				if(res.status){
					$('#forgotEmailId').val("");
					$('#errorDivForgotpassword').text("");
					$("#forgotPasswordCloseId").click();
					showSuccessPopup("Temporary password sent sucessfully.");
				}else {
					$("#errorDivForgotpassword").addClass("errormsg_form").text("Entered email does not exit.").show();
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
 * Name 				: passwodValidation 
 * Return type 			: None 
 * Input Parameter(s) 	: None
 * Purpose 				: This is used to validate reset password form.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	22 Jun, 2017 	Prabina Rout
 * **************************************************************************
 */
function passwodValidation() {
	var flag = true;
	var oldpasswprd = $("#oldResetPasswordId").val();
	var newpassword = $("#newResetPasswordId").val();
	var confirmpassword = $("#confirmresetPasswordId").val();
	if (!oldpasswprd && !newpassword && !confirmpassword) {
		$("#errdorDivResetPasswordId").text("Please fill all required fields").show();
		flag = false;
	} /*else if (oldpasswprd == newpassword) {
		$("#errdorDivResetPasswordId").text("Old password and New Password cannot be same").show();
		flag = false;
	} */else if (confirmpassword != newpassword) {
		$("#errdorDivResetPasswordId").text("Password mismatch").show();
		flag = false;
	}
	return flag;
}

/***************************************************************************
 * Name 				: loadAdminPanelBody 
 * Return type 			: None 
 * Input Parameter(s) 	: actionUrl
 * Purpose 				: This is used to load response data on screen body..
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	29 Jun, 2017 	Prabina Rout
 * **************************************************************************
 */
function loadAdminPanelBody(actionUrl) {
	try {
		$("#adminPanelBody").load(actionUrl, function(response, status, xhr) {
			if (status === "error") {
				$("#adminPanelBody").html(response);
			}
			registerForNumericDecimal();
			loadDatePicker();
			clearData();
			loadFunctions();
			if($('#adminPanelBody form').prop('id') == "merchantRegistrationFormId" || $('#adminPanelBody form').prop('id') == "voucherCompanyRegFormId"){
				disableRegistrationDiv();
			}
			 $(".phnMask").mask("+1(999)-999-9999", {
			        placeholder : "+1(###)-###-####"
		     });
		});
	} catch (err) {
		console.log("Log" + err + "\nError" + err.printStackTrace);
	}
}
function loadFunctions(){
	if($('#adminPanelBody form').prop('id') == "manageEmployeeFormId"){
		loadEmployeeDataTable();
	}else if($('#adminPanelBody form').prop('id') == "merchantRegistrationFormId"){
		loadDocumentsTable();
	}else if($('#adminPanelBody form').prop('id') == "managemerchantFormId" || $('#adminPanelBody form').prop('id') == "merchantsearchFormId" ){
		loadMerchantDetails();
	}else if($('#adminPanelBody form').prop('id') == "manageRoleFormId"){
		loadRoleTable();
	}else if($('#adminPanelBody form').prop('id') == "invitationTrackingFormId" || $('#adminPanelBody form').prop('id') == "invitationTrackSearchFormId"){
		loadInvitationTrcaking($('#typeValueHiddenId').val());
	}else if($('#adminPanelBody form').prop('id') == "addEditRoleFormId"){
		$('#productGroup').multiselect({
            enableClickableOptGroups: true,
            enableCollapsibleOptGroups: true,
            includeSelectAllOption: true,
        });
	}else if($('#adminPanelBody form').prop('id') == "merchnatVerificationFormId" || $('#adminPanelBody form').prop('id') == "merchantTypeSearchFormId" || $('#adminPanelBody form').prop('id') == "merchantverificationSearchFormId"){
		loadMerchantverificationDetails();
	}else if($('#adminPanelBody form').prop('id') == "manageVoucherCompanyFormId" || $('#adminPanelBody form').prop('id') == "voucherCompanysearchFormId"){
		loadVoucherCompanyDetails();
	}
}

function loadInvitationTrcaking(typeValue){
	callForPGDataTable("trackInvitationDataTableId","type="+typeValue, "fetchinvitationtrack");
}

/***************************************************************************
* Name 					: merchantInvitation 
* Return type 			: None 
* Input Parameter(s) 	: actionUrl
* Purpose 				: This is used to send invitation to email.
* History Header 		: Version 		Date 			Developer Name
* Added By 				: 1.0 		 	29 Jun, 2017 	Satyam Gupta
* **************************************************************************
*/
function merchantInvitation(actionUrl) {
	if (validateEmail($("#invitationMailId").val()) && $("#inMerchantNotesId").val()) {
		var request = {};
		request.emailId = $("#invitationMailId").val();
		request.notes = $("#inMerchantNotesId").val();
		request.subject = $("#invitationSubjectId").val();
		request.invitationType = $("#invitationTypeId").val();
		displayProgressBarPopup();
		makeJsonAjaxCall({
			url : actionUrl,
			requestObj : request,
			onSuccess : function(req, status, res) {
				try {
					if (res.status) {
						$("#merchantEmailId").removeClass("error_link_msg");
						$("#merchantEmailId").addClass("sucess_link_msg").text("Registration Link sent sucessfully.").show().delay(10000).fadeOut(500);
						$("#invitationMailId").val("");
					} else {
						$("#merchantEmailId").addClass("error_link_msg").text("Entered email does not exit.").show();
					}
					hideProgressBarPopup();
				} catch (e) {
					hideProgressBarPopup();
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
	}else{
		$("#merchantEmailId").removeClass("sucess_link_msg");
		$("#merchantEmailId").addClass("error_link_msg").text("Please enter a valid email or fill all the fields").show();
	}
}

/***************************************************************************
* Name 					: validateEmail 
* Return type 			: None 
* Input Parameter(s) 	: email
* Purpose 				: This is used to validate email.
* History Header 		: Version 		Date 			Developer Name
* Added By 				: 1.0 		 	29 Jun, 2017 	Prabina Rout
* **************************************************************************
*/
function validateEmail(email) {
	if(email){
		var emailReg = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		if(!emailReg.test(email)) {
			return false;
		} else {
			return true;
		}
	}
}

/***************************************************************************
 * Name 				: loadDatePicker 
 * Return type 			: None 
 * Input Parameter(s) 	: None
 * Purpose 				: This is used to load Date picker functionality after page load.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	01 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function loadDatePicker(){
	$(".datetimepicker" ).datetimepicker({
		format: 'm/d/Y',
		timepicker: false,
		yearEnd: 2115,
		closeOnDateSelect: true,
		scrollMonth : false,
		scrollInput : false,
		onSelectDate:function(dateText, inputField) {}
	});
}

/***************************************************************************
 * Name 				: editMerchantData 
 * Return type 			: None 
 * Input Parameter(s) 	: objMerchant,actionUrl
 * Purpose 				: This method is used to edit merchnat registration.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	01 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function editMerchantData(objMerchant){
	if($(objMerchant).attr('id')){
		var merchnatId = $(objMerchant).attr('id').substr(2);
		loadAdminPanelBodyByID(document.URL.substring(0, document.URL.lastIndexOf("/")) + "/editregistration",merchnatId);
	}
}

/***************************************************************************
 * Name 				: loadAdminPanelBodyByID 
 * Return type 			: None 
 * Input Parameter(s) 	: actionUrl,objId
 * Purpose 				: This method is used to for load edit page using object id..
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	01 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function loadAdminPanelBodyByID(actionUrl,objId) {
	try {
		$("#adminPanelBody").load(actionUrl + "?objId=" + objId, function(response, status, xhr) {
			if (status === "error") {
				$("#adminPanelBody").html(response);
			}
			registerForNumericDecimal();
			loadDatePicker();
			clearData();
			loadFunctions();
			 $(".phnMask").mask("+1(999)-999-9999", {
			        placeholder : "+1(###)-###-####"
		     });
		});
	} catch (err) {
		console.log("Log" + err + "\nError" + err.printStackTrace);
	}
}

/***************************************************************************
 * Name 				: loadAdminPanelBodyByID 
 * Return type 			: None 
 * Input Parameter(s) 	: objMerchant,actionUrl
 * Purpose 				: This method is used to delete merchant data on basis of merchant id.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	01 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function deleteMerchantData(objBtn,actionUrl){
	var merchantId = $("#merchantRemoveId").val();
	if(merchantId){
			var request = {};
			request.merchantId = merchantId;
			makeJsonAjaxCall({
				url : actionUrl,
				requestObj : request,
				onSuccess : function(req, status, res) {
					try {
						if (res.status) {
							window.location = document.URL.substring(0, document.URL.lastIndexOf("/")) + "/managemerchant";
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
 * Name 				: validateNextButton 
 * Return type 			: boolean 
 * Input Parameter(s) 	: registrationDivId
 * Purpose 				: This method is used to validate next button.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	04 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function validateNextButton(registrationDivId) {
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
				message = "Please enter a valid email";
				flag = false;
			}
		} else if ($(this).hasClass('email')
				&& valueLength <= 0) {
			message = "Please enter a valid email";
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
	if(emailExistFlag){
		$("#errorDivrgpEmailId").text("This email already exist").addClass("display_block");
	}else{
		$("#errorDivrgpEmailId").text("").addClass("display_none");
	}
	if (registrationDivId == "personalInformationDivId" && flag && emailExistFlag == false) {
		$('#liBusinessInfoId a').prop('disabled', false);
		$('#liBusinessInfoId a').removeClass("disable_color");

		$("#liPersonalInfoId").removeClass("active");
		$("#liBusinessInfoId").addClass("active");

		$(".tab-content").find("#home").closest("div").removeClass("in active");
		$(".tab-content").find("#menu1").closest("div").addClass("in active");
	} else if (registrationDivId == "mainDivID" && flag == true) {
		$('#liFinancialInfoId a').prop('disabled', false);
		$('#liFinancialInfoId a').removeClass("disable_color");

		$("#liBusinessInfoId").removeClass("active");
		$("#liFinancialInfoId").addClass("active");

		$(".tab-content").find("#menu1").closest("div")
				.removeClass("in active");
		$(".tab-content").find("#menu2").closest("div").addClass("in active");
	} else if (registrationDivId == "financialContactDivId" && flag == true) {
		$('#liBankInfoId a').prop('disabled', false);
		$('#liBankInfoId a').removeClass("disable_color");
		$("#liFinancialInfoId").removeClass("active");
		$("#liBankInfoId").addClass("active");

		$(".tab-content").find("#menu2").closest("div")
				.removeClass("in active");
		$(".tab-content").find("#menu3").closest("div").addClass("in active");
	} else if (registrationDivId == "bankInformationDivId" && flag == true) {
		$('#liReasonInfoId a').prop('disabled', false);
		$('#liReasonInfoId a').removeClass("disable_color");

		$("#liBankInfoId").removeClass("active");
		$("#liReasonInfoId").addClass("active");
		$(".tab-content").find("#menu3").closest("div")
				.removeClass("in active");
		$(".tab-content").find("#menu4").closest("div").addClass("in active");
	} else if (registrationDivId == "documentInformationDivId"
			&& flag == true) {
		$("#liDocumentInfoId").removeClass("active");
		$(".tab-content").find("#menu5").closest("div").removeClass("in active");
		if(merchantType == 'Voucher'){
			$('#liStCompanyInfoId a').prop('disabled', false);
			$('#liStCompanyInfoId a').removeClass("disable_color");
			
			$("#liStCompanyInfoId").addClass("active");
			$(".tab-content").find("#menu7").closest("div").addClass("in active");
		}else{
			$('#liStatusInfoId a').prop('disabled', false);
			$('#liStatusInfoId a').removeClass("disable_color");
			
			$("#liStatusInfoId").addClass("active");
			$(".tab-content").find("#menu6").closest("div").addClass("in active");
		}
		
	}else if (registrationDivId == "processingInformationDivId"
		&& flag == true) {
	$('#liDocumentInfoId a').prop('disabled', false);
	$('#liDocumentInfoId a').removeClass("disable_color");

	$("#liReasonInfoId").removeClass("active");
	$("#liDocumentInfoId").addClass("active");
	$(".tab-content").find("#menu4").closest("div")
			.removeClass("in active");
	$(".tab-content").find("#menu5").closest("div").addClass("in active");
	}else if (registrationDivId == "voucherCompanyDivId" && flag == true) {
		$('#liStatusInfoId a').prop('disabled', false);
		$('#liStatusInfoId a').removeClass("disable_color");
		$("#liStCompanyInfoId").removeClass("active");
		$("#liStatusInfoId").addClass("active");
		$(".tab-content").find("#menu7").closest("div").removeClass("in active");
		$(".tab-content").find("#menu6").closest("div").addClass("in active");
	}else if (registrationDivId == "passportInformationDivId" && flag == true) {
		$('#liIDInfoId a').prop('disabled', false);
		$('#liIDInfoId a').removeClass("disable_color");
		$("#liPassportInfoId").removeClass("active");
		$("#liIDInfoId").addClass("active");

		$(".tab-content").find("#accountPassportSection").closest("div")
				.removeClass("in active");
		$(".tab-content").find("#accountIdSection").closest("div").addClass("in active");
	}else if (registrationDivId == "idInformationDivID" && flag == true) {
		$('#liAddressInfoId a').prop('disabled', false);
		$('#liAddressInfoId a').removeClass("disable_color");
		$("#liIDInfoId").removeClass("active");
		$("#liAddressInfoId").addClass("active");

		$(".tab-content").find("#accountIdSection").closest("div")
				.removeClass("in active");
		$(".tab-content").find("#accountAddressSection").closest("div").addClass("in active");
	}else {
		return flag;
	}
}

/***************************************************************************
 * Name 				: validateBackButton 
 * Return type 			: None 
 * Input Parameter(s) 	: registrationDivId
 * Purpose 				: This method is used to validate back button.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	04 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function validateBackButton(registrationDivId){
	if(registrationDivId == "mainDivID"){
		$("#liBusinessInfoId").removeClass("active");
	    $("#liPersonalInfoId").addClass("active");
	    $(".tab-content").find("#menu1").closest("div").removeClass("in active");
	    $(".tab-content").find("#home").closest("div").addClass("in active");
	}else if(registrationDivId == "financialContactDivId"){
		$("#liFinancialInfoId").removeClass("active");
	    $("#liBusinessInfoId").addClass("active");
	    $(".tab-content").find("#menu2").closest("div").removeClass("in active");
	    $(".tab-content").find("#menu1").closest("div").addClass("in active");
	}else if(registrationDivId == "bankInformationDivId"){
		$("#liBankInfoId").removeClass("active");
	    $("#liFinancialInfoId").addClass("active");
	    $(".tab-content").find("#menu3").closest("div").removeClass("in active");
	    $(".tab-content").find("#menu2").closest("div").addClass("in active");
	}else if(registrationDivId == "processingInformationDivId"){
		$("#liReasonInfoId").removeClass("active");
	    $("#liBankInfoId").addClass("active");
	    $(".tab-content").find("#menu4").closest("div").removeClass("in active");
	    $(".tab-content").find("#menu3").closest("div").addClass("in active");
	}else if(registrationDivId == "documentInformationDivId"){
		$("#liDocumentInfoId").removeClass("active");
	    $("#liReasonInfoId").addClass("active");
		$(".tab-content").find("#menu5").closest("div").removeClass("in active");
		$(".tab-content").find("#menu4").closest("div").addClass("in active");
	}else if(registrationDivId == "voucherCompanyDivId"){
		$("#liStCompanyInfoId").removeClass("active");
	    $("#liDocumentInfoId").addClass("active");
		$(".tab-content").find("#menu7").closest("div").removeClass("in active");
		$(".tab-content").find("#menu5").closest("div").addClass("in active");
	}else if(registrationDivId == "statusDivId"){
		$(".tab-content").find("#menu6").closest("div").removeClass("in active");
		if(merchantType == 'Voucher'){
			$("#liStatusInfoId").removeClass("active");
		    $("#liStCompanyInfoId").addClass("active");
			$(".tab-content").find("#menu7").closest("div").addClass("in active");
		}else{
			$("#liStatusInfoId").removeClass("active");
		    $("#liDocumentInfoId").addClass("active");
			$(".tab-content").find("#menu5").closest("div").addClass("in active");
		}
	}else if(registrationDivId == "idInformationDivID"){
		$("#liIDInfoId").removeClass("active");
	    $("#liPassportInfoId").addClass("active");
	    $(".tab-content").find("#accountIdSection").closest("div").removeClass("in active");
	    $(".tab-content").find("#accountPassportSection").closest("div").addClass("in active");
	}else if(registrationDivId == "addressDivId"){
		$("#liAddressInfoId").removeClass("active");
	    $("#liIDInfoId").addClass("active");
	    $(".tab-content").find("#accountAddressSection").closest("div").removeClass("in active");
	    $(".tab-content").find("#accountIdSection").closest("div").addClass("in active");
	}
}

/***************************************************************************
 * Name 				: disableRegistrationDiv 
 * Return type 			: None 
 * Input Parameter(s) 	: None
 * Purpose 				: This method is used to disable registration tab.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	04 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function disableRegistrationDiv(){
	$('#liBusinessInfoId a').prop('disabled', true); 
	$('#liBusinessInfoId a').addClass("disable_color"); 
	
	$('#liFinancialInfoId a').prop('disabled', true); 
	$('#liFinancialInfoId a').addClass("disable_color"); 
	
	$('#liBankInfoId a').prop('disabled', true); 
	$('#liBankInfoId a').addClass("disable_color"); 
	
	$('#liReasonInfoId a').prop('disabled', true);
	$('#liReasonInfoId a').addClass("disable_color"); 
	
	$('#liDocumentInfoId a').prop('disabled', true); 
	$('#liDocumentInfoId a').addClass("disable_color"); 
	
	$('#liStCompanyInfoId a').prop('disabled', true); 
	$('#liStCompanyInfoId a').addClass("disable_color"); 
}

/***************************************************************************
 * Name 				: addDocumentSection 
 * Return type 			: None 
 * Input Parameter(s) 	: innerDivId,outerDivId
 * Purpose 				: This method is used to clone add document section.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	04 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function addDocumentSection(innerDivId,outerDivId){
	counter = counter + 1; 
    var divClone = $("#"+innerDivId).clone();
    divClone.find("#rgDocumentTypeId").val("");
    divClone.find("#requiredDocumentId").val("");

    divClone.find(".docTypeClass").attr("id", "rgDocumentTypeId" + counter);
    divClone.find(".docTypeClass").attr("name", "documents[" + counter +"].documentTypeId");
    
    divClone.find(".fileClass").attr("id", "requiredDocumentId" + counter);
    divClone.find(".fileClass").attr("name", "documents[" + counter +"].document");
    divClone.find(".docErrorDiv").attr("id", "errorDivrgDocumentTypeId" + counter);
    $("#"+outerDivId).append(divClone);
}

/***************************************************************************
 * Name 				: hidePopup 
 * Return type 			: None 
 * Input Parameter(s) 	: objId
 * Purpose 				: This method is used to hide popup.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	04 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function hidePopup(objId) {
	$(objId).closest('.pop_up').hide();
}

/***************************************************************************
 * Name 				: openRemovePopup 
 * Return type 			: None 
 * Input Parameter(s) 	: objMerchant
 * Purpose 				: This method is used to open remove pop up for merchant.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	04 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function openRemovePopup(objMerchant){
	var merchnatId = $(objMerchant).attr('id').substr(2);
	$("#merchantRemoveId").val(merchnatId);
	$("#popupRemoveMerchantDivId").show();
}

/***************************************************************************
 * Name 				: openEmployeeRemovePopup 
 * Return type 			: None 
 * Input Parameter(s) 	: None
 * Purpose 				: This method is used to open remove pop up for employee.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	04 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function openEmployeeRemovePopup(){
	$("#popupRemoveEmployeeDivId").show();
}

/***************************************************************************
 * Name 				: loadRegistrationValidation 
 * Return type 			: None 
 * Input Parameter(s) 	: None
 * Purpose 				: This method is used to load validation for merchant invitation link.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	04 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function loadRegistrationValidation(){
	registerForNumericDecimal();
	loadDatePicker();
	clearData();
	disableRegistrationDiv();
}

/***************************************************************************
 * Name 				: printDocument 
 * Return type 			: None 
 * Input Parameter(s) 	: None
 * Purpose 				: This method is used to print doc.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	04 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function printDocument(){
	window.print();
}

/***************************************************************************
 * Name 				: loadMerchantDetails 
 * Return type 			: None 
 * Input Parameter(s) 	: None
 * Purpose 				: This method is used to load merchant details.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	04 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function loadMerchantDetails() {
	callForPGDataTable("merchantMainDataTableId", "", "fetchmerchantdata");
};

/***************************************************************************
 * Name 				: loadVoucherCompanyDetails 
 * Return type 			: None 
 * Input Parameter(s) 	: None
 * Purpose 				: This method is used to load voucher company details.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	04 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function loadVoucherCompanyDetails() {
	callForPGDataTable("voucherCompanyDataTableId", "", "fetchvouchercompanydata");
};

/***************************************************************************
 * Name 				: loadMerchantverificationDetails 
 * Return type 			: None 
 * Input Parameter(s) 	: None
 * Purpose 				: This method is used to load merchant verification details.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	04 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function loadMerchantverificationDetails() {
	callForPGDataTable("merchantMainVerificationDataTableId", "", "fetchmerchantverificationdata");
};

/***************************************************************************
 * Name 				: callForPGDataTable 
 * Return type 			: None 
 * Input Parameter(s) 	: tableName, formData, url
 * Purpose 				: This method is used to load data on paging nation.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	04 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function callForPGDataTable(tableName, formData, url) {
	var datatable = $("#" + tableName).DataTable({
		"bDestroy" : true,
		//"processing" : false,
		"serverSide" : true,
		//"scrollX" : true,
		// "scrollY" : "220px", // used to vertical scroll
		// "scrollCollapse" : true,
		"bLengthChange" : false, // used to disable Show field
		"aaSorting" : [],
		"bSort" : false,
		"bFilter" : false, // used to disable search field
		//"iDisplayLength" : 5,
		"bAutoWidth": false,
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
 * Name 				: pipelineFn 
 * Return type 			: None 
 * Input Parameter(s) 	: opts
 * Purpose 				: This method is used to load data on paging nation.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	04 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function pipelineFn ( opts ) {
    // Configuration options
    var conf = $.extend( {
        pages: 5,     // number of pages to cache
        url: '',      // script url
        data: null,   // function or object with parameters to send to the server
                      // matching how `ajax.data` works in DataTables
        method: 'POST' // Ajax HTTP method
    }, opts );
 
    // Private variables for storing the cache
    var cacheLower = -1;
    var cacheUpper = null;
    var cacheLastRequest = null;
    var cacheLastJson = null;
 
    return function ( request, drawCallback, settings ) {
        var ajax          = false;
        var requestStart  = request.start;
        var drawStart     = request.start;
        var requestLength = request.length;
        var requestEnd    = requestStart + requestLength;
         
        if ( settings.clearCache ) {
            // API requested that the cache be cleared
            ajax = true;
            settings.clearCache = false;
        }
        else if ( cacheLower < 0 || requestStart < cacheLower || requestEnd > cacheUpper ) {
            // outside cached data - need to make a request
            ajax = true;
        }
        else if ( JSON.stringify( request.order )   !== JSON.stringify( cacheLastRequest.order ) ||
                  JSON.stringify( request.columns ) !== JSON.stringify( cacheLastRequest.columns ) ||
                  JSON.stringify( request.search )  !== JSON.stringify( cacheLastRequest.search )
        ) {
            // properties changed (ordering, columns, searching)
            ajax = true;
        }
         
        // Store the request for checking next time around
        cacheLastRequest = $.extend( true, {}, request );
 
        if ( ajax ) {
            // Need data from the server
            if ( requestStart < cacheLower ) {
                requestStart = requestStart - (requestLength*(conf.pages-1));
 
                if ( requestStart < 0 ) {
                    requestStart = 0;
                }
            }
             
            cacheLower = requestStart;
            cacheUpper = requestStart + (requestLength * conf.pages);
 
            request.start = requestStart;
            request.length = requestLength*conf.pages;
 
            // Provide the same `data` options as DataTables.
            if ( $.isFunction ( conf.data ) ) {
                // As a function it is executed with the data object as an arg
                // for manipulation. If an object is returned, it is used as the
                // data object to submit
                var d = conf.data( request );
                if ( d ) {
                    $.extend( request, d );
                }
            }
            else if ( $.isPlainObject( conf.data ) ) {
                // As an object, the data given extends the default
                $.extend( request, conf.data );
            }
            displayProgressBarPopup();
            settings.jqXHR = $.ajax( {
                "type":     conf.method,
                "url":      conf.url,
                "data":     request,
                "dataType": "json",
                "cache":    false,
                "success":  function ( json ) {
                    
                	if(json != null && json != undefined && json != "" && !jQuery.isEmptyObject(json)){
                		cacheLastJson = $.extend(true, {}, json);
                		 
                        if ( cacheLower != drawStart ) {
                            json.data.splice( 0, drawStart-cacheLower );
                        }
                        if ( requestLength >= -1 ) {
                            json.data.splice( requestLength, json.data.length );
                        }
                         
                        drawCallback( json );
                        hideProgressBarPopup();
                	}else{
                		hideProgressBarPopup();
                	}
                }
            } );
        }
        else {
            json = $.extend( true, {}, cacheLastJson );
            json.draw = request.draw; // Update the echo for each response
            json.data.splice( 0, requestStart-cacheLower );
            json.data.splice( requestLength, json.data.length );
 
            drawCallback(json);
        }
    }
}

/***************************************************************************
 * Name 				: hideProgressBarPopup 
 * Return type 			: None 
 * Input Parameter(s) 	: None
 * Purpose 				: This method is used to hide progress bar.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	04 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function hideProgressBarPopup() {
    $("#face").fadeOut();
}

/***************************************************************************
 * Name 				: displayProgressBarPopup 
 * Return type 			: None 
 * Input Parameter(s) 	: None
 * Purpose 				: This method is used to display progress bar.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	04 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function displayProgressBarPopup() {
    $("#loaderPopupMsg").text("");
    $("#face").fadeIn();
    $("#loaderPopupMsg").text(" Please Wait ...");
}

/***************************************************************************
 * Name 				: serchMerchantData 
 * Return type 			: None 
 * Input Parameter(s) 	: None
 * Purpose 				: This method is used to search merchant data.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	04 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function serchMerchantData(){
	var formData = $('form#' + "merchantsearchFormId").serialize();
	callForPGDataTable("merchantMainDataTableId", formData, "fetchmerchantdata")
}

/***************************************************************************
 * Name 				: editEmployee 
 * Return type 			: None 
 * Input Parameter(s) 	: objEmployee, actionUrl
 * Purpose 				: This method is used to edit employee details.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	07 Jul, 2017 	Satyamg
 * **************************************************************************
 */
function loadEmployeeByID(objEmployee, actionUrl){
	if($(objEmployee).attr('id')){
		var employeeId = $(objEmployee).attr('id').substr(2);
		loadAdminPanelBodyByID(actionUrl, employeeId);
	}
}


/***************************************************************************
 * Name 				: loadMerchantSubject 
 * Return type 			: None 
 * Input Parameter(s) 	: actionUrl
 * Purpose 				: This method is used to load merchnat subject.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		    10 Jul, 2017 	Keshav Bhagat
 * **************************************************************************
 */
function loadMerchantSubject(actionUrl) {
	if ($("#inMerchantTypeId :selected").text()) {
		var request = {};
		request.notes = $("#inMerchantTypeId :selected").text();
		makeJsonAjaxCall({
			url : actionUrl,
			requestObj : request,
			onSuccess : function(req, status, res) {
				try {
					if (res.status) {
						$("#invitationSubjectId").val("");
						$("#inMerchantNotesId").val("");
						$("#invitationSubjectId").val(res.data.subject);
						$("#inMerchantNotesId").val(res.data.body);
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
 * Name 				: deleteEmployeeData 
 * Return type 			: None 
 * Input Parameter(s) 	: objBtn,actionUrl
 * Purpose 				: This method is used delete employee data
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		    12 Jul, 2017 	Shravan Shrivatasv
 * **************************************************************************
 */
function deleteEmployeeData(objBtn,actionUrl){
	var userTableId = $('#userDataTableId').DataTable();
	var userTableIdRow = userTableId.rows('.selected').nodes();
	employeeId = $(userTableIdRow).find('td:eq(0)').html();
	if(employeeId){
			var request = {};
			request.employeeId = employeeId;
			makeJsonAjaxCall({
				url : actionUrl,
				requestObj : request,
				onSuccess : function(req, status, res) {
					try {
						if (res.status) {
							$(objBtn).closest('.pop_up').hide();
							userTableId.row('.selected').remove().draw(false);
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
 * Name 				: checkDuplicateExist 
 * Return type 			: None 
 * Input Parameter(s) 	: actionUrl
 * Purpose 				: This method is used check duplicate email,phone and unique id exist or not
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		    12 Jul, 2017 	Keshav Bhagat
 * **************************************************************************
 */
function checkDuplicateExist(actionUrl,objField,column){
	var value = $(objField).val();
	var errorDiv = "errorDiv" + $(objField).attr('id');
	if(value){
		uniqueValidate(actionUrl,value,column,errorDiv);
	}
}


function uniqueValidate(actionUrl,value,column,errorDivId){
	var request = {};
	request.uniqueId = value;
	request.columnName = column;
	makeJsonAjaxCall({
		url : actionUrl,
		requestObj : request,
		onSuccess : function(req, status, res) {
			try {
				if(res.status == true){
					$("#" + errorDivId).val("");
					$("#" + errorDivId).text(res.data).addClass("display_block");
				}else{
					$("#" + errorDivId).text("").addClass("display_none");
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

function emailUniqueValidate(actionUrl,fieldId){
	var value = $('#'+fieldId).val();
	var objId = $('#hiddenRGPEmployeeId').val() != null ? $('#hiddenRGPEmployeeId').val() : $('#hiddenMerchantId').val();
	if(value){
		var request = {};
		request.emailId = value;
		request.objectId = objId;
		makeJsonAjaxCall({
			url : actionUrl,
			requestObj : request,
			onSuccess : function(req, status, res) {
				try {
					if(res.status == true){
						$("#errorDiv" + fieldId).val("");
						$("#errorDiv" +fieldId).text(res.data).addClass("display_block");
						$("#merchnatEmailHiddenId").val("true");
						emailExistFlag = true;
					}else{
						$("#errorDiv" + fieldId).text("").addClass("display_none");
						$("#merchnatEmailHiddenId").val("false");
						emailExistFlag = false;
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
 * Name 				: previewImage 
 * Return type 			: None 
 * Input Parameter(s) 	: objIcon
 * Purpose 				: This method is used to view merchant doc before save.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	04 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function previewImage(objIcon) {
	var textId = $(objIcon).closest('.merchnatDocClass').find("input:file").attr('id');
    pdffile = document.getElementById(textId).files[0];
    if(pdffile){
      pdffile_url = URL.createObjectURL(pdffile);
      window.open(pdffile_url);
    }
}

/***************************************************************************
 * Name 				: deleteRegistrationDocument 
 * Return type 			: None 
 * Input Parameter(s) 	: objIcon
 * Purpose 				: This method is used to delete merchant upload doc.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	04 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function deleteRegistrationDocument(objIcon){
	var textId = $(objIcon).closest('.merchnatDocClass').find("input:file").attr('id');
	$("#"+textId).val("");
}

/***************************************************************************
 * Name 				: loadDocumentsTable 
 * Return type 			: None 
 * Input Parameter(s) 	: None
 * Purpose 				: This method is used to document table.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	04 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function loadDocumentsTable() {
	var merchnatId = $("#hiddenMerchantId").val();
	callForPGDataTable("merchantDocumentDataTableId", "id= "+ merchnatId , "fetchdocuments");
};

/***************************************************************************
 * Name 				: clearErrrorMessage 
 * Return type 			: None 
 * Input Parameter(s) 	: objErrorMsg
 * Purpose 				: This method is used to clear error message.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	04 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function clearErrrorMessage(objErrorMsg){
	var id = $(objErrorMsg).attr('id');
	$("#errorDiv"+id).text("");
}

/***************************************************************************
 * Name 				: addMerchantDocument 
 * Return type 			: None 
 * Input Parameter(s) 	: documentsFormData,actionUrl
 * Purpose 				: This method is used to add merchant doc.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	04 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function addMerchantDocument(documentsFormData,actionUrl){
	var formData = new FormData($(documentsFormData)[0]);
	var merchantId = $("#hiddenMerchantId").val();
	formData.append("merchantId", merchantId);
	if($("#rgDocumentTypeId :selected").val() && $("#documentMerchantFileId").val()){
		makeFormAjaxCall({
			url : actionUrl,
			requestObj : formData,
			contentType : false,
			processData : false,
			onSuccess : function(req, status, res) {
				try {
					if (res.status == true) {
						$("#rgDocumentTypeId").val("");
						$("#documentMerchantFileId").val("");
						$("[data-dismiss=modal]").trigger({ type: "click" });
						loadDocumentsTable();
						return false;
					}
				} catch (e) {
					console.log('Exception in onSuccess: ' + e.stack);
				}
			},
			onError : function(req, status, res) {
				try {
					return false;
				} catch (e) {
					console.log('Exception in onError: ' + e.stack);
				}
			}
		});
	}else{
		if(!$("#rgDocumentTypeId :selected").val()){
			$("#errorDivrgDocumentTypeId").text("This field should not be blank !").addClass("display_block");
		}
		if(!$("#documentMerchantFileId").val()){
			$("#errorDivdocumentMerchantFileId").text("This field should not be blank !").addClass("display_block");
		}
	}
	return false;
}

/***************************************************************************
 * Name 				: addMerchantDocument 
 * Return type 			: None 
 * Input Parameter(s) 	: objIcon
 * Purpose 				: This method is used to view merchant doc after save.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	04 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function viewMerchantDocuments(objIcon){
	var id = $(objIcon).attr('id').substr(2);
	window.open(document.URL.substring(0, document.URL.lastIndexOf("/")) + "/viewmerchantdocument" + "?id=" + id);
}

/***************************************************************************
 * Name 				: openDeleteDocumentPopup 
 * Return type 			: None 
 * Input Parameter(s) 	: objIcon
 * Purpose 				: This method is used to open remove popup.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	04 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function openDeleteDocumentPopup(objIcon){
	$('#popupRemoveMerchantDocDivId').show();
	 documentId = $(objIcon).attr('id').substr(2);
}

/***************************************************************************
 * Name 				: deleteMerchantDocument 
 * Return type 			: None 
 * Input Parameter(s) 	: objBtn,actionUrl
 * Purpose 				: This method is used to delete merchant documents.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	04 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function deleteMerchantDocument(objBtn,actionUrl){
	if(documentId){
			var request = {};
			request.documentId = documentId;
			makeJsonAjaxCall({
				url : actionUrl,
				requestObj : request,
				onSuccess : function(req, status, res) {
					try {
						if (res.status) {
							$(objBtn).closest('.pop_up').hide();
							//docTable.row('.selected').remove().draw(false);
							loadDocumentsTable();
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
 * Name 				: serchMerchantType 
 * Return type 			: None 
 * Input Parameter(s) 	: None
 * Purpose 				: This method is used to search merchant type.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	04 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function serchMerchantType(tableId,url){
	var formData = $('form#' + "merchantTypeSearchFormId").serialize();
	merchantType = $('#merchantType :selected').val();
	callForPGDataTable(tableId, formData, url)
}

/***************************************************************************
 * Name 				: serchMerchantVerificationData 
 * Return type 			: None 
 * Input Parameter(s) 	: None
 * Purpose 				: This method is used to search merchant verification data.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	04 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function serchMerchantVerificationData(){
	var formData = $('form#' + "merchantverificationSearchFormId").serialize();
	callForPGDataTable("merchantMainVerificationDataTableId", formData, "fetchmerchantverificationdata")
}

/***************************************************************************
 * Name 				: loadRoleTable 
 * Return type 			: None 
 * Input Parameter(s) 	: None
 * Purpose 				: This method is used to load role data table.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	04 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function loadRoleTable(){
    var roleTable = $('#roleTableTableId').DataTable({
	    "bDestroy" : true,
		"bLengthChange" : false, // used to disable Show field
		//"scrollX" : true,
		//"scrollY" : "220px", // used to vertical scroll
		//"scrollCollapse" : true,
		"paging" : true,
		"aaSorting" : [],
		//"bInfo": false,
		"bSort": false,
		"bAutoWidth": false, // Disable the auto width calculation  
		/*"columnDefs" : [{ "width" : "5%", "targets" : 1},{ "width" : "10%", "targets" : 2},{ "width" : "10%", "targets" : 3},{ "width" : "28%", "targets" : 4}]*/
	});
     $('#roleTableTableId tbody').on('click', 'tr', function() {
			if ($(this).hasClass('selected')) {
				$(this).removeClass('selected');
			} else {
				roleTable.$('tr.selected').removeClass('selected');
				$(this).addClass('selected');
			}
	});
}

/***************************************************************************
 * Name 				: updateRoleById 
 * Return type 			: None 
 * Input Parameter(s) 	: objRole, actionUrl
 * Purpose 				: This method is used to update role by id.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	04 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function updateRoleById(objRole, actionUrl){
	if($(objRole).attr('id')){
		var roleId = $(objRole).attr('id').substr(2);
		loadAdminPanelBodyByID(actionUrl, roleId);
	}
}

/***************************************************************************
 * Name 				: openRemoveRolePopup 
 * Return type 			: None 
 * Input Parameter(s) 	: objIcon
 * Purpose 				: This method is open role remove popup.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	04 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function openRemoveRolePopup(objIcon){
	roleId = $(objIcon).attr('id').substr(2);
	$("#popupRemoveRoleDivId").show();
}

/***************************************************************************
 * Name 				: deleteRoleData 
 * Return type 			: None 
 * Input Parameter(s) 	: objBtn,actionUrl
 * Purpose 				: This method is used to delete role data.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	04 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function deleteRoleData(objBtn,actionUrl){
	var roleTableId = $('#roleTableTableId').DataTable();
	if(roleId){
			var request = {};
			request.roleId = roleId;
			makeJsonAjaxCall({
				url : actionUrl,
				requestObj : request,
				onSuccess : function(req, status, res) {
					try {
						if (res.status) {
							$(objBtn).closest('.pop_up').hide();
							roleTableId.row('.selected').remove().draw(false);
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
 * Name 				: verifyMerchantDetails 
 * Return type 			: None 
 * Input Parameter(s) 	: objIcon
 * Purpose 				: This method is used to verify merchant details.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	04 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function verifyMerchantDetails(objIcon) {
	var merchnatId = $(objIcon).attr('id').substr(2);
	if(merchnatId){
		loadAdminPanelBodyByID(document.URL.substring(0, document.URL.lastIndexOf("/")) + "/openverificationtab",merchnatId);
		if(merchantType == 'Voucher'){
			$('#liStCompanyInfoId').show();
		}
	}
};

/***************************************************************************
 * Name 				: showSuccessPopup 
 * Return type 			: None 
 * Input Parameter(s) 	: messageValue
 * Purpose 				: This method is used to open success popup.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	04 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function showSuccessPopup(messageValue){
	BootstrapDialog.show({
		title : "Information",
		message : messageValue,
		type : BootstrapDialog.TYPE_SUCCESS,
		buttons : [ {
			label : 'Ok',
			cssClass : 'btn-primary',
			action : function(dialog) {
				dialog.close();
			}
		} ]
	});
}

/***************************************************************************
 * Name 				: showWarningPopup 
 * Return type 			: None 
 * Input Parameter(s) 	: messageValue
 * Purpose 				: This method is used to open warning popup.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	04 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function showWarningPopup(messageValue){
	BootstrapDialog.show({
		title : "Warning !!",
		message : messageValue,
		type : BootstrapDialog.TYPE_WARNING,
		buttons : [ {
			label : 'Ok',
			cssClass : 'btn-primary',
			action : function(dialog) {
				dialog.close();
			}
		} ]
	});
}

/***************************************************************************
 * Name 				: merchantVerification 
 * Return type 			: None 
 * Input Parameter(s) 	: actionUrl,divId,notes
 * Purpose 				: This method is used for merchant verification.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	04 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function merchantVerification(actionUrl,divId,notes){
		var merchantId = $("#hiddenMerchantId").val();
		if(merchantId){
			var request = {};
			request.merchantId = merchantId;
			request.notes = notes;
			makeJsonAjaxCall({
				url : actionUrl,
				requestObj : request,
				onSuccess : function(req, status, res) {
					try {
						if(res.status){
							$('#'+divId).text(res.data).addClass("display_block");;
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
 * Name 				: approveMerchant 
 * Return type 			: None 
 * Input Parameter(s) 	: None
 * Purpose 				: This method is used for approve merchant.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	04 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function approveMerchant(){
	var verificationCode = $('#rgVerificationCodeId :selected').val();
	var approveNotes = $('#approvereasonId').val();
	if(verificationCode && approveNotes){
		var merchantId = $("#hiddenMerchantId").val();
		if(merchantId){
			var request = {};
			request.merchantId = merchantId;
			request.notes = "Approve";
			makeJsonAjaxCall({
				url : document.URL.substring(0, document.URL.lastIndexOf("/")) + "/verifymerchantsections",
				requestObj : request,
				onSuccess : function(req, status, res) {
					try {
						if(res.status){
							var formdata = $('#adminPanelBody form');
							var formAction = formdata.prop('action');
							var formData = new FormData($(formdata)[0]);
							if(merchantType == 'Voucher'){
								formData.append("verificationType", "Voucher");
							}
							displayProgressBarPopup();
							makeFormAjaxCall({
								url : formAction,
								requestObj : formData,
								contentType : false,
								processData : false,
								async : false,
								onSuccess : function(req, status, res) {
									$("#approveCloseId").click();
									hideProgressBarPopup();
									merchantStatusPopup("Merchant Approved......");
								},
								onError : function(req, status, res) {
									hideProgressBarPopup();
								}
							});
						}else{
							$('#errorDivapprovereasonId').text(res.data).addClass("display_block");;
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
	}else{
		$('#errorDivapprovereasonId').text("Please fill Merchant Verification code and Approve Notes.").addClass("display_block");
	}
}

/***************************************************************************
 * Name 				: rejectMerchant 
 * Return type 			: None 
 * Input Parameter(s) 	: None
 * Purpose 				: This method is used for reject merchant.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	04 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function rejectMerchant(){
	var rejectReason = $('#rejectreasonId').val();
	if(rejectReason){
		var formdata = $('#adminPanelBody form');
		var formAction = formdata.prop('action');
		var formData = new FormData($(formdata)[0]);
		if(merchantType == 'Voucher'){
			formData.append("verificationType", "Voucher");
		}
		displayProgressBarPopup();
		makeFormAjaxCall({
			url : formAction,
			requestObj : formData,
			contentType : false,
			processData : false,
			async : false,
			onSuccess : function(req, status, res) {
				$("#rejectColseId").click();
				hideProgressBarPopup();
				merchantStatusPopup("Merchant Rejected....");
			},
			onError : function(req, status, res) {
				hideProgressBarPopup();
			}
			
		});
	}else{
		$('#errorDivrejectreasonId').text("Please fill Merchant Reject Reason.").addClass("display_block");
	}
}

/***************************************************************************
 * Name 				: merchantStatusPopup 
 * Return type 			: None 
 * Input Parameter(s) 	: messageValue
 * Purpose 				: This method is used open merchant status popup.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	04 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function merchantStatusPopup(messageValue){
	BootstrapDialog.show({
		title : "Merchant Status",
		message : messageValue,
		type : BootstrapDialog.TYPE_SUCCESS,
		buttons : [ {
			label : 'Ok',
			cssClass : 'btn-primary search',
			action : function(dialog) {
				window.location = document.URL.substring(0, document.URL.lastIndexOf("/")) + "/verifymerchant";
			}
		} ]
	});
}

/***************************************************************************
 * Name 				: updateMerchantVerificationCode 
 * Return type 			: None 
 * Input Parameter(s) 	: actionUrl
 * Purpose 				: This method is used update merchant verification code.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	04 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function updateMerchantVerificationCode(actionUrl){
	var tccId = $("#rgTransactionCodeId :selected").val();
	var mccId = $("#rgDescriptionId :selected").val();
	if(mccId && tccId){
		var request = {};
		request.mccId = mccId;
		request.tccId = tccId;
		makeJsonAjaxCall({
			url : actionUrl,
			requestObj : request,
			onSuccess : function(req, status, res) {
				try {
					if(res.status && res.data){
						$("#rgVerificationCodeId").empty();
						$("#rgVerificationCodeId").append('<option value = >Select Verification Code</option>');
						for(var index in res.data){
							$('#rgVerificationCodeId').append(
									'<option value=' + res.data[index].id + '>'
									+ res.data[index].codeDescription + '</option>');
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
}

/***************************************************************************
 * Name 				: validateEndDate 
 * Return type 			: None 
 * Input Parameter(s) 	: objEndDate,objStartDate
 * Purpose 				: This method is used validate end date.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	04 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function validateEndDate(objEndDate,objStartDate){
	var startDate = $("#"+objStartDate).val();
	var endDate = $("#"+objEndDate).val();
	if(Date.parse(endDate) < Date.parse(startDate)){
	    alert("Please select a different End Date.");
	}
}

/***************************************************************************
 * Name 				: serchInvitationData 
 * Return type 			: None 
 * Input Parameter(s) 	: None
 * Purpose 				: This method is used to search invitation details.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	14 Aug, 2017 	prabina Rout
 * **************************************************************************
 */
function serchInvitationData(){
	var formData = $('form#' + "invitationTrackSearchFormId").serialize();
	callForPGDataTable("trackInvitationDataTableId", formData, "fetchinvitationtrack")
}

/***************************************************************************
 * Name 				: viewUserAccounts 
 * Return type 			: None 
 * Input Parameter(s) 	: None
 * Purpose 				: This method is used to view wallet user account details.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	04 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function viewUserAccounts(){
	var accountsType = $("#userAccountsSelectId :selected").val();
	if(accountsType == "1"){
		$("#loan_account_summary_div_id").hide();
		$("#euro_account_summary_div_id").hide();
		callForPGDataTable("dollarTableId", "account= Dollar" , "fetchuseraccounts")
		$("#dollar_account_summary_div_id").show();
	}else if(accountsType == "2"){
		$("#euro_account_summary_div_id").hide();
		$("#dollar_account_summary_div_id").hide();
		callForPGDataTable("loanTableId", "account= Loan" , "fetchuseraccounts")
		$("#loan_account_summary_div_id").show();
	}else if(accountsType == "3"){
		$("#dollar_account_summary_div_id").hide();
		$("#loan_account_summary_div_id").hide();
		callForPGDataTable("euroTableId", "account= Euro" , "fetchuseraccounts")
		$("#euro_account_summary_div_id").show();
	}
}

/***************************************************************************
 * Name 				: approveUserAccounts 
 * Return type 			: None 
 * Input Parameter(s) 	: objButton
 * Purpose 				: This method is used to approve user accounts.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	04 Jul, 2017 	prabina Rout
 * **************************************************************************
 */
function approveUserAccounts(objButton){
	var accountsType = $("#userAccountsSelectId :selected").val();
    var account = null;
    var table = null;
	if(accountsType == "1"){
		account = "Dollar";
		table = "dollarTableId";
	}else if(accountsType == "2"){
		account = "Loan";
		table = "loanTableId";
	}else if(accountsType == "3"){
		account = "Euro";
		table = "euroTableId";
	}
	var accountsId = $(objButton).attr('id').substr(2);
	if(accountsId){
		loadAdminPanelBodyByID(document.URL.substring(0, document.URL.lastIndexOf("/")) + "/openaccverificationtab", accountsId);
		
	/*	var request = {};
		request.objectId = accountsId;
		displayProgressBarPopup();
		makeJsonAjaxCall({
			url : document.URL.substring(0, document.URL.lastIndexOf("/")) + "/approveaccount",
			requestObj : request,
			onSuccess : function(req, status, res) {
				try {
					if(res.status){
						callForPGDataTable(table, "account="+ account, "fetchuseraccounts");
						return false;
					}
				} catch (e) {
					console.log("Error " + e.stack);
				}
			},
			onError : function(req, status, error) {
				try {
					hideProgressBarPopup();
					console.log("Error " + req);
				} catch (e) {
					console.log("Error " + e.stack);
				}
			}
		});*/
	}
}

/***************************************************************************
 * Name 				: closePopup 
 * Return type 			: None 
 * Input Parameter(s) 	: objIcon
 * Purpose 				: This method is used to close popup.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	05 Sep, 2017 	Abhishek Suman
 * **************************************************************************
 */
function closePopup(modelId){
	$("#"+modelId).click();
}

/***************************************************************************
 * Name 				: deleteVoucherCompanyFromMerchant 
 * Return type 			: None 
 * Input Parameter(s) 	: objIcon
 * Purpose 				: This method is used to delete voucher company data.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	05 Sep, 2017 	Abhishek Suman
 * **************************************************************************
 */
function deleteVoucherCompanyFromMerchant(objIcon){
	$('#popupRemoveVoucherCompanyDivId').show();
	voucherCompanyId = $(objIcon).attr('id').substr(2);
}

/***************************************************************************
 * Name 				: editVoucherCompanyData 
 * Return type 			: None 
 * Input Parameter(s) 	: objIcon
 * Purpose 				: This method is used to edit voucher company data.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	05 Sep, 2017 	Abhishek Suman
 * **************************************************************************
 */
function editVoucherCompanyData(objIcon){
	if($(objIcon).attr('id')){
		var voucherCompanyId = $(objIcon).attr('id').substr(2);
		loadAdminPanelBodyByID(document.URL.substring(0, document.URL.lastIndexOf("/")) + "/edit/voucher/registration",voucherCompanyId);
	}
}

/***************************************************************************
 * Name 				: loadAccountverificationDetails 
 * Return type 			: None 
 * Input Parameter(s) 	: None
 * Purpose 				: This method is used to load Account Application verification details.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	05 Sep, 2017 	Abhishek Suman
 * **************************************************************************
 */
function loadAccountverificationDetails() {
	callForPGDataTable("accountApplicationVerifyDataTableId", "", "fetch/account/verification");
};

/***************************************************************************
 * Name 				: serchAccountType 
 * Return type 			: None 
 * Input Parameter(s) 	: None
 * Purpose 				: This method is used to search Account type.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	05 Sep, 2017 	Abhishek Suman
 * **************************************************************************
 */
function serchAccountType(tableId,url){
	var formData = $('form#' + "accountTypeSearchFormId").serialize();
	accountType = $('#accountType :selected').val();
	callForPGDataTable(tableId, formData, url)
}

/***************************************************************************
 * Name 				: serchAccountVerificationData 
 * Return type 			: None 
 * Input Parameter(s) 	: None
 * Purpose 				: This method is used to search Account Application verification data.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	05 Sep, 2017 	Abhishek Suman
 * **************************************************************************
 */
function serchAccountVerificationData(){
	var formData = $('form#' + "accountSearchFormId").serialize();
	callForPGDataTable("accountApplicationVerifyDataTableId", formData, "fetch/account/verification")
}

/***************************************************************************
 * Name 				: verifyAccountApplicationDetails 
 * Return type 			: None 
 * Input Parameter(s) 	: objIcon
 * Purpose 				: This method is used to verify Account Application details.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	06 Sep, 2017 	Abhishek Suman
 * **************************************************************************
 */
function verifyAccountApplicationDetails(objIcon) {
	var userId = $(objIcon).attr('id').substr(2);
	if(userId){
		loadAdminPanelBodyByID(document.URL.substring(0, document.URL.lastIndexOf("/")) + "/verify/user/accounts",userId);
	}
};

/***************************************************************************
 * Name 				: approveAccount 
 * Return type 			: None 
 * Input Parameter(s) 	: None
 * Purpose 				: This method is used to approve user Account Application details.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	12 Sep, 2017 	Abhishek Suman
 * **************************************************************************
 */
function approveAccount(){
	var approveNotes = $('#approvereasonId').val();
	if(approveNotes){
		var accountId = $("#hiddenAccountId").val();
		if(accountId){
			var request = {};
			request.accountId = accountId;
			request.notes = "Approve";
			makeJsonAjaxCall({
				url : document.URL.substring(0, document.URL.lastIndexOf("/")) + "/verifyaccountapplication",
				requestObj : request,
				onSuccess : function(req, status, res) {
					try {
						if(res.status){
							var formdata = $('#adminPanelBody form');
							var formAction = formdata.prop('action');
							var formData = new FormData($(formdata)[0]);
							displayProgressBarPopup();
							makeFormAjaxCall({
								url : formAction,
								requestObj : formData,
								contentType : false,
								processData : false,
								async : false,
								onSuccess : function(req, status, res) {
									$("#approveCloseId").click();
									hideProgressBarPopup();
									accountStatusPopup("Account Approved......");
								},
								onError : function(req, status, res) {
									hideProgressBarPopup();
								}
							});
						}else{
							$('#errorDivapprovereasonId').text(res.data).addClass("display_block");;
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
	}else{
		$('#errorDivapprovereasonId').text("Please fill Merchant Verification code and Approve Notes.").addClass("display_block");
	}
}

/***************************************************************************
 * Name 				: accountStatusPopup 
 * Return type 			: None 
 * Input Parameter(s) 	: None
 * Purpose 				: This method is used to open account status popup.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	12 Sep, 2017 	Abhishek Suman
 * **************************************************************************/
function accountStatusPopup(messageValue){
	BootstrapDialog.show({
		title : "user Account Status",
		message : messageValue,
		type : BootstrapDialog.TYPE_SUCCESS,
		buttons : [ {
			label : 'Ok',
			cssClass : 'btn-primary search',
			action : function(dialog) {
				window.location = document.URL.substring(0, document.URL.lastIndexOf("/")) + "/manageuseraccounts";
			}
		} ]
	});
}

/***************************************************************************
 * Name 				: accountVerification 
 * Return type 			: None 
 * Input Parameter(s) 	: actionUrl,divId,notes
 * Purpose 				: This method is used to verify the account details.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	12 Sep, 2017 	Abhishek Suman
 * **************************************************************************/
function accountVerification(actionUrl,divId,notes){
	var accountId = $("#hiddenAccountId").val();
	if(accountId){
		var request = {};
		request.accountId = accountId;
		request.notes = notes;
		makeJsonAjaxCall({
			url : actionUrl,
			requestObj : request,
			onSuccess : function(req, status, res) {
				try {
					if(res.status){
						$('#'+divId).text(res.data).addClass("display_block");;
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
 * Name 				: rejectAccount 
 * Return type 			: None 
 * Input Parameter(s) 	: actionUrl,divId,notes
 * Purpose 				: This method is used to reject the account details.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	12 Sep, 2017 	Abhishek Suman
 * **************************************************************************/
function rejectAccount(){
	debugger;
	var rejectReason = $('#rejectreasonId').val();
	if(rejectReason){
		var formdata = $('#adminPanelBody form');
		var formAction = formdata.prop('action');
		var formData = new FormData($(formdata)[0]);
		displayProgressBarPopup();
		makeFormAjaxCall({
			url : formAction,
			requestObj : formData,
			contentType : false,
			processData : false,
			async : false,
			onSuccess : function(req, status, res) {
				$("#rejectColseId").click();
				hideProgressBarPopup();
				accountStatusPopup("User Account Rejected....");
			},
			onError : function(req, status, res) {
				hideProgressBarPopup();
			}
		});
	}else{
		$('#errorDivrejectreasonId').text("Please fill User Account Reject Reason.").addClass("display_block");
	}
}
/***************************************************************************
 * Name 				: loadLogoDiv 
 * Return type 			: None 
 * Input Parameter(s) 	: actionUrl
 * Purpose 				: This is used to load response data on screen body.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	29 Jun, 2017 	Prabina Rout
 * **************************************************************************
 */
function loadLogoDiv() {
	var actionUrl = document.URL.substring(0, document.URL.lastIndexOf("/")) + "/loadlogoimage";
	try {
		$("#logoDivId").load(actionUrl, function(response, status, xhr) {
			if (status === "error") {
				$("#logoDivId").html(response);
			}
		});
	} catch (err) {
		console.log("Log" + err + "\nError" + err.printStackTrace);
	}
}

/***************************************************************************
 * Name 				: openAcntDtlsPopUp 
 * Return type 			: None 
 * Input Parameter(s) 	: None
 * Purpose 				: This method is used to open remove pop up.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	15 Sep, 2017 	shravans
 * **************************************************************************
 */
function openAcntDtlsPopUp(){
	$('#userAccountDetailsDivId').modal("show");
}

/***************************************************************************
 * Name 				: viewMerchantPdf 
 * Return type 			: None 
 * Input Parameter(s) 	: object
 * Purpose 				: This method is used to view merchant pdf on click of pdf btn.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	15 Sep, 2017 	shravans
 * **************************************************************************
 */
function viewMerchantPdf(object){
	if($(object).attr('merchantId')){
		var merchantId = $(object).attr('merchantId');
		var merchantName =  $(object).attr('merchantName');
		if(merchantId){
			checkFileExistForMerchant(merchantId);
			if (isFileExistForMerchant) {
				fileViewPopupForMerchant(merchantId, merchantName);
		    }
		}
	}
	
}

/***************************************************************************
 * Name 				: checkFileExistForMerchant 
 * Return type 			: None 
 * Input Parameter(s) 	: merchantId
 * Purpose 				: This method is used to check whether file exists or not.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	15 Sep, 2017 	shravans
 * **************************************************************************
 */
function checkFileExistForMerchant(merchantId) {

	$.ajax({
		type : "post",
		url : document.URL.substring(0, document.URL.lastIndexOf("/"))
				+ "/fileexistcheck/formerchant",
		datatype : "json",
		async : false,
		data : "merchantId=" + merchantId,
		success : function(response) {
			isFileExistForMerchant = response;
		},
		error : function(e) {
			ajaxErrorOccur();
		}
	});

	if (!isFileExistForMerchant) {
		BootstrapDialog.show({
			title : 'Information',
			type : BootstrapDialog.TYPE_DANGER,
			message : "Unable to access  requested  file, file is not available in the disk"
		});
		event.preventDefault();
	}
}

/***************************************************************************
 * Name 				: fileViewPopupForMerchant 
 * Return type 			: None 
 * Input Parameter(s) 	: merchantId
 * Purpose 				: This method is used to show merchant pdf file.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	15 Sep, 2017 	shravans
 * **************************************************************************
 */
function fileViewPopupForMerchant (merchantId, merchantName) {
	var urld = "get/filepopup/formerchant?merchantId=" + merchantId;
	BootstrapDialog.show({
		title : merchantName,
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

function viewAccountDocuments(objIcon){
	var accountId = $("#hiddenAccountId").val();
	window.open(document.URL.substring(0, document.URL.lastIndexOf("/")) + "/viewAccountdocument" + "?id=" + accountId);
}