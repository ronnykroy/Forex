package transactionBO;

import transactionBean.CorpusBean;
import transactionBean.TnxRateBean;
import transactionDA.ExecuteFwdDA;

public class ExecuteFwdBO {
	
	public int checkForwards(int rollNbr){
		return new ExecuteFwdDA().CheckForwards(rollNbr);
	}

	public int execForwards(CorpusBean corpus, TnxRateBean rateBean){
		return new ExecuteFwdDA().ExecuteFwd(corpus,rateBean);
	}
}
