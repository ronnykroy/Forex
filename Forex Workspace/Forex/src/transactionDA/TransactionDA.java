package transactionDA;

import transactionBean.CorpusBean;
import transactionBean.TnxRateBean;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.math.*;

import utility.Constants;
import dbconnect.GetCon;


public class TransactionDA {
	int dummy =10000;
	int allDummy =10000;
	public static Connection con;
	
	public static int verifyLogin(String userName,String password){
		Connection con=GetCon.getCon();
		PreparedStatement ps;
		try {
			ps=con.prepareStatement(Constants.VERIFY_LOGIN);
			ps.setString(1, userName);
			ps.setString(2, password);
			
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				if(rs.getInt("ISADMIN") == 1 ){
						return 2;
				}else if(rs.getInt("ISADMIN") == 0 ){
				return 1;	
				}
			}else{
				return 0;
			}
			
		} catch (SQLException e) {
			System.out.println("Error Connecting to DB!");
			e.printStackTrace();
		}
		
		return 0 ;
	}
	/* Get user's Corpus
	 * Input : UserID and Rate Bean
	 * */
	public CorpusBean getUserCorpus(int userName, TnxRateBean rateBean){
		CorpusBean userCorpus = new CorpusBean();
		Connection con=GetCon.getCon();
		PreparedStatement ps;
		
		try {
			ps=con.prepareStatement(Constants.GET_CORPUS);
			ps.setString(1, Integer.toString(userName));
			
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				userCorpus.setRollNbr(Integer.parseInt(rs.getString(Constants.query_rollnbr)));
				userCorpus.setFirstName(rs.getString(Constants.query_fName));
				userCorpus.setLastName(rs.getString(Constants.query_lName));
				userCorpus.setAuD(Float.parseFloat(rs.getString(Constants.query_aud)));
				userCorpus.setInR(Float.parseFloat(rs.getString(Constants.query_inr)));
				userCorpus.setUsD(Float.parseFloat(rs.getString(Constants.query_usd)));
				userCorpus.setGbP(Float.parseFloat(rs.getString(Constants.query_gbp)));
				userCorpus.setJpY(Float.parseFloat(rs.getString(Constants.query_yen)));
				userCorpus.setEuR(Float.parseFloat(rs.getString(Constants.query_euro)));
				userCorpus.setAllowableINR(Float.parseFloat(rs.getString(Constants.query_allowable)));
				userCorpus.setAllowableAUD(Float.parseFloat(rs.getString(Constants.query_aud)));
				userCorpus.setAllowableUSD(Float.parseFloat(rs.getString(Constants.query_usd)));
				userCorpus.setAllowableGBP(Float.parseFloat(rs.getString(Constants.query_gbp)));
				userCorpus.setAllowableJPY(Float.parseFloat(rs.getString(Constants.query_yen)));
				userCorpus.setAllowableEURO(Float.parseFloat(rs.getString(Constants.query_euro)));
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}

		/*Max Available*/
		userCorpus.setAudMax((float)(userCorpus.getAllowableINR()/rateBean.getAUD()));
		userCorpus.setEuroMax((float)(userCorpus.getAllowableINR()/rateBean.getEURO()));
		userCorpus.setGbpMax((float)(userCorpus.getAllowableINR()/rateBean.getGBP()));
		userCorpus.setJpyMax((float)(userCorpus.getAllowableINR()/rateBean.getJPY()));
		userCorpus.setUsdMax((float)(userCorpus.getAllowableINR()/rateBean.getUSD()));
		userCorpus.setAllowableINR(userCorpus.getAllowableINR());
		
		return userCorpus;
	}
	
	public double getRate(TnxRateBean rateBean,String toCur){
		if(toCur.equalsIgnoreCase(Constants.query_aud)){
			return rateBean.getAUD();
		}else if(toCur.equalsIgnoreCase(Constants.query_euro)){
			return rateBean.getEURO();
		}else if(toCur.equalsIgnoreCase(Constants.query_gbp)){
			return rateBean.getGBP();
		}else if(toCur.equalsIgnoreCase(Constants.query_usd)){
			return rateBean.getUSD();
		}else if(toCur.equalsIgnoreCase(Constants.query_yen)){
			return rateBean.getJPY();
		}else {
			return 0.00;
		}
			
	}
	
	public int buyCurrency(CorpusBean corpusBean,TnxRateBean rateBean, String toCur, double amt){
		/* DB connections here
		 * If Buy success return 1
		 * else return 0;
		 */
		/* Get the transfer rate of particular currency*/
		double rate = getRate(rateBean,toCur);
		
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
			ps.setDouble(4, getNewAlwbleAmt(toCur, amt, corpusBean));
			ps.setString(5,	Integer.toString(corpusBean.getRollNbr()) );
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
	
	//execute sell currency
	
	public int eSellCurrency(CorpusBean corpusBean,TnxRateBean rateBean, String toCur, double amt){
		/* DB connections here
		 * If Buy success return 1
		 * else return 0;
		 */
		/* Get the transfer rate of particular currency*/
		double rate = getRate(rateBean,toCur);
		
		Connection con=GetCon.getCon();
		PreparedStatement ps;
		try {
			/* Update Corpus*/
			
			double eInr = corpusBean.getAllowableINR()+(amt*rate);
					if(eInr< 0){
						return 0;
					}
			ps=con.prepareStatement(getUpdateQuery(toCur));
			ps.setDouble(1,  corpusBean.getInR()-(amt*rate)); 
			ps.setDouble(2, eInr);
			ps.setDouble(3, getNewAmt(toCur, amt, corpusBean));
			ps.setDouble(4, getNewAlwbleAmt(toCur, amt, corpusBean));
			ps.setString(5,	Integer.toString(corpusBean.getRollNbr()) );
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
	
	
	// FORWARD TRANSFER
	public int buyCurrency(CorpusBean corpusBean, String toCur, double amt, double fQuote, String dateFuture){
		/* DB connections here
		 * If Buy success return 1
		 * else return 0;
		 */
		Connection con=GetCon.getCon();
		PreparedStatement ps;
		try {
			/* Update Corpus*/
			
			double eInr = corpusBean.getAllowableINR()-(amt*fQuote);
					if(eInr< 0){
						return 0;
					}
			ps=con.prepareStatement(Constants.FWD_BUY_UPDATE_CORPUS);
			ps.setDouble(1, eInr);
			ps.setString(2,	Integer.toString(corpusBean.getRollNbr()) );
			ps.executeQuery();
			System.out.println("Forward Buy Currency : Corpus Updated ");
			
			ps=con.prepareStatement(Constants.FORWARD_BUY);
			ps.setString(1,Integer.toString(corpusBean.getRollNbr())); 
			ps.setString(2, Constants.query_buy);
			ps.setTimestamp(3, getTimeStamp(dateFuture));
			ps.setString(4, Constants.query_inr);
			ps.setDouble(5, RoundTo2Decimals(fQuote));
			
			ps.setString(6, toCur);
			ps.setDouble(7, Constants.query_inr_rate);
			
			ps.setDouble(8, amt);
			
			ps.setTimestamp(9, getCurrentTimeStamp());
			ps.setInt(10,0);
			System.out.println(Integer.toString(corpusBean.getRollNbr())); 
			System.out.println(Constants.query_buy);
			System.out.println(getTimeStamp(dateFuture));
			System.out.println(Constants.query_inr);
			System.out.println(RoundTo2Decimals(fQuote));
			
			System.out.println(toCur);
			System.out.println(Constants.query_inr_rate);
			
			System.out.println(amt);
			
			System.out.println(getCurrentTimeStamp());
			
			ps.executeQuery();
			
			
			return 1;	
			
		} catch (SQLException e) {
			System.out.println("Error Connecting to DB!");
			e.printStackTrace();
		}
		
		return 0;
	}
	public int sellCurrency(CorpusBean CorpusBean,TnxRateBean rateBean, String fromCur, double amt){
		/* DB connections here
		 * If Buy success return 1
		 * else return 0;
		 */
		/* Get the transfer rate of particular currency*/
		double rate = getRate(rateBean,fromCur);
		
		Connection con=GetCon.getCon();
		PreparedStatement ps;
		try {
			/* Update Corpus*/
			
			double eInr = CorpusBean.getInR()+(rate*amt);
			double eCur =getNewAllwbleSellAmt(fromCur, amt, CorpusBean);
					if(eCur< 0){
						return 0;
					}
			ps=con.prepareStatement(getSellUpdateQuery(fromCur));
			ps.setDouble(1, eInr ); 
			ps.setDouble(2, getNewSellAmt(fromCur, amt, CorpusBean));
			ps.setDouble(3, eCur);
			ps.setDouble(4, CorpusBean.getAllowableINR()+(rate*amt));
			ps.setString(5,	Integer.toString(CorpusBean.getRollNbr()) );
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
	//FORWARD SELL
	public int sellCurrency(String frmCur, double amt, String dateFuture,double fQuote,CorpusBean corpusBean){
		/* DB connections here
		 * If Buy success return 1
		 * else return 0;
		 */
		Connection con=GetCon.getCon();
		PreparedStatement ps;
		try {
			/* Update Corpus*/
			
			
			ps=con.prepareStatement(fwdSellQuery(frmCur));
			ps.setDouble(1, getNewSellAmt(frmCur, amt, corpusBean));
			ps.setString(2,	Integer.toString(corpusBean.getRollNbr()) );
			ps.executeQuery();
			System.out.println("Forward Sell Currency : Corpus Updated ");
			
			ps=con.prepareStatement(Constants.FORWARD_BUY);
			ps.setString(1,Integer.toString(corpusBean.getRollNbr())); 
			ps.setString(2, Constants.query_sell);
			ps.setTimestamp(3, getTimeStamp(dateFuture));
			ps.setString(4, frmCur);
			ps.setDouble(5, Constants.query_inr_rate);
			
			ps.setString(6, Constants.query_inr);
			ps.setDouble(7, RoundTo2Decimals(fQuote));
			
			ps.setDouble(8, amt);
			
			ps.setTimestamp(9, getCurrentTimeStamp());
			ps.setInt(10,0);
			
			System.out.println(Integer.toString(corpusBean.getRollNbr())); 
			System.out.println(Constants.query_buy);
			System.out.println(getTimeStamp(dateFuture));
			System.out.println(Constants.query_inr);
			System.out.println(RoundTo2Decimals(fQuote));
			
			System.out.println(frmCur);
			System.out.println(Constants.query_inr_rate);
			
			System.out.println(amt);
			
			System.out.println(getCurrentTimeStamp());
			
			ps.executeQuery();
			
			
			return 1;	
			
		} catch (SQLException e) {
			System.out.println("Error Connecting to DB!");
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	/* Initial corpus
	 * max values not set*/
	public CorpusBean getUserCorpus(int userID){
		CorpusBean userCorpus = new CorpusBean();
/* DB connections here
*/
		userCorpus.setRollNbr(userID);
		userCorpus.setFirstName("Baskar");
		userCorpus.setLastName("Sadasivam");
		userCorpus.setAuD(dummy);
		userCorpus.setInR(dummy);
		userCorpus.setUsD(dummy);
		userCorpus.setGbP(dummy);
		userCorpus.setJpY(dummy);
		userCorpus.setEuR(dummy);
		
		// Admin Privileges
		//userCorpus.setAdmin(true);
	
		return userCorpus;
	}
	public CorpusBean getAdmin(String UserID){
		CorpusBean userCorpus = new CorpusBean();
		/* DB connections here
		*/	
		Connection con=GetCon.getCon();
		PreparedStatement ps;
		
		try {
			ps=con.prepareStatement(Constants.ADMIN_DETAILS);
			ps.setString(1, UserID);
			
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				userCorpus.setAdmin(true);
				userCorpus.setFirstName(rs.getString(Constants.query_fName));
				userCorpus.setLastName(rs.getString(Constants.query_lName));
			}
		}catch(SQLException se){
			se.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
			
			return userCorpus;
	}
	
	private static java.sql.Timestamp getCurrentTimeStamp() {
		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());
	}
	private String getUpdateQuery(String toCur){
		String query = Constants.BUY_UPDATE_CORPUS_1+ toCur+" = ? ,"+Constants.ALLOWABLE+ toCur+" = ? "+Constants.BUY_UPDATE_CORPUS_2;
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
	
	private double getNewAllwbleSellAmt(String fromCur, double amt,CorpusBean corpBean){
		if(fromCur.equalsIgnoreCase(Constants.query_aud)){
			return corpBean.getAllowableAUD()-(amt);
		}else if(fromCur.equalsIgnoreCase(Constants.query_euro)){
			return corpBean.getAllowableEURO()-(amt);
		}else if(fromCur.equalsIgnoreCase(Constants.query_gbp)){
			return corpBean.getAllowableGBP()-(amt);
		}else if(fromCur.equalsIgnoreCase(Constants.query_usd)){
			return corpBean.getAllowableUSD()-(amt);
		}else if(fromCur.equalsIgnoreCase(Constants.query_yen)){
			return corpBean.getAllowableJPY()-(amt);
		}else {
			return 0.00;
		}	
	}	
	private String getSellUpdateQuery(String fromCur){
		String query = Constants.SELL_UPDATE_CORPUS_1+ fromCur+" = ? ,"+Constants.ALLOWABLE+ fromCur+" = ? "+Constants.SELL_UPDATE_CORPUS_2;
		return query;
		}
	
	private double RoundTo2Decimals(double val) {
        DecimalFormat df2 = new DecimalFormat("###.##");
        return Double.valueOf(df2.format(val));
		}
	private String fwdSellQuery( String toCur){
		if(toCur.equalsIgnoreCase(Constants.query_aud)){
			return Constants.FWD_SELL_UPDATE_CORPUS_1+Constants.query_A_aud+" = ? " +Constants.BUY_UPDATE_CORPUS_2;
		}else if(toCur.equalsIgnoreCase(Constants.query_euro)){
			return Constants.FWD_SELL_UPDATE_CORPUS_1+Constants.query_A_euro+" = ? "+Constants.BUY_UPDATE_CORPUS_2;
		}else if(toCur.equalsIgnoreCase(Constants.query_gbp)){
			return Constants.FWD_SELL_UPDATE_CORPUS_1+Constants.query_A_gbp+" = ? "+Constants.BUY_UPDATE_CORPUS_2;
		}else if(toCur.equalsIgnoreCase(Constants.query_usd)){
			return Constants.FWD_SELL_UPDATE_CORPUS_1+Constants.query_A_usd+" = ? "+Constants.BUY_UPDATE_CORPUS_2;
		}else if(toCur.equalsIgnoreCase(Constants.query_yen)){
			return Constants.FWD_SELL_UPDATE_CORPUS_1+Constants.query_A_yen+" = ? "+Constants.BUY_UPDATE_CORPUS_2;
		}else {
			return Constants.FWD_SELL_UPDATE_CORPUS_1+Constants.query_A_usd+" = ? "+Constants.BUY_UPDATE_CORPUS_2;
		}
		
	}
	
	private double getNewAlwbleAmt(String toCur, double amt,CorpusBean corpBean){
		if(toCur.equalsIgnoreCase(Constants.query_aud)){
			return corpBean.getAllowableAUD()+(amt);
		}else if(toCur.equalsIgnoreCase(Constants.query_euro)){
			return corpBean.getAllowableEURO()+(amt);
		}else if(toCur.equalsIgnoreCase(Constants.query_gbp)){
			return corpBean.getAllowableGBP()+(amt);
		}else if(toCur.equalsIgnoreCase(Constants.query_usd)){
			return corpBean.getAllowableUSD()+(amt);
		}else if(toCur.equalsIgnoreCase(Constants.query_yen)){
			return corpBean.getAllowableJPY()+(amt);
		}else {
			return 0.00;
		}		
	}
	
	
	public int buyCurrency(CorpusBean corpusBean,TnxRateBean rateBean, String toCur, double amt, int ID){
		/* DB connections here
		 * If Buy success return 1
		 * else return 0;
		 */
		/* Get the transfer rate of particular currency*/
		double rate = getRate(rateBean,toCur);
		
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
			ps.setDouble(4, getNewAlwbleAmt(toCur, amt, corpusBean));
			ps.setString(5,	Integer.toString(corpusBean.getRollNbr()) );
			ps.executeQuery();
			System.out.println("Buy Currency : Corpus Updated ");
			
			ps=con.prepareStatement(Constants.EXEC_BUY_QUERY);
			ps.setInt(1, ID);
			ps.setString(2,Integer.toString(corpusBean.getRollNbr())); 
			ps.setString(3, Constants.query_buy);
			ps.setInt(4, Constants.query_spot);
			ps.setString(5, Constants.query_inr);
			ps.setDouble(6, rate);
			
			ps.setString(7, toCur);
			ps.setDouble(8, Constants.query_inr_rate);
			
			ps.setDouble(9, amt);
			
			ps.setTimestamp(10, getCurrentTimeStamp());
			
			ps.executeQuery();
			
			
			return 1;	
			
		} catch (SQLException e) {
			System.out.println("Error Connecting to DB!");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int[] getUsers(){
		int[] rNbr = null;
		int i=0;
		Connection con=GetCon.getCon();
		PreparedStatement ps;
		try{
			ps=con.prepareStatement(Constants.usersList);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				rNbr[i]=Integer.parseInt(rs.getString("USERNAME"));
			}
			System.out.println("User List Retrieved ");
		}catch(SQLException e){
			e.printStackTrace();
		}
			
		return rNbr;
	}
	
	
}
