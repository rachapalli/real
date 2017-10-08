/**
 * This file is used to contain the web service method for public and private api calls
 * used by various methods within the application.
 */


function makeJsonAjaxCall(authCall) {
    var url = authCall.url;
    var requestObj = authCall.requestObj;
    var onSuccess = authCall.onSuccess;
    var onError = authCall.onError;
    var timeout = 30000; /* in miliseconds means 30 second*/
    if (!url) {
        console.log('url was null to makeJsonAjaxCall');
        return;
    }
    if (!requestObj) {
        console.log('requestObj was null to makeJsonAjaxCall');
        return;
    }
    if (!onSuccess) {
        console.log('onSuccess function was null to makeJsonAjaxCall');
        return;
    }
    if (!onError) {
        console.log('onError function was null to makeJsonAjaxCall');
        return;
    }
    if(!timeout && timeout != 0) {
    	timeout = 300000;
    }
    $.ajax({
	       url: url,
	       type: 'POST',
	       contentType: 'application/json; charset=utf-8',
	       data: JSON.stringify(requestObj),
	       dataType: 'json',
	       timeout: timeout,
	       cache: false,
	       success: function (data, status, req) {
	           try {
	               onSuccess(req, status, data);
	           } catch (e) {
	               console.log('Error apiName = ' + url + ", " + e);
	               throw e;
	           }
	       },
	       error: function (req, status, error) {
               onError(req, status, error);
	       },
	   });
}



function makeGetAjaxCall(authCall) {
    var url = authCall.url;
    var requestObj = authCall.requestObj;
    var onSuccess = authCall.onSuccess;
    var onError = authCall.onError;
    var timeout = 30000; /* in miliseconds means 30 second*/
    if (!url) {
        console.log('url was null to makeJsonAjaxCall');
        return;
    }
    if (!requestObj) {
        console.log('requestObj was null to makeJsonAjaxCall');
        return;
    }
    if (!onSuccess) {
        console.log('onSuccess function was null to makeJsonAjaxCall');
        return;
    }
    if (!onError) {
        console.log('onError function was null to makeJsonAjaxCall');
        return;
    }
    if(!timeout && timeout != 0) {
    	timeout = 30000;
    }
    $.ajax({
	       url: url,
	       type: 'GET',
	       contentType: 'application/json; charset=utf-8',
	       data: JSON.stringify(requestObj),
	       dataType: 'json',
	       timeout: timeout,
	       cache: false,
	       success: function (data, status, req) {
	           try {
	               onSuccess(req, status, data);
	           } catch (e) {
	               console.log('Error apiName = ' + url + ", " + e);
	               throw e;
	           }
	       },
	       error: function (req, status, error) {
               onError(req, status, error);
	       },
	   });
}


