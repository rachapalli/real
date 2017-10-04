/**
 * This file is used to contain the web service method for public and private api calls
 * used by various methods within the application.
 */

/********************************************************************************
 * Name                 :   makeJsonAjaxCall
 * Return type          :   none
 * Input Parameter(s)   :   authCall
 * Purpose              :   Common ajax module that will make API calls with JSON request.
 * History Header       :   Version   -   Date              	-   Developer Name
 * Added By             :   1.0       -   20 Jun 2017       	-   Prabina Rout
 ********************************************************************************/
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
    	timeout = 30000;
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

/**********************************************************************************
 * Name                 :   makeFormAjaxCall
 * Return type          :   none
 * Input Parameter(s)   :   authCall
 * Purpose              :   Common ajax module that will make API calls with form serialization.
 * History Header       :   Version   -   Date              	-   Developer Name
 * Added By             :   1.0       -   20 Jun 2017      		-   Prabina Rout
 * ****************************************************************************** */
function makeFormAjaxCall(authCall) {
    var url = authCall.url;
    var requestObj = authCall.requestObj;
    var onSuccess = authCall.onSuccess;
    var onError = authCall.onError;
    var contentTypeVal = authCall.contentType;
    var processDataVal =  authCall.processData;
    var timeout = authCall.timeout; /* 30000 in miliseconds means 30 second*/
    if (!url) {
        console.log('url was null to makeFormAjaxCall');
        return;
    }
    if (!requestObj) {
        console.log('requestObj was null to makeFormAjaxCall');
        return;
    }
    if (!onSuccess) {
        console.log('onSuccess function was null to makeFormAjaxCall');
        return;
    }
    if (!onError) {
        console.log('onError function was null to makeFormAjaxCall');
        return;
    }
    if(contentTypeVal === undefined || contentTypeVal === null || contentTypeVal === "") {
    	contentTypeVal = 'application/x-www-form-urlencoded; charset=UTF-8';
    }
    if(processDataVal === undefined || processDataVal === null || processDataVal === "") {
    	processDataVal = true;
    }
    if(!timeout && timeout != 0) {
    	timeout = 30000;
    }
    
    $.ajax({
	       type: 'POST',
	       url: url,
	       data: requestObj,
	       processData: processDataVal,
	       contentType: contentTypeVal,
	       cache: false,
	       /*contentType: 'application/json; charset=utf-8',
	       dataType: 'json',*/
	       timeout: timeout,
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