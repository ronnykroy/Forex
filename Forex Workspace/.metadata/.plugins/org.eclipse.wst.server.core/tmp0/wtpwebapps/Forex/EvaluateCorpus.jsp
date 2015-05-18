<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="templatemo_style.css" rel="stylesheet" type="text/css" />
<title>My Corpus</title>
<%@ include file="header.jsp"%>
<script type="text/javascript">
function gotoBUY(){
	window.location = "TransactionBuyRH";
}
function gotoSELL(){
	window.location = "TransactionSellRH";
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
	CorpusBean myCorpus = new CorpusBean();
	myCorpus =  (CorpusBean) session.getAttribute(Constants.corpusBean);
	
	TnxRateBean rate = new TnxRateBean(); 
	rate =  (TnxRateBean) session.getAttribute(Constants.rateBean);
	
	int rollNbr = myCorpus.getRollNbr();
	String Name = myCorpus.getFirstName().concat(" ").concat(myCorpus.getLastName());
	DecimalFormat df = new DecimalFormat("#.##");
	
	int nbrFwd = 0;
	Integer oNbrFwd = (Integer)session.getAttribute(Constants.nbrFwds);
	
	if(oNbrFwd != null){
		nbrFwd = oNbrFwd.intValue();
	}
	
%>
	<table align="left">
		<h4>
			Welcome
			<%= Name %>
		</h4>
		<tr>
		</tr>
	</table>
	<br>
	<br>
	<br>
	<br>
	<table cellspacing="10" align="center">
		<tr>
			<td>
				<table cellspacing="6" cellpadding="3" align="right">
					<tr>
						<td colspan="2"><h1>MY CORPUS</h1></td>
					</tr>
					<tr>
						<td>INDIAN RUPEES</td>
						<td><%= df.format(myCorpus.getInR()) %></td>
					</tr>
					<tr>
						<td>US DOLLARS</td>
						<td><%= df.format(myCorpus.getUsD()) %></td>
					</tr>
					<tr>
						<td>GB POUNDS</td>
						<td><%= df.format(myCorpus.getGbP()) %></td>
					</tr>
					<tr>
						<td>JAPAN YEN</td>
						<td><%= df.format(myCorpus.getJpY()) %></td>
					</tr>
					<tr>
						<td>AUS DOLLARS</td>
						<td><%= df.format(myCorpus.getAuD()) %></td>
					</tr>
					<tr>
						<td>EURO</td>
						<td><%= df.format(myCorpus.getEuR()) %></td>
					</tr>
					<tr>
						<td>Net Worth</td>
						<td><%= df.format(myCorpus.getTotal_NetWorth()) %></td>
					</tr>
				</table>
			</td>
			<td>Current Rank: <%=  %>
			</td>
		</tr>
	</table>
</body>
</html>
<%@ include file="footer.jsp"%>