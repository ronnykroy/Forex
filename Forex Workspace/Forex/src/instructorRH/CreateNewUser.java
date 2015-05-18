package instructorRH;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import transactionDA.VerifyLogin;
import utility.Constants;

/**
 * Servlet implementation class CreateNewUser
 */
@WebServlet("/CreateNewUser")
public class CreateNewUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateNewUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
		int rollNbr = Integer.parseInt(request.getParameter("ROLLNBR"));
		String firstName = request.getParameter("FIRSTNAME");
		String lastName = request.getParameter("LASTNAME"); 
		String email = request.getParameter("EMAIL");
		int status = new VerifyLogin().createLogin(rollNbr, firstName, lastName, email);
		
		*/
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		RequestDispatcher rd=request.getRequestDispatcher("NewUser.jsp");
		rd.include(request, response);


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html");
		PrintWriter out=response.getWriter();

		
		int rollNbr = Integer.parseInt(request.getParameter("ROLLNBR"));
		String firstName = request.getParameter("FIRSTNAME");
		String lastName = request.getParameter("LASTNAME"); 
		String email = request.getParameter("EMAIL");
		int status = new VerifyLogin().createLogin(rollNbr, firstName, lastName, email);

		if(status != 0){
			request.setAttribute(Constants.DISPLAY_MSG, "New User Successfully Created !");
		}else{
			request.setAttribute(Constants.DISPLAY_MSG, "Sorry. Unable to Create New User!");
		}
		
		RequestDispatcher rd=request.getRequestDispatcher("NewUser.jsp");
		rd.include(request, response);

	}

}
