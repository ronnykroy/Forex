<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Ajax</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">
<script>
	$(function() {
		$("#datepicker").datepicker({
			minDate : "+7D",
			maxDate : "+30D"
		});
		$("#datepicker").datepicker("option", "dateFormat", "dd-MM-yy");

	});
</script>

<script type="text/javascript">
	function ajaxAsyncRequest(reqURL) {
		//Creating a new XMLHttpRequest object
		var xmlhttp;
		if (window.XMLHttpRequest) {
			xmlhttp = new XMLHttpRequest(); //for IE7+, Firefox, Chrome, Opera, Safari
		} else {
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP"); //for IE6, IE5
		}
		//Create a asynchronous GET request
		xmlhttp.open("GET", reqURL, true);

		//When readyState is 4 then get the server output
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4) {
				if (xmlhttp.status == 200) {
					document.getElementById("message").innerHTML = xmlhttp.responseText;
					//alert(xmlhttp.responseText);
				} else {
					alert('Something is wrong !!');
				}
			}
		};

		xmlhttp.send(null);
	}

	function ajaxSyncRequest(reqURL) {
		var cur = document.getElementById("currency").value;
		var date = document.getElementById("datepicker").value;
		var pURL = reqURL + "?Currency=" + cur+ "&FwdDate=" + date;
		//Creating a new XMLHttpRequest object
		var xmlhttp;
		if (window.XMLHttpRequest) {
			xmlhttp = new XMLHttpRequest(); //for IE7+, Firefox, Chrome, Opera, Safari
		} else {
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP"); //for IE6, IE5
		}

		//Create a synchronous GET request
		xmlhttp.open("POST", pURL, false);
		xmlhttp.send(null);

		//Execution blocked till server send the response
		if (xmlhttp.readyState == 4) {
			if (xmlhttp.status == 200) {
				document.getElementById("message").innerHTML = xmlhttp.responseText;
				//alert(xmlhttp.responseText);
			} else {
				alert('Something is wrong !!');
			}
		}
	}
</script>
</head>
<body>
	<%@ page import="utility.Constants"%>
	<%
		String dCurrency[] = Constants.tnxCurrencyList;
		String dCurrencyID[] = Constants.tnxCurrencyID;
		int listLen = dCurrency.length;
		int i = 0;
	%>
	<br /> Select the currency you want to BUY:
	<select name="currency" id="currency">
		<%
			for (i = 0; i < listLen; i++) {
		%><option value="<%=dCurrencyID[i]%>"><%=dCurrency[i]%></option>
		<%
			}
		%>
	</select>
	<br> Select a date:
	<input name="selectDate" type="text" id="datepicker">
	<br>
	<input type="button" value="Calculate Fwd Rates"
		onclick='ajaxSyncRequest("ForwardRateRH")' />
	<br />
	<br /> Forward Rate :
	<span id="message"></span>
</body>
</html>