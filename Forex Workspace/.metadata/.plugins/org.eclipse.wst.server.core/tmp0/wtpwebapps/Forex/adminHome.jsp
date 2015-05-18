<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="templatemo_style.css" rel="stylesheet" type="text/css" />
<title>Admin Home</title>
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
	return true;
}

function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
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
	<%@ page import="utility.*"%>
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
	<table align="left">
		<tr><td align="right"><a href="/Forex/LogOutRH">LogOut
					</a></td>
		</tr>
		</tr>
	</table>
	<table align="right">
		<tr><td>
		</h4></td>
		</tr>
	</table>
	<br>
	<br>
	<table cellspacing="6" cellpadding="0" align="center">
		<tr>
			<td align="left"><a href="/Forex/EvaluatePerformance">Evaluate
					Performance</a></td>
					<td align="right"><%= displayMsg %></td>
		</tr>
		<tr>
			<td align="left"><a href="/Forex/CreateNewUser">Register a
					New User</a></td>
		</tr>
	</table>
	<br>
	<form name=resetPwd onSubmit="return dil(this)" action="ResetPwdRH"
		method="get">
		<table align="center">
			<tr>
				<td>Enter UserName:</td>
				<td><input type="text" onkeypress="return isNumber(event)" name="userName" /></td>
				<td><input type="submit" value="Reset" /></td>
			</tr>
			</table>
		</form>
</body>
</html>