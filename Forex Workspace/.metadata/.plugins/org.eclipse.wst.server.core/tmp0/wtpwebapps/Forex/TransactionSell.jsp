<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="templatemo_style.css" rel="stylesheet" type="text/css" />
<title>Sell Currency</title>
<%@ include file="header.jsp"%>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">
<script>
  $(function() {
	  $("#datepicker").datepicker({dateFormat:'dd-MM-yy',minDate:"+1D" ,maxDate:"+4D"});
    
  });
  </script>
<script type="text/javascript">

function typeSetter(){
	var type = "";
	if (document.getElementById('yesCheck').checked) {
	  type = document.getElementById('yesCheck').value;
	}else if (document.getElementById('noCheck').checked) {
	  type = document.getElementById('noCheck').value;
	}
	document.sellForm.tnxType.value = type;
}
function preprocessAJAX(){
	if(document.sellForm.selectDate.value == ""){
			alert("Please Enter Date");
		   document.sellForm.selectDate.focus();
		   return false;
		}
	ajaxSyncRequest("ForwardRateRH");	
}
function ajaxSyncRequest(reqURL)
	{
		var cur = document.sellForm.sellCurrency.value;
		var date = document.getElementById("datepicker").value;
		var pURL = reqURL + "?Currency=" + cur+ "&FwdDate=" + date;
		//Creating a new XMLHttpRequest object
		var xmlhttp;
		if (window.XMLHttpRequest){
			xmlhttp = new XMLHttpRequest(); //for IE7+, Firefox, Chrome, Opera, Safari
		} else {
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP"); //for IE6, IE5
		}
		
		
		//Create a synchronous GET request
		xmlhttp.open("POST", pURL, false);
		xmlhttp.send(null);
		
		//Execution blocked till server send the response
		if (xmlhttp.readyState == 4) { 
			if (xmlhttp.status == 200) 
			{
				document.getElementById("fwdRate").value = Number(xmlhttp.responseText).toFixed(2);
				//alert(xmlhttp.responseText);
			} 
			else
			{
				alert('Unable to Fetch Forward Rates!');
			}
		}
	}

function yesnoCheck() {
	// Dynamically display Future Options
	if(document.sellForm.sellCurrency.value == "init")
	{
	   alert("Please Select a Currency to Sell");
	   document.sellForm.sellCurrency.focus();
		
		document.getElementById('yesCheck').checked = false ;
		document.getElementById('noCheck').checked = true ;	 
	   
	   return false;
	}
	
    if (document.getElementById('yesCheck').checked) {
        document.getElementById('ifYesQ').style.display = 'block';
        document.getElementById('ifYesA').style.display = 'block';
        document.getElementById('ifYesAmt').style.display = 'block';
        document.getElementById('ifYesValue').style.display = 'block';
        document.getElementById("futAmountDIV").style.display = 'block';
        document.sellForm.fwdCurrency.value = document.sellForm.sellCurrency.value;      
        document.sellForm.sellCurrency.disabled = true ;
        document.sellForm.tnxType.value = "Future";
    } else {
        document.getElementById('ifYesQ').style.display = 'none';
        document.getElementById('ifYesA').style.display = 'none';
        document.getElementById('ifYesAmt').style.display = 'none';
        document.getElementById('ifYesValue').style.display = 'none';
        document.getElementById('futAmountDIV').style.display = 'none';
        document.sellForm.sellCurrency.disabled = false ;
        document.sellForm.tnxType.value = "Spot";
    }
}

function isNumber(evt) {
	// Restrict entry to only numbers
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
	}
    return true;
}

