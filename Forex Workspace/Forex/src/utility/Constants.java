package utility;

public class Constants {
	
	public static final String converterLink = "http://www.google.com/finance/converter?a=1&from=";
	public static final String currencyList[] =   {"Rupee (INR)",
											"Pounds (GBP)",
											"US Dollars (USD)",
											"AUS Dollars (AUD)",
											"Japan Yen (JPY)",
											"EURO (EURO)"
											};
	public static final String	 tnxCurrencyList[] =   {"------",
														"Pounds (GBP)",
														"US Dollars (USD)",
														"AUS Dollars (AUD)",
														"Japan Yen (JPY)",
														"EURO (EURO)"
														};
	public static final String tnxCurrencyID[] =   {"init","GBP","USD","AUD","JPY","EURO"};
	public static final String currencyID[] =   {"INR","GBP","USD","AUD","JPY","EURO"};
	
	public static final String corpusBean = "corpusBean" ;
	public static final String rateBean = "rateBean" ;
	public static final String rollNbr = "rollNbr";
	public static final String password = "password";
	public static final String userName = "username";
	public static final String repassword = "repassword";
	/* DB queries*/
	/* Verify Login*/
	public static final String VERIFY_LOGIN = "SELECT * FROM LOGIN WHERE"+
												" USERNAME = ? AND PASSWORD = ?";
	/* Get Corpus*/
	public static final String GET_CORPUS = "SELECT  L.ROLLNBR, "
											+ "L.FIRSTNAME, "
											+ "L.LASTNAME, "
											+ "C.ALLOWABLEINR, "
											+ "C.AUD, "
											+ "C.GBP, "
											+ "C.INR, "
											+ "C.USD, "
											+ "C.JPY, "
											+ "C.EURO, "
											+ "C.ALLOWABLE_AUD, "
											+ "C.ALLOWABLE_GBP, "
											+ "C.ALLOWABLE_USD, "
											+ "C.ALLOWABLE_JPY, "
											+ "C.ALLOWABLE_EURO "
											+ "FROM CORPUS C JOIN LOGIN L "
											+ "ON C.USERNAME = L.USERNAME AND"
											+ " C.USERNAME = ? ";
			
	/* BUY QUERY*/
	public static final String BUY_QUERY = "INSERT INTO TRANSACTION_REPORT    ( TNX_ID, "
	 +"                                    USERNAME, "
	 +"                                    TNX_TYPE , "
	 +"                                    IS_FUTURE , "
	 +"                                    FROM_CNY , "
	 +"                                    FROM_CNY_RATE , "
	 +"                                    TO_CNY , "
	 +"                                    TO_CNY_RATE , "
	 +"                                    AMOUNT, "
	 +"                                    EXEC_DATE  ) "
	 +"VALUES (TRIM_FOUR.NEXTVAL,?,?,?,?,?,?,?,?,?) ";
	/* POST BUY CORPUS UPDATE QUERY*/
	public static final String BUY_UPDATE_CORPUS_1 =" UPDATE CORPUS SET INR = ?, ALLOWABLEINR =? , ";
	public static final String BUY_UPDATE_CORPUS_2 =" WHERE USERNAME =? ";
	public static final String ALLOWABLE =" ALLOWABLE_";
	/* FWD BUY QUERY*/
	public static final String FWD_BUY_UPDATE_CORPUS =" UPDATE CORPUS SET ALLOWABLEINR =?  WHERE USERNAME =? ";
	/* SELL QUERY*/
	public static final String SELL_QUERY = "INSERT INTO TRANSACTION_REPORT    ( TNX_ID, "
	 +"                                    USERNAME, "
	 +"                                    TNX_TYPE , "
	 +"                                    IS_FUTURE , "
	 +"                                    FROM_CNY , "
	 +"                                    FROM_CNY_RATE , "
	 +"                                    TO_CNY , "
	 +"                                    TO_CNY_RATE , "
	 +"                                    AMOUNT, "
	 +"                                    EXEC_DATE  ) "
	 +"VALUES (TRIM_FOUR.NEXTVAL,?,?,?,?,?,?,?,?,?) ";
	
	/* FWD SELL QUERY*/
	public static final String FWD_SELL_UPDATE_CORPUS_1 =" UPDATE CORPUS SET  ";
	
