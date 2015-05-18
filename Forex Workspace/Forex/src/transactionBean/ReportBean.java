package transactionBean;

import java.sql.Timestamp;
import java.util.Date;



public class ReportBean {
	int tnx_id;
	String tnx_type;
	int is_future;
	Date future_date;
	String from_currency;
	double from_crate;
	String to_cny;
	double to_cny_rate;
	String comments;
	double amount;
	String username;
	Timestamp exec_date;
	
	public int getTnx_id() {
		return tnx_id;
	}
	public void setTnx_id(int tnx_id) {
		this.tnx_id = tnx_id;
	}
	public String getTnx_type() {
		return tnx_type;
	}
	public void setTnx_type(String tnx_type) {
		this.tnx_type = tnx_type;
	}
	public int getIs_future() {
		return is_future;
	}
	public void setIs_future(int is_future) {
		this.is_future = is_future;
	}
	public Date getFuture_date() {
		return future_date;
	}
	public void setFuture_date(Date future_date) {
		this.future_date = future_date;
	}
	public String getFrom_currency() {
		return from_currency;
	}
	public void setFrom_currency(String from_currency) {
		this.from_currency = from_currency;
	}
	public double getFrom_crate() {
		return from_crate;
	}
	public void setFrom_crate(double from_crate) {
		this.from_crate = from_crate;
	}
	public String getTo_cny() {
		return to_cny;
	}
	public void setTo_cny(String to_cny) {
		this.to_cny = to_cny;
	}
	public double getTo_cny_rate() {
		return to_cny_rate;
	}
	public void setTo_cny_rate(double to_cny_rate) {
		this.to_cny_rate = to_cny_rate;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Timestamp getExec_date() {
		return exec_date;
	}
	public void setExec_date(Timestamp exec_date) {
		this.exec_date = exec_date;
	}
}
