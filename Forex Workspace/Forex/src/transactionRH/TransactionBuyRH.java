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

import org.apache.catalina.Session;

import transactionBO.*;
import transactionBean.*;
import utility.Constants;
/**
 * Servlet implementation class TransactionBuyRH
 */
@WebServlet("/TransactionBuyRH")
public class TransactionBuyRH extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 public TransactionBuyRH() {
	        super();
	        // TODO Auto-generated constructor stub
	    }
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute(Constants.corpusBean) != null){
		CorpusBean sessionCorpusBean = new CorpusBean();
		sessionCorpusBean = (CorpusBean)session.getAttribute(Constants.corpusBean);	
		/* Get Currency Rates & Update Corpus */
		session.removeAttribute(Constants.corpusBean);
		session.removeAttribute(Constants.rateBean);
		
		TnxRateBean rateBean = new TnxRateBean();
		TnxRateBO rateBO = new TnxRateBO();
		rateBean = rateBO.getTnxRates();

		CorpusBean corpusBean = new CorpusBean();
		CorpusBO buyBO = new CorpusBO();
		
		corpusBean = buyBO.getUserCorpus(sessionCorpusBean.getRollNbr(), rateBean);
		session.setAttribute(Constants.corpusBean, corpusBean);
		session.setAttribute(Constants.rateBean, rateBean);
		 
		RequestDispatcher rd=request.getRequestDispatcher("TransactionBuy.jsp");
		rd.include(request, response);	
	}else{
		RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
		rd.include(request, response);
	}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		HttpSession session = request.getSession(true);
		CorpusBean corpusBean = new CorpusBean();
		TnxRateBean rateBean = new TnxRateBean();
		if(session.getAttribute(Constants.corpusBean) != null){
		rateBean = (TnxRateBean) session.getAttribute(Constants.rateBean);

		corpusBean = (CorpusBean)session.getAttribute(Constants.corpusBean);
	
		String dateFuture ="";
		boolean isFuture = false;
		int status = 0;
		String toCur = null;
		if(request.getParameter("buyCurrency") != null){
			toCur = (String)request.getParameter("buyCurrency");
		}else if(request.getParameter("fwdCurrency") != null){
			toCur = request.getParameter("fwdCurrency");
		}
		String amt = (String)request.getParameter("amount");
		double amount = Double.parseDouble(amt);
		double fwdQuote = 0.0d;
		String tnxType = (String)request.getParameter("tnxType");
		if(!rateBean.isError()){
			if((tnxType).equalsIgnoreCase("Future")){
				dateFuture = (String)request.getParameter("selectDate");
				if (session.getAttribute(Constants.FORWARD_QUOTE) != null)
					fwdQuote = (Double)session.getAttribute(Constants.FORWARD_QUOTE);
				isFuture = true;
				System.out.println("Date Selected :"+dateFuture);
			}else{
				isFuture = false;
			}
		
		session.removeAttribute(Constants.corpusBean);
		
		CorpusBO buyBO = new CorpusBO();
			if(!isFuture){
				System.out.println("SPOT TRANSFER");	
				status = buyBO.buyCurrency(toCur, amount,rateBean,corpusBean);
			}else{
				System.out.println("FUTURE TRANSFER");
				status = buyBO.buyCurrency(corpusBean, fwdQuote, toCur,  amount, dateFuture);
			}
		corpusBean = buyBO.getUserCorpus(corpusBean.getRollNbr(),rateBean);
		session.setAttribute(Constants.corpusBean, corpusBean);
		session.setAttribute(Constants.rateBean, rateBean);
		}else{
			status = 0;
			request.setAttribute(Constants.DISPLAY_MSG, "Unable to update Currency Rates!! !");
		}
		
		if(status != 0){
			request.setAttribute(Constants.DISPLAY_MSG, "Transaction Complete !");
		}else{
			request.setAttribute(Constants.DISPLAY_MSG, "Sorry Transaction failed !");
		}
		RequestDispatcher rd=request.getRequestDispatcher("transaction.jsp");
		rd.include(request, response);
		out.close();
		}else{
			RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
			rd.include(request, response);
		}	
	}

}
