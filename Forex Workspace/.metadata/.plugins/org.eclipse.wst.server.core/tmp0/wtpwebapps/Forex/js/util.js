function typeSetter(){
var type = "";
if (document.getElementById('yesCheck').checked) {
  type = document.getElementById('yesCheck').value;
}else if (document.getElementById('noCheck').checked) {
  type = document.getElementById('noCheck').value;
}
document.buyForm.tnxType.value = type;
}
function preprocessAJAX(){
	if(document.buyForm.selectDate.value == ""){
			alert("Please Enter Date");
		   document.buyForm.selectDate.focus();
		   return false;
		}
		
	ajaxSyncRequest("ForwardRateRH");	
}
function ajaxSyncRequest(reqURL)
	{
		var cur = document.getElementById("buyCurrency").value;
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
	 if(document.buyForm.buyCurrency.value == "init"){
	   alert("Please Select a Currency to Buy");
	   document.buyForm.buyCurrency.focus();
	   
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
        document.buyForm.fwdCurrency.value = document.buyForm.buyCurrency.value;      
        document.buyForm.buyCurrency.disabled = true ;
        document.buyForm.tnxType.value = 'Future';
        
        
    } else {
        document.getElementById('ifYesQ').style.display = 'none';
        document.getElementById('ifYesA').style.display = 'none';
        document.getElementById('ifYesAmt').style.display = 'none';
        document.getElementById('ifYesValue').style.display = 'none';
        document.getElementById('futAmountDIV').style.display = 'none';
        document.buyForm.buyCurrency.disabled = false ;
        
        document.getElementById("fwdRate").value = "****";
        document.getElementById("datepicker").value = "";
        
        document.buyForm.tnxType.value = 'Spot';
    }
  }
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
	if(document.buyForm.noInternet.value == 'true'){
		   alert("No Internet Connection!! Try again Later");
		   document.buyForm.amount.focus();
		   return false;
	}
	if(document.buyForm.buyCurrency.value == "init"){
	   alert("Please Select a Currency to Buy");
	   document.buyForm.buyCurrency.focus();
	   return false;
	}
   if(document.buyForm.amount.value == ""){
		   alert("Please Enter All Details");
		   document.buyForm.amount.focus();
		   return false;
		}
   if(document.buyForm.amount.value < 1000){
	   alert("Minimum Transaction Amount :1000");
	   document.buyForm.amount.focus();
	   return false;
	}

	if (document.getElementById('yesCheck').checked) {
		  if(document.buyForm.selectDate.value == ""){
			alert("Please Enter All Details");
		   document.buyForm.selectDate.focus();
		   return false;
		   }
		   if(document.getElementById("fwdRate").value==""){
			alert("Please Calculate  Forward Rates");
		   document.buyForm.fwdRate.focus();
		   return false;
		   }
		   if(isNaN(document.getElementById("fwdRate").value)){
			alert("Please Calculate  Forward Rates");
		   document.buyForm.fwdRate.focus();
		   return false;
		   }
	}
   // Corpus
  	var GBP = document.buyForm.GBP.value;
	var USD = document.buyForm.USD.value;
	var AUD = document.buyForm.AUD.value;
	var EURO = document.buyForm.EURO.value;
	var JPY = document.buyForm.JPY.value;
	
	var amtValue = document.buyForm.amount.value;
	
	var currency = document.buyForm.buyCurrency.value;
		if( currency == "GBP" && Number(amtValue) > Number(GBP)){
		alert("Amount Exceeds available limits! Limit : "+GBP);
		document.buyForm.amount.focus();
		return false;
	}else if( currency == "USD"  && Number(amtValue) > Number(USD) ){
		alert("Amount Exceeds available limits! Limit : "+USD);
		document.buyForm.amount.focus();
		return false;
	}else if( currency == "AUD" && Number(amtValue) > Number(AUD)){
		alert("Amount Exceeds available limits! Limit : "+AUD);
		document.buyForm.amount.focus();
		return false;
	}else if( currency == "EURO" && Number(amtValue) > Number(EURO)){
		alert("Amount Exceeds available limits! Limit : "+EURO);
		document.buyForm.amount.focus();
		return false;
	}else if( currency == "JPY" && Number(amtValue) > Number(JPY)){
		alert("Amount Exceeds available limits! Limit : "+JPY);
		document.buyForm.amount.focus();
		return false;
	}
   typeSetter();
   	return true;
}
function calMaxAmt(){
	var amtValue = document.buyForm.amount.value;
	var iNR = document.buyForm.awINR.value;
	//alert(amtValue+"  "+iNR);
	document.getElementById("maxAmountDIV").innerHTML =
	                                      "Max :"+amtEnd*iNR;
}
function maxAmount(){
	var currency = document.buyForm.buyCurrency.value;
	var inrAvailable = document.buyForm.CorpusINR.value;
	var GBP = document.buyForm.GBP.value;
	var USD = document.buyForm.USD.value;
	var AUD = document.buyForm.AUD.value;
	var EURO = document.buyForm.EURO.value;
	var JPY = document.buyForm.JPY.value;
	if(document.buyForm.noInternet.value == 'false'){
		if( currency.localeCompare('GBP') == 0){
			document.getElementById("maxAmountDIV").innerHTML =
	        "Max  Buyable:"+Number(GBP).toFixed(2);
		}else if( currency.localeCompare('USD') == 0){
			document.getElementById("maxAmountDIV").innerHTML =
		        "Max  Buyable:"+Number(USD).toFixed(2);
		}else if( currency.localeCompare('AUD') == 0){
			document.getElementById("maxAmountDIV").innerHTML =
			    "Max  Buyable:"+Number(AUD).toFixed(2);
		}else if( currency.localeCompare('EURO') == 0){
			document.getElementById("maxAmountDIV").innerHTML =
				 "Max  Buyable:"+Number(EURO).toFixed(2);
		}else if( currency.localeCompare('JPY') == 0){
				document.getElementById("maxAmountDIV").innerHTML =
					        "Max  Buyable:"+Number(JPY).toFixed(2);
		}else{
				document.getElementById("maxAmountDIV").innerHTML =
					        "Max  Buyable: ****";
							}
	}else{
				document.getElementById("maxAmountDIV").innerHTML =
					        "Max  Buyable: ****";
		
	}					
}