<%@page import="transactionBean.ReportBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="templatemo_style.css" rel="stylesheet" type="text/css" />
<title>Evaluate Performance</title>
<script type="text/javascript">
function IndvEvalFn(rollNbr){
	alert("Before redirecting"+rollNbr);
	window.location = "EvaluateSummaryRH?rollNbr="+rollNbr;
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
		
DecimalFormat df = new DecimalFormat("#,###");
	int rows= 1;
	String Forward = "Forward ";
	String tnx_type ="";
	ArrayList<EvaluateBean> report = new ArrayList<EvaluateBean>();
	if((session.getAttribute(Constants.EVALUATE_BEAN_LIST)) != null){
		report = (ArrayList<EvaluateBean>) session.getAttribute(Constants.EVALUATE_BEAN_LIST);
	}
	
%>
	<br>
	<br>
	<table border="0" cellspacing="0" width="95%" align="center">
		<tr>
			<td align="left"><a href="/Forex/EvaluatePerformance">Refresh</a></td>
			<td align="right"><a href="adminHome.jsp">Home</a></td>
		</tr>
	</table>
	<br>
	<br>
	<table cellspacing="3" cellpadding="3" width="95%" align="center" border="2">
		<tr>
			<td colspan="7" align="center"><h1>PERFORMANCE EVALUATION</h1></td>
		</tr>
		<tr>
			<h2>
				<td align="center">RANK</td>
				<td align="center">USER</td>
				<td align="center">CORPUS VALUE</td>
				<td align="center">SPOT BUYS</td>
				<td align="center">SPOT SELLS</td>
				<td align="center">FORWARD BUYS</td>
				<td align="center">FORWARD SELLS</td>
		</tr>
		<% for (EvaluateBean iReport : report) { %>
		<tr>
			<td align="center"><%= iReport.getRank() %></td>
			<td align="center"><a id="indvEvaluation"
				href="EvaluateSummaryRH?rollNbr=<%= iReport.getRollNbr() %>"><%= iReport.getRollNbr() %></a></td>
			<td align="center"><%= df.format(iReport.getTotalValue()) %></td>
			<td align="center"><%= df.format(iReport.getNbrSales()) %></td>
			<td align="center"><%= df.format(iReport.getNbrPurchases()) %></td>
			<td align="center"><%= df.format(iReport.getNbrFwdSales()) %></td>
			<td align="center"><%= df.format(iReport.getNbrFwdPurchases()) %></td>
		</tr>
		<% } %>

	</table>
</body>
</html>