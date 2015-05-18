package instructorBO;
import instructorDA.*;
public class ResetPwdBO {

	public int ResetPwd(int userName, String pwd){
		return new instructorDA.ResetPwdDA().ResetPWd(userName, pwd);
	}
}
