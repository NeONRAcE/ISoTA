import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientModel
{

	private int id;

	public int getID()
	{
		return this.id;
	}

	private int revisionNum; // OKPO

	public int getRevisionNum()
	{
		return this.revisionNum;
	}

	public void setRevisionNum(int newRevisionNum)
	{
		this.revisionNum = newRevisionNum;
	}

	private Date registrationDate;

	public Date getRegistrationDate()
	{
		return this.registrationDate;
	}

	public void setRegistrationDate(Date newRegistrationDate)
	{
		this.registrationDate = newRegistrationDate;
	}

	private String adress;

	public String getAdress()
	{
		return this.adress;
	}

	public void setAdress(String newAdress)
	{
		this.adress = newAdress;
	}

	private String FIO;

	public String getFIO()
	{
		return this.FIO;
	}

	public void setFIO(String newFIO)
	{
		this.FIO = newFIO;
	}

	private int UID;

	public int getUID()
	{
		return this.UID;
	}

	public void setUID(int newUID)
	{
		this.UID = newUID;
	}

	private int phoneNumber;

	public int getPhoneNumber()
	{
		return this.phoneNumber;
	}

	public void setPhoneNumber(int newPhoneNumber)
	{
		this.phoneNumber = newPhoneNumber;
	}

	// юр лица

	private String directorFIO;

	public String getDirectorFIO()
	{
		return this.directorFIO;
	}

	public void setDirectorFIO(String newDirectorFIO)
	{
		this.directorFIO = newDirectorFIO;
	}

	private int directorUID;

	public int getDirectorUID()
	{
		return this.directorUID;
	}

	public void setDirectorUID(int newDirectorUID)
	{
		this.directorUID = newDirectorUID;
	}

	private String directorAdress;

	public String getDirectorAdress()
	{
		return this.directorAdress;
	}

	public void setDirectorAdress(String newDirectorAdress)
	{
		this.directorAdress = newDirectorAdress;
	}
	
	private int directorNumber;
	
	public int getDirectorNumber()
	{
		return this.directorNumber;
	}
	
	public void setDirectorNumber(int newDirectorNumber)
	{
		this.directorNumber = newDirectorNumber;
	}
	
	
	private int capitalSum;

	public int getCapitalSum()
	{
		return this.capitalSum;
	}

	public void setCapitalSum(int newCapitalSum)
	{
		this.capitalSum = newCapitalSum;
	}

	public static ClientModel[] findClientsAll()
	{
		List<ClientModel> res = null;

		MySQLConnector connector = new MySQLConnector();
		if (connector.SQLConnect())
		{
			ResultSet rs = connector.executeSQL("SELECT * FROM clients");
			if (rs != null)
			{
				res = new ArrayList<ClientModel>();

				try
				{
					while (rs.next())
					{
						ClientModel t = new ClientModel();
						t.id = rs.getInt("ID");
						t.revisionNum = rs.getInt("RevisionNum");
						t.registrationDate = rs.getDate("RegistrationDate");
						t.adress = rs.getString("Adress");
						t.FIO = rs.getString("FIO");
						t.UID = rs.getInt("UID");
						t.phoneNumber = rs.getInt("PhoneNumber");
						t.directorFIO = rs.getString("DirectorFIO");
						t.directorUID = rs.getInt("DirectorUID");
						t.directorAdress = rs.getString("DirectorAdress");
						t.directorNumber = rs.getInt("DirectorNumber");
						t.capitalSum = rs.getInt("CapitalSum");
						res.add(t);
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
		
		ClientModel[] result = new ClientModel[res.size()];
		result = res.toArray(result);
		
		return result;
	}
	
	public void save()
	{
		MySQLConnector connector = new MySQLConnector();
		if (connector.SQLConnect())
		{
			if (this.id != 0)
			{
				ResultSet rs = connector.executeSQL("UPDATE clients "
						+"SET RevisionNum=" + this.revisionNum + "' AND RegistrationDate='" + this.registrationDate
						+"' AND Adress='" + this.adress
						+"' AND FIO='"+this.FIO+"' AND UID='"+this.UID
						+"' AND PhoneNumber='"+this.phoneNumber
						+"' AND DirectorFIO='"+this.directorFIO+"' AND DirectorUID='"+this.directorUID
						+"' AND DirectorAdress='"+this.directorAdress+"' AND DirectorNumber='"+this.directorNumber
						+"' AND CapitalSum='"+this.capitalSum
						+" WHERE ID="+this.id);
			}
			else
			{
				ResultSet rs = connector.executeSQL("INSERT INTO clients(RevisionNum,RegistrationDate,Adress,FIO,UID,PhoneNumber,DirectorFIO,DirectorUID,DirectorAdress,DirectorNumber,CapitalSum)"
						+" VALUES ('" + this.revisionNum + "','" + this.registrationDate + "','"+this.adress
						+"','"+this.FIO+"','"+this.UID+"','"+this.phoneNumber+"','"+this.directorFIO+"','"+this.directorUID
						+"','"+this.directorAdress+"','"+this.directorNumber+"','"+this.capitalSum+"')");
			}

		}
		connector.SQLDisconnect();
	}
	
	public boolean delete()
	{
		boolean res = false;
		MySQLConnector connector = new MySQLConnector();
		if (connector.SQLConnect())
		{
			if (this.getID() == 0)
			{
				res = false;
			}
			else
			{
				ResultSet rs = connector.executeSQL("DELETE FROM clients WHERE ID="+this.id);
				res = true;
			}
		}
		connector.SQLDisconnect();
		return res;
	}
	
	public static ClientModel[] findClientsByName(String name)
	{
		List<ClientModel> res = null;

		MySQLConnector connector = new MySQLConnector();
		if (connector.SQLConnect())
		{
			ResultSet rs = connector.executeSQL("SELECT * FROM clients WHERE FIO LIKE '%"+name+"%'");
			if (rs != null)
			{
				res = new ArrayList<ClientModel>();

				try
				{
					while (rs.next())
					{
						ClientModel t = new ClientModel();
						t.id = rs.getInt("ID");
						t.revisionNum = rs.getInt("RevisionNum");
						t.registrationDate = rs.getDate("RegistrationDate");
						t.adress = rs.getString("Adress");
						t.FIO = rs.getString("FIO");
						t.UID = rs.getInt("UID");
						t.phoneNumber = rs.getInt("PhoneNumber");
						t.directorFIO = rs.getString("DirectorFIO");
						t.directorUID = rs.getInt("DirectorUID");
						t.directorAdress = rs.getString("DirectorAdress");
						t.directorNumber = rs.getInt("DirectorNumber");
						t.capitalSum = rs.getInt("CapitalSum");
						res.add(t);
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
		
		ClientModel[] result = new ClientModel[res.size()];
		result = res.toArray(result);
		
		return result;
	}
}
