
/***************************************************************************
* Name                      : emailIdLoginCheck 
* Return type               : None 
* Input Parameter(s)        : None
* Purpose                   : This is used for emailId Login Check.
* History Header            : Version       Date            Developer Name
* Added By                  : 1.0           26 FEB, 2016    Uttam Kumar
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
 * Name                      : isEmail 
 * Return type               : boolean 
 * Input Parameter(s)        : email
 * Purpose                   : This is used for emailId validation.
 * History Header            : Version       Date            Developer Name
 * Added By                  : 1.0           26 FEB, 2016    Uttam Kumar
 ****************************************************************************/
    function isEmail(email) {
        var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        return regex.test(email);
    }