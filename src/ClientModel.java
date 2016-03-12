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

	private String juridicalAdress;

	public String getJuridicalAdress()
	{
		return this.juridicalAdress;
	}

	public void setJuridicalAdress(String newJuridicalAdress)
	{
		this.juridicalAdress = newJuridicalAdress;
	}

	private String directorFIO;

	public String getDirectorFIO()
	{
		return this.directorFIO;
	}

	public void setDirectorFIO(String newDirectorFIO)
	{
		this.directorFIO = newDirectorFIO;
	}

	private String directorUID;

	public String getDirectorUID()
	{
		return this.directorUID;
	}

	public void setDirectorUID(String newDirectorUID)
	{
		this.directorUID = newDirectorUID;
	}

	private String phoneNumber;

	public String getPhoneNumber()
	{
		return this.phoneNumber;
	}

	public void setPhoneNumber(String newPhoneNumber)
	{
		this.phoneNumber = newPhoneNumber;
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

	// TODO разделение на лица (ЮР-ФИЗ)

	private String founderFIO;

	public String getFounderFIO()
	{
		return this.founderFIO;
	}

	public void setFounderFIO(String newFounderFIO)
	{
		this.founderFIO = newFounderFIO;
	}

	private String founderUID;

	public String getFounderUID()
	{
		return this.founderUID;
	}

	public void setFounderUID(String newFounderUID)
	{
		this.founderUID = newFounderUID;
	}

	private String founderAdress;

	public String getFounderAdress()
	{
		return this.founderAdress;
	}

	public void setFounderAdress(String newFounderAdress)
	{
		this.founderAdress = newFounderAdress;
	}

	private int founderPart;

	public int getFounderPart()
	{
		return this.founderPart;
	}

	public void setFounderPart(int newFounderPart)
	{
		this.founderPart = newFounderPart;
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
						t.juridicalAdress = rs.getString("JuridicalAdress");
						t.directorFIO = rs.getString("DirectorFIO");
						t.directorUID = rs.getString("DirectorUID");
						t.phoneNumber = rs.getString("PhoneNumber");
						t.capitalSum = rs.getInt("CapitalSum");
						t.founderFIO = rs.getString("FounderFIO");
						t.founderUID = rs.getString("FounderUID");
						t.founderAdress = rs.getString("FounderAdress");
						t.founderPart = rs.getInt("FounderPart");
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
						+"SET RevisionNum=" + this.revisionNum + " AND RegistrationDate='" + this.registrationDate
						+ " AND JuridicalAdress='" + this.juridicalAdress
						+"' AND DirectorFIO='"+this.directorFIO+"' AND DirectorUID='"+this.directorUID
						+"' AND PhoneNumber='"+this.phoneNumber+"' AND CapitalSum="+this.capitalSum
						+" AND FounderFIO='"+this.founderFIO+"' AND FounderUID='"+this.founderUID
						+"' AND FounderAdress='"+this.founderAdress+"' AND FounderPart="+this.founderPart
						+" WHERE ID="+this.id);
			}
			else
			{
				ResultSet rs = connector.executeSQL("INSERT INTO clients(RevisionNum,RegistrationDate,RegistrationNum,JuridicalAdress,DirectorFIO,DirectorUID,PhoneNumber,CapitalSum,FounderFio,FounderUID,FounderAdress,FounderPart)"
						+" VALUES (" + this.revisionNum + ",'" + this.registrationDate + ",'"+this.juridicalAdress
						+"','"+this.directorFIO+"','"+this.directorUID+"','"+this.phoneNumber+"',"+this.capitalSum+",'"+this.founderFIO+"','"+this.founderUID
						+"','"+this.founderAdress+"',"+this.founderPart+")");
			}

		}
		connector.SQLDisconnect();
	}
}
