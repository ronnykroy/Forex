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

import transactionBO.CorpusBO;
import transactionBO.EvaluateCorpusBO;
import transactionBO.CorpusEvaluationBO;
import transactionBO.ExecuteFwdBO;
import transactionBean.CorpusBean;
import transactionBean.EvaluateBean;
import transactionBean.TnxRateBean;
import utility.Constants;

/**
 * Servlet implementation class EvaluateCorpus
 */
@WebServlet("/EvaluateCorpus")
public class EvaluateCorpus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EvaluateCorpus() {
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
		rateBean = (TnxRateBean) session.getAttribute(Constants.rateBean);
		corpusBean = (CorpusBean)session.getAttribute(Constants.corpusBean);

		EvaluateBean eBean =  new EvaluateBean();
		
		CorpusEvaluationBO evalBO = new CorpusEvaluationBO();
		if(corpusBean !=null && corpusBean.getRollNbr() != 0)
		eBean = evalBO.evaluateUserCorpus(corpusBean.getRollNbr());
		
		session.setAttribute(Constants.EVALUATE_BEAN, eBean);
		
		RequestDispatcher rd=request.getRequestDispatcher("EvaluateCorpus.jsp");
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
