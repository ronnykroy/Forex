package transactionDA;
import utility.*;
import transactionBean.ForwardsBean;
import transactionBean.TnxRateBean;
public class ForwardRateDA {
	ForwardRateGetter fwdRateGetter = new ForwardRateGetter();
	// public static final String tnxCurrencyID[] =   {"init","GBP","USD","AUD","JPY","EURO"};
	public String getCurrTag(String ID){
		if(ID.equalsIgnoreCase(Constants.tnxCurrencyID[1])){
			return Constants.GBPTag;
		}else if(ID.equalsIgnoreCase(Constants.tnxCurrencyID[2])){
			return Constants.USDTag;
		}else if(ID.equalsIgnoreCase(Constants.tnxCurrencyID[3])){
			return Constants.AUDTag;
		}else if(ID.equalsIgnoreCase(Constants.tnxCurrencyID[4])){
			return Constants.JPYTag;
		}else if(ID.equalsIgnoreCase(Constants.tnxCurrencyID[5])){
			return Constants.EUROTag;
		}
		
		return Constants.INRTag;
	}
	
	public double getTnxRate(String ID, TnxRateBean rateBean){
		if(ID.equalsIgnoreCase(Constants.tnxCurrencyID[1])){
			return rateBean.getGBP();
		}else if(ID.equalsIgnoreCase(Constants.tnxCurrencyID[2])){
			return rateBean.getUSD();
		}else if(ID.equalsIgnoreCase(Constants.tnxCurrencyID[3])){
			return rateBean.getAUD();
		}else if(ID.equalsIgnoreCase(Constants.tnxCurrencyID[4])){
			return rateBean.getJPY();
		}else if(ID.equalsIgnoreCase(Constants.tnxCurrencyID[5])){
			return rateBean.getEURO();
		}
		
		return 1.00d;
	}
	
	public ForwardsBean fwdRate(String currID, TnxRateBean rateBean, int flag){
		ForwardsBean fwdBean = new ForwardsBean();
		double OneMonfwdRate = 0.0d;
		double TwoWeekfwdRate = 0.0d;
		double INRrate =  getINRRate(currID, 0);

		double OneMonFNXrate = -1.0;
		double TwoWeekFNXrate= -1.0;
		
		if(flag == 2){
		OneMonFNXrate = get1MonRate(currID, 0);
		OneMonfwdRate =  getTnxRate(currID, rateBean)* fwd1monFactor(INRrate, OneMonFNXrate);
		setBeanValue(OneMonfwdRate,flag,currID, fwdBean);
		}else if(flag ==1){
		TwoWeekFNXrate = get2WRate(currID, 0);
		TwoWeekfwdRate = getTnxRate(currID, rateBean)* fwd2WFactor(INRrate, TwoWeekFNXrate);
		setBeanValue(TwoWeekfwdRate,flag,currID, fwdBean);
		}
		
		
		//setBeanValue(OneMonfwdRate,TwoWeekfwdRate,currID, fwdBean);
		
		return fwdBean;
	}
	// public static final String tnxCurrencyID[] =   {"init","GBP","USD","AUD","JPY","EURO"};
	public ForwardsBean setBeanValue(double OneMonfwdRate,double TwoWeekfwdRate,
			String ID,ForwardsBean fwdBean){
		if(ID.equalsIgnoreCase(Constants.tnxCurrencyID[1])){
			fwdBean.setFwd1MonGBP(OneMonfwdRate);
			fwdBean.setFwd2WGBP(TwoWeekfwdRate);
		}else if(ID.equalsIgnoreCase(Constants.tnxCurrencyID[2])){
			fwdBean.setFwd1MonUSD(OneMonfwdRate);
			fwdBean.setFwd2WUSD(TwoWeekfwdRate);
		}else if(ID.equalsIgnoreCase(Constants.tnxCurrencyID[3])){
			fwdBean.setFwd1MonUSD(OneMonfwdRate);
			fwdBean.setFwd2WUSD(TwoWeekfwdRate);
		}else if(ID.equalsIgnoreCase(Constants.tnxCurrencyID[4])){
			fwdBean.setFwd1MonUSD(OneMonfwdRate);
			fwdBean.setFwd2WUSD(TwoWeekfwdRate);
		}else if(ID.equalsIgnoreCase(Constants.tnxCurrencyID[5])){
			fwdBean.setFwd1MonUSD(OneMonfwdRate);
			fwdBean.setFwd2WUSD(TwoWeekfwdRate);
		}
		return fwdBean;
	}

