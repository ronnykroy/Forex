<%@page import="utility.Constants"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Footer</title>
<link href="templatemo_style.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<br>
	<br>
	<br>
	<%  
	String Message;
	if(request.getParameter(Constants.DISPLAY_MSG) == null)
		Message ="******";
	else
	Message = request.getParameter(Constants.DISPLAY_MSG);
%>
	<center>
		<FONT SIZE="4" FACE="courier" COLOR=blue><MARQUEE WIDTH=100%
				BEHAVIOR=SCROLL BGColor=yellow LOOP=3><%= Message %></MARQUEE></FONT>
	</center>
</body>
</html>