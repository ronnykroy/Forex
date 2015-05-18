package transactionRH;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import transactionBO.CorpusEvaluationBO;
import transactionBO.TnxSummaryBO;
import transactionBean.CorpusBean;
import transactionBean.EvaluateBean;
import transactionBean.ReportBean;
import transactionBean.TnxRateBean;
import utility.Constants;

/**
 * Servlet implementation class EvaluatePerformance
 */
@WebServlet(description = "Complete Evaluation", urlPatterns = { "/EvaluatePerformance" })
public class EvaluatePerformance extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EvaluatePerformance() {
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
		ArrayList<EvaluateBean> eBeanList = new ArrayList<EvaluateBean>();
		int status = new CorpusEvaluationBO().UpdateRate();
		eBeanList = new CorpusEvaluationBO().fullEvaluation();
		
		session.setAttribute(Constants.EVALUATE_BEAN_LIST,eBeanList);
		request.setAttribute(Constants.DISPLAY_MSG, "Transaction Summary Fetch Successful!");
		RequestDispatcher rd=request.getRequestDispatcher("evaluatePerformance.jsp");
		rd.include(request, response);
		out.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		HttpSession session = request.getSession(true);
		CorpusBean corpusBean = new CorpusBean();
		TnxRateBean rateBean = new TnxRateBean();
		rateBean = (TnxRateBean) session.getAttribute(Constants.rateBean);
		corpusBean = (CorpusBean)session.getAttribute(Constants.corpusBean);
		String userName = Integer.toString(corpusBean.getRollNbr());
		TnxSummaryBO tnxBO = new TnxSummaryBO();
		ArrayList<ReportBean> indvReport = tnxBO.getTnxSummary(corpusBean.getRollNbr());
		
		EvaluateBean eBean = new EvaluateBean();
		eBean = new CorpusEvaluationBO().evaluateUserCorpus(corpusBean.getRollNbr());
		
		
		session.setAttribute(Constants.REPORT, indvReport);
		session.setAttribute(Constants.EVALUATE_BEAN,eBean);
		request.setAttribute(Constants.DISPLAY_MSG, "Transaction Summary Fetch Successful!");
		RequestDispatcher rd=request.getRequestDispatcher("transactionSummary.jsp");
		rd.include(request, response);
		out.close();

	}

}
