<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="templatemo_style.css" rel="stylesheet" type="text/css" />
<title>Buy Currency</title>
<%@ include file="header.jsp"%>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">
<script>
  $(function() {
    $("#datepicker").datepicker({dateFormat:'dd-MM-yy',minDate:"+1D" ,maxDate:"+4D" });
    
  });
  </script>
  <script type="text/javascript" src="js/util.js"></script>
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
	if(session.getAttribute(Constants.corpusBean) == null){
		response.sendRedirect("/Forex/LogOutRH");
	}
	CorpusBean myCorpus = new CorpusBean();
	myCorpus =  (CorpusBean) session.getAttribute(Constants.corpusBean);
	TnxRateBean rateBean = new TnxRateBean();
	rateBean = (TnxRateBean)session.getAttribute(Constants.rateBean);
	DecimalFormat df = new DecimalFormat("#.##");
	
	double INR = 0.00;
	double GBP = 0.00;
	double USD = 0.00;
	double AUD = 0.00;
	double EURO = 0.00;
	double JPY = 0.00;
	double awINR = 0.00;
	
	double CorpGBP = 0.00;
	double CorpUSD = 0.00;
	double CorpAUD = 0.00;
	double CorpEURO = 0.00;
	double CorpJPY = 0.00;
	//Tnx Rate
	double cGBP = 0.00;
	double cUSD = 0.00;
	double cAUD = 0.00;
	double cEURO = 0.00;
	double cJPY = 0.00;
	
	//Circuit
	double circuitValue = 0.100 ;
	boolean noInternet = false ;
	
	
	
	int rollNbr =  0 ;
	rollNbr = myCorpus.getRollNbr();
	String Name = myCorpus.getFirstName().concat(" ").concat(myCorpus.getLastName());
	String dCurrency[] = Constants.tnxCurrencyList;
	String dCurrencyID[] = Constants.tnxCurrencyID;
		if(myCorpus != null){
			 INR = Double.parseDouble(df.format( myCorpus.getInR()));
			 GBP = myCorpus.getGbpMax();
			 USD = myCorpus.getUsdMax();
			 AUD = myCorpus.getAudMax();
			 EURO = myCorpus.getEuroMax();
			 JPY = myCorpus.getJpyMax();
			 awINR = myCorpus.getAllowableINR();
			 
			 CorpGBP = myCorpus.getAllowableGBP();
			 CorpUSD = myCorpus.getAllowableUSD();
			 CorpAUD = myCorpus.getAllowableAUD();
			 CorpEURO = myCorpus.getAllowableEURO();
			 CorpJPY = myCorpus.getAllowableJPY();
			 
			}
	if(rateBean != null){
			 // Transfer Rates
			 cGBP = rateBean.getGBP();
			 cUSD = rateBean.getUSD();
			 cAUD = rateBean.getAUD();
			 cEURO = rateBean.getEURO();
			 cJPY = rateBean.getJPY();
			 circuitValue = rateBean.getCircuit_limit();
			 noInternet = rateBean.isError();
			}else{
			noInternet = true;	
		}
		if(noInternet){
			 INR = myCorpus.getInR();
			 GBP = 0.00;
			 USD = 0.00;
			 AUD = 0.00;
			 EURO = 0.00;
			 JPY = 0.00;
			 
			 CorpGBP = myCorpus.getGbP();
			 CorpUSD = myCorpus.getUsD();
			 CorpAUD = myCorpus.getAuD();
			 CorpEURO = myCorpus.getEuR();
			 CorpJPY = myCorpus.getJpY();
		
			 cGBP = 0.00;
			 cUSD = 0.00;
			 cAUD = 0.00;
			 cEURO = 0.00;
			 cJPY = 0.00;
		
		}
		
	int listLen = dCurrency.length;
	int i=0;
	double maxAmt = 0.00;
	double vDisplay = 0.00;
	double uLimit = 1.00 + circuitValue;
	double lLimit = 1.00 - circuitValue;		
	  
