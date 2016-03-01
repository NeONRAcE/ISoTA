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
	
}
