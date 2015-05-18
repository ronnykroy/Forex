package transactionRH;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import utility.Constants;

import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import transactionBean.*;
import transactionBO.ForwardRateBO;

/**
 * Servlet implementation class ForwardRateRH
 */
@WebServlet("/ForwardRateRH")
public class ForwardRateRH extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ForwardRateRH() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		PrintWriter out = response.getWriter();

		ForwardRateBO fwdBO = new ForwardRateBO();
		HttpSession session = request.getSession(true);
		String currID = request.getParameter("Currency");
		String date = request.getParameter("FwdDate");
		double fQuote;
		Date fwdDate = parseDate(date);
		Date presentdate = parseDate("now");

		int flag = dateCompare(fwdDate, presentdate);

		ForwardsBean fwdBean = new ForwardsBean();
		if (session.getAttribute(Constants.FORWARD_BEAN) != null){
			fwdBean = (ForwardsBean) session.getAttribute(Constants.FORWARD_BEAN);
		}
		if (session.getAttribute(Constants.FORWARD_QUOTE) != null)
			session.removeAttribute(Constants.FORWARD_QUOTE);

		TnxRateBean rateBean = new TnxRateBean();
		rateBean = (TnxRateBean) session.getAttribute(Constants.rateBean);

		if (!(session.getAttribute(Constants.FORWARD_BEAN) != null)){
			if (! checkCurrAvail(currID, fwdBean,flag)) {
			fwdBean = fwdBO.fwdRateSetter(currID, rateBean, flag);
			}
		}else{
			fwdBean = fwdBO.fwdRateSetter(currID, rateBean, flag);
		}

		// Part Currency = request.getPart("Currency");
		// String Currency = getValue(request.getPart("Currency"));
		fQuote = displayValue(fwdBean, currID, flag);
		session.setAttribute(Constants.FORWARD_QUOTE, fQuote);
		out.println(fQuote);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Implement here
	}

	// {"init","GBP","USD","AUD","JPY","EURO"};
	private boolean checkCurr(String ID, ForwardsBean fwdBean) {
		if (ID.equalsIgnoreCase(Constants.tnxCurrencyID[1])
				&& fwdBean.getFwd1MonGBP() > 0) {
			return true;
		} else if (ID.equalsIgnoreCase(Constants.tnxCurrencyID[2])
				&& fwdBean.getFwd1MonUSD() > 0) {
			return true;
		} else if (ID.equalsIgnoreCase(Constants.tnxCurrencyID[3])
				&& fwdBean.getFwd1MonAUD() > 0) {
			return true;
		} else if (ID.equalsIgnoreCase(Constants.tnxCurrencyID[4])
				&& fwdBean.getFwd1MonJPY() > 0) {
			return true;
		} else if (ID.equalsIgnoreCase(Constants.tnxCurrencyID[5])
				&& fwdBean.getFwd1MonEURO() > 0) {
			return true;
		}

		return false;
	}

	private boolean checkCurrAvail(String ID, ForwardsBean fwdBean, int flag) {
		if (flag == 2) {
			if (ID.equalsIgnoreCase(Constants.tnxCurrencyID[1])
					&& fwdBean.getFwd1MonGBP() > 0) {
				return true;
			} else if (ID.equalsIgnoreCase(Constants.tnxCurrencyID[2])
					&& fwdBean.getFwd1MonUSD() > 0) {
				return true;
			} else if (ID.equalsIgnoreCase(Constants.tnxCurrencyID[3])
					&& fwdBean.getFwd1MonAUD() > 0) {
				return true;
			} else if (ID.equalsIgnoreCase(Constants.tnxCurrencyID[4])
					&& fwdBean.getFwd1MonJPY() > 0) {
				return true;
			} else if (ID.equalsIgnoreCase(Constants.tnxCurrencyID[5])
					&& fwdBean.getFwd1MonEURO() > 0) {
				return true;
			}
		} else if (flag == 1) {
			if (ID.equalsIgnoreCase(Constants.tnxCurrencyID[1])
					&& fwdBean.getFwd2WGBP() > 0) {
				return true;
			} else if (ID.equalsIgnoreCase(Constants.tnxCurrencyID[2])
					&& fwdBean.getFwd2WUSD() > 0) {
				return true;
			} else if (ID.equalsIgnoreCase(Constants.tnxCurrencyID[3])
					&& fwdBean.getFwd2WAUD() > 0) {
				return true;
			} else if (ID.equalsIgnoreCase(Constants.tnxCurrencyID[4])
					&& fwdBean.getFwd2WJPY() > 0) {
				return true;
			} else if (ID.equalsIgnoreCase(Constants.tnxCurrencyID[5])
					&& fwdBean.getFwd2WEURO() > 0) {
				return true;
			}
		}
		return false;
	}

	public Date parseDate(String date) {
		DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		Date dateNew = null;
		if (!date.equalsIgnoreCase("now")) {
			try {
				dateNew = formatter.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			dateNew = new Date();
		}
		return dateNew;
	}

	public int dateCompare(Date fwdDate, Date presentDate) {
		long diff = fwdDate.getTime() - presentDate.getTime();
		long diffDays = diff / (24 * 60 * 60 * 1000);

		if (diffDays < 20) {
			return 1;
		} else {
			return 2;
		}
	}

	public double displayValue(ForwardsBean fwdBean, String ID, int flag) {
		if (flag == 2) {
			if (ID.equalsIgnoreCase(Constants.tnxCurrencyID[1])) {
				return fwdBean.getFwd1MonGBP();
			} else if (ID.equalsIgnoreCase(Constants.tnxCurrencyID[2])) {
				return fwdBean.getFwd1MonUSD();
			} else if (ID.equalsIgnoreCase(Constants.tnxCurrencyID[3])) {
				return fwdBean.getFwd1MonAUD();
			} else if (ID.equalsIgnoreCase(Constants.tnxCurrencyID[4])) {
				return fwdBean.getFwd1MonJPY();
			} else if (ID.equalsIgnoreCase(Constants.tnxCurrencyID[5])) {
				return fwdBean.getFwd1MonEURO();
			} else {
				return 0.00d;
			}
		} else if (flag == 1) {
			if (ID.equalsIgnoreCase(Constants.tnxCurrencyID[1])) {
				return fwdBean.getFwd2WGBP();
			} else if (ID.equalsIgnoreCase(Constants.tnxCurrencyID[2])) {
				return fwdBean.getFwd2WUSD();
			} else if (ID.equalsIgnoreCase(Constants.tnxCurrencyID[3])) {
				return fwdBean.getFwd2WAUD();
			} else if (ID.equalsIgnoreCase(Constants.tnxCurrencyID[4])) {
				return fwdBean.getFwd2WJPY();
			} else if (ID.equalsIgnoreCase(Constants.tnxCurrencyID[5])) {
				return fwdBean.getFwd2WEURO();
			} else {
				return 0.00d;
			}
		} else {
			return 0.0d;
		}

	}
}