	/* POST BUY CORPUS UPDATE QUERY*/
	public static final String SELL_UPDATE_CORPUS_1 =" UPDATE CORPUS SET INR = ?,  ";
	public static final String SELL_UPDATE_CORPUS_2 =" , ALLOWABLEINR = ? WHERE USERNAME =? ";
	/* QUERY CONSTANTS*/
	public static final String query_rollnbr = "ROLLNBR";
	public static final String query_fName = "FIRSTNAME";
	public static final String query_lName = "LASTNAME";
	public static final String query_allowable = "ALLOWABLEINR";
	public static final String query_inr = "INR";
	public static final String query_aud = "AUD";
	public static final String query_gbp = "GBP";
	public static final String query_usd = "USD";
	public static final String query_yen = "JPY";
	public static final String query_euro = "EURO";
	
	public static final String query_A_aud = "ALLOWABLE_AUD";
	public static final String query_A_gbp = "ALLOWABLE_GBP";
	public static final String query_A_usd = "ALLOWABLE_USD";
	public static final String query_A_yen = "ALLOWABLE_JPY";
	public static final String query_A_euro = "ALLOWABLE_EURO";
	
	
	public static final String query_password = "PASSWORD";
	public static final String query_username = "USERNAME";
	public static final String query_buy ="BUY";
	public static final String query_sell ="SELL";
	public static final int query_spot = 0;
	public static final int query_future = 0;
	public static final double query_inr_rate = 1.0;
	
	
	/* Currency rates to be passed as parameters for FORWARDS*/
	   
	public static final String USDTag ="american-dollar/usd";
	public static final String EUROTag ="european-euro/eur";
	public static final String YENTag ="china-1-year";
	public static final String INRTag ="india-3-month";
	public static final String JPYTag ="japanese-yen/jpy";
	public static final String AUDTag  = "australian-dollar/aud";
	public static final String GBPTag = "british-pound-sterling/gbp";

	
	public static final String FORWARD_BEAN = "fwdBean";
	public static final String FORWARD_QUOTE = "fwdQuote";

	/* FORWARD BUY*/
	public static final String FORWARD_BUY = " INSERT INTO FORWARDS ( "
												+ "TNX_ID, "
												+ "USERNAME, "
												+ "TNX_TYPE , "
												+ "FUTURE_DATE, "
												+ "FROM_CNY , "
												+ "FROM_CNY_RATE , "
												+ "TO_CNY , "
												+ "TO_CNY_RATE , "
												+ "AMOUNT, "
												+ "EXEC_DATE,COMPLETED ) VALUES "
												+ "(TRIM_FOUR.NEXTVAL,"
												+ "?,?,?,?,?,?,?,?,?,?)";
	
	
	public static final String query_fwdbuy ="FORWARD BUY";
	public static final String query_fwdsell ="FORWARD SELL";
	public static final String select_today_forwards = " SELECT TNX_ID,  USERNAME, TNX_TYPE , FUTURE_DATE, FROM_CNY , FROM_CNY_RATE , TO_CNY , TO_CNY_RATE , "
														+ "AMOUNT, EXEC_DATE , FUTURE_DATE FROM FORWARDS WHERE USERNAME = ? and "
														+ "FUTURE_DATE = SYSDATE AND COMPLETED = 0" ;
	public static final String select_unexecuted_forwards = " SELECT TNX_ID,  USERNAME, TNX_TYPE , FUTURE_DATE, FROM_CNY , FROM_CNY_RATE , TO_CNY , TO_CNY_RATE , "
															+ "AMOUNT, EXEC_DATE , FUTURE_DATE FROM FORWARDS WHERE USERNAME = ? and "
															+ "FUTURE_DATE <= SYSDATE AND COMPLETED = 0" ;
	public static final String check_today_forwards = "SELECT COUNT(TNX_ID) FROM FORWARDS WHERE USERNAME = ? and FUTURE_DATE = SYSDATE AND COMPLETED = 0";
	public static final String check_unexecuted_forwards = "SELECT COUNT(TNX_ID) FROM FORWARDS WHERE USERNAME = ? and FUTURE_DATE <= SYSDATE AND COMPLETED = 0";
	public static final String update_executed_forwards = "UPDATE FORWARDS SET COMPLETED = 1 WHERE TNX_ID = ? ";
	public static final String nbrFwds ="nbrFwds";
	/* TRANSACTION REPORT*/
	public static final String report_query = "SELECT TNX_ID, "
												+ "TNX_TYPE, "
												+ "IS_FUTURE, "
												+ "FUTURE_DATE, "
												+ "FROM_CNY, "
												+ "FROM_CNY_RATE, "
												+ "TO_CNY, "
												+ "TO_CNY_RATE, "
												+ "AMOUNT, "
												+ "USERNAME, "
												+ "EXEC_DATE  "
												+ "FROM TRANSACTION_REPORT"
												+ " WHERE USERNAME= ? ";
	
