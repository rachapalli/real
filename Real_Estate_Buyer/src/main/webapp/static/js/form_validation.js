/***************************************************************************
 * Name 				: clearData 
 * Return type 			: None 
 * Input Parameter(s) 	: None
 * Purpose 				: This is used to clear error message on key down.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	29 Jun, 2017 	Prabina Rout
 * **************************************************************************
 */
function clearData(){
	$("#approveCloseId").click(function(e) {
		$('#errorDivapprovereasonId').text("");
		$('#approvereasonId').val("");
	});
	$("#rejectColseId").click(function(e) {
		$('#rejectreasonId').val("");
	});
	$("#rgDocumentTypeId").click(function(e) {
		$("#errorDivrgDocumentTypeId").text("");
	});
	$("#rgCountryId").click(function(e) {
		$("#errorDivrgCountryId").text("");
	});
	$("#rgppCountryId").click(function(e) {
		$("#errorDivrgppCountryId").text("");
	});
	$("#rgApplicationId").click(function(e) {
		$("#errorDivrgApplicationId").text("");
	});

	$("#rgDescriptionId").keydown(function(e) {
		$("#errorDivrgDescriptionId").text("");
	});

	$("#rgCompanynameId").keydown(function(e) {
		$("#errorDivrgCompanynameId").text("");
	});

	$("#rgCompanyAddressId").keydown(function(e) {
		$("#errorDivrgCompanyAddressId").text("");
	});

	$("#rgCityId").keydown(function(e) {
		$("#errorDivrgCityId").text("");
	});

	$("#rgContactNoId").keydown(function(e) {
		$("#errorDivrgContactNoId").text("");
	});

	$("#rgCountryId").keydown(function(e) {
		$("#errorDivrgCountryId").text("");
	});
	
	$("#rgFirstNameId").keydown(function(e) {
		$("#errorDivrgFirstNameId").text("");
	});
	
	$("#rgLastNameId").keydown(function(e) {
		$("#errorDivrgLastNameId").text("");
	});

	$("#rgpEmailId").keydown(function(e) {
		$("#errorDivrgpEmailId").text("");
	});

	$("#rgpAddressId").keydown(function(e) {
		$("#errorDivrgpAddressId").text("");
	});
	
	$("#rgpUniqueId").keydown(function(e) {
		$("#errorDivrgpUniqueId").text("");
	});
	
	$("#rgpContactId").keydown(function(e) {
		$("#errorDivrgpContactId").text("");
	});
	
	$("#rgmNameId").keydown(function(e) {
		$("#errorDivrgmNameId").text("");
	});

	$("#rgmUniqueId").keydown(function(e) {
		$("#errorDivrgmUniqueId").text("");
	});
	
	$("#rgmEmailId").keydown(function(e) {
		$("#errorDivrgmEmailId").text("");
	});
	
	$("#rgbNameId").keydown(function(e) {
		$("#errorDivrgbNameId").text("");
	});

	$("#rgbAccountHolderId").keydown(function(e) {
		$("#errorDivrgbAccountHolderId").text("");
	});

	$("#rgbAccountNoId").keydown(function(e) {
		$("#errorDivrgbAccountNoId").text("");
	});
	
	$("#rgbBankCodeId").keydown(function(e) {
		$("#errorDivrgbBankCodeId").text("");
	});

	$("#rgprMerchantAccId").keydown(function(e) {
		$("#errorDivrgprMerchantAccId").text("");
	});
}

/***************************************************************************
 * Name 				: validateFormSubmit 
 * Return type 			: None 
 * Input Parameter(s) 	: formId ,objForm
 * Purpose 				: This is used to validate form on submit button.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	29 Jun, 2017 	Prabina Rout
 * **************************************************************************
 */
