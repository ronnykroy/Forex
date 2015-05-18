<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="templatemo_style.css" rel="stylesheet" type="text/css" />
<title>Change Password</title>
<%@ include file="header.jsp"%>
<script language="javascript" type="text/javascript">
$("loginform").submit(function() {
if ($('#loading_image').length == 0) { //is the image on the form yet?
                // add it just before the submit button
$(':submit').before('<img src="/images/load_one.gif" style="display: none;" alt="loading" id="loading_image">')
}
    $('#loading_image').show(); // show the animated image    
    $(':submit',this).attr('disabled','disabled'); // disable double submits
    return true; // allow regular form submission
});
</script>
<script type="text/javascript"> 
function dil(form)
{
   for(var i=0; i<form.elements.length; i++)
   {
		if(form.elements[i].value == "")
		{
		   alert("Please Enter All Details");
		   document.resetPwd.rollNbr.focus();
		   return false;
		}
   }
   if((document.resetPwd.NewPassword.value).localeCompare(document.resetPwd.repassword.value) != 0){
	   alert("New Password Entered Do Not Match!!");
	   return false;
   }
	return true;
}

</script>
</head>
<body>
<%@ page import="java.sql.*"%>
	<%@ page import="java.io.*"%>
	<%@ page import="javax.servlet.*"%>
	<%@ page import="transactionBean.*"%>
	<%@ page import="utility.*"%>
	<%@ page import="java.text.*"%>
<%
	if(session.getAttribute(Constants.corpusBean) == null){
		response.sendRedirect("/Forex/LogOutRH");
	}
%>
	<form name=resetPwd onSubmit="return dil(this)" action="ResetPwdRH"
		method="post">
		<table align="center">
			<tr>
				<td>Enter New Password:</td>
				<td><input type="password" name="NewPassword" /></td>
			</tr>
			<tr>
				<td>Re-Enter New Password:</td>
				<td><input type="password" name="repassword" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Submit" /> <INPUT TYPE=RESET
					VALUE="CLEAR"></td>
			</tr>
		</table>
	</form>
	</div>
	<!-- end of content -->
	</div>
	<!-- end of content -->
</body>
</html>