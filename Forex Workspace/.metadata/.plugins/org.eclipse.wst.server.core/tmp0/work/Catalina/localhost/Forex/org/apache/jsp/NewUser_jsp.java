/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.34
 * Generated at: 2015-04-12 11:41:45 UTC
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
import java.text.*;
import transactionBean.TnxRateBean;
import utility.Constants;

public final class NewUser_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/footer.jsp", Long.valueOf(1425577437315L));
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
      out.write("<title>Register New User</title>\r\n");
      out.write("<link rel=\"stylesheet\"\r\n");
      out.write("\thref=\"//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css\">\r\n");
      out.write("<script src=\"//code.jquery.com/jquery-1.10.2.js\"></script>\r\n");
      out.write("<script src=\"//code.jquery.com/ui/1.10.4/jquery-ui.js\"></script>\r\n");
      out.write("<link rel=\"stylesheet\" href=\"/resources/demos/style.css\">\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("function isNumber(evt) {\r\n");
      out.write("    evt = (evt) ? evt : window.event;\r\n");
      out.write("    var charCode = (evt.which) ? evt.which : evt.keyCode;\r\n");
      out.write("    if (charCode > 31 && (charCode < 48 || charCode > 57)) {\r\n");
      out.write("        return false;\r\n");
      out.write("    }\r\n");
      out.write("    return true;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function dil(form)\r\n");
      out.write("{\r\n");
      out.write("\t//futAmountDIV\r\n");
      out.write("\tif(document.newUserForm.ROLLNBR.value == \"\"){\r\n");
      out.write("\t\t   alert(\"Please Enter Roll Number Of the User\");\r\n");
      out.write("\t\t   document.newUserForm.ROLLNBR.focus();\r\n");
      out.write("\t\t   return false;\r\n");
      out.write("\t}\r\n");
      out.write("\tif(document.newUserForm.FIRSTNAME.value == \"\"){\r\n");
      out.write("\t   alert(\"Please Enter First Name Of The User\");\r\n");
      out.write("\t   document.newUserForm.FIRSTNAME.focus();\r\n");
      out.write("\t   return false;\r\n");
      out.write("\t}\r\n");
      out.write("   if(document.newUserForm.LASTNAME.value == \"\"){\r\n");
      out.write("\t\t   alert(\"Please Enter All Details\");\r\n");
      out.write("\t\t   document.newUserForm.LASTNAME.focus();\r\n");
      out.write("\t\t   return false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\r\n");
      out.write("   if(document.newUserForm.EMAIL.value == \"\"){\r\n");
      out.write("\t   alert(\"Please Enter All Details\");\r\n");
      out.write("\t   document.newUserForm.EMAIL.focus();\r\n");
      out.write("\t   return false;\r\n");
      out.write("\t}\r\n");
      out.write("     \treturn true;\r\n");
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

      out.write("\r\n");
      out.write("\t<br>\r\n");
      out.write("\t<table border=\"0\" cellspacing=\"0\" width=\"95%\" align=\"center\">\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td align=\"left\"><a href=\"adminHome.jsp\">Home</a></td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t</table>\r\n");
      out.write("\t<form name=newUserForm onSubmit=\"return dil(this)\"\r\n");
      out.write("\t\taction=\"CreateNewUser\" method=\"post\">\r\n");
      out.write("\t\t<table align=\"center\" border=\"0\" width=\"80%\">\r\n");
      out.write("\t\t\t<tr align=\"center\">\r\n");
      out.write("\t\t\t\t<td colspan=\"2\"><h1>NEW USER REGISTRATION</h1></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td>Roll Number :</td>\r\n");
      out.write("\t\t\t\t<td><input type=\"text\" name=\"ROLLNBR\" maxlength=\"5\"\r\n");
      out.write("\t\t\t\t\tonkeypress=\"return isNumber(event)\"></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td>First Name :</td>\r\n");
      out.write("\t\t\t\t<td><input type=\"text\" name=\"FIRSTNAME\"></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td>Last Name :</td>\r\n");
      out.write("\t\t\t\t<td><input type=\"text\" name=\"LASTNAME\"></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td>E-Mail :</td>\r\n");
      out.write("\t\t\t\t<td><input type=\"text\" name=\"EMAIL\"></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td></td>\r\n");
      out.write("\t\t\t\t<td><input type=\"submit\" value=\"Submit\" /> <INPUT TYPE=RESET\r\n");
      out.write("\t\t\t\t\tVALUE=\"CLEAR\"></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t</table>\r\n");
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