function dil(form)
{
	// Final Check function before form Submission
	if(document.sellForm.noInternet.value == 'true'){
		   alert("No Internet Connection!! Try again Later");
		   document.sellForm.amount.focus();
		   return false;
	}	
	//Check for Empty fields
   if(document.sellForm.amount.value == "")
		{
		   alert("Please Enter All Details");
		   document.sellForm.amount.focus();
		   return false;
		}
   if(document.sellForm.amount.value < 1000)
	{
	   alert("Minimum Transaction Amount :1000");
	   document.sellForm.amount.focus();
	   return false;
	}
   if(document.sellForm.sellCurrency.value == "init")
	{
	   alert("Please Select a Currency to Sell");
	   document.sellForm.sellCurrency.focus();
	   return false;
	}
	if (document.getElementById('yesCheck').checked) {
		  if(document.sellForm.selectDate.value == ""){
			alert("Please Enter All Details");
		   document.sellForm.selectDate.focus();
		   return false;
		   }
		   if(document.getElementById("fwdRate").value==""){
			alert("Please Calculate  Forward Rates");
		   document.sellForm.fwdRate.focus();
		   return false;
		   }
		   if(isNaN(document.getElementById("fwdRate").value)){
			alert("Please Calculate  Forward Rates");
		   document.sellForm.fwdRate.focus();
		   return false;
		   }
	}
   // Corpus
  	var GBP = document.sellForm.GBP.value;
	var USD = document.sellForm.USD.value;
	var AUD = document.sellForm.AUD.value;
	var EURO = document.sellForm.EURO.value;
	var JPY = document.sellForm.JPY.value;
		
	var amtValue = document.sellForm.amount.value;
	var currency = document.sellForm.sellCurrency.value;
	if( currency == "GBP" && Number(amtValue) > Number(GBP)){
		alert("Amount Exceeds available limits! Limit : "+GBP);
		document.sellForm.amount.focus();
		return false;
	}else if( currency == "USD"  && Number(amtValue) > Number(USD) ){
		alert("Amount Exceeds available limits! Limit : "+USD);
		document.sellForm.amount.focus();
		return false;
	}else if( currency == "AUD" && Number(amtValue) > Number(AUD)){
		alert("Amount Exceeds available limits! Limit : "+AUD);
		document.sellForm.amount.focus();
		return false;
	}else if( currency == "EURO" && Number(amtValue) > Number(EURO)){
		alert("Amount Exceeds available limits! Limit : "+EURO);
		document.sellForm.amount.focus();
		return false;
	}else if( currency == "JPY" && Number(amtValue) > Number(JPY)){
		alert("Amount Exceeds available limits! Limit : "+JPY);
		document.sellForm.amount.focus();
		return false;
	}
	typeSetter();	
   	return true;
}

