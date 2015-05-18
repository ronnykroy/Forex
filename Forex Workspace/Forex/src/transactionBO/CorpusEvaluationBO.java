package transactionBO;
import java.util.ArrayList;

import transactionBean.EvaluateBean; 
import transactionBean.TnxRateBean;
import transactionDA.CorpusEvaluationDA;
import transactionDA.TnxRateDA;

public class CorpusEvaluationBO {

	public EvaluateBean evaluateUserCorpus(int rollNbr){
		return new CorpusEvaluationDA().getUserRank(rollNbr);
	}
	
	public ArrayList<EvaluateBean> fullEvaluation(){
		return new CorpusEvaluationDA().fullEvaluation();
	}
	
	public int UpdateRate(TnxRateBean rateBean){
		return new TnxRateDA().updateRates(rateBean);
	}
	
	public int UpdateRate(){
		return new TnxRateDA().updateRates();
	}
}
