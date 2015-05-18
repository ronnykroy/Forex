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

import transactionBO.TnxSummaryBO;
import transactionBean.CorpusBean;
import transactionBean.EvaluateBean;
import transactionBean.ReportBean;
import transactionBean.TnxRateBean;
import utility.Constants;
import transactionBO.CorpusEvaluationBO;

/**
 * Servlet implementation class TnxSummaryRH
 */
@WebServlet("/TnxSummaryRH")
public class TnxSummaryRH extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TnxSummaryRH() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		HttpSession session = request.getSession(true);
		if(session.getAttribute(Constants.corpusBean) != null){
		CorpusBean corpusBean = new CorpusBean();
		TnxRateBean rateBean = new TnxRateBean();
		rateBean = (TnxRateBean) session.getAttribute(Constants.rateBean);
		corpusBean = (CorpusBean)session.getAttribute(Constants.corpusBean);
		String userName = Integer.toString(corpusBean.getRollNbr());
		TnxSummaryBO tnxBO = new TnxSummaryBO();
		ArrayList<ReportBean> indvReport = tnxBO.getTnxSummary(corpusBean.getRollNbr());
		ArrayList<ReportBean> indvFwdReport = tnxBO.getFwdTnxSummary(corpusBean.getRollNbr());
		
		EvaluateBean eBean = new EvaluateBean();
		int status = new CorpusEvaluationBO().UpdateRate(rateBean);
		eBean = new CorpusEvaluationBO().evaluateUserCorpus(corpusBean.getRollNbr());
		
		
		session.setAttribute(Constants.REPORT, indvReport);
		session.setAttribute(Constants.FwdREPORT, indvFwdReport);
		session.setAttribute(Constants.EVALUATE_BEAN,eBean);
		request.setAttribute(Constants.DISPLAY_MSG, "Transaction Summary Fetch Successful!");
		RequestDispatcher rd=request.getRequestDispatcher("transactionSummary.jsp");
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
		CorpusBean corpusBean = new CorpusBean();
		TnxRateBean rateBean = new TnxRateBean();
		rateBean = (TnxRateBean) session.getAttribute(Constants.rateBean);
		corpusBean = (CorpusBean)session.getAttribute(Constants.corpusBean);
		String userName = Integer.toString(corpusBean.getRollNbr());
		TnxSummaryBO tnxBO = new TnxSummaryBO();
		ArrayList<ReportBean> indvReport = tnxBO.getTnxSummary(corpusBean.getRollNbr());
		
		session.setAttribute(Constants.REPORT, indvReport);
		RequestDispatcher rd=request.getRequestDispatcher("transactionSummary.jsp");
		rd.include(request, response);
		out.close();
		
	}

}
