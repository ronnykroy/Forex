/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.34
 * Generated at: 2015-04-14 13:19:52 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import transactionBean.CorpusBean;
import utility.Constants;
import transactionBean.TnxRateBean;
import java.text.*;
import utility.Constants;

public final class TransactionSell_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/footer.jsp", Long.valueOf(1425577437315L));
    _jspx_dependants.put("/header.jsp", Long.valueOf(1425577437326L));
  }

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\r\n");
      out.write("<link href=\"templatemo_style.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("<title>Sell Currency</title>\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\r\n");
      out.write("<title>Header</title>\r\n");
      out.write("<link href=\"templatemo_style.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<center>\r\n");
      out.write("\t\t<div id=\"templatemo_menu_wrapper\">\r\n");
      out.write("\t\t\t<div id=\"templatemo_menu\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<ul>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"/Forex/TransactionRH\">Home</a></li>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"/Forex/TransactionBuyRH\">Buy</a></li>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"/Forex/TransactionSellRH\">Sell</a></li>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"/Forex/TnxSummaryRH\">Summary</a></li>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"/Forex/LogOutRH\">LogOut</a></li>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"ChangePwd.jsp\">Change Password</a></li>\r\n");
      out.write("\t\t\t\t</ul>\r\n");
      out.write("\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t\t<!-- end of templatemo_menu -->\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</center>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\"\r\n");
      out.write("\thref=\"//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css\">\r\n");
      out.write("<script src=\"//code.jquery.com/jquery-1.10.2.js\"></script>\r\n");
      out.write("<script src=\"//code.jquery.com/ui/1.10.4/jquery-ui.js\"></script>\r\n");
      out.write("<link rel=\"stylesheet\" href=\"/resources/demos/style.css\">\r\n");
      out.write("<script>\r\n");
      out.write("  $(function() {\r\n");
      out.write("\t  $(\"#datepicker\").datepicker({dateFormat:'dd-MM-yy',minDate:\"+1D\" ,maxDate:\"+4D\"});\r\n");
      out.write("    \r\n");
      out.write("  });\r\n");
      out.write("  </script>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\r\n");
      out.write("function typeSetter(){\r\n");
      out.write("\tvar type = \"\";\r\n");
      out.write("\tif (document.getElementById('yesCheck').checked) {\r\n");
      out.write("\t  type = document.getElementById('yesCheck').value;\r\n");
      out.write("\t}else if (document.getElementById('noCheck').checked) {\r\n");
      out.write("\t  type = document.getElementById('noCheck').value;\r\n");
      out.write("\t}\r\n");
      out.write("\tdocument.sellForm.tnxType.value = type;\r\n");
      out.write("}\r\n");
      out.write("function preprocessAJAX(){\r\n");
      out.write("\tif(document.sellForm.selectDate.value == \"\"){\r\n");
      out.write("\t\t\talert(\"Please Enter Date\");\r\n");
      out.write("\t\t   document.sellForm.selectDate.focus();\r\n");
      out.write("\t\t   return false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\tajaxSyncRequest(\"ForwardRateRH\");\t\r\n");
      out.write("}\r\n");
      out.write("function ajaxSyncRequest(reqURL)\r\n");
      out.write("\t{\r\n");
      out.write("\t\tvar cur = document.sellForm.sellCurrency.value;\r\n");
      out.write("\t\tvar date = document.getElementById(\"datepicker\").value;\r\n");
      out.write("\t\tvar pURL = reqURL + \"?Currency=\" + cur+ \"&FwdDate=\" + date;\r\n");
      out.write("\t\t//Creating a new XMLHttpRequest object\r\n");
      out.write("\t\tvar xmlhttp;\r\n");
      out.write("\t\tif (window.XMLHttpRequest){\r\n");
      out.write("\t\t\txmlhttp = new XMLHttpRequest(); //for IE7+, Firefox, Chrome, Opera, Safari\r\n");
      out.write("\t\t} else {\r\n");
      out.write("\t\t\txmlhttp = new ActiveXObject(\"Microsoft.XMLHTTP\"); //for IE6, IE5\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t//Create a synchronous GET request\r\n");
      out.write("\t\txmlhttp.open(\"POST\", pURL, false);\r\n");
      out.write("\t\txmlhttp.send(null);\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t//Execution blocked till server send the response\r\n");
      out.write("\t\tif (xmlhttp.readyState == 4) { \r\n");
      out.write("\t\t\tif (xmlhttp.status == 200) \r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\tdocument.getElementById(\"fwdRate\").value = Number(xmlhttp.responseText).toFixed(2);\r\n");
      out.write("\t\t\t\t//alert(xmlhttp.responseText);\r\n");
      out.write("\t\t\t} \r\n");
      out.write("\t\t\telse\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\talert('Unable to Fetch Forward Rates!');\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("function yesnoCheck() {\r\n");
      out.write("\t// Dynamically display Future Options\r\n");
      out.write("\tif(document.sellForm.sellCurrency.value == \"init\")\r\n");
      out.write("\t{\r\n");
      out.write("\t   alert(\"Please Select a Currency to Sell\");\r\n");
      out.write("\t   document.sellForm.sellCurrency.focus();\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tdocument.getElementById('yesCheck').checked = false ;\r\n");
      out.write("\t\tdocument.getElementById('noCheck').checked = true ;\t \r\n");
      out.write("\t   \r\n");
      out.write("\t   return false;\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("    if (document.getElementById('yesCheck').checked) {\r\n");
      out.write("        document.getElementById('ifYesQ').style.display = 'block';\r\n");
      out.write("        document.getElementById('ifYesA').style.display = 'block';\r\n");
      out.write("        document.getElementById('ifYesAmt').style.display = 'block';\r\n");
      out.write("        document.getElementById('ifYesValue').style.display = 'block';\r\n");
      out.write("        document.getElementById(\"futAmountDIV\").style.display = 'block';\r\n");
      out.write("        document.sellForm.fwdCurrency.value = document.sellForm.sellCurrency.value;      \r\n");
      out.write("        document.sellForm.sellCurrency.disabled = true ;\r\n");
      out.write("        document.sellForm.tnxType.value = \"Future\";\r\n");
      out.write("    } else {\r\n");
      out.write("        document.getElementById('ifYesQ').style.display = 'none';\r\n");
      out.write("        document.getElementById('ifYesA').style.display = 'none';\r\n");
      out.write("        document.getElementById('ifYesAmt').style.display = 'none';\r\n");
      out.write("        document.getElementById('ifYesValue').style.display = 'none';\r\n");
      out.write("        document.getElementById('futAmountDIV').style.display = 'none';\r\n");
      out.write("        document.sellForm.sellCurrency.disabled = false ;\r\n");
      out.write("        document.sellForm.tnxType.value = \"Spot\";\r\n");
      out.write("    }\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function isNumber(evt) {\r\n");
      out.write("\t// Restrict entry to only numbers\r\n");
      out.write("    evt = (evt) ? evt : window.event;\r\n");
      out.write("    var charCode = (evt.which) ? evt.which : evt.keyCode;\r\n");
      out.write("    if (charCode > 31 && (charCode < 48 || charCode > 57)) {\r\n");
      out.write("        return false;\r\n");
      out.write("\t}\r\n");
      out.write("    return true;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function dil(form)\r\n");
      out.write("{\r\n");
      out.write("\t// Final Check function before form Submission\r\n");
      out.write("\tif(document.sellForm.noInternet.value == 'true'){\r\n");
      out.write("\t\t   alert(\"No Internet Connection!! Try again Later\");\r\n");
      out.write("\t\t   document.sellForm.amount.focus();\r\n");
      out.write("\t\t   return false;\r\n");
      out.write("\t}\t\r\n");
      out.write("\t//Check for Empty fields\r\n");
      out.write("   if(document.sellForm.amount.value == \"\")\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t   alert(\"Please Enter All Details\");\r\n");
      out.write("\t\t   document.sellForm.amount.focus();\r\n");
      out.write("\t\t   return false;\r\n");
      out.write("\t\t}\r\n");
      out.write("   if(document.sellForm.amount.value < 1000)\r\n");
      out.write("\t{\r\n");
      out.write("\t   alert(\"Minimum Transaction Amount :1000\");\r\n");
      out.write("\t   document.sellForm.amount.focus();\r\n");
      out.write("\t   return false;\r\n");
      out.write("\t}\r\n");
      out.write("   if(document.sellForm.sellCurrency.value == \"init\")\r\n");
      out.write("\t{\r\n");
      out.write("\t   alert(\"Please Select a Currency to Sell\");\r\n");
      out.write("\t   document.sellForm.sellCurrency.focus();\r\n");
      out.write("\t   return false;\r\n");
      out.write("\t}\r\n");
      out.write("\tif (document.getElementById('yesCheck').checked) {\r\n");
      out.write("\t\t  if(document.sellForm.selectDate.value == \"\"){\r\n");
      out.write("\t\t\talert(\"Please Enter All Details\");\r\n");
      out.write("\t\t   document.sellForm.selectDate.focus();\r\n");
      out.write("\t\t   return false;\r\n");
      out.write("\t\t   }\r\n");
      out.write("\t\t   if(document.getElementById(\"fwdRate\").value==\"\"){\r\n");
      out.write("\t\t\talert(\"Please Calculate  Forward Rates\");\r\n");
      out.write("\t\t   document.sellForm.fwdRate.focus();\r\n");
      out.write("\t\t   return false;\r\n");
      out.write("\t\t   }\r\n");
      out.write("\t\t   if(isNaN(document.getElementById(\"fwdRate\").value)){\r\n");
      out.write("\t\t\talert(\"Please Calculate  Forward Rates\");\r\n");
      out.write("\t\t   document.sellForm.fwdRate.focus();\r\n");
      out.write("\t\t   return false;\r\n");
      out.write("\t\t   }\r\n");
      out.write("\t}\r\n");
      out.write("   // Corpus\r\n");
      out.write("  \tvar GBP = document.sellForm.GBP.value;\r\n");
      out.write("\tvar USD = document.sellForm.USD.value;\r\n");
      out.write("\tvar AUD = document.sellForm.AUD.value;\r\n");
      out.write("\tvar EURO = document.sellForm.EURO.value;\r\n");
      out.write("\tvar JPY = document.sellForm.JPY.value;\r\n");
      out.write("\t\t\r\n");
      out.write("\tvar amtValue = document.sellForm.amount.value;\r\n");
      out.write("\tvar currency = document.sellForm.sellCurrency.value;\r\n");
      out.write("\tif( currency == \"GBP\" && Number(amtValue) > Number(GBP)){\r\n");
      out.write("\t\talert(\"Amount Exceeds available limits! Limit : \"+GBP);\r\n");
      out.write("\t\tdocument.sellForm.amount.focus();\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}else if( currency == \"USD\"  && Number(amtValue) > Number(USD) ){\r\n");
      out.write("\t\talert(\"Amount Exceeds available limits! Limit : \"+USD);\r\n");
      out.write("\t\tdocument.sellForm.amount.focus();\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}else if( currency == \"AUD\" && Number(amtValue) > Number(AUD)){\r\n");
      out.write("\t\talert(\"Amount Exceeds available limits! Limit : \"+AUD);\r\n");
      out.write("\t\tdocument.sellForm.amount.focus();\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}else if( currency == \"EURO\" && Number(amtValue) > Number(EURO)){\r\n");
      out.write("\t\talert(\"Amount Exceeds available limits! Limit : \"+EURO);\r\n");
      out.write("\t\tdocument.sellForm.amount.focus();\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}else if( currency == \"JPY\" && Number(amtValue) > Number(JPY)){\r\n");
      out.write("\t\talert(\"Amount Exceeds available limits! Limit : \"+JPY);\r\n");
      out.write("\t\tdocument.sellForm.amount.focus();\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}\r\n");
      out.write("\ttypeSetter();\t\r\n");
      out.write("   \treturn true;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function availAmount(){\r\n");
      out.write("\tvar currency = document.sellForm.sellCurrency.value;\r\n");
      out.write("\tvar inrAvailable = document.sellForm.CorpusINR.value;\r\n");
      out.write("\tvar GBP = document.sellForm.GBP.value;\r\n");
      out.write("\tvar USD = document.sellForm.USD.value;\r\n");
      out.write("\tvar AUD = document.sellForm.AUD.value;\r\n");
      out.write("\tvar EURO = document.sellForm.EURO.value;\r\n");
      out.write("\tvar JPY = document.sellForm.JPY.value;\r\n");
      out.write("\t\r\n");
      out.write("\tif( currency.localeCompare('GBP') == 0){\r\n");
      out.write("\t\tdocument.getElementById(\"availAmountDIV\").innerHTML =\r\n");
      out.write("        \"\"+Number(GBP).toFixed(2);\r\n");
      out.write("\t}else if( currency.localeCompare('USD') == 0){\r\n");
      out.write("\t\tdocument.getElementById(\"availAmountDIV\").innerHTML =\r\n");
      out.write("\t        \"\"+Number(USD).toFixed(2);\r\n");
      out.write("\t}else if( currency.localeCompare('AUD') == 0){\r\n");
      out.write("\t\tdocument.getElementById(\"availAmountDIV\").innerHTML =\r\n");
      out.write("\t\t    \"\"+Number(AUD).toFixed(2);\r\n");
      out.write("\t}else if( currency.localeCompare('EURO') == 0){\r\n");
      out.write("\t\tdocument.getElementById(\"availAmountDIV\").innerHTML =\r\n");
      out.write("\t\t\t \"\"+Number(EURO).toFixed(2);\r\n");
      out.write("\t}else if( currency.localeCompare('JPY') == 0){\r\n");
      out.write("\t\t\tdocument.getElementById(\"availAmountDIV\").innerHTML =\r\n");
      out.write("\t\t\t\t        \"\"+Number(JPY).toFixed(2);\r\n");
      out.write("\t}else{\r\n");
      out.write("\t\t\tdocument.getElementById(\"availAmountDIV\").innerHTML =\r\n");
      out.write("\t\t\t\t        \"****\";\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t");
 
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
	  

      out.write("\r\n");
      out.write("\t<table>\r\n");
      out.write("\t\t<tr align=\"left\" style=\"height: 40px; width: 300px;\">\r\n");
      out.write("\t\t\t<td><h4>");
      out.print( Name );
      out.write("\r\n");
      out.write("\t\t\t\t</h4></td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t</table>\r\n");
      out.write("\t<table border=\"0\" align=\"center\"></table>\r\n");
      out.write("\t<table>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td width=\"700\">\r\n");
      out.write("\t\t\t\t<form name=sellForm onSubmit=\"return dil(this)\"\r\n");
      out.write("\t\t\t\t\taction=\"TransactionSellRH\" method=\"post\">\r\n");
      out.write("\t\t\t\t\t<input type=\"hidden\" id=\"maxAvailable\" /> <input type=\"hidden\"\r\n");
      out.write("\t\t\t\t\t\tid=\"CorpusINR\" value=\"");
      out.print( INR );
      out.write("\" /> <input type=\"hidden\"\r\n");
      out.write("\t\t\t\t\t\tid=\"GBP\" value=\"");
      out.print( GBP );
      out.write("\" /> <input type=\"hidden\" id=\"USD\"\r\n");
      out.write("\t\t\t\t\t\tvalue=\"");
      out.print( USD );
      out.write("\" /> <input type=\"hidden\" id=\"AUD\"\r\n");
      out.write("\t\t\t\t\t\tvalue=\"");
      out.print( AUD );
      out.write("\" /> <input type=\"hidden\" id=\"EURO\"\r\n");
      out.write("\t\t\t\t\t\tvalue=\"");
      out.print( EURO );
      out.write("\" /> <input type=\"hidden\" id=\"JPY\"\r\n");
      out.write("\t\t\t\t\t\tvalue=\"");
      out.print( JPY );
      out.write("\" /> <input type=\"hidden\" id=\"cGBP\"\r\n");
      out.write("\t\t\t\t\t\tvalue=\"");
      out.print( cGBP );
      out.write("\" /> <input type=\"hidden\" id=\"cUSD\"\r\n");
      out.write("\t\t\t\t\t\tvalue=\"");
      out.print( cUSD );
      out.write("\" /> <input type=\"hidden\" id=\"cAUD\"\r\n");
      out.write("\t\t\t\t\t\tvalue=\"");
      out.print( cAUD );
      out.write("\" /> <input type=\"hidden\" id=\"cEURO\"\r\n");
      out.write("\t\t\t\t\t\tvalue=\"");
      out.print( cEURO );
      out.write("\" /> <input type=\"hidden\" id=\"cJPY\"\r\n");
      out.write("\t\t\t\t\t\tvalue=\"");
      out.print( cJPY );
      out.write("\" /> <input type=\"hidden\" id=\"uLimit\"\r\n");
      out.write("\t\t\t\t\t\tvalue=\"");
      out.print( uLimit );
      out.write("\" /> <input type=\"hidden\" id=\"lLimit\"\r\n");
      out.write("\t\t\t\t\t\tvalue=\"");
      out.print( lLimit );
      out.write("\" /> <input type=\"hidden\" id=\"noInternet\"\r\n");
      out.write("\t\t\t\t\t\tvalue=\"");
      out.print( noInternet );
      out.write("\" /> <input type=\"hidden\"\r\n");
      out.write("\t\t\t\t\t\tname=\"displayValue\" id=\"displayValue\" value=\"");
      out.print( vDisplay );
      out.write("\" />\r\n");
      out.write("\t\t\t\t\t<input type=\"hidden\" name=\"tnxType\" id=\"tnxType\" value=\"\" />\r\n");
      out.write("\t\t\t\t\t<table align=\"center\" border=\"0\">\r\n");
      out.write("\t\t\t\t\t\t<tr align=\"center\">\r\n");
      out.write("\t\t\t\t\t\t\t<td colspan=\"2\"><h1>SELL CURRENCY</h1></td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td>Select the currency you want to SELL:</td>\r\n");
      out.write("\t\t\t\t\t\t\t<td><select name=\"sellCurrency\" onchange=\"availAmount(this)\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t");
 for(i=0; i<listLen;i++) {
      out.write("<option\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tvalue=\"");
      out.print( dCurrencyID[i] );
      out.write('"');
      out.write('>');
      out.print( dCurrency[i] );
      out.write("</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t");
} 
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t</select></td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td>Available :</td>\r\n");
      out.write("\t\t\t\t\t\t\t<td><div id=\"availAmountDIV\"></div></td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td>Enter the amount you want to SELL:</td>\r\n");
      out.write("\t\t\t\t\t\t\t<td><input type=\"text\" name=\"amount\"\r\n");
      out.write("\t\t\t\t\t\t\t\tonkeypress=\"return isNumber(event)\"></td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td>Forwards?</td>\r\n");
      out.write("\t\t\t\t\t\t\t<td>Yes<input type=\"radio\" onclick=\"yesnoCheck();\"\r\n");
      out.write("\t\t\t\t\t\t\t\tname=\"yesno\" id=\"yesCheck\" value=\"Future\" />No <input\r\n");
      out.write("\t\t\t\t\t\t\t\ttype=\"radio\" onclick=\"yesnoCheck();\" name=\"yesno\" id=\"noCheck\"\r\n");
      out.write("\t\t\t\t\t\t\t\tvalue=\"Spot\" checked />\r\n");
      out.write("\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t\t<div id=\"ifYesQ\" style=\"display: none\">Select a date:</div>\r\n");
      out.write("\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t<td><div id=\"ifYesA\" style=\"display: none\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<input name=\"selectDate\" type=\"text\" id=\"datepicker\">\r\n");
      out.write("\t\t\t\t\t\t\t\t</div></td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td><div id=\"ifYesAmt\" style=\"display: none\">Forward\r\n");
      out.write("\t\t\t\t\t\t\t\t\tRate:</div></td>\r\n");
      out.write("\t\t\t\t\t\t\t<td><div id=\"ifYesValue\" style=\"display: none\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<input id=\"fwdRate\" readonly=\"readonly\">\r\n");
      out.write("\t\t\t\t\t\t\t\t</div></td>\r\n");
      out.write("\t\t\t\t\t\t\t<td><div id=\"futAmountDIV\" style=\"display: none\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<input type=\"button\" id=\"getFwdRate\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\tvalue=\"Calculate Forward Rates\" onclick='preprocessAJAX()' />\r\n");
      out.write("\t\t\t\t\t\t\t\t</div></td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td></td>\r\n");
      out.write("\t\t\t\t\t\t\t<td align=\"center\"><input type=\"submit\" value=\"Submit\" /> <INPUT\r\n");
      out.write("\t\t\t\t\t\t\t\tTYPE=RESET VALUE=\"CLEAR\"></td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t</form>\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t\t<td width=\"400\"><table width=\"400\">\r\n");
      out.write("\t\t\t\t\t<tr width=\"400\" style=\"height: 28px;\" align=\"center\">\r\n");
      out.write("\t\t\t\t\t\t<th colspan=\"2\"><h3>CURRENCY RATES</h3></th>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>US DOLLAR :</td>\r\n");
      out.write("\t\t\t\t\t\t<td>");
      out.print(df.format(cUSD));
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>GB POUND :</td>\r\n");
      out.write("\t\t\t\t\t\t<td>");
      out.print(df.format(cGBP));
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>AUSTRALIAN DOLLAR :</td>\r\n");
      out.write("\t\t\t\t\t\t<td>");
      out.print(df.format(cAUD));
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>EURO :</td>\r\n");
      out.write("\t\t\t\t\t\t<td>");
      out.print(df.format(cEURO));
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>JAPAN YEN :</td>\r\n");
      out.write("\t\t\t\t\t\t<td>");
      out.print(df.format(cJPY));
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td colspan=\"2\" align=\"center\"><a\r\n");
      out.write("\t\t\t\t\t\t\thref=\"/Forex/TransactionSellRH\">Refresh Exchange Rates</a></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr style=\"height: 4px;\" bgcolor=\"black\"></tr>\r\n");
      out.write("\t\t\t\t\t<tr width=\"400\" style=\"height: 28px;\" align=\"center\">\r\n");
      out.write("\t\t\t\t\t\t<td colspan=\"2\"><h3>MY CORPUS</h3></td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>INDIAN RUPEES :</td>\r\n");
      out.write("\t\t\t\t\t\t<td>");
      out.print(df.format(INR));
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>US DOLLAR :</td>\r\n");
      out.write("\t\t\t\t\t\t<td>");
      out.print(df.format(USD));
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>GB POUND :</td>\r\n");
      out.write("\t\t\t\t\t\t<td>");
      out.print(df.format(GBP));
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>AUSTRALIAN DOLLAR :</td>\r\n");
      out.write("\t\t\t\t\t\t<td>");
      out.print(df.format(AUD));
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>EURO :</td>\r\n");
      out.write("\t\t\t\t\t\t<td>");
      out.print(df.format(EURO));
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td>JAPAN YEN :</td>\r\n");
      out.write("\t\t\t\t\t\t<td>");
      out.print(df.format(JPY));
      out.write("</td>\r\n");
      out.write("\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t</table></td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t</table>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\r\n");
      out.write("<title>Footer</title>\r\n");
      out.write("<link href=\"templatemo_style.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<br>\r\n");
      out.write("\t<br>\r\n");
      out.write("\t<br>\r\n");
      out.write("\t");
  
	String Message;
	if(request.getParameter(Constants.DISPLAY_MSG) == null)
		Message ="******";
	else
	Message = request.getParameter(Constants.DISPLAY_MSG);

      out.write("\r\n");
      out.write("\t<center>\r\n");
      out.write("\t\t<FONT SIZE=\"4\" FACE=\"courier\" COLOR=blue><MARQUEE WIDTH=100%\r\n");
      out.write("\t\t\t\tBEHAVIOR=SCROLL BGColor=yellow LOOP=3>");
      out.print( Message );
      out.write("</MARQUEE></FONT>\r\n");
      out.write("\t</center>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
