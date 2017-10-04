$(document).ready(function() {
   var selector = '.sidemenu li';
	$(selector).on('click', function(){
	    $(selector).removeClass('active');
	    $(this).addClass('active');
	});
	
});
