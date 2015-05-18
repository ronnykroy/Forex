package transactionBO;

import java.util.ArrayList;

import transactionBean.ReportBean;
import transactionDA.TnxSummaryDA;

public class TnxSummaryBO {
	public ArrayList<ReportBean> getTnxSummary(int rollNbr){
		return new TnxSummaryDA().getTnxSummary(rollNbr);
	}
	
	public ArrayList<ReportBean> getFwdTnxSummary(int rollNbr){
		return new TnxSummaryDA().getFwdTnxSummary(rollNbr);
	}

}
