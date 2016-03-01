import java.sql.*;

public class ReportModel {
	private int id;

	public int getID()
	{
		return this.id;
	}
	
	private String surname;
	
	public String getSurname()
	{
		return this.surname;
	}
	
	public void setSurname(String newSurname)
	{
		this.surname = newSurname;
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
	
	private String patronymic;
	
	public String getPatronymic()
	{
		return this.patronymic;
	}
	
	public void setPatronymic(String newPatronymic)
	{
		this.patronymic = newPatronymic;
	}
	
	private int inn;
	
	public int getInn()
	{
		return this.inn;
	}
	
	public void setInn(int newInn)
	{
		this.inn = newInn;
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
	
	private int share;
	
	public int getShare()
	{
		return this.share;
	}
	
	public void setShare(int newShare)
	{
		this.share = newShare;
	}
	
	private int capitalAmount;
	
	public int getCapitalAmount()
	{
		return this.capitalAmount;
	}
	
	public void setCapitalAmount(int newCapitalAmount)
	{
		this.capitalAmount = newCapitalAmount;
	}
	
	private int registryDate;
	
	public int getRegistryDate()
	{
		return this.registryDate;
	}
	
	public void setRegistryDate(int newRegistryDate)
	{
		this.registryDate = newRegistryDate;
	}
	
	private int key;
	
	public int getKey()
	{
		return this.key;
	}
	
	public void setKey(int newKey)
	{
		this.key = newKey;
	}
	
	//TODO разделение на лица (ЮР-ФИЗ)
	
private String fsurname;
	
	public String getfSurname()
	{
		return this.fsurname;
	}
	
	public void setfSurname(String newfSurname)
	{
		this.fsurname = newfSurname;
	}
	
	private String fname;
	
	public String getfName()
	{
		return this.fname;
	}
	
	public void setfName(String newfName)
	{
		this.fname = newfName;
	}
	
	private String fpatronymic;
	
	public String getfPatronymic()
	{
		return this.fpatronymic;
	}
	
	public void setfPatronymic(String newfPatronymic)
	{
		this.fpatronymic = newfPatronymic;
	}
	
private int finn;
	
	public int getfInn()
	{
		return this.finn;
	}
	
	public void setfInn(int newfInn)
	{
		this.finn = newfInn;
	}
	
private String fadress;
	
	public String getfAdress()
	{
		return this.fadress;
	}
	
	public void setfAdress(String newfAdress)
	{
		this.fadress = newfAdress;
	}
	
	private int fshare;
	
	public int getfShare()
	{
		return this.fshare;
	}
	
	public void setfShare(int newfShare)
	{
		this.fshare = newfShare;
	}
	
}
