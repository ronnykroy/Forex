package utility;

import java.io.IOException;

import org.apache.catalina.ant.FindLeaksTask;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ForwardRateGetter {
	 
	public double findRate(String currencyTag) {
			Document doc;
			double rate = -99999 ;
			boolean isParsed = false;
			try {
					doc = Jsoup.connect("http://www.global-rates.com/interest-rates/libor/"+currencyTag+"-libor-interest-rate-2-weeks.aspx").get();
					parseComplete:
					if(!isParsed){				
					for (Element table : doc.select("table")) {
			            for (Element row : table.select("tr")) {
			            	Elements new1  = row.getElementsByClass("tabledata1") ;
			                Elements tds = new1.select("td");
			                if((tds.text().trim().length() != 0 ) ){
			                	String whole = tds.text();
			                	rate = parseToRate(whole);
			                	isParsed = true;
			                	break parseComplete;
			                }
			            }
			            }
					}
					System.out.println(" Two Week Rate : "+rate);
					return rate; 
				} catch (IOException e) {
						e.printStackTrace();
						rate = -99999.00;
						return rate;
					}catch (Exception e) {
						e.printStackTrace();
						rate = -99999.00;
						return rate;
					}
			 
		  }
	 
	 
	 /* 1-Month Yield*/
	
	 public double findOneMonRate(String currencyTag) {
			Document doc;
			double rate = -99999 ;
			boolean isParsed = false;
			try {
					doc = Jsoup.connect("http://www.global-rates.com/interest-rates/libor/"+currencyTag+"-libor-interest-rate-1-month.aspx").get();
					parseComplete:
					if(!isParsed){				
					for (Element table : doc.select("table")) {
			            for (Element row : table.select("tr")) {
			            	Elements new1  = row.getElementsByClass("tabledata1") ;
			                Elements tds = new1.select("td");
			                if((tds.text().trim().length() != 0 ) ){
			                	String whole = tds.text();
			                	rate = parseToRate(whole);
			                	isParsed = true;
			                	break parseComplete;
			                }
			            }
			            }
					}
					System.out.println(" One Month Rate : "+rate);
					return rate; 
				} catch (IOException e) {
						e.printStackTrace();
						rate = -99999.00;
						return rate;
					}catch (Exception e) {
						e.printStackTrace();
						rate = -99999.00;
						return rate;
					}
			 
		  }
	 public double parseToRate(String fullDoc){
		 double rate = 0 ;
     	rate = Double.parseDouble(fullDoc.substring(fullDoc.indexOf(" ")+9,fullDoc.indexOf("%")-1));
     	return (rate/100) ;
		 
	 }
	 
	 public double parseToRateINV(String fullDoc){
		 double rate = 0 ;
     	rate = Double.parseDouble(fullDoc.substring(0,fullDoc.indexOf(" ")-1));
     	return (rate/100) ;
		 
	 }
	/*
	 * Chinnese rates
	 * 
	 */
	 public double invDCRate(String currencyTag) {
			Document doc;
			double rate = -99999 ;
			boolean isParsed = false;
			try {
					doc = Jsoup.connect("http://www.investing.com/rates-bonds/"+currencyTag+"-bond-yield-historical-data")
							.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
							.get();
					parseComplete:
					if(!isParsed){	
						for (Element div : doc.select("div#results_box")) {
							for (Element table : div.select("table#curr_table")) {
					            for (Element row : table.select("tr")) {
					            	Elements new1  = row.getElementsByClass("greenFont") ;
					                Elements tds = new1.select("td");
					                if((tds.text().trim().length() != 0 ) ){
					                	String whole = tds.text();
					                	System.out.println(whole);
					                	rate = parseToRateINV(whole);
					                	isParsed = true;
					                	break parseComplete;
					                }
					            }

				            }
				            }
					
					}
					System.out.println(" One Month Rate : "+rate);
					return rate; 
				} catch (IOException e) {
						e.printStackTrace();
						rate = -99999.00;
						return rate;
					}catch (Exception e) {
						e.printStackTrace();
						rate = -99999.00;
						return rate;
					}
			 
		  }
	 
	 /* Currency rates to be passed as parameters
	  * USD  = findOneMonRate("american-dollar/usd");		 String currTag ="american-dollar/usd";
	  * EURO = String currTag ="european-euro/eur";
	  * 
	  * 
	  * YEN  =  www.investing.com/rates-bonds/china-1-year-bond-yield-historical-data  
	  * china-1-year
	  * INR  =  http://www.investing.com/rates-bonds/india-3-month-bond-yield-historical-data
	  * india-3-month
	  * 
	  * JPY  = String currTag ="japanese-yen/jpy";
	  * GBP  = british-pound-sterling/gbp
	  * AUD  = australian-dollar/aud
	  * */
	 
	 
	 /*public static void main(String args[]){
		 
		 String currTag ="/british-pound-sterling/gbp";
		 double test = findOneMonRate(currTag);
		 System.out.println(test);
		 test = findRate(currTag);
		 System.out.println(test);
		 
		 
		 double test = invDCRate("india-3-month");
		 System.out.println(test);
		 
	 }*/
}
