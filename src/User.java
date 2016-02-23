public class User
{
	private int id;
	private String name;
	private String lastName;
	private byte securityClass;

	public User()
	{
		id = 0;
		name = "";
		lastName = "";
		securityClass = 0;
	}
	
	public User(int id, String name, String lastName, byte securityClass)
	{
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.securityClass = securityClass;
	}
}