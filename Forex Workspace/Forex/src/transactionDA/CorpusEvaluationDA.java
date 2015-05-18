package transactionDA;

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

public class CorpusEvaluationDA {
	
	public EvaluateBean getUserRank(int rollNbr){
		EvaluateBean eBean = new EvaluateBean();
		Connection con=GetCon.getCon();
		PreparedStatement ps;
		try {
			ps=con.prepareStatement(Constants.EVALUATE_USER_CORPUS);
			ps.setInt(1, rollNbr);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()){
				eBean.setRank(rs.getInt(Constants.RANK));
				eBean.setTotalValue(rs.getFloat(Constants.TOTALVALUE));
			}
			
		} catch (SQLException e) {
			System.out.println("Error Connecting to DB!");
			e.printStackTrace();
		}
	
		try {
			ps=con.prepareStatement(Constants.USER_PURCHASE);
			ps.setInt(1, rollNbr);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()){
				eBean.setPurchase(rs.getFloat(Constants.PURCHASE));
			}
			
		} catch (SQLException e) {
			System.out.println("Error Connecting to DB!");
			e.printStackTrace();
		}
		
		try {
			ps=con.prepareStatement(Constants.USER_SALES);
			ps.setInt(1, rollNbr);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()){
				eBean.setSales(rs.getFloat(Constants.SALES));
			}
			
		} catch (SQLException e) {
			System.out.println("Error Connecting to DB!");
			e.printStackTrace();
		}
		
		try {
			ps=con.prepareStatement(Constants.USER_CL_INV);
			ps.setInt(1, rollNbr);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()){
				eBean.setClInv(rs.getFloat(Constants.CL_INV));
			}
			
		} catch (SQLException e) {
			System.out.println("Error Connecting to DB!");
			e.printStackTrace();
		}
		
		eBean.setProfit(eBean.getSales()-eBean.getPurchase()+eBean.getClInv());
		return eBean;
	}

	public ArrayList<EvaluateBean> fullEvaluation(){
		ArrayList<EvaluateBean> eList = new ArrayList<EvaluateBean>();
		EvaluateBean eBean;
		Connection con=GetCon.getCon();
		PreparedStatement ps;
		try {
			ps=con.prepareStatement(Constants.EVALUATE_WHOLE_USERS_CORPUS);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()){
				eBean = new EvaluateBean();
				eBean.setRank(rs.getInt(Constants.RANK));
				eBean.setRollNbr(rs.getInt(Constants.query_username));
				eBean.setTotalValue(rs.getFloat(Constants.TOTALVALUE));
				eBean.setINR(rs.getFloat("INR"));
				eBean.setUSD(rs.getFloat("USD"));
				eBean.setGBP(rs.getFloat("GBP"));
				eBean.setJPY(rs.getFloat("JPY"));
				eBean.setAUD(rs.getFloat("AUD"));
				eBean.setEURO(rs.getFloat("EURO"));
				eBean.setNbrSales(rs.getInt("SALES"));
				eBean.setNbrPurchases(rs.getInt("PURCHASE"));
				eBean.setNbrFwdSales(rs.getInt("FWDSALE"));
				eBean.setNbrFwdPurchases(rs.getInt("FWDPURCHASE"));
				eList.add(eBean);
			}
			System.out.println(eList.size()+" Students Evaluated");
			
		} catch (SQLException e) {
			System.out.println("Error Connecting to DB!");
			e.printStackTrace();
		}
	
		return eList;
	}

}
