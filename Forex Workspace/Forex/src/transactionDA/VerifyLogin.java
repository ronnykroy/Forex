package transactionDA;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utility.Constants;
import dbconnect.GetCon;


public class VerifyLogin {
	public static Connection con;
	
	public static int verifyLogin(String userName,String password){
		Connection con=GetCon.getCon();
		PreparedStatement ps;
		try {
			ps=con.prepareStatement(Constants.VERIFY_LOGIN);
			ps.setString(1, userName);
			ps.setString(2, password);
			
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				if(rs.getInt("ISADMIN") == 1 ){
						return 2;
				}else if(rs.getInt("ISADMIN") == 0 ){
				return 1;	
				}
			}else{
				return 0;
			}
			
		} catch (SQLException e) {
			System.out.println("Error Connecting to DB!");
			e.printStackTrace();
		}
		
		return 0 ;
	}
	
	public int createLogin(int rollNbr, String firstName, String lastName, String email){
		Connection con=GetCon.getCon();
		PreparedStatement ps;
		try {
			ps=con.prepareStatement(Constants.INSERT_NEW_USER_LOGIN);
			ps.setInt(1, rollNbr);
			ps.setString(2, Integer.toString(rollNbr));
			ps.setString(3, Constants.DEFAULT_PWD);
			ps.setString(4, email);
			ps.setInt(5,0);
			ps.setString(6, firstName);
			ps.setString(7, lastName);
			ps.executeQuery();
		} catch (SQLException e) {
			System.out.println("Error Connecting to DB!");
			e.printStackTrace();
			return 0;
		}
		
		try {
			ps=con.prepareStatement(Constants.INSERT_NEW_USER_CORPUS);
			
			ps.setString(1, Integer.toString(rollNbr));
			ps.setInt(2, Constants.DEFAULT_AMOUNT);
			ps.setInt(3, Constants.DEFAULT_AMOUNT);
			ps.setInt(4,0);
			ps.setInt(5,0);
			ps.setInt(6,0);
			ps.setInt(7,0);
			ps.setInt(8,0);
			ps.setInt(9,0);
			ps.setInt(10,0);
			ps.setInt(11,0);
			ps.setInt(12,0);
			ps.setInt(13,0);
			ps.executeQuery();
		} catch (SQLException e) {
			System.out.println("Error Connecting to DB!");
			e.printStackTrace();
			return 0;
		}
		
		return 1;
	}
	
}
