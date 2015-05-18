package instructorBO;

import java.util.ArrayList;
import transactionBean.*;
import instructorDA.*;
import transactionBean.CorpusBean;

public class TotalEvalBO {
	EvaluatePerformanceDA eVP ;
	public ArrayList<CorpusBean> totalEval(TnxRateBean rateBean){
		eVP = new EvaluatePerformanceDA();
		int result = eVP.updateNetWorth(eVP.totalEval(),rateBean);
		if(result == 1)
			return eVP.totalEval();
		else
			return null;
	}
}
