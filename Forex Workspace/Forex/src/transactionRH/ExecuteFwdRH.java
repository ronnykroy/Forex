package transactionRH;

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
import transactionBean.TnxRateBean;
import transactionDA.VerifyLogin;
import transactionBO.CorpusBO;
import transactionBO.ExecuteFwdBO;
import utility.Constants;

/**
 * Servlet implementation class ExecuteFwdRH
 */
@WebServlet("/ExecuteFwdRH")
public class ExecuteFwdRH extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecuteFwdRH() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		HttpSession session = request.getSession(true);
		CorpusBean corpusBean = new CorpusBean();
		TnxRateBean rateBean = new TnxRateBean();
		ExecuteFwdBO execBO = new ExecuteFwdBO();
		CorpusBO buyBO = new CorpusBO();
		rateBean = (TnxRateBean) session.getAttribute(Constants.rateBean);

		corpusBean = (CorpusBean)session.getAttribute(Constants.corpusBean);
		
		int status = execBO.execForwards(corpusBean,rateBean);
		
		session.removeAttribute(Constants.corpusBean);
		
		corpusBean = buyBO.getUserCorpus(corpusBean.getRollNbr(),rateBean);
		session.setAttribute(Constants.corpusBean, corpusBean);
		session.setAttribute(Constants.rateBean, rateBean);
		if(status == 1){
			request.setAttribute(Constants.DISPLAY_MSG, "Transaction Processed");
		}else{
			request.setAttribute(Constants.DISPLAY_MSG, "Unable to Execute Forwards!");
		}
		
		/* Check for Forwards to be Executed*/
		int fwdCount = execBO.checkForwards(corpusBean.getRollNbr());
		session.setAttribute(Constants.nbrFwds, fwdCount);
		RequestDispatcher rd=request.getRequestDispatcher("transaction.jsp");
		rd.include(request, response);
		out.close();
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