function validateFormSubmit(formId, objForm) {
	var flag = true;
	$("#" + formId + " " + "input").each(function() {
		var attributeId = $(this).attr('id');
		var fieldValue = $(this).val().trim();
		var valueLength = fieldValue.length;
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
		} else if ($(this).hasClass('password')) {
			var passWordVal = $('#txtPassword').val();
			var conPasswordVal = $('#txtConPassword').val();
			if (passWordVal != conPasswordVal) {
				message = "Password does not match with confirm password";
				attributeId = 'txtConPassword';
				flag = false;
			}
		}
		if (flag === false) {
			var errorDivId = 'errorDiv' + attributeId;
			$("#" + errorDivId).text(message).addClass(
					"display_block");
		}
	});

	$("#" + formId).find('textarea').each(function() {
		var attributeId = $(this).attr('id');
		var valueLength = $(this).val().length;
		var minLength = $(this).attr('min');
		var message = "";
		if ($(this).hasClass('required') && valueLength < minLength * 1) {
			message = "Must be at least " + minLength + " characters long.";
			var errorDivId = 'errorDiv' + attributeId;
			$("#" + errorDivId).text(message).addClass("display_block");
			return flag = false;
		}
	});

	$("#" + formId + " " + "select").each(function() {
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
	
	if(formId == "merchantRegistrationFormId" || formId == "addEmployeeFormId"){
		var value = $('#merchnatEmailHiddenId').val();
		if(value == "true"){
			flag = false;
		}
	}
	if(flag){
		displayProgressBarPopup();
	}
	return flag;
}

/***************************************************************************
 * Name 				: validateTextArea 
 * Return type 			: None 
 * Input Parameter(s) 	: formId ,objForm
 * Purpose 				: This is used to validate textarea when we submit form.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	29 June, 2017 	Prabina Rout
 * **************************************************************************
 */
function validateTextArea(formId, objForm){
	var flag = true;
	$("#" + formId).find('textarea').each(function() {
		var attributeId = $(this).attr('id');
		var fieldValue = $(this).val().trim();
		var valueLength =fieldValue.length;
		var minLength = $(this).attr('min');
		var message = "";
		if (($(this).hasClass('required') || valueLength != 0 ) && valueLength < minLength * 1) {
			message = "Must be at least " + minLength + " characters long.";
			var errorDivId = 'errorDiv' + attributeId;
			$("#" + errorDivId).text(message).addClass("display_block");
			flag = false;
		}
	});
	return flag;
}

/***************************************************************************
 * Name 				: registerForNumericDecimal 
 * Return type 			: None 
 * Input Parameter(s) 	: None
 * Purpose 				: This is used to check the numeric and decimal 
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	29 Jun, 2017 	Prabina Rout
 * **************************************************************************
 */
function registerForNumericDecimal() {
	var obj = $(".numeric");
	var maxlength = $(".numeric").attr("maxlength");
	$(obj).each(function(index) {
		maxlength = $(this).attr("maxlength");
		if(!maxlength) {
			$(this).attr("maxlength", 10);
		}
	});
	obj = $(".short");
	$(obj).each(function(index) {
		maxlength = $(this).attr("maxlength");
		if(!maxlength) {
			$(this).attr("maxlength", 4);
		}
	});
	obj = $(".decimal");
	$(obj).each(function(index) {
		maxlength = $(this).attr("maxlength");
		if(!maxlength) {
			$(this).attr("maxlength", 10);
		}
	});
	obj = $(".decimalNegative");
	$(obj).each(function(index) {
		maxlength = $(this).attr("maxlength");
		if(!maxlength) {
			$(this).attr("maxlength", 13);
		}
	});
	obj = $(".decimalCurrency");
	$(obj).each(function(index) {
		maxlength = $(this).attr("maxlength");
		if(!maxlength) {
			$(this).attr("maxlength", 13);
		}
	});
	unbindEvents();
	
	$('.specialnumber').keypress(function(event) {
		var keycode = event.which;
	    if (!(keycode == 40 || keycode == 41 || keycode == 43 || keycode == 45 || keycode == 8 || keycode == 34 || (keycode >= 48 && keycode <= 57))) {
	        event.preventDefault();
	    }
	});
	$('.numeric').keypress(function(event) {
		if (event.which != 8 && event.which != 0
				&& (event.which < 48 || event.which > 57)) {
			event.preventDefault();
		}
	});
	$('.short').keypress(function(event) {
		if (event.which != 8 && event.which != 0
				&& (event.which < 48 || event.which > 57)) {
			event.preventDefault();
		}
	});
	$('.decimal').keypress(function(event) {
		if ((event.which != 8 && event.which != 0)
				&& (event.which != 46 || $(this).val().indexOf('.') != -1)
				&& (event.which < 48 || event.which > 57)) {
			event.preventDefault();
		}
	});
	$('.decimalCurrency').keypress(function(event) {
		if ((event.which != 8 && event.which != 0)
				&& (event.which != 46 || $(this).val().indexOf('.') != -1)
				&& (event.which != 44)
				&& (event.which < 48 || event.which > 57)) {
			event.preventDefault();
		}
	});
	$('.decimalNegative').keypress(function(event) {
		if ((event.which != 8 && event.which != 0)
				&& (event.which != 46 || $(this).val().indexOf('.') != -1)
				&& ((event.which < 48) && (event.which != 45 || $(this).val().indexOf('-') != -1) || event.which > 57)) {
			event.preventDefault();
		}
	});
	
	$('.specialnumber').keypress(function(event) {
		if (event.originalEvent.clipboardData.getData('Text').match(/[^\d]/)) {
			event.preventDefault();
		}
	});
	$('.numeric').bind('paste', function(event) {
		if (event.originalEvent.clipboardData.getData('Text').match(/[^\d]/)) {
			event.preventDefault();
		}
	});
	$('.short').bind('paste', function(event) {
		if (event.originalEvent.clipboardData.getData('Text').match(/[^\d]/)) {
			event.preventDefault();
		}
	});
	$('.decimal').bind('paste',function(event) {
		if (event.originalEvent.clipboardData.getData('Text').match(/[^\d.]/g, '')) {
			event.preventDefault();
		}
	});

	$('.decimalCurrency').bind('paste',function(event) {
		if (event.originalEvent.clipboardData.getData('Text').match(/[^\d.,]/g, '')) {
			event.preventDefault();
		}
	});
	$('.decimalNegative').bind('paste',function(event) {
		if (event.originalEvent.clipboardData.getData('Text').match(/[^\d.-]/g, '')) {
			event.preventDefault();
		}
	});

	$('.alphabate').keypress(
			function(event) {
				if (event.charCode != 0) {
					var regex = new RegExp("^[a-zA-Z ]+$");
					var key = String.fromCharCode(!event.charCode ? event.which
							: event.charCode);
					if (!regex.test(key)) {
						event.preventDefault();
						return false;
					}
				}
			});

	$('.alphanumeric').keypress(
			function(event) {
				if (event.charCode != 0) {
					var regex = new RegExp("^[A-Za-z0-9 _.-]+$");
					var key = String.fromCharCode(!event.charCode ? event.which
							: event.charCode);
					if (!regex.test(key)) {
						event.preventDefault();
						return false;
					}
				}
			});
}

/***************************************************************************
 * Name 				: unbindEvents 
 * Return type 			: None 
 * Input Parameter(s) 	: None
 * Purpose 				: This is used to unbind events with the DOM element.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	29 Jun, 2017 	Prabina Rout
 * **************************************************************************
 */
function unbindEvents() {
	$('.specialnumber').unbind("keypress");
	$('.specialnumber').unbind("paste");
	$('.numeric').unbind("keypress");
	$('.numeric').unbind("paste");
	$('.short').unbind("keypress");
	$('.short').unbind("paste");
	$('.decimal').unbind("keypress");
	$('.decimal').unbind("paste");
	$('.decimalNegative').unbind("keypress");
	$('.decimalNegative').unbind("paste");
	$('.character').unbind("keypress");
}