package transactionBean;

public class EvaluateBean {
	public int rank;
	public float totalValue;
	public float INR;
	public float USD;
	public float GBP;
	public float JPY;
	public float EURO;
	public float AUD;
	public int rollNbr;
	public float sales;
	public float purchase;
	public float clInv;
	public float profit;
	
	public int nbrSales;
	public int nbrPurchases;
	public int nbrFwdSales;
	public int nbrFwdPurchases;
	
	public float getProfit() {
		return profit;
	}
	public void setProfit(float profit) {
		this.profit = profit;
	}
	public int getRollNbr() {
		return rollNbr;
	}
	public void setRollNbr(int rollNbr) {
		this.rollNbr = rollNbr;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public float getTotalValue() {
		return totalValue;
	}
	public void setTotalValue(float totalValue) {
		this.totalValue = totalValue;
	}
	public float getINR() {
		return INR;
	}
	public void setINR(float iNR) {
		INR = iNR;
	}
	public float getUSD() {
		return USD;
	}
	public void setUSD(float uSD) {
		USD = uSD;
	}
	public float getGBP() {
		return GBP;
	}
	public void setGBP(float gBP) {
		GBP = gBP;
	}
	public float getJPY() {
		return JPY;
	}
	public void setJPY(float jPY) {
		JPY = jPY;
	}
	public float getEURO() {
		return EURO;
	}
	public void setEURO(float eURO) {
		EURO = eURO;
	}
	public float getAUD() {
		return AUD;
	}
	public void setAUD(float aUD) {
		AUD = aUD;
	}
	public float getSales() {
		return sales;
	}
	public int getNbrSales() {
		return nbrSales;
	}
	public void setNbrSales(int nbrSales) {
		this.nbrSales = nbrSales;
	}
	public int getNbrPurchases() {
		return nbrPurchases;
	}
	public void setNbrPurchases(int nbrPurchases) {
		this.nbrPurchases = nbrPurchases;
	}
	public int getNbrFwdSales() {
		return nbrFwdSales;
	}
	public void setNbrFwdSales(int nbrFwdSales) {
		this.nbrFwdSales = nbrFwdSales;
	}
	public int getNbrFwdPurchases() {
		return nbrFwdPurchases;
	}
	public void setNbrFwdPurchases(int nbrFwdPurchases) {
		this.nbrFwdPurchases = nbrFwdPurchases;
	}
	public void setSales(float sales) {
		this.sales = sales;
	}
	public float getPurchase() {
		return purchase;
	}
	public void setPurchase(float purchase) {
		this.purchase = purchase;
	}
	public float getClInv() {
		return clInv;
	}
	public void setClInv(float clInv) {
		this.clInv = clInv;
	}

}
