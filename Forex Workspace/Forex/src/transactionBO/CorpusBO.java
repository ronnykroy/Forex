package transactionBO;
import transactionBean.*;
import transactionDA.TransactionDA;

public class CorpusBO {
	public TransactionDA tnxDA;
	
	public CorpusBean getAdmin(String userID){
		tnxDA = new TransactionDA();
		return tnxDA.getAdmin(userID);
	}
	public CorpusBean getUserCorpus(int userID, TnxRateBean rateBean){
		tnxDA = new TransactionDA();
		return tnxDA.getUserCorpus(userID,rateBean);
	}
	
	public int buyCurrency(String toCur, double amt,TnxRateBean rateBean, CorpusBean CorpusBean){
		tnxDA = new TransactionDA();
		return tnxDA.buyCurrency(CorpusBean, rateBean, toCur, amt);
		
	}
	public int buyCurrency(CorpusBean corpusBean,double fQuote,String toCur, double amt,String dateFuture){
		tnxDA = new TransactionDA();
		return tnxDA.buyCurrency(corpusBean,toCur,amt,fQuote,dateFuture);
		
	}
	public CorpusBean getInitUserCorpus(int userID){
		tnxDA = new TransactionDA();
		return tnxDA.getUserCorpus(userID);
	}
	public int sellCurrency(String frmCur, double amt,TnxRateBean rateBean, CorpusBean CorpusBean){
		tnxDA = new TransactionDA();
		return tnxDA.sellCurrency(CorpusBean, rateBean, frmCur,amt);
		
	}
	public int sellCurrency(String toCur, double amt, String dateFuture,double fQuote,CorpusBean CorpusBean){
		tnxDA = new TransactionDA();
		return tnxDA.sellCurrency(toCur,amt,dateFuture,fQuote,CorpusBean);
		
	}
}

