package transactionBO;

import transactionBean.*;
import transactionDA.ForwardRateDA;

public class ForwardRateBO {
	ForwardRateDA fwdDA ;
	public ForwardsBean fwdRateSetter(String currID,TnxRateBean rateBean, int flag){
		fwdDA  = new ForwardRateDA();
		return fwdDA.fwdRate(currID, rateBean, flag);
	}
	
}
