import java.sql.*;
import com.mysql.jdbc.Driver ;

import sun.security.pkcs11.Secmod.DbMode;

public class MySQLConnection 
{
	
	private static String username = "root";
	private static String password = "root";
	
	Connection con = null;
	
	public void SQLConnect()
	{
		try 
			{
			con = DriverManager.getConnection("jdbc:mysql://localhost/", username, password);
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Statement state = con.createStatement();
			ResultSet rslt = null;	
			} 
		catch(SQLException ex)
			{
		
			}
	}
}