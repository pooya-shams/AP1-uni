package Main;
import App.Application;
import data.Users;

public class Main
{
	public static void main(String[] args)
	{
		Users.populate_users();
		Application app = new Application();
		app.run();
	}
}