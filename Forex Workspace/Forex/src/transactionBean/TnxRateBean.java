package transactionBean;

public class TnxRateBean {
	public double INR =1.00;
	public double USD ; 
	public double AUD ; 
	public double EURO ; 
	public double JPY ;
	public double GBP ;
	public boolean isError = false ;
	
	public double circuit_limit = 0.10;
	
	public double getINR() {
		return INR;
	}
	public void setINR(double iNR) {
		INR = iNR;
	}
	public double getGBP() {
		return GBP;
	}
	public void setGBP(double gBP) {
		GBP = gBP;
	}
	public double getUSD() {
		return USD;
	}
	public void setUSD(double uSD) {
		USD = uSD;
	}
	public double getAUD() {
		return AUD;
	}
	public void setAUD(double aUD) {
		AUD = aUD;
	}
	public double getEURO() {
		return EURO;
	}
	public void setEURO(double eURO) {
		EURO = eURO;
	}
	public double getJPY() {
		return JPY;
	}
	public void setJPY(double jPY) {
		JPY = jPY;
	}
	public double getCircuit_limit() {
		return circuit_limit;
	}
	public void setCircuit_limit(double circuit_limit) {
		this.circuit_limit = circuit_limit;
	}
	public boolean isError() {
		return isError;
	}
	public void setError(boolean isError) {
		this.isError = isError;
	}
	
}
