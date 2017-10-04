var userVoucherPdfChk;
$(document).ready(
		function($) {
			$("#allVoucherUserId").val(true);
			var formData = $('form#voucherUserSearchFormId').serialize();
			callForPGDataTable("allVoucherUserTableId", formData,
					"fetchassignedwalletusers");
			setformatValidation();
		});

/*******************************************************************************
 * Name : viewUserVoucherPdf Return type : None Input Parameter(s) : object
 * Purpose : This method is used to view voucher pdf on click of pdf btn.
 * History Header : Version Date Developer Name Added By : 1.0 09 Aug, 2017
 * shravans
 * **************************************************************************
 */
function viewUserVoucherPdf(object) {
	if ($(object).attr('userVoucherId')) {
		var userVoucherId = $(object).attr('userVoucherId');
		var voucherName = $(object).attr('voucherName');
		checkFileUserVch(userVoucherId);
		if (userVoucherPdfChk) {
			fileViewPopupUserVoucher(userVoucherId, voucherName);
		}
	}

}

/*******************************************************************************
 * Name : checkFileUserVch Return type : None Input Parameter(s) : voucherId
 * Purpose : This method is used to check whether file exists or not. History
 * Header : Version Date Developer Name Added By : 1.0 11 Aug, 2017 shravans
 * **************************************************************************
 */
function checkFileUserVch(userVoucherId) {

	$.ajax({
		type : "post",
		url : document.URL.substring(0, document.URL.lastIndexOf("/"))
				+ "/fileexist/uservoucher",
		datatype : "json",
		async : false,
		data : "userVoucherId=" + userVoucherId,
		success : function(response) {
			userVoucherPdfChk = response;
		},
		error : function(e) {
			ajaxErrorOccur();
		}
	});

	if (!userVoucherPdfChk) {
		BootstrapDialog
				.show({
					title : 'Information',
					type : BootstrapDialog.TYPE_DANGER,
					message : "Unable to access  requested  file, file is not available in the disk"
				});
		event.preventDefault();
	}
}

/*******************************************************************************
 * Name : fileViewPopup Return type : None Input Parameter(s) : voucherId
 * Purpose : This method is used to show voucher pdf file. History Header :
 * Version Date Developer Name Added By : 1.0 09 Aug, 2017 shravans
 * **************************************************************************
 */
function fileViewPopupUserVoucher(uservouchId, voucherName) {

	var urld = "get/filepopup/foruservoucher?uservouchId=" + uservouchId;

	BootstrapDialog
			.show({
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
