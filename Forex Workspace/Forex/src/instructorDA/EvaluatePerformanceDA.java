package instructorDA;

import transactionBean.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.math.*;

import utility.Constants;
import dbconnect.GetCon;


public class EvaluatePerformanceDA {
	ReportBean report;
	
	public ReportBean TnxReport(int rollNbr){
		report = new ReportBean();
		Connection con=GetCon.getCon();
		PreparedStatement ps;
		
		report.setUsername(Integer.toString(rollNbr));
		
		try {
			ps=con.prepareStatement(Constants.report_query);
			ps.setString(1, Integer.toString(rollNbr));
			
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				report.setTnx_id(rs.getInt(Constants.Report_TNX_ID));
				report.setTnx_type(rs.getString(Constants.Report_TNX_TYPE));
				report.setIs_future(rs.getInt(Constants.Report_IS_FUTURE));
				report.setFuture_date(rs.getDate(Constants.Report_FUTURE_DATE));
				report.setFrom_currency(rs.getString(Constants.Report_FROM_CNY));
				report.setFrom_crate(rs.getDouble(Constants.Report_TO_CNY_RATE));
				report.setTo_cny(rs.getString(Constants.Report_TO_CNY));
				report.setTo_cny_rate(rs.getDouble(Constants.Report_TO_CNY_RATE));
				report.setExec_date(rs.getTimestamp(Constants.Report_EXEC_DATE));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return report;
	}
	
	public ArrayList<CorpusBean> totalEval(){
		ArrayList<CorpusBean> totalEval = new ArrayList<CorpusBean> ();
		
		CorpusBean cBean = null;
		Connection con=GetCon.getCon();
		PreparedStatement ps;
		
		try {
			ps=con.prepareStatement(Constants.report_query);
			
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				cBean = new CorpusBean();
				cBean.setRollNbr(Integer.parseInt(rs.getString(Constants.query_rollnbr)));
				cBean.setFirstName(rs.getString(Constants.query_fName));
				cBean.setLastName(rs.getString(Constants.query_lName));
				cBean.setAuD(Float.parseFloat(rs.getString(Constants.query_aud)));
				cBean.setInR(Float.parseFloat(rs.getString(Constants.query_inr)));
				cBean.setUsD(Float.parseFloat(rs.getString(Constants.query_usd)));
				cBean.setGbP(Float.parseFloat(rs.getString(Constants.query_gbp)));
				cBean.setJpY(Float.parseFloat(rs.getString(Constants.query_yen)));
				cBean.setEuR(Float.parseFloat(rs.getString(Constants.query_euro)));
				cBean.setTotal_NetWorth(Double.parseDouble(rs.getString(Constants.netWorth)));
				
				totalEval.add(cBean);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return totalEval;
	}
	
	public int updateNetWorth(ArrayList<CorpusBean> totalBeans, TnxRateBean rBean){
		int rNbr;
		double nWorth;
		Connection con=GetCon.getCon();
		PreparedStatement ps;
		
		for (CorpusBean cBean : totalBeans){
			rNbr=cBean.getRollNbr();
			nWorth = cBean.getAuD() * rBean.getAUD() +
					 cBean.getEuR() * rBean.getEURO() +
					 cBean.getGbP() * rBean.getGBP() +
					 cBean.getJpY() * rBean.getJPY() +
					 cBean.getUsD() * rBean.getUSD() +
					 cBean.getInR();
			//TOTAL_EVAL_UPDATE
			
			try {
				ps=con.prepareStatement(Constants.TOTAL_EVAL_UPDATE);
				ps.setDouble(1, nWorth);
				ps.setString(2, Integer.toString(rNbr));
				
				ResultSet rs=ps.executeQuery();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return 1;
	}
}