	public static final String fwd_report_query = 		"SELECT TNX_ID, "
														+ "TNX_TYPE, "
														+ "FUTURE_DATE, "
														+ "FROM_CNY, "
														+ "FROM_CNY_RATE, "
														+ "TO_CNY, "
														+ "TO_CNY_RATE, "
														+ "AMOUNT, "
														+ "USERNAME, "
														+ "EXEC_DATE  "
														+ "FROM FORWARDS "
														+ " WHERE USERNAME= ? "
														+ "AND COMPLETED = 0 ";
	public static final String Report_TNX_ID =	"TNX_ID";
	public static final String Report_TNX_TYPE = "TNX_TYPE";
	public static final String Report_IS_FUTURE = "IS_FUTURE";
	public static final String Report_FUTURE_DATE = "FUTURE_DATE";
	public static final String Report_FROM_CNY = "FROM_CNY" ;
	public static final String Report_FROM_CNY_RATE = "FROM_CNY_RATE";
	public static final String Report_TO_CNY = "TO_CNY";
	public static final String Report_TO_CNY_RATE= "TO_CNY_RATE" ;
	public static final String Report_COMMENTS ="COMMENTS";
	public static final String Report_AMOUNT = "AMOUNT";
	public static final String Report_USERNAME = "USERNAME";
	public static final String Report_EXEC_DATE = "EXEC_DATE"; 
	public static final String netWorth = "NET_WORTH";
	// total evaluation
	public static final String TOTAL_CORPUS = "SELECT  L.ROLLNBR, "
												+ "L.FIRSTNAME, "
												+ "L.LASTNAME, "
												+ "C.ALLOWABLEINR, "
												+ "C.AUD, "
												+ "C.GBP, "
												+ "C.INR, "
												+ "C.USD, "
												+ "C.JPY, "
												+ "C.EURO, C.NET_WORTH "
												+ "FROM CORPUS C JOIN LOGIN L "
												+ "ON C.USERNAME = L.USERNAME "
												+ "ORDER BY C.NET_WORTH" ;
	public static final String TOTAL_EVAL_UPDATE = "UPDATE CORPUS SET NET_WORTH = ? WHERE USERNAME = ? ";


	/* RESET PASSSWORD*/
	public static final String RESET_PWD = "UPDATE LOGIN SET PASSWORD = ? WHERE ROLLNBR = ?";
	
	
	/* DISPLAY MESSAGE*/
	public static final String DISPLAY_MSG = "Marque_Msg";
	
	/* SUmmary */
	public static final String REPORT= "report" ;
	public static final String FwdREPORT= "fwdreport" ;
	public static final String TOTAL_REPORT= "totalReport" ;
	public static final String Report_Bean= "ReportBean" ;
	
	public static final float penaltyValue = 0.2f;
	public static final String penalty = "PEN";
	public static final  double penDummy = 0.0d;
	
	/* BUY QUERY EXECUTE FORWARDS*/
	public static final String EXEC_BUY_QUERY = "INSERT INTO TRANSACTION_REPORT    ( TNX_ID, "
	 +"                                    USERNAME, "
	 +"                                    TNX_TYPE , "
	 +"                                    IS_FUTURE , "
	 +"                                    FROM_CNY , "
	 +"                                    FROM_CNY_RATE , "
	 +"                                    TO_CNY , "
	 +"                                    TO_CNY_RATE , "
	 +"                                    AMOUNT, "
	 +"                                    EXEC_DATE  ) "
	 +"VALUES (?,?,?,?,?,?,?,?,?,?) ";
	//RATE UPDATE REGULAR INTERVAL
	public static String rateUpdate = " UPDATE RATE_TABLE SET "
										+ " LAST_UPADTE = SYSTIMESTAMP, "
										+ " INR = ?, USD = ?,GBP = ?, JPY = ?, EURO = ?,AUD = ? "
										+ " WHERE R_NBR=1 " ;
	
	// CORPUS EVALUATION ---- INSERT USERNAME INTO THIS TABLE. USE UPDATE ALONE
	public static String EVALUATE_CORPUS = "  UPDATE EVALUATE_CORPUS "
										+ "SET INR = (( SELECT INR FROM RATE_TABLE WHERE R_NBR =1 ) * (SELECT INR FROM CORPUS WHERE USERNAME = ?)),  "
										+ "USD = (( SELECT USD FROM RATE_TABLE WHERE R_NBR =1 ) * (SELECT USD FROM CORPUS WHERE USERNAME = ?)),  "
										+ "GBP =(( SELECT GBP FROM RATE_TABLE WHERE R_NBR =1 ) * (SELECT GBP FROM CORPUS WHERE USERNAME = ?)),  "
										+ "YEN =(( SELECT JPY FROM RATE_TABLE WHERE R_NBR =1 ) * (SELECT JPY FROM CORPUS WHERE USERNAME = ?)),  "
										+ "EURO =(( SELECT EURO FROM RATE_TABLE WHERE R_NBR =1 ) * (SELECT EURO FROM CORPUS WHERE USERNAME = ?)),  "
										+ "AUD=(( SELECT AUD FROM RATE_TABLE WHERE R_NBR =1 ) * (SELECT AUD FROM CORPUS WHERE USERNAME = ?)),  "
										+ "LAST_UPDATE = SYSTIMESTAMP" ;
	
