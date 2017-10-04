$(document).ready(function($) {
	$("#allWalletUserId").val(false);
	var formData = $('form#userSearchFormId').serialize();
	loadWalletUserTable("userMainDataTableId", formData, "fetchwalletusers");
	setformatValidation();
});



/***************************************************************************
 * Name 				: assignVouchers 
 * Return type 			: None 
 * Input Parameter(s) 	: object
 * Purpose 				: This method is used to assign Vouchers.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	27 July, 2017 	shravans 
 * **************************************************************************
 */
function assignVouchers(object){
    var rows_selected = datatable.column(0).checkboxes.selected();
    var formId = $(object).closest("form").attr("id");
    var voucherIds = $("#assignVcIds").val();
    var userIds = [];
    // Iterate over all selected checkboxes
    $.each(rows_selected, function(index, rowId){
    	userIds.push(rowId)
    });
    
    if(userIds.length > 0 && voucherIds!=null && voucherIds.length > 0){
    	displayProgressBarPopup();
    	$.ajax({
            type : "post",
            url :  document.URL.substring(0, document.URL.lastIndexOf("/")) + "/assign/vouchers/touser",
            datatype : "json",
//            async : false,
            data : "userIds=" + userIds + "&voucherIds=" + $("#assignVcIds").val(),
            success : function(data) {
                if(data){
                	showPostAssignVouchersMsg(data, formId);
                	hideProgressBarPopup();
            	} else {
            		showWarningPopup("Voucher could not be assigned");
            		hideProgressBarPopup();
                }
            },
            error : function(e) {
            	showWarningPopup("Voucher could not be assigned due to error");
            	hideProgressBarPopup();
            }
        });
    }else{
    	showWarningPopup("Please select vouchers and users");
    }
    

}

/***************************************************************************
 * Name 				: showPostAssignVouchersMsg 
 * Return type 			: None 
 * Input Parameter(s) 	: resData, formId
 * Purpose 				: This method is used to show post assign vouchers message.
 * History Header 		: Version 		Date 			Developer Name
 * Added By 			: 1.0 		 	27 July, 2017 	shravans 
 * **************************************************************************
 */
function showPostAssignVouchersMsg(resData, formId) {
	if (resData.EMAILSUCCESS == true && resData.VOUCHERSUCCESS == true) {
		showSuccessPopup("Voucher Assigned successfully to users");
	} else if (resData.VOUCHERSUCCESS == false) {
		var message = "Voucher could no be assigned to all or some of the selected users\n";
		var users = JSON.parse(resData.VOUCHERFAILUREUSERS);
		var index;
		for (index = 0; index < users.length; index++) {
			
			var row = "<div class='row'>" +
			"<div class='col-sm-4'>" +
			"<b> User Name : </b>" + users[index].firstName + " " +  users[index].lastName + "</div>" +
			"<div class='col-sm-4'>" +
			"<b>voucher name : </b>" +  users[index].voucherName + " </div>" +
			"<div class='col-sm-4'>" +
			"<b>Reason : </b>" + users[index].reason + " </div></div>";
			
			message = message + row;
		}
		
		showWarningPopup(message);
	} else if (resData.EMAILSUCCESS == false && resData.VOUCHERSUCCESS == true) {
		showWarningPopup("Voucher assigned to all selected users but email could not be sent to some or all users");
	} 

}