	public ForwardsBean setBeanValue(double fwdRate,int flag,
			String ID,ForwardsBean fwdBean){
		if(flag == 2){
			if(ID.equalsIgnoreCase(Constants.tnxCurrencyID[1])){
				 fwdBean.setFwd1MonGBP(fwdRate) ;
			}else if(ID.equalsIgnoreCase(Constants.tnxCurrencyID[2])){
					 fwdBean.setFwd1MonUSD(fwdRate);
			}else if(ID.equalsIgnoreCase(Constants.tnxCurrencyID[3])){
				 	fwdBean.setFwd1MonAUD(fwdRate);
			}else if(ID.equalsIgnoreCase(Constants.tnxCurrencyID[4])){
					 fwdBean.setFwd1MonJPY(fwdRate);
			}else if(ID.equalsIgnoreCase(Constants.tnxCurrencyID[5])){
					 fwdBean.setFwd1MonEURO(fwdRate);
			}
		}else if(flag == 1){
			if(ID.equalsIgnoreCase(Constants.tnxCurrencyID[1])){
				 fwdBean.setFwd2WGBP(fwdRate) ;
			}else if(ID.equalsIgnoreCase(Constants.tnxCurrencyID[2])){
					fwdBean.setFwd2WUSD(fwdRate);
			}else if(ID.equalsIgnoreCase(Constants.tnxCurrencyID[3])){
				 fwdBean.setFwd2WAUD(fwdRate);
			}else if(ID.equalsIgnoreCase(Constants.tnxCurrencyID[4])){
					fwdBean.setFwd2WJPY(fwdRate);
			}else if(ID.equalsIgnoreCase(Constants.tnxCurrencyID[5])){
					fwdBean.setFwd2WEURO(fwdRate);
			}
		}		
			return fwdBean;
	}
	
	private double getINRRate(String curID, int tryCount){
		double temp = 0.00;
		
		int i=0;
			temp = fwdRateGetter.invDCRate(Constants.INRTag);
			if(temp <0 && tryCount <3){
				tryCount++;
				temp = getINRRate(curID, tryCount);
			}else{
				tryCount = 0;
				return temp;
			}
		return temp;
		
	}
	
	private double getCHNRate(String curID, int tryCount){
		double temp = 0.00;
		
		int i=0;
			temp = fwdRateGetter.invDCRate(Constants.YENTag);
			if(temp <0 && tryCount <3){
				tryCount++;
				temp = getCHNRate(curID, tryCount);
			}else{
				tryCount = 0;
				return temp;
			}
		return temp;
		
	}
	private double get1MonRate(String curID, int tryCount){
		double temp = 0.00;
		
		int i=0;
			temp = fwdRateGetter.findOneMonRate(getCurrTag(curID));
			if(temp <0 && tryCount <3){
				tryCount++;
				temp = get1MonRate(curID, tryCount);
			}else{
				tryCount = 0;
				return temp;
			}
		return temp;
		
	}
	private double get2WRate(String curID, int tryCount){
		double temp = 0.00;
		
		int i=0;
			temp = fwdRateGetter.findRate(getCurrTag(curID));
			if(temp <0 && tryCount <3){
				tryCount++;
				temp = get2WRate(curID, tryCount);
			}else{
				tryCount = 0;
				return temp;
			}
		return temp;
		
	}
	
	public double fwd1monFactor(double INR, double Fgn){
		double cInr = (INR*30)/91;
		double fxFactor = (1+ cInr) / (1+ Fgn);
		
		return fxFactor;
	}
	
	public double fwd2WFactor(double INR, double Fgn){
		double cInr = (INR*15)/91;
		double fxFactor = (1+ cInr) / (1+ Fgn);
		
		return fxFactor;
	}
}