%>
	<table>
		<tr align="left" style="height: 40px; width: 300px;">
			<td><h4><%= Name %>
				</h4></td>
		</tr>
	</table>
	<table border="0" align="center"></table>
	<table>
		<tr>
			<td width="700">

				<form name=buyForm onSubmit="return dil(this)"
					action="TransactionBuyRH" method="post">
					<input type="hidden" id="maxAvailable" /> <input type="hidden"
						id="CorpusINR" value="<%= INR %>" /> <input type="hidden"
						id="awINR" value="<%= awINR %>" /> <input type="hidden" id="GBP"
						value="<%= GBP %>" /> <input type="hidden" id="USD"
						value="<%= USD %>" /> <input type="hidden" id="AUD"
						value="<%= AUD %>" /> <input type="hidden" id="EURO"
						value="<%= EURO %>" /> <input type="hidden" id="JPY"
						value="<%= JPY %>" /> <input type="hidden" id="CorpusGBP"
						value="<%= CorpGBP %>" /> <input type="hidden" id="CorpusUSD"
						value="<%= CorpUSD %>" /> <input type="hidden" id="CorpusAUD"
						value="<%= CorpAUD %>" /> <input type="hidden" id="CorpusEURO"
						value="<%= CorpEURO %>" /> <input type="hidden" id="CorpusJPY"
						value="<%= CorpJPY %>" /> <input type="hidden" id="cGBP"
						value="<%= cGBP %>" /> <input type="hidden" id="cUSD"
						value="<%= cUSD %>" /> <input type="hidden" id="cAUD"
						value="<%= cAUD %>" /> <input type="hidden" id="cEURO"
						value="<%= cEURO %>" /> <input type="hidden" id="cJPY"
						value="<%= cJPY %>" /> <input type="hidden" id="noInternet"
						value="<%= noInternet %>" /> <input type="hidden" id="uLimit"
						value="<%= uLimit %>" /> <input type="hidden" id="lLimit"
						value="<%= lLimit %>" /> <input type="hidden" name="tnxType"
						id="tnxType" value="" /> <input type="hidden" name="fwdCurrency"
						id="fwdCurrency" value="" />
					<table align="center" border="0">
						<tr align="center">
							<td colspan="2"><h1>BUY CURRENCY</h1></td>
						</tr>
						<tr>
							<td>Corpus Available :</td>
							<td>Rs.<%= df.format(INR) %></td>
						</tr>
						<tr>
							<td>Select the currency you want to BUY:</td>
							<td><select name="buyCurrency" id="buyCurrency"
								onchange="maxAmount(this)">
									<% for(i=0; i<listLen;i++) {%><option
										value="<%= dCurrencyID[i] %>"><%= dCurrency[i] %></option>
									<%} %>
							</select></td>
						</tr>
						<tr>
							<td>Enter the amount you want to BUY:</td>
							<td><input type="text" name="amount"
								onkeypress="return isNumber(event)"></td>
							<td><div id="maxAmountDIV"></div></td>
						</tr>
						<tr>
							<td>Forward Transfer?</td>
							<td>Yes<input type="radio" onclick="yesnoCheck();"
								name="yesno" id="yesCheck" value="Future" />No <input
								type="radio" onclick="yesnoCheck();" name="yesno" id="noCheck"
								value="Spot" checked />
							</td>
						</tr>
						<tr>
							<td>
								<div id="ifYesQ" style="display: none">Select a date:</div>
							</td>
							<td><div id="ifYesA" style="display: none">
									<input name="selectDate" type="text" id="datepicker"
										onselect="resetDate()">
								</div></td>
						</tr>
						<tr>
							<td><div id="ifYesAmt" style="display: none">Forward
									Rate:</div></td>
							<td><div id="ifYesValue" style="display: none">
									<input id="fwdRate" readonly="readonly">
								</div></td>
							<td><div id="futAmountDIV" style="display: none">
									<input type="button" id="getFwdRate"
										value="Calculate Forward Rates" onclick='preprocessAJAX()' />
								</div></td>
						</tr>
						<tr>
							<td></td>
							<td align="center"><input type="submit" value="Submit" /> <INPUT
								TYPE=RESET VALUE="CLEAR"></td>
						</tr>
					</table>
				</form>
			</td>
			<td width="400"><table width="400">
					<tr width="400" style="height: 28px;" align="center">
						<th colspan="2"><h3>CURRENCY RATES</h3></th>
					</tr>
					<tr>
						<td>US DOLLAR :</td>
						<td><%=df.format(cUSD)%></td>
					</tr>
					<tr>
						<td>GB POUND :</td>
						<td><%=df.format(cGBP)%></td>
					</tr>
					<tr>
						<td>AUSTRALIAN DOLLAR :</td>
						<td><%=df.format(cAUD)%></td>
					</tr>
					<tr>
						<td>EURO :</td>
						<td><%=df.format(cEURO)%></td>
					</tr>
					<tr>
						<td>JAPAN YEN :</td>
						<td><%=df.format(cJPY)%></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><a
							href="/Forex/TransactionBuyRH">Refresh Exchange Rates</a></td>
					</tr>
					<tr style="height: 4px;" bgcolor="black"></tr>
					<tr width="400" style="height: 28px;" align="center">
						<td colspan="2"><h3>MY CORPUS</h3></td>
					</tr>
					<tr>
						<td>INDIAN RUPEES :</td>
						<td><%=df.format(INR)%></td>
					</tr>
					<tr>
						<td>US DOLLAR :</td>
						<td><%=df.format(CorpUSD)%></td>
					</tr>
					<tr>
						<td>GB POUND :</td>
						<td><%=df.format(CorpGBP)%></td>
					</tr>
					<tr>
						<td>AUSTRALIAN DOLLAR :</td>
						<td><%=df.format(CorpAUD)%></td>
					</tr>
					<tr>
						<td>EURO :</td>
						<td><%=df.format(CorpEURO)%></td>
					</tr>
					<tr>
						<td>JAPAN YEN :</td>
						<td><%=df.format(CorpJPY)%></td>
					</tr>
				</table></td>
		</tr>
	</table>
</body>
</html>
<%@ include file="footer.jsp"%>