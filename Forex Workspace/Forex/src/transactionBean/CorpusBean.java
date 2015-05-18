/**
 * 
 */
package transactionBean;

/**
 * @author Baskar Sadasivam
 *
 */
public class CorpusBean {
	int rollNbr;
	String firstName;
	String lastName;
	boolean isAdmin = false;
	
	float inR;
	float usD;
	float gbP;
	float euR;
	float auD;
	float jpY;
	
	float usdMax;
	float gbpMax;
	float euroMax;
	float audMax;
	float jpyMax;
	
	float allowableINR ;
	float allowableUSD ;
	float allowableGBP ;
	float allowableEURO ;
	float allowableAUD ;
	float allowableJPY ;
	
	double total_NetWorth ;
	int Rank;
	
	public double getTotal_NetWorth() {
		return total_NetWorth;
	}
	public void setTotal_NetWorth(double total_NetWorth) {
		this.total_NetWorth = total_NetWorth;
	}
	public int getRank() {
		return Rank;
	}
	public void setRank(int rank) {
		Rank = rank;
	}
	public int getRollNbr() {
		return rollNbr;
	}
	public void setRollNbr(int rollNbr) {
		this.rollNbr = rollNbr;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public float getInR() {
		return inR;
	}
	public void setInR(float inR) {
		this.inR = inR;
	}
	public float getUsD() {
		return usD;
	}
	public void setUsD(float usD) {
		this.usD = usD;
	}
	public float getGbP() {
		return gbP;
	}
	public void setGbP(float gbP) {
		this.gbP = gbP;
	}
	public float getEuR() {
		return euR;
	}
	public void setEuR(float euR) {
		this.euR = euR;
	}
	public float getAuD() {
		return auD;
	}
	public void setAuD(float auD) {
		this.auD = auD;
	}
	public float getJpY() {
		return jpY;
	}
	public void setJpY(float jpY) {
		this.jpY = jpY;
	}
	public float getUsdMax() {
		return usdMax;
	}
	public void setUsdMax(float usdMax) {
		this.usdMax = usdMax;
	}
	public float getGbpMax() {
		return gbpMax;
	}
	public void setGbpMax(float gbpMax) {
		this.gbpMax = gbpMax;
	}
	public float getEuroMax() {
		return euroMax;
	}
	public void setEuroMax(float euroMax) {
		this.euroMax = euroMax;
	}
	public float getAudMax() {
		return audMax;
	}
	public void setAudMax(float audMax) {
		this.audMax = audMax;
	}
	public float getJpyMax() {
		return jpyMax;
	}
	public void setJpyMax(float jpyMax) {
		this.jpyMax = jpyMax;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public float getAllowableINR() {
		return allowableINR;
	}
	public void setAllowableINR(float allowableINR) {
		this.allowableINR = allowableINR;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public float getAllowableUSD() {
		return allowableUSD;
	}
	public void setAllowableUSD(float allowableUSD) {
		this.allowableUSD = allowableUSD;
	}
	public float getAllowableGBP() {
		return allowableGBP;
	}
	public void setAllowableGBP(float allowableGBP) {
		this.allowableGBP = allowableGBP;
	}
	public float getAllowableEURO() {
		return allowableEURO;
	}
	public void setAllowableEURO(float allowableEURO) {
		this.allowableEURO = allowableEURO;
	}
	public float getAllowableAUD() {
		return allowableAUD;
	}
	public void setAllowableAUD(float allowableAUD) {
		this.allowableAUD = allowableAUD;
	}
	public float getAllowableJPY() {
		return allowableJPY;
	}
	public void setAllowableJPY(float allowableJPY) {
		this.allowableJPY = allowableJPY;
	}	

}
