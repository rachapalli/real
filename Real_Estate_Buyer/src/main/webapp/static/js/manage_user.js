/***************************************************************************
* Name 					: loadMerchantTable 
* Return type 			: None 
* Input Parameter(s) 	: None
* Purpose 				: This is used to load merchant data table.
* History Header 		: Version 		Date 			Developer Name
* Added By 				: 1.0 		 	29 Jun, 2017 	Prabina Rout
* **************************************************************************
*/
function loadEmployeeDataTable(){
	     var employeeTable = $('#userDataTableId').dataTable({
		    "bDestroy" : true,
			"bLengthChange" : false, // used to disable Show field
		/*	"scrollX" : true,
			"scrollY" : "220px", // used to vertical scroll
			"scrollCollapse" : true,*/
			"paging" : true,
			"aaSorting" : [],
			//"bInfo": false,
			"bSort": false,
			"bFilter" : false, 
		});
	      $('#userDataTableId tbody').on('click', 'tr', function() {
				if ($(this).hasClass('selected')) {
					$(this).removeClass('selected');
				} else {
					employeeTable.$('tr.selected').removeClass('selected');
					$(this).addClass('selected');
				}
		});
}