	public static String usersList = " SELECT USERNAME FROM CORPUS";
	
	// CORPUS EVALUATION FOR USER--
	public static String EVALUATE_USER_CORPUS =	"SELECT RANK, USERNAME, TOTAL_VALUE FROM (SELECT ROWNUM AS RANK, USERNAME, TOTAL_VALUE,"
												+ " INR, USD, GBP, JPY, EURO, AUD, LAST_UPDATE FROM (SELECT   C.USERNAME AS USERNAME,"
												+ " C.INR * R.INR AS INR, C.USD * R.USD AS USD,  C.GBP * R.GBP AS GBP, "
												+ " C.JPY * R.JPY AS JPY,  C.EURO * R.EURO AS EURO, C.AUD * R.AUD AS AUD ,  "
												+ " SYSTIMESTAMP AS LAST_UPDATE,  (  C.INR * R.INR + C.USD * R.USD +  C.GBP * R.GBP + "
												+ " C.JPY * R.JPY +  C.EURO * R.EURO + C.AUD * R.AUD  ) AS TOTAL_VALUE  FROM CORPUS C,"
												+ " RATE_TABLE R WHERE R.R_NBR=1 ORDER BY TOTAL_VALUE DESC ) )WHERE USERNAME= ?" ;
	/*
	public static String EVALUATE_WHOLE_USERS_CORPUS =	"SELECT ROWNUM AS RANK, USERNAME, TOTAL_VALUE, INR, USD, GBP, JPY, EURO, AUD, "
														+ "LAST_UPDATE FROM (SELECT   C.USERNAME AS USERNAME, C.INR * R.INR AS INR, "
														+ "C.USD * R.USD AS USD, C.GBP * R.GBP AS GBP, C.JPY * R.JPY AS JPY,  "
														+ "C.EURO * R.EURO AS EURO, C.AUD * R.AUD AS AUD ,  SYSTIMESTAMP AS LAST_UPDATE, "
														+ "(  C.INR * R.INR + C.USD * R.USD +  C.GBP * R.GBP + C.JPY * R.JPY +  C.EURO * R.EURO + C.AUD * R.AUD  ) AS TOTAL_VALUE"
														+ " FROM CORPUS C, RATE_TABLE R WHERE R.R_NBR=1  ORDER BY TOTAL_VALUE DESC ) ";
	*/
	public static String EVALUATE_WHOLE_USERS_CORPUS = " SELECT RANKTABLE.RANK AS RANK, RANKTABLE.USERNAME AS USERNAME, "
														+ "RANKTABLE.TOTAL_VALUE AS TOTAL_VALUE, RANKTABLE.INR AS INR, RANKTABLE.USD AS USD, "
														+ "RANKTABLE.GBP AS GBP,RANKTABLE.JPY AS JPY, RANKTABLE.EURO AS EURO, RANKTABLE.AUD AS AUD,	"
														+ "RANKTABLE.LAST_UPDATE AS LAST_UPDATE, 	ACTIVITY.PURCHASE AS PURCHASE ,  ACTIVITY.SALES AS SALES,  "
														+ "ACTIVITY.FWDPURCHASE AS FWDPURCHASE, ACTIVITY.FWDSALE AS FWDSALE FROM 	"
														+ "(SELECT UNIQUE(T.USERNAME) AS USERNAME , PURCHASE.TOT_PUR AS PURCHASE,  SALE.TOT_SALES AS SALES,  	"
														+ "FWDPURCHASE.TOT_FWD_PUR AS FWDPURCHASE, FWDSALE.TOT_FWD_SALE AS FWDSALE FROM TRANSACTION_REPORT T,"
														+ " (SELECT USERNAME, COUNT(*) AS TOT_PUR  FROM TRANSACTION_REPORT WHERE TNX_TYPE= 'BUY' AND IS_FUTURE=0 "
														+ "GROUP BY USERNAME)PURCHASE , 	(SELECT USERNAME, COUNT(*) AS TOT_SALES FROM TRANSACTION_REPORT"
														+ " WHERE TNX_TYPE= 'SELL' AND IS_FUTURE=0 GROUP BY USERNAME)SALE , 	"
														+ "(SELECT USERNAME, COUNT(*) AS TOT_FWD_PUR FROM TRANSACTION_REPORT WHERE TNX_TYPE= 'BUY' AND IS_FUTURE=1 "
														+ "GROUP BY USERNAME)FWDPURCHASE , 	(SELECT USERNAME, COUNT(*) AS TOT_FWD_SALE FROM "
														+ "TRANSACTION_REPORT WHERE TNX_TYPE= 'SELL' AND IS_FUTURE=1 GROUP BY USERNAME)FWDSALE 	"
														+ "WHERE  T.USERNAME = PURCHASE.USERNAME(+) 	AND PURCHASE.USERNAME = SALE.USERNAME (+)	"
														+ "AND SALE.USERNAME = FWDPURCHASE.USERNAME (+)	AND FWDPURCHASE.USERNAME = FWDSALE.USERNAME (+) ) ACTIVITY, "
														+ "	(SELECT ROWNUM AS RANK, USERNAME, TOTAL_VALUE, INR, USD, GBP, JPY, EURO, AUD,"
														+ " 	LAST_UPDATE FROM (SELECT   C.USERNAME AS USERNAME, C.INR * R.INR AS INR, "
														+ "	C.USD * R.USD AS USD, C.GBP * R.GBP AS GBP, C.JPY * R.JPY AS JPY,"
														+ "  	C.EURO * R.EURO AS EURO, C.AUD * R.AUD AS AUD ,  SYSTIMESTAMP AS LAST_UPDATE, 	"
														+ "(  C.INR * R.INR + C.USD * R.USD +  C.GBP * R.GBP + C.JPY * R.JPY +  C.EURO * R.EURO + C.AUD * R.AUD  )"
														+ " AS TOTAL_VALUE 	FROM CORPUS C, RATE_TABLE R WHERE R.R_NBR=1  ORDER BY TOTAL_VALUE DESC ) )RANKTABLE "
														+ "WHERE RANKTABLE.USERNAME = ACTIVITY.USERNAME";
	public static String TOTALVALUE = "TOTAL_VALUE";
	public static String RANK = "RANK";
	public static String EVALUATE_BEAN = "EVALUATE_BEAN";
	public static String EVALUATE_BEAN_LIST = "EVALUATE_BEAN_LIST";
	public static String UPDATE_RATE = " UPDATE RATE_TABLE SET INR = ? , USD = ? , "
										+ "GBP = ? , JPY = ? , EURO = ? , AUD = ? , "
										+ "LAST_UPADTE = SYSTIMESTAMP WHERE R_NBR = 1";
	public static int DEFAULT_AMOUNT = 100000000;
	
