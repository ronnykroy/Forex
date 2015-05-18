package instructorDA;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utility.Constants;
import dbconnect.GetCon;

public class ResetPwdDA {

	public int ResetPWd(int userName, String pwd){
		Connection con=GetCon.getCon();
		PreparedStatement ps;
		
		try {
			ps=con.prepareStatement(Constants.RESET_PWD);
			ps.setInt(2, userName);
			ps.setString(1, pwd);
			
			int rs =ps.executeUpdate();
			System.out.println(" Update Success!!"+rs);
			return 1;
	}catch(SQLException e){
		e.printStackTrace();
		return 0;
	}catch(Exception e){
		e.printStackTrace();
		return 0;
	}
	}
}
