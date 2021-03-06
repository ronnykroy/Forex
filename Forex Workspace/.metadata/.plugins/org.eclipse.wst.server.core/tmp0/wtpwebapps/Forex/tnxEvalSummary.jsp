<%@page import="transactionBean.ReportBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="templatemo_style.css" rel="stylesheet" type="text/css" />
<title>Transaction Summary Evaluation</title>
</head>
<body>
	<%@ page import="java.sql.*"%>
	<%@ page import="java.io.*"%>
	<%@ page import="javax.servlet.*"%>
	<%@ page import="transactionBean.*"%>
	<%@ page import="utility.*"%>
	<%@ page import="java.text.*"%>
	<%  
	CorpusBean myCorpus = new CorpusBean();
	if(session.getAttribute(Constants.corpusBean) != null){
		myCorpus =  (CorpusBean) session.getAttribute(Constants.corpusBean);
		if(myCorpus.isAdmin()){
			String Name = myCorpus.getFirstName().concat(" ").concat(myCorpus.getLastName());
		}else{
			//redirect block here
		}
	}else{
		//redirect block here
	}
	EvaluateBean eBean =  new EvaluateBean();
	 
	myCorpus =  (CorpusBean) session.getAttribute(Constants.corpusBean);
	eBean = (EvaluateBean) session.getAttribute(Constants.EVALUATE_BEAN);
	/*
	int rank = Integer.parseInt((String)session.getAttribute(Constants.RANK));
	float totalValue = Float.parseFloat((String) session.getAttribute(Constants.TOTALVALUE));
	*/
	int rank = eBean.getRank();
	float totalValue = eBean.getTotalValue();
	String Name = myCorpus.getFirstName().concat(" ").concat(myCorpus.getLastName());
	DecimalFormat df = new DecimalFormat("#,###");
	int rows= 1;
	String Forward = "Forward ";
	String tnx_type ="";
	ArrayList<ReportBean> report = new ArrayList<ReportBean>();
	if((session.getAttribute(Constants.REPORT)) != null){
		report = (ArrayList<ReportBean>) session.getAttribute(Constants.REPORT);
	}
	
	
%>
	<br>
	<br>
	<table cellspacing="2" cellpadding="0" align="center" border="0" >
	<tr>
			<td align="left"><a href="/Forex/EvaluatePerformance">Back</a></td>
			<td width="10%"></td>
			<td align="right"><a href="adminHome.jsp">Home</a></td>
		</tr>
		<tr>
			<td>
	<table border="0" cellspacing="0" width="95%" align="center">
	</table>
	<table cellspacing="6" cellpadding="3" align="center" border="2">
		<tr>
			<td colspan="4" align="LEFT"><h1>
					CORPUS VALUE:
					<%= df.format(totalValue) %>
				</h1></td>
			<td colspan="1" align="LEFT"><h1>
					RANK :
					<%= rank %>
				</h1></td>
		</tr>
		<tr height="2" bgcolor="black">
			<td colspan="5" align="center"></td>
		</tr>
		<tr>
			<td colspan="5" align="center"><h1>TRANSACTION SUMMARY</h1></td>
		</tr>
		<tr>
			<h2>
				<td align="center">Transaction ID</td>
				<td align="center">Transaction Type</td>
				<td align="center">Transaction Description</td>
				<td align="center">Amount</td>
				<td align="center">Date</td>
			</h2>
		</tr>
		<% for (ReportBean iReport : report) { %>
		<tr>
			<td align="center"><%= iReport.getTnx_id() %></td>
			<% if(iReport.getIs_future()==1){ tnx_type = Forward+ iReport.getTnx_type() ;
		}else{	tnx_type = iReport.getTnx_type() ;	}%>
			<td align="center"><%= tnx_type %></td>
			<td align="center"><%= iReport.getFrom_crate()+" "+ iReport.getFrom_currency()+" = "+iReport.getTo_cny_rate()+" "+ iReport.getTo_cny() %></td>
			<td align="center"><%= iReport.getAmount() %></td>
			<td align="center"><%= iReport.getExec_date() %></td>
		</tr>
		<% } %>

	</table>
	</td>
			<td width="10%"></td>
			<td>
				<table cellspacing="6" cellpadding="3" align="center" border="2">
					<tr>
						<td>SALES</td>
						<td align="right"><%= df.format(eBean.getSales()) %></td>
					</tr>
					<tr>
						<td>PURCHASE</td>
						<td align="right"><%= df.format(eBean.getPurchase()) %></td>
					</tr>
					<tr>
						<td>CLOSING INVENTORY</td>
						<td align="right"><%= df.format(eBean.getClInv()) %></td>
					</tr>
					<tr>
						<td>PROFIT</td>
						<td align="right"><%= df.format(eBean.getProfit()) %></td>
					</tr>
				</table>
			</td>
		</tr>
</body>
</html>