function availAmount(){
	var currency = document.sellForm.sellCurrency.value;
	var inrAvailable = document.sellForm.CorpusINR.value;
	var GBP = document.sellForm.GBP.value;
	var USD = document.sellForm.USD.value;
	var AUD = document.sellForm.AUD.value;
	var EURO = document.sellForm.EURO.value;
	var JPY = document.sellForm.JPY.value;
	
	if( currency.localeCompare('GBP') == 0){
		document.getElementById("availAmountDIV").innerHTML =
        ""+Number(GBP).toFixed(2);
	}else if( currency.localeCompare('USD') == 0){
		document.getElementById("availAmountDIV").innerHTML =
	        ""+Number(USD).toFixed(2);
	}else if( currency.localeCompare('AUD') == 0){
		document.getElementById("availAmountDIV").innerHTML =
		    ""+Number(AUD).toFixed(2);
	}else if( currency.localeCompare('EURO') == 0){
		document.getElementById("availAmountDIV").innerHTML =
			 ""+Number(EURO).toFixed(2);
	}else if( currency.localeCompare('JPY') == 0){
			document.getElementById("availAmountDIV").innerHTML =
				        ""+Number(JPY).toFixed(2);
	}else{
			document.getElementById("availAmountDIV").innerHTML =
				        "****";
						}
}
</script>
</head>
<body>
	<%@ page import="java.sql.*"%>
	<%@ page import="java.io.*"%>
	<%@ page import="javax.servlet.*"%>
	<%@ page import="transactionBean.CorpusBean"%>
	<%@ page import="utility.Constants"%>
	<%@page import="transactionBean.TnxRateBean"%>
	<%@ page import="java.text.*"%>
	<% 
	if(session.getAttribute(Constants.corpusBean) == null){
		response.sendRedirect("/Forex/LogOutRH");
	}
	CorpusBean myCorpus = new CorpusBean();
	myCorpus =  (CorpusBean) session.getAttribute(Constants.corpusBean);
	TnxRateBean rateBean = new TnxRateBean();
	rateBean = (TnxRateBean)session.getAttribute(Constants.rateBean);
	DecimalFormat df = new DecimalFormat("#.##");
	
	//Corpus Value
	double INR = 0.00;
	double GBP = 0.00;
	double USD = 0.00;
	double AUD = 0.00;
	double EURO = 0.00;
	double JPY = 0.00;
	//Tnx Rate
	double cGBP = 0.00;
	double cUSD = 0.00;
	double cAUD = 0.00;
	double cEURO = 0.00;
	double cJPY = 0.00;
	
	//Circuit
	double circuitValue = 0.100 ;
	boolean noInternet = false ;
	
	int rollNbr = myCorpus.getRollNbr();
	String Name = myCorpus.getFirstName().concat(" ").concat(myCorpus.getLastName());
	String dCurrency[] = Constants.tnxCurrencyList;
	String dCurrencyID[] = Constants.tnxCurrencyID;
		if(myCorpus != null){
			 // Corpus Value
			 INR = myCorpus.getInR();
			 GBP = myCorpus.getAllowableGBP();
			 USD = myCorpus.getAllowableUSD();
			 AUD = myCorpus.getAllowableAUD();
			 EURO = myCorpus.getAllowableEURO();
			 JPY = myCorpus.getAllowableJPY();
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
				<form name=sellForm onSubmit="return dil(this)"
					action="TransactionSellRH" method="post">
					<input type="hidden" id="maxAvailable" /> <input type="hidden"
						id="CorpusINR" value="<%= INR %>" /> <input type="hidden"
						id="GBP" value="<%= GBP %>" /> <input type="hidden" id="USD"
						value="<%= USD %>" /> <input type="hidden" id="AUD"
						value="<%= AUD %>" /> <input type="hidden" id="EURO"
						value="<%= EURO %>" /> <input type="hidden" id="JPY"
						value="<%= JPY %>" /> <input type="hidden" id="cGBP"
						value="<%= cGBP %>" /> <input type="hidden" id="cUSD"
						value="<%= cUSD %>" /> <input type="hidden" id="cAUD"
						value="<%= cAUD %>" /> <input type="hidden" id="cEURO"
						value="<%= cEURO %>" /> <input type="hidden" id="cJPY"
						value="<%= cJPY %>" /> <input type="hidden" id="uLimit"
						value="<%= uLimit %>" /> <input type="hidden" id="lLimit"
						value="<%= lLimit %>" /> <input type="hidden" id="noInternet"
						value="<%= noInternet %>" /> <input type="hidden"
						name="displayValue" id="displayValue" value="<%= vDisplay %>" />
					<input type="hidden" name="tnxType" id="tnxType" value="" />
					<table align="center" border="0">
						<tr align="center">
							<td colspan="2"><h1>SELL CURRENCY</h1></td>
						</tr>
						<tr>
							<td>Select the currency you want to SELL:</td>
							<td><select name="sellCurrency" onchange="availAmount(this)">
									<% for(i=0; i<listLen;i++) {%><option
										value="<%= dCurrencyID[i] %>"><%= dCurrency[i] %></option>
									<%} %>
							</select></td>
						</tr>
						<tr>
							<td>Available :</td>
							<td><div id="availAmountDIV"></div></td>
						</tr>
						<tr>
							<td>Enter the amount you want to SELL:</td>
							<td><input type="text" name="amount"
								onkeypress="return isNumber(event)"></td>
						</tr>
						<tr>
							<td>Forwards?</td>
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
									<input name="selectDate" type="text" id="datepicker">
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
							href="/Forex/TransactionSellRH">Refresh Exchange Rates</a></td>
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
						<td><%=df.format(USD)%></td>
					</tr>
					<tr>
						<td>GB POUND :</td>
						<td><%=df.format(GBP)%></td>
					</tr>
					<tr>
						<td>AUSTRALIAN DOLLAR :</td>
						<td><%=df.format(AUD)%></td>
					</tr>
					<tr>
						<td>EURO :</td>
						<td><%=df.format(EURO)%></td>
					</tr>
					<tr>
						<td>JAPAN YEN :</td>
						<td><%=df.format(JPY)%></td>
					</tr>
				</table></td>
		</tr>
	</table>
</body>
</html>
<%@ include file="footer.jsp"%>