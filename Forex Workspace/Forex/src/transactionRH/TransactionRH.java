package transactionRH;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.ProcessBuilder.Redirect;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import transactionBO.CorpusBO;
import transactionBO.TnxRateBO;
import transactionBean.CorpusBean;
import transactionBean.TnxRateBean;
import transactionDA.VerifyLogin;
import transactionBO.ExecuteFwdBO;
import utility.*;

/**
 * Servlet implementation class TransactionRH
 */
@WebServlet("/TransactionRH")
public class TransactionRH extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public  CorpusBean myCorpus = null ;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransactionRH() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Method="Get"
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		HttpSession session = request.getSession(true);
		
		if(session.getAttribute(Constants.corpusBean) != null){
		session.removeAttribute(Constants.rateBean);
		TnxRateBean rateBean = new TnxRateBean();
		TnxRateBO rateBO = new TnxRateBO();
		rateBean = rateBO.getTnxRates();
		
		if((rateBean.getAUD()<0) || (rateBean.getEURO()<0)||(rateBean.getGBP()<0)||(rateBean.getJPY()<0)
				||(rateBean.getUSD()<0)){
			rateBean.setError(true);
		}else{
			rateBean.setError(false);
		}
		session.setAttribute(Constants.rateBean, rateBean);
		RequestDispatcher rd=request.getRequestDispatcher("transaction.jsp");
		rd.include(request, response);
		out.close();
		}else{
			RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
			rd.include(request, response);
			out.close();
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		HttpSession session = request.getSession(true);
		/* Verify Login */
		String userName = request.getParameter(Constants.rollNbr);
		String password = request.getParameter(Constants.password);
		int status=VerifyLogin.verifyLogin(userName, password);
		ExecuteFwdBO fwdBO = new ExecuteFwdBO();
		int fwdCount = 0;

		if(status != 0){
			/* Get Currency Rates*/
			TnxRateBean rateBean = new TnxRateBean();
			TnxRateBO rateBO = new TnxRateBO();
			rateBean = rateBO.getTnxRates();
			
			if((rateBean.getAUD()<0) || (rateBean.getEURO()<0)||(rateBean.getGBP()<0)||(rateBean.getJPY()<0)
					||(rateBean.getUSD()<0)){
				rateBean.setError(true);
			}else{
				rateBean.setError(false);
			}


			if(status == 1){
			/* Get User's Corpus */
			myCorpus = new CorpusBean();
			myCorpus = new CorpusBO().getUserCorpus(Integer.parseInt(userName),rateBean);
			
			/* Check for Forwards to be Executed*/
			fwdCount = fwdBO.checkForwards(Integer.parseInt(userName));
			
			/* Set Session variables*/
			session.setAttribute(Constants.corpusBean, myCorpus);
			session.setAttribute(Constants.rateBean, rateBean);
			session.setAttribute(Constants.nbrFwds, fwdCount);
			/* Cannot retrieve Currency Rates; Throw Error*/
				if(rateBean.isError()){
					request.setAttribute(Constants.DISPLAY_MSG, "No Internet Connection!! Unable to get Currency Rates!");
				}
			RequestDispatcher rd=request.getRequestDispatcher("transaction.jsp");
			rd.include(request, response);
			
			}
			if(status ==2){
				myCorpus = new CorpusBean();
				myCorpus = new CorpusBO().getAdmin(userName);
				session.setAttribute(Constants.corpusBean, myCorpus);
				RequestDispatcher rd=request.getRequestDispatcher("adminHome.jsp");
				rd.include(request, response);
			}
 		}else{
 			request.setAttribute(Constants.DISPLAY_MSG, "Authenitication Error!! Please Enter Correct Username & Password ");
			RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
			rd.include(request, response);
 		}

		out.close();		
	}
		
	}

