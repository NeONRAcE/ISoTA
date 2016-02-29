public class User
{
	private static UserModel user;

	public static void setCurrentUser(UserModel um)
	{
		user = um;
	}
	
	public static UserModel getCurrentUser()
	{
		return user;
	}
}