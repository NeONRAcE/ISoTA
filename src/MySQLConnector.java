import java.sql.*;

import sun.security.pkcs11.Secmod.DbMode;

public class MySQLConnector 
{
	
	private static String username = "root";
	private static String password = "";//cop
	
	private Connection con = null;
	
	public boolean SQLConnect()
	{
		try 
			{
			this.con = DriverManager.getConnection("jdbc:mysql://localhost/isota", username, password);	
			return true;
			} 
		catch(SQLException ex)
			{
			ex.printStackTrace();
			return false;	
			}
	}
	
	public void SQLDisconnect()
	{
		try
		{
			if (!this.con.isClosed()) this.con.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public ResultSet executeSQL(String sql)
	{
		ResultSet res = null;
		
		try
		{
			Statement stm = this.con.createStatement();
			res = stm.executeQuery(sql);
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}
	
	public int executeUpdate(String sql)
	{
		int res = 0;
		
		try
		{
			Statement stm = this.con.createStatement();
			res = stm.executeUpdate(sql);
			this.con.setAutoCommit(false); this.con.commit(); this.con.setAutoCommit(true);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return res;
	}
	
}