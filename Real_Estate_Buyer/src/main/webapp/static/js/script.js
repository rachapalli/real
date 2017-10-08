var baseUrl = $("#baseUrl").val();
$(document).ready(function() {
});

/***************************************************************************
* Name                      : validateLoginForm 
* Return type               : None 
* Input Parameter(s)        : None
* Purpose                   : This is used to validate login form.
* History Header            : Version       Date            Developer Name
* Added By                  : 1.0           05 Oct, 2017    Umamaheswarar
****************************************************************************/
function validateLoginForm() {
	var userName = $("#loginUserId").val();
	var password = $("#loginUserPassId").val();
	var msg = "";
	if (!userName && !password) {
		msg = "Please enter user name and password.";
	} else if (!userName) {
		msg = "Please enter user name.";
	} else if (!password) {
		msg = "Please enter password.";
	}
	if (msg) {
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
* Name                      : validateRegisterForm 
* Return type               : None 
* Input Parameter(s)        : formId, objForm
* Purpose                   : This is used to validate registration form.
* History Header            : Version       Date            Developer Name
* Added By                  : 1.0           05 Oct, 2017    Umamaheswarar
****************************************************************************/
function validateRegisterForm(formId, objForm) {
	var flag = true;
	$("#" + formId)
	.find('input')
	.each(
			function() {
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

	return flag;
}

/***************************************************************************
* Name                      : emailIdLoginCheck 
* Return type               : status 
* Input Parameter(s)        : None
* Purpose                   : This is used to validate email.
* History Header            : Version       Date            Developer Name
* Added By                  : 1.0           05 Oct, 2017    Umamaheswarar
****************************************************************************/
function emailIdLoginCheck() {
	$('#loginCheckMSG').text("");
	var email = $('#emailId').val();
	var status = isEmail(email);
	if (!status) {
		$('#loginCheckMSG').text("Invalid Email ID")
	}
	return status;
}

/***************************************************************************
* Name                      : emailIdLoginCheck 
* Return type               : None 
* Input Parameter(s)        : email
* Purpose                   : This is used to validate email.
* History Header            : Version       Date            Developer Name
* Added By                  : 1.0           05 Oct, 2017    Umamaheswarar
****************************************************************************/
function isEmail(email) {
	var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	return regex.test(email);
}

/***************************************************************************
* Name                      : clearData 
* Return type               : None 
* Input Parameter(s)        : email
* Purpose                   : This is used to clear error messages.
* History Header            : Version       Date            Developer Name
* Added By                  : 1.0           05 Oct, 2017    Umamaheswarar
****************************************************************************/
function clearData() {
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

/***************************************************************************
* Name                      : fetchResults 
* Return type               : None 
* Input Parameter(s)        : obj, fetchUrl
* Purpose                   : This is used to fetch results of property.
* History Header            : Version       Date            Developer Name
* Added By                  : 1.0           05 Oct, 2017    Umamaheswarar
****************************************************************************/
function fetchResults(obj, fetchUrl) {
	var searchItem = $("#buyerLookupForm  #searchItem").val();
	if (searchItem) {
		var request = {};
		request.propertyName = searchItem;
		makeJsonAjaxCall({
			url : fetchUrl,
			requestObj : request,
			onSuccess : function(req, status, res) {
				if (res != null && res != '' && res != undefined) {
					populateData(res.data);
				}
			},
			onError : function(req, status, error) {
			}
		});
	}
}

/***************************************************************************
* Name                      : populateData 
* Return type               : None 
* Input Parameter(s)        : data
* Purpose                   : This is used to populate property data.
* History Header            : Version       Date            Developer Name
* Added By                  : 1.0           05 Oct, 2017    Umamaheswarar
****************************************************************************/
function populateData(data) {
	if (data) {
		$("#mainId").empty();
		var htmlCode = '<ul class="result">';
		for ( var index in data) {

			htmlCode += '<li >'
				+ '<div class= "moredata" data-toggle="modal" data-target="#myModal1" id="'
				+ data[index].id
				+ '" onclick="populateMoreDetails(this)">'
				+ '<p class="row"><span class="col-md-3">Property Name</span><span class="col-md-1">:</span> <span class="col-md-3" id="propertyNamaeId">'
				+ data[index].propertyName
				+ '</span></p>'
				+ '<p class="row"><span class="col-md-3">Place</span><span class="col-md-1">:</span> <span class="col-md-3">'
				+ data[index].city
				+ '</span></p>'
				+ '<p class="row"><span class="col-md-3">Price</span><span class="col-md-1">:</span> <span class="col-md-3">'
				+ data[index].price
				+ '</span></p>'
				+ '</div>'
				+ '<a class="btn btn-primary" href="#" data-toggle="modal" data-target="#myModal" name="'
				+ data[index].email 
				+ '" onclick="setMail(this)">Contact</a>'
				+ '</li>';
		}
		htmlCode += '</ul >';
		$("#mainId").append(htmlCode);
	}

}

/***************************************************************************
* Name                      : populateMoreDetails 
* Return type               : None 
* Input Parameter(s)        : object
* Purpose                   : This is used to populate property more details.
* History Header            : Version       Date            Developer Name
* Added By                  : 1.0           05 Oct, 2017    Umamaheswarar
****************************************************************************/
function populateMoreDetails(object) {
	var divId = $(object).attr('id');
	var url = $("#mainId").parent('div').attr('id');
	if (divId && url) {
		var request = {};
		request.id = divId;
		makeJsonAjaxCall({
			url : url,
			requestObj : request,
			onSuccess : function(req, status, res) {
				if (res != null && res != '' && res != undefined) {
					populateLoadedMoreDetails(res.data);
				}
			},
			onError : function(req, status, error) {
			}
		});
	}
}

/***************************************************************************
* Name                      : populateLoadedMoreDetails 
* Return type               : None 
* Input Parameter(s)        : data
* Purpose                   : This is used to populate property more details.
* History Header            : Version       Date            Developer Name
* Added By                  : 1.0           05 Oct, 2017    Umamaheswarar
****************************************************************************/
function populateLoadedMoreDetails(data) {
	if (data) {
		for ( var index in data) {
			if (data[index].propertyName) {
				$("#propertyNameId").text(data[index].propertyName);
			}
			if (data[index].city) {
				$("#propertyLacationId").text(data[index].city);
			}
			if (data[index].streetAddress) {
				$("#streetId").text(data[index].streetAddress);
			}
			if (data[index].state) {
				$("#stateId").text(data[index].state);
			}
			if (data[index].price) {
				$("#priceId").text(data[index].price);
			}
			if (data[index].plotArea) {
				$("#areaId").text(data[index].plotArea);
			}
			if (data[index].postedBy) {
				$("#postedById").text(data[index].postedBy);
			}
			if (data[index].noOfBedRooms) {
				$("#bedRoomsId").text(data[index].noOfBedRooms);
			}
			if (data[index].noOfBathRooms) {
				$("#bathRoomsId").val(data[index].noOfBathRooms);
			}
		}
	}
}

/***************************************************************************
* Name                      : filterProperties 
* Return type               : None 
* Input Parameter(s)        : url
* Purpose                   : This is used to filter property more details.
* History Header            : Version       Date            Developer Name
* Added By                  : 1.0           05 Oct, 2017    Umamaheswarar
****************************************************************************/
function filterProperties(url) {
	var low = $("#lowPriceId").val();
	var high = $("#highPriceId").val();
	if (low && high) {
		var request = {};
		request.lowPrice = low;
		request.highPrice = high;
		makeJsonAjaxCall({
			url : url,
			requestObj : request,
			onSuccess : function(req, status, res) {
				if (res != null && res != '' && res != undefined) {
					populateData(res.data);
				}
			},
			onError : function(req, status, error) {
			}
		});
	}

}

/***************************************************************************
* Name                      : sortProperties 
* Return type               : None 
* Input Parameter(s)        : url, object
* Purpose                   : This is used to sort property more details.
* History Header            : Version       Date            Developer Name
* Added By                  : 1.0           05 Oct, 2017    Umamaheswarar
****************************************************************************/
function sortProperties(url, object) {
	var selected = $(object).find(":selected").text().split(" ")[0];
	if (selected && url) {
		var request = {};
		request.highTolow = selected == "low" ? false : true;
		makeJsonAjaxCall({
			url : url,
			requestObj : request,
			onSuccess : function(req, status, res) {
				if (res != null && res != '' && res != undefined) {
					populateData(res.data);
				}
			},
			onError : function(req, status, error) {
			}
		});
	}
}

var email;
function setMail(object){
	email = $(object).attr("name");
}

/***************************************************************************
* Name                      : sendMailToSeller 
* Return type               : None 
* Input Parameter(s)        : url, object
* Purpose                   : This is used to send mail to seller.
* History Header            : Version       Date            Developer Name
* Added By                  : 1.0           05 Oct, 2017    Umamaheswarar
****************************************************************************/
function sendMailToSeller(url) {
	var message = $("#messageId").val();
	if (message && url) {
		var request = {};
		request.message = message;
		request.email = email;
		makeJsonAjaxCall({
			url : url,
			requestObj : request,
			onSuccess : function(req, status, res) {
				if (res != null && res != '' && res != undefined) {
					//populateData(res.data);
					alert(res);
				}
			},
			onError : function(req, status, error) {
			}
		});
	}
}