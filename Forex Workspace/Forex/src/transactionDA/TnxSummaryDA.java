package transactionDA;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbconnect.GetCon;
import transactionBean.ReportBean;
import utility.Constants;

public class TnxSummaryDA {
	
	public ArrayList<ReportBean> getTnxSummary(int rollNbr){
			ArrayList<ReportBean> finReport = new ArrayList<ReportBean>();
			ReportBean report ;
			Connection con=GetCon.getCon();
			PreparedStatement ps;
			
			try {
				ps=con.prepareStatement(Constants.report_query);
				ps.setString(1, Integer.toString(rollNbr));
				
				ResultSet rs=ps.executeQuery();
				while(rs.next()){
					report = new ReportBean();
					report.setTnx_id(rs.getInt(Constants.Report_TNX_ID));
					report.setTnx_type(rs.getString(Constants.Report_TNX_TYPE));
					report.setIs_future(rs.getInt(Constants.Report_IS_FUTURE));
					report.setFuture_date(rs.getDate(Constants.Report_FUTURE_DATE));
					report.setFrom_currency(rs.getString(Constants.Report_FROM_CNY));
					report.setFrom_crate(rs.getDouble(Constants.Report_FROM_CNY_RATE));
					report.setTo_cny(rs.getString(Constants.Report_TO_CNY));
					report.setTo_cny_rate(rs.getDouble(Constants.Report_TO_CNY_RATE));
					report.setExec_date(rs.getTimestamp(Constants.Report_EXEC_DATE));
					report.setAmount(rs.getDouble(Constants.Report_AMOUNT));
					
					finReport.add(report);
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
			
		
		return finReport;
	}

	public ArrayList<ReportBean> getFwdTnxSummary(int rollNbr){
		ArrayList<ReportBean> finReport = new ArrayList<ReportBean>();
		ReportBean report ;
		Connection con=GetCon.getCon();
		PreparedStatement ps;
		
		try {
			ps=con.prepareStatement(Constants.fwd_report_query);
			ps.setString(1, Integer.toString(rollNbr));
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				report = new ReportBean();
				report.setTnx_id(rs.getInt(Constants.Report_TNX_ID));
				report.setTnx_type(rs.getString(Constants.Report_TNX_TYPE));
				report.setIs_future(1);
				report.setFuture_date(rs.getDate(Constants.Report_FUTURE_DATE));
				report.setFrom_currency(rs.getString(Constants.Report_FROM_CNY));
				report.setFrom_crate(rs.getDouble(Constants.Report_FROM_CNY_RATE));
				report.setTo_cny(rs.getString(Constants.Report_TO_CNY));
				report.setTo_cny_rate(rs.getDouble(Constants.Report_TO_CNY_RATE));
				report.setExec_date(rs.getTimestamp(Constants.Report_EXEC_DATE));
				report.setAmount(rs.getDouble(Constants.Report_AMOUNT));
				
				finReport.add(report);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	
	return finReport;
}

}
