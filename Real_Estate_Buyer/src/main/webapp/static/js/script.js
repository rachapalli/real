var baseUrl = $("#baseUrl").val();
$(document).ready(function() {
});

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

function validateRegisterForm(formId, objForm) {
	var flag = true;
	$("#" + formId ).find('input').each(function() {
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
		}  else if ($(this).hasClass('password')) {
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

	return flag;
}


function emailIdLoginCheck() {
        $('#loginCheckMSG').text("");
        var email = $('#emailId').val();
        var status = isEmail(email);
        if (!status) {
            $('#loginCheckMSG').text("Invalid Email ID")
        }
        return status;
    }

    function isEmail(email) {
        var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        return regex.test(email);
    }

    function clearData(){
    	$("#rgFirstName").keydown(function(e) {
    		$('#errorDivrgFirstName').text("");
    	});
    	$("#rgLastName").keydown(function(e) {
    		$('#errorDivrgLastName').text("");
    	});
    	$("#rgEmail").keydown(function(e) {
    		$("#errorDivrgEmail").text("");
    	});
    	$("#rgPassword").keydown(function(e) {
    		$("#errorDivrgPassword").text("");
    	});
    	$("#rgPhone").keydown(function(e) {
    		$("#errorDivrgPhone").text("");
    	});
    	
    }
    
    function fetchResults(obj,fetchUrl){
    	var searchItem = $("#buyerLookupForm  #searchItem").val();
    	alert(searchItem);
    	if(searchItem){
    		var request = {};
        	request.propertyName = searchItem;
        	makeJsonAjaxCall({
        		url 		: fetchUrl,
        		requestObj  : request,
        		onSuccess 	: function(req, status, res) {
        			if (res != null && res != '' && res != undefined) {
        				alert(res.data);
        			}
        		},
        		onError : function(req, status, error) {
        		}
        	});
    	}
    }