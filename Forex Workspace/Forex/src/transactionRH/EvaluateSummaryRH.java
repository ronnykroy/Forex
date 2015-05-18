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
 * Servlet implementation class EvaluateSummaryRH
 */
@WebServlet("/EvaluateSummaryRH")
public class EvaluateSummaryRH extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EvaluateSummaryRH() {
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
		int rollNbr = Integer.parseInt(request.getParameter("rollNbr"));
		TnxSummaryBO tnxBO = new TnxSummaryBO();
		ArrayList<ReportBean> indvReport = tnxBO.getTnxSummary(rollNbr);
		
		EvaluateBean eBean = new EvaluateBean();
		eBean = new CorpusEvaluationBO().evaluateUserCorpus(rollNbr);
		
		
		session.setAttribute(Constants.REPORT, indvReport);
		session.setAttribute(Constants.EVALUATE_BEAN,eBean);
		request.setAttribute(Constants.DISPLAY_MSG, "Transaction Summary Fetch Successful!");
		RequestDispatcher rd=request.getRequestDispatcher("tnxEvalSummary.jsp");
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
