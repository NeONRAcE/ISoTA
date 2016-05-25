import java.sql.*;

public class UserModel
{
	private Integer id;

	public Integer getID()
	{
		return this.id;
	}

	private String login;

	public String getLogin()
	{
		return this.login;
	}

	public void setLogin(String newLogin)
	{
		this.login = newLogin;
	}

	private String password;

	public String getPassword()
	{
		return this.password;
	}

	public void setPassword(String newPassword)
	{
		this.password = newPassword;
	}

	private String name;

	public String getName()
	{
		return this.name;
	}

	public void setName(String newName)
	{
		this.name = newName;
	}

	private String lastName;

	public String getLastName()
	{
		return this.lastName;
	}

	public void setLastName(String newLastName)
	{
		this.lastName = newLastName;
	}

	private byte securityClass;

	public byte getSecurityClass()
	{
		return this.securityClass;
	}

	public void setSecurityClass(byte newSecurityClass)
	{
		this.securityClass = newSecurityClass;
	}

	public static UserModel findUser(String login, String password)
	{
		UserModel res = null;

		MySQLConnector connector = new MySQLConnector();
		if (connector.SQLConnect())
		{
			ResultSet rs = connector
					.executeSQL("SELECT * FROM users WHERE Login='" + login
							+ "' OR Password='" + password + "'");
			if (rs != null)
			{
				res = new UserModel();
				try
				{
					while (rs.next())
					{
						res.id = rs.getInt("ID");
						res.login = login;
						res.password = password;
						res.name = rs.getString("Name");
						res.lastName = rs.getString("LastName");
						res.securityClass = rs.getByte("SecurityClass");
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

		return res;
	}

	public void save()
	{
		MySQLConnector connector = new MySQLConnector();
		if (connector.SQLConnect())
		{
			if (this.id != null)
			{
				int res = connector.executeUpdate("UPDATE users"
						+" SET Login='" + this.login + "' AND Password='" + this.password + "' AND Name='"+this.name + "' AND LastName='"+this.lastName+"' AND SecurityClass="+this.securityClass
						+" WHERE ID="+this.id);
			}
			else
			{
				int rs = connector.executeUpdate("INSERT INTO users(Login,Password,Name,LastName,SecurityClass)"
						+" VALUES ('" + this.login + "','" + this.password + "','"+this.name + "','"+this.lastName+"',"+this.securityClass+")");
			}

		}
		connector.SQLDisconnect();
	}
	
	public String toString()
	{
		String res = null;
		
		res+="ID: "+this.id+"\nName: "+this.name+"\nLastName: "+this.lastName+"\n";
		
		return res;
	}
}
