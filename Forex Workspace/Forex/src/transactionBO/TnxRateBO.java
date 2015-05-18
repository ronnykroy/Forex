package transactionBO;
import transactionBean.TnxRateBean;
import transactionDA.TnxRateDA;
public class TnxRateBO {
	TnxRateDA tnxDA;
	public TnxRateBean getTnxRates(){
		tnxDA = new TnxRateDA();
		return tnxDA.setTnxRate();
	}
}
