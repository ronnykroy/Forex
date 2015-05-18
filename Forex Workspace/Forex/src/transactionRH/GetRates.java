package transactionRH;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import transactionBO.TnxRateBO;
import transactionBean.TnxRateBean;

/**
 * Servlet implementation class GetRates
 */
@WebServlet("/GetRates")
public class GetRates extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetRates() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