	public static String INSERT_NEW_USER_LOGIN = "INSERT INTO LOGIN (ROLLNBR, USERNAME, PASSWORD, EMAIL,ISADMIN,LASTNAME,FIRSTNAME) VALUES (?,?,?,?,?,?,?)" ;
	public static String INSERT_NEW_USER_CORPUS = "INSERT INTO CORPUS (USERNAME ,INR , ALLOWABLEINR ,USD , GBP , JPY , AUD , "
													+ " EURO , ALLOWABLE_AUD , ALLOWABLE_GBP , ALLOWABLE_USD , ALLOWABLE_JPY ,"
													+ " ALLOWABLE_EURO ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
	public static String DEFAULT_PWD = "password";
	public static String ADMIN_DETAILS = "SELECT  LASTNAME, FIRSTNAME FROM LOGIN WHERE USERNAME = ?";
	
	public static String USER_PURCHASE = " SELECT SUM(FROM_CNY_RATE *AMOUNT) AS PURCHASE FROM TRANSACTION_REPORT WHERE TNX_TYPE= 'BUY'AND USERNAME = ? ";
	public static String USER_SALES = " SELECT SUM(FROM_CNY_RATE *AMOUNT) AS SALES FROM TRANSACTION_REPORT WHERE TNX_TYPE= 'SELL'AND USERNAME = ? ";
	public static String USER_CL_INV = " SELECT ( C.USD * R.USD +  C.GBP * R.GBP + C.JPY * R.JPY +  C.EURO * R.EURO + C.AUD * R.AUD  ) AS CL_INV  FROM CORPUS C, RATE_TABLE R WHERE C.USERNAME = ?  AND R.R_NBR = 1 ";
	
	public static String PURCHASE = "PURCHASE";
	public static String SALES = "SALES";
	public static String CL_INV = "CL_INV";
}
