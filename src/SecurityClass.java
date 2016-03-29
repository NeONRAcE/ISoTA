import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SecurityClass {

	private int id;
	
	public int getID()
	{
		return this.id;
	}
	
	private String securityClass;
	
	public String getSecurityClass()
	{
		return this.securityClass;
	}
	
	public void setSecurityClass(String newSecurityClass)
	{
		this.securityClass=newSecurityClass;
	}
	
	public static SecurityClass[] findSecurityClassesAll()
	{
		List<SecurityClass> res = null;

		MySQLConnector connector = new MySQLConnector();
		if (connector.SQLConnect())
		{
			ResultSet rs = connector.executeSQL("SELECT * FROM securityclasses");
			if (rs != null)
			{
				res = new ArrayList<SecurityClass>();

				try
				{
					while (rs.next())
					{
						SecurityClass sc = new SecurityClass();
						sc.id = rs.getInt("ID");
						sc.securityClass = rs.getString("SecurityClass");
						res.add(sc);
					}
				}
				catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		connector.SQLDisconnect();
		
		SecurityClass[] result = new SecurityClass[res.size()];
		result = res.toArray(result);
		
		return result;
	}
}
