package utility;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import transactionDA.TnxRateDA;
import transactionDA.TransactionDA;
import transactionBean.TnxRateBean;
 
public class Evaluator extends TimerTask {
 
	TnxRateDA rateUPD = new TnxRateDA();
	TnxRateBean rateBean = new TnxRateBean();
	TransactionDA tnxDA = new TransactionDA();
    @Override
    public void run() {
        System.out.println("Last Rate Updation:"+new Date());
        completeTask();
    }
 
    private void completeTask() {
        try {
            rateBean = rateUPD.GetRate();
            if(CheckRateBean(rateBean)){
            int success = 0;
            if(rateBean !=null)
            	success = rateUPD.insertRate(rateBean);
            if(success ==1){
            	 System.out.println("Rate Updation Sucess:"+new Date());
            	 evaluateCorpus();
            }else{
            	 System.out.println("Rate Updation Failed:"+new Date());
            }
            }else{
            	System.out.println("No Internet Acess. Rate Updation Failed:"+new Date());
            }
        }catch (Exception e) {
			// TODO: handle exception
        	completeTask();
		}
    }
     
    public static void main(String args[]){
        TimerTask timerTask = new Evaluator();
        //running timer task as daemon thread
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0, 60000); //900000 ==> 15 mins once
        System.out.println("Rate Updation Started");
        //cancel after sometime
        try {
            Thread.sleep(120000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }

    private boolean CheckRateBean(TnxRateBean rateBean){
    	if(rateBean != null){
    		if((rateBean.getAUD()<0) || (rateBean.getEURO()<0)||(rateBean.getGBP()<0)||(rateBean.getJPY()<0)
					||(rateBean.getUSD()<0)){
				rateBean.setError(true);
				return false;
			}else{
				rateBean.setError(false);
				return true;
			}
    	}else{
    		return false;
    	}
    }
    
    private void evaluateCorpus(){
    	int[] rollNbr = null;
    	int[] success = null;
    	rollNbr = tnxDA.getUsers();
    	for(int i=0;i<rollNbr.length;i++)
    		success[i] = rateUPD.evaluateCorpus(rollNbr[i]);
    	
    }
    
    /*timer.cancel();
    System.out.println("TimerTask cancelled");
    try {
        Thread.sleep(30000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }*/
 
}

