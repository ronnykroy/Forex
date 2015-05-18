package transactionBO;
import transactionDA.EvaluateCorpusDA;;
public class EvaluateCorpusBO {
	public int evaluateCorpus(){
		return new EvaluateCorpusDA().completeTask();
	}
}
