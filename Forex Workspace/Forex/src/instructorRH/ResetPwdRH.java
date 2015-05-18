package instructorRH;

import instructorBO.ResetPwdBO;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import transactionBean.CorpusBean;
import utility.Constants;

/**
 * Servlet implementation class ResetPwdRH
 */
@WebServlet("/ResetPwdRH")
public class ResetPwdRH extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetPwdRH() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				response.setContentType("text/html");
				PrintWriter out=response.getWriter();
				
				HttpSession session = request.getSession(true);
				
				int userName = Integer.parseInt((String) request.getParameter("userName"));
				
				String password = "password";
				ResetPwdBO resetPwd = new ResetPwdBO();
				int status = resetPwd.ResetPwd(userName, password);
				 if(status == 1)
					 request.setAttribute(Constants.DISPLAY_MSG, "Password Changed Successfully!");
				 else
					 request.setAttribute(Constants.DISPLAY_MSG, "Unable to Change Password!");
				 
				 RequestDispatcher rd=request.getRequestDispatcher("adminHome.jsp");
					rd.include(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		HttpSession session = request.getSession(true);
		
		CorpusBean corpBean = (CorpusBean) session.getAttribute(Constants.corpusBean);
		
		//String userName = request.getParameter(Constants.rollNbr);
		int userName = corpBean.getRollNbr();
		String password = request.getParameter("NewPassword");
		ResetPwdBO resetPwd = new ResetPwdBO();
		int status = resetPwd.ResetPwd(userName, password);
		 if(status == 1)
			 request.setAttribute(Constants.DISPLAY_MSG, "Password Changed Successfully!");
		 else
			 request.setAttribute(Constants.DISPLAY_MSG, "Unable to Change Password!");
		 
		 RequestDispatcher rd=request.getRequestDispatcher("transaction.jsp");
			rd.include(request, response);
	}

}
