package transactionDA;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import transactionDA.TnxRateDA;
import transactionDA.TransactionDA;
import transactionBean.TnxRateBean;

public class EvaluateCorpusDA {

	TnxRateDA rateUPD = new TnxRateDA();
	TnxRateBean rateBean = new TnxRateBean();
	TransactionDA tnxDA = new TransactionDA();

	public int completeTask() {
			rateBean = rateUPD.GetRate();
			int success = 0;
			if (CheckRateBean(rateBean)) {
				if (rateBean != null)
					success = rateUPD.insertRate(rateBean);
				if (success == 1) {
					System.out.println("Rate Updation Sucess:" + new Date());
					evaluateCorpus();
					return success;
				} else {
					System.out.println("Rate Updation Failed:" + new Date());
					return success;
				}
			} else {
				System.out.println("No Internet Acess. Rate Updation Failed:"
						+ new Date());
				return success;
			}
	}

	private boolean CheckRateBean(TnxRateBean rateBean) {
		if (rateBean != null) {
			if ((rateBean.getAUD() < 0) || (rateBean.getEURO() < 0)
					|| (rateBean.getGBP() < 0) || (rateBean.getJPY() < 0)
					|| (rateBean.getUSD() < 0)) {
				rateBean.setError(true);
				return false;
			} else {
				rateBean.setError(false);
				return true;
			}
		} else {
			return false;
		}
	}

	private void evaluateCorpus() {
		int[] rollNbr = null;
		int[] success = null;
		rollNbr = tnxDA.getUsers();
		for (int i = 0; i < rollNbr.length; i++)
			success[i] = rateUPD.evaluateCorpus(rollNbr[i]);

	}
	

}
