package transactionDA;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbconnect.GetCon;
import transactionBean.*;
import utility.*;

public class TnxRateDA {
	TnxRateBean tnxRateBean;
	CurrencyRateGetter rateGetter ;
	public TnxRateBean setTnxRate(){
		tnxRateBean = new TnxRateBean();
		tnxRateBean = GetRate();
		/*tnxRateBean.setAUD(57.00);
		tnxRateBean.setEURO(80.00);
		tnxRateBean.setJPY(45.00);
		tnxRateBean.setUSD(61.00);
		tnxRateBean.setYEN(50.00); */
		
		return tnxRateBean;
	}
	public TnxRateBean GetRate(){
		rateGetter = new CurrencyRateGetter();
		tnxRateBean = new TnxRateBean();
		try{
			int i; 
		for(i=0; i<Constants.currencyID.length; i++){
			
			tnxRateBean.setGBP(cValue("GBP", "INR", 0));
			tnxRateBean.setAUD(cValue("AUD", "INR", 0));
			tnxRateBean.setEURO(cValue("EUR", "INR", 0));
			tnxRateBean.setJPY(cValue("JPY", "INR", 0));
			tnxRateBean.setUSD(cValue("USD", "INR", 0));
			
			return tnxRateBean;
		}
		}catch(Exception e){
		return tnxRateBean;
		}
		return tnxRateBean;
	}
	
	private double cValue(String fCurrency,String tCurrency, int tryCount){
		double temp = 0.00;
		int i=0;
			temp = rateGetter.findRate(fCurrency,tCurrency);
			if(temp <0 && tryCount <3){
				tryCount++;
				temp = cValue(fCurrency, tCurrency, tryCount);
			}else{
				tryCount = 0;
				return temp;
			}
		return temp;
		
	}
	public int insertRate(TnxRateBean rateBean){
		Connection con=GetCon.getCon();
		PreparedStatement ps;
		try {
			ps=con.prepareStatement(Constants.rateUpdate);
			ps.setDouble(1, rateBean.getINR());
			ps.setDouble(2, rateBean.getUSD());
			ps.setDouble(3, rateBean.getGBP());
			ps.setDouble(4, rateBean.getJPY());
			ps.setDouble(5, rateBean.getEURO());
			ps.setDouble(6, rateBean.getAUD());
			
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				return 1;	
			}else{
				return 0;
			}
		}catch(SQLException s){
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return 1;	
	}

	
	public int evaluateCorpus(int rollNbr){
		Connection con=GetCon.getCon();
		PreparedStatement ps;
		try {
			ps=con.prepareStatement(Constants.EVALUATE_CORPUS);
			ps.setString(1, Integer.toString(rollNbr));
			ps.setString(2, Integer.toString(rollNbr));
			ps.setString(3, Integer.toString(rollNbr));
			ps.setString(4, Integer.toString(rollNbr));
			ps.setString(5, Integer.toString(rollNbr));
			ps.setString(6, Integer.toString(rollNbr));
			
			ResultSet rs=ps.executeQuery();
			return 1;
		}catch(SQLException s){
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		return 1;	
	}
	
	public int updateRates(){
		TnxRateBean rateBean = new TnxRateBean();
		rateBean = GetRate();
		Connection con=GetCon.getCon();
		PreparedStatement ps;
		
		try {
			ps=con.prepareStatement(Constants.UPDATE_RATE);
			ps.setDouble(1, rateBean.getINR());
			ps.setDouble(2, rateBean.getUSD());
			ps.setDouble(3, rateBean.getGBP());
			ps.setDouble(4, rateBean.getJPY());
			ps.setDouble(5, rateBean.getEURO());
			ps.setDouble(6, rateBean.getAUD());

			ps.execute();
		}catch(SQLException e){
			e.printStackTrace();
			return 0;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
			
		public int updateRates(TnxRateBean rateBean){
			Connection con=GetCon.getCon();
			PreparedStatement ps;
			
			try {
				ps=con.prepareStatement(Constants.UPDATE_RATE);
				ps.setDouble(1, rateBean.getINR());
				ps.setDouble(2, rateBean.getUSD());
				ps.setDouble(3, rateBean.getGBP());
				ps.setDouble(4, rateBean.getJPY());
				ps.setDouble(5, rateBean.getEURO());
				ps.setDouble(6, rateBean.getAUD());

				ps.execute();
			}catch(SQLException e){
				e.printStackTrace();
				return 0;
			}catch(Exception e){
				e.printStackTrace();
				return 1;
			}
		return 1;
	}
	
	
	}