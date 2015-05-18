package transactionDA;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;
import sun.security.util.PendingException;
import transactionDA.TransactionDA;
import transactionBean.*;
import utility.Constants;
import dbconnect.GetCon;

public class ExecuteFwdDA {

	public int ExecuteFwd(CorpusBean cBean, TnxRateBean ratebean){
		Connection con=GetCon.getCon();
		PreparedStatement ps;
		ArrayList<ReportBean> fwdsExec = new ArrayList<ReportBean>();
		ReportBean rptBean ;
		TransactionDA tnxDA = new TransactionDA();
		int reportID = 0 ;
		int rollNbr = cBean.getRollNbr();
		try {
			ps=con.prepareStatement(Constants.select_unexecuted_forwards);
			ps.setString(1, Integer.toString(cBean.getRollNbr()));
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				rptBean = new ReportBean();
				rptBean.setTnx_id(rs.getInt("TNX_ID"));
				rptBean.setAmount(rs.getDouble("AMOUNT"));
				rptBean.setFrom_currency(rs.getString("FROM_CNY"));
				rptBean.setFrom_crate(rs.getDouble("FROM_CNY_RATE"));
				rptBean.setTo_cny_rate(rs.getDouble("TO_CNY_RATE"));
				rptBean.setTo_cny(rs.getString("TO_CNY"));
				rptBean.setTnx_type(rs.getString("TNX_TYPE"));
				rptBean.setFuture_date(rs.getDate("FUTURE_DATE"));
				
				fwdsExec.add(rptBean);
			}
			
			ReportBean rBean = new ReportBean();
			
			for( int i=0;i<fwdsExec.size();i++){
				rBean=fwdsExec.get(i);
				int updStatus = 0;
				if(rBean.getTnx_type().equalsIgnoreCase(Constants.query_buy)){
					/* BUY 
					 * INR = INR - amount * rate
					 * Update FORWARDS TABLE, insert into Transaction table  
					 */
					reportID = rBean.getTnx_id();
					updStatus = tnxDA.buyCurrency(cBean, ratebean, rBean.getTo_cny(), rBean.getAmount());
					//Update rateBean
					cBean = tnxDA.getUserCorpus(rollNbr, ratebean);
					if(updStatus != 0){
						if( rBean.getFuture_date().before(getCurrentTimeStamp())){
							double amt =   cBean.getInR() - (rBean.getTo_cny_rate()*rBean.getAmount()* Constants.penaltyValue);
							double availAmt =  cBean.getAllowableINR() - (rBean.getTo_cny_rate()*rBean.getAmount()* Constants.penaltyValue);
							updStatus = levyPenalty(amt, availAmt, Integer.toString(cBean.getRollNbr()));
							//Update rateBean
							cBean = tnxDA.getUserCorpus(rollNbr, ratebean);
					}
					}
				}else if(rBean.getTnx_type().equalsIgnoreCase(Constants.query_sell)){
					/* SELL 
					 * INR = INR + amount * (fwd rate - current rate)
					 * Update FORWARDS TABLE, insert into Transaction table  
					 * Buy equal amount from spot and replace sold amount
					 */
					reportID = rBean.getTnx_id();
					updStatus = tnxDA.sellCurrency(cBean, ratebean, rBean.getFrom_currency(), rBean.getAmount());
					//Update rateBean
					cBean = tnxDA.getUserCorpus(rollNbr, ratebean);
					if(updStatus != 0){
						if( rBean.getFuture_date().before(getCurrentTimeStamp())){
							double amt =   cBean.getInR() - (rBean.getTo_cny_rate()*rBean.getAmount()* Constants.penaltyValue);
							double availAmt =  cBean.getAllowableINR() - (rBean.getTo_cny_rate()*rBean.getAmount()* Constants.penaltyValue);
							updStatus = levyPenalty(amt, availAmt, Integer.toString(cBean.getRollNbr()));
							//Update rateBean
							cBean = tnxDA.getUserCorpus(rollNbr, ratebean);
					}
					}
					updStatus = tnxDA.buyCurrency(cBean, ratebean, rBean.getFrom_currency(), rBean.getAmount());
					//Update rateBean
					cBean = tnxDA.getUserCorpus(rollNbr, ratebean);
			}
				//update corpus -- taken care
				//update fwds
				//update transanction
				if(reportID != 0){
					//Update Fwds table -- 
					int status = updateFwdsTble(reportID);
				}
			}
			return 1;
		}catch(SQLException e){
			e.printStackTrace();
			return 0;
		}
	}
	public int CheckForwards(int rollNbr){
		Connection con=GetCon.getCon();
		PreparedStatement ps;
		int count = 0;
		try {
			ps=con.prepareStatement(Constants.check_unexecuted_forwards);
			ps.setString(1, Integer.toString(rollNbr));
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
			return count;
		}catch(SQLException e){
			e.printStackTrace();
			return 0;
		}
	}

	public int updateFwdsTble(int reportID){
		/* UPDATES FORWARD TABLE*/
		String nbrIDs = " ";

		String query = Constants.update_executed_forwards;
		Connection con=GetCon.getCon();
		PreparedStatement ps;
		int count = 0;
		try {
			ps=con.prepareStatement(query);
			ps.setInt(1, reportID);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				return 1;
			}
			return 0;
		}catch(SQLException e){
			e.printStackTrace();
			return 0;
		}
		
	}
	
	
	public int buyCurrency(CorpusBean corpusBean,TnxRateBean rateBean, String toCur, double amt, double rate){
		/* DB connections here
		 * If Buy success return 1
		 * else return 0;
		 */
		/* Get the transfer rate of particular currency*/
		
		Connection con=GetCon.getCon();
		PreparedStatement ps;
		try {
			/* Update Corpus*/
			
			double eInr = corpusBean.getAllowableINR()-(amt*rate);
					if(eInr< 0){
						return 0;
					}
			ps=con.prepareStatement(getUpdateQuery(toCur));
			ps.setDouble(1,  corpusBean.getInR()-(amt*rate)); 
			ps.setDouble(2, eInr);
			ps.setDouble(3, getNewAmt(toCur, amt, corpusBean));
			ps.setString(4,	Integer.toString(corpusBean.getRollNbr()) );
			ps.executeQuery();
			System.out.println("Buy Currency : Corpus Updated ");
			
			ps=con.prepareStatement(Constants.BUY_QUERY);
			ps.setString(1,Integer.toString(corpusBean.getRollNbr())); 
			ps.setString(2, Constants.query_buy);
			ps.setInt(3, Constants.query_spot);
			ps.setString(4, Constants.query_inr);
			ps.setDouble(5, rate);
			
			ps.setString(6, toCur);
			ps.setDouble(7, Constants.query_inr_rate);
			
			ps.setDouble(8, amt);
			
			ps.setTimestamp(9, getCurrentTimeStamp());
			
			ps.executeQuery();
			
			
			return 1;	
			
		} catch (SQLException e) {
			System.out.println("Error Connecting to DB!");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	public int updateSellCurrency(CorpusBean CorpusBean,TnxRateBean rateBean, String fromCur, double amt, double rate){
		/* DB connections here
		 * If Buy success return 1
		 * else return 0;
		 */
		/* Get the transfer rate of particular currency*/
		
		Connection con=GetCon.getCon();
		PreparedStatement ps;
		try {
			/* Update Corpus*/
			
			double eInr = CorpusBean.getInR()+(rate*amt);
			double eCur = 0;
			ps=con.prepareStatement(getSellUpdateQuery(fromCur));
			ps.setDouble(1, eInr ); 
			ps.setDouble(2, eCur);
			ps.setString(3,	Integer.toString(CorpusBean.getRollNbr()) );
			ps.executeQuery();
			System.out.println("Sell Currency : Corpus Updated ");
			
			ps=con.prepareStatement(Constants.SELL_QUERY);
			ps.setString(1,Integer.toString(CorpusBean.getRollNbr())); 
			ps.setString(2, Constants.query_sell);
			ps.setInt(3, Constants.query_spot);
			ps.setString(4, fromCur);
			ps.setDouble(5, rate);
			
			ps.setString(6, Constants.query_inr);
			ps.setDouble(7, Constants.query_inr_rate);
			
			ps.setDouble(8, amt);
			
			ps.setTimestamp(9, getCurrentTimeStamp());
			
			ps.executeQuery();
			
			
			return 1;	
			
		} catch (SQLException e) {
			System.out.println("Error Connecting to DB!");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	private String getSellUpdateQuery(String fromCur){
		String query = Constants.SELL_UPDATE_CORPUS_1+ fromCur+" = ? "+Constants.SELL_UPDATE_CORPUS_2;
		return query;
		}
	private static java.sql.Timestamp getCurrentTimeStamp() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());
	}
	private String getUpdateQuery(String toCur){
		String query = Constants.BUY_UPDATE_CORPUS_1+ toCur+" = ? "+Constants.BUY_UPDATE_CORPUS_2;
		return query;
		}
	private static java.sql.Timestamp getTimeStamp(String fwdDate) {
		java.util.Date date = null;
		try {
			date = new SimpleDateFormat("dd-MMMM-yyyy", Locale.ENGLISH).parse(fwdDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new java.sql.Timestamp(date.getTime());
	}
		
	private double getNewAmt(String toCur, double amt,CorpusBean corpBean){
		if(toCur.equalsIgnoreCase(Constants.query_aud)){
			return corpBean.getAuD()+(amt);
		}else if(toCur.equalsIgnoreCase(Constants.query_euro)){
			return corpBean.getEuR()+(amt);
		}else if(toCur.equalsIgnoreCase(Constants.query_gbp)){
			return corpBean.getGbP()+(amt);
		}else if(toCur.equalsIgnoreCase(Constants.query_usd)){
			return corpBean.getUsD()+(amt);
		}else if(toCur.equalsIgnoreCase(Constants.query_yen)){
			return corpBean.getJpY()+(amt);
		}else {
			return 0.00;
		}		
	}
	private double getNewSellAmt(String fromCur, double amt,CorpusBean corpBean){
		if(fromCur.equalsIgnoreCase(Constants.query_aud)){
			return corpBean.getAuD()-(amt);
		}else if(fromCur.equalsIgnoreCase(Constants.query_euro)){
			return corpBean.getEuR()-(amt);
		}else if(fromCur.equalsIgnoreCase(Constants.query_gbp)){
			return corpBean.getGbP()-(amt);
		}else if(fromCur.equalsIgnoreCase(Constants.query_usd)){
			return corpBean.getUsD()-(amt);
		}else if(fromCur.equalsIgnoreCase(Constants.query_yen)){
			return corpBean.getJpY()-(amt);
		}else {
			return 0.00;
		}		
	}
	private int levyPenalty(double amt, double availAmt, String userName){
		CorpusBean userCorpus = new CorpusBean();
		Connection con=GetCon.getCon();
		PreparedStatement ps;
		double penAmt = amt-availAmt;
		
		try {
			ps=con.prepareStatement(Constants.BUY_UPDATE_CORPUS_1.substring(0, Constants.BUY_UPDATE_CORPUS_1.length()-3)+Constants.BUY_UPDATE_CORPUS_2);
			ps.setDouble(1, amt);
			ps.setDouble(2, availAmt);
			ps.setString(3, userName);
			
			ResultSet rs = ps.executeQuery();
			
			System.out.println("Buy Currency : Corpus Updated ");
			
			ps=con.prepareStatement(Constants.BUY_QUERY);
			ps.setString(1,userName); 
			ps.setString(2, Constants.penalty);
			ps.setInt(3, Constants.query_future);
			ps.setString(4, Constants.query_inr);
			ps.setDouble(5, Constants.penDummy);
			ps.setString(6, Constants.query_inr);
			ps.setDouble(7, Constants.penDummy);
			ps.setDouble(8, penAmt);
			
			ps.setTimestamp(9, getCurrentTimeStamp());
			
			ps.executeQuery();
			
			
			return 1;	
			
			
		}catch(SQLException se){
			se.printStackTrace();
		}
		
			return 1;
	}
			
}
