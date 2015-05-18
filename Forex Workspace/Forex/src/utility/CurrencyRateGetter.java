package utility;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
 
public class CurrencyRateGetter {
 
  public double findRate(String fCurrency,String tCurrency) {
 
	Document doc;
	double curValue = 99999999.00;
	try {
		
			doc = Jsoup.connect("http://www.google.com/finance/converter?a=1&from="+fCurrency+"&to="+tCurrency).get();
			Elements info = doc.select("div#currency_converter_result");
			Elements asdf = info.select("> span.bld");
			String toParse = asdf.text(); 
			curValue = Double.valueOf(toParse.substring(0, toParse.indexOf(" ")));
			System.out.println( fCurrency+" : "+curValue);
			System.out.println( "tCurrency"+" : "+tCurrency);

			return curValue; 
		} catch (IOException e) {
				e.printStackTrace();
				curValue = -99999.00;
				return curValue;
			}catch (Exception e) {
				e.printStackTrace();
				curValue = -99999.00;
				return curValue;
			}
	 
  }
 
}
