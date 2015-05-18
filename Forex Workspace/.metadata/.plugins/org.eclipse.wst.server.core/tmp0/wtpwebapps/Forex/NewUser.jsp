<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="templatemo_style.css" rel="stylesheet" type="text/css" />
<title>Register New User</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">
<script type="text/javascript">
function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}

function dil(form)
{
	//futAmountDIV
	if(document.newUserForm.ROLLNBR.value == ""){
		   alert("Please Enter Roll Number Of the User");
		   document.newUserForm.ROLLNBR.focus();
		   return false;
	}
	if(document.newUserForm.FIRSTNAME.value == ""){
	   alert("Please Enter First Name Of The User");
	   document.newUserForm.FIRSTNAME.focus();
	   return false;
	}
   if(document.newUserForm.LASTNAME.value == ""){
		   alert("Please Enter All Details");
		   document.newUserForm.LASTNAME.focus();
		   return false;
		}

   if(document.newUserForm.EMAIL.value == ""){
	   alert("Please Enter All Details");
	   document.newUserForm.EMAIL.focus();
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
	<%@ page import="transactionBean.CorpusBean"%>
	<%@ page import="utility.Constants"%>
	<%@ page import="java.text.*"%>
	<%@page import="transactionBean.TnxRateBean"%>
	<%
	String Name = ""; 
	String displayMsg = " ";
	if(session.getAttribute(Constants.corpusBean) != null){
		CorpusBean myCorpus = new CorpusBean();
		myCorpus =  (CorpusBean) session.getAttribute(Constants.corpusBean);
		if(myCorpus.isAdmin()){
			Name = myCorpus.getFirstName().concat(" ").concat(myCorpus.getLastName());
		}else{
			//redirect block here
			response.sendRedirect("/Forex/LogOutRH");
		}
		if( request.getAttribute("Constants.DISPLAY_MSG") != null)
			 displayMsg= (String) request.getAttribute("Constants.DISPLAY_MSG");
	}else{
		//redirect block here
		response.sendRedirect("/Forex/LogOutRH");
	}
%>
	<br>
	<table border="0" cellspacing="0" width="95%" align="center">
		<tr>
			<td align="left"><a href="adminHome.jsp">Home</a></td>
		</tr>
	</table>
	<form name=newUserForm onSubmit="return dil(this)"
		action="CreateNewUser" method="post">
		<table align="center" border="0" width="80%">
			<tr align="center">
				<td colspan="2"><h1>NEW USER REGISTRATION</h1></td>
			</tr>
			<tr>
				<td>Roll Number :</td>
				<td><input type="text" name="ROLLNBR" maxlength="5"
					onkeypress="return isNumber(event)"></td>
			</tr>
			<tr>
				<td>First Name :</td>
				<td><input type="text" name="FIRSTNAME"></td>
			</tr>
			<tr>
				<td>Last Name :</td>
				<td><input type="text" name="LASTNAME"></td>
			</tr>
			<tr>
				<td>E-Mail :</td>
				<td><input type="text" name="EMAIL"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Submit" /> <INPUT TYPE=RESET
					VALUE="CLEAR"></td>
			</tr>
		</table>
</body>
</html>
<%@ include file="footer.jsp"%>