package Main;
import App.Application;
import data.Departments;
import data.Users;

public class Main
{
	public static void main(String[] args)
	{
		Departments.populate_deps();
		Users.populate_users();
		Application app = new Application();
		app.run();
	}
}