package transactionDA;
import transactionBean.CorpusBean;

public class UpdateCorpus {

	public CorpusBean updateCorpus(int userID){
		int dummy =10000;
		CorpusBean userCorpus = new CorpusBean();
		/* DB connections here
		*/
		userCorpus.setFirstName("Baskar");
		userCorpus.setLastName("Sadasivam");
		userCorpus.setAuD(dummy);
		userCorpus.setInR(dummy-987);
		userCorpus.setUsD(dummy);
		userCorpus.setGbP(dummy);
		userCorpus.setJpY(dummy);
		userCorpus.setEuR(dummy);
		return userCorpus;
	}
}
