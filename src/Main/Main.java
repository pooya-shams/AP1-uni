package Main;
import App.Application;
import data.Departments;
import data.Users;

public class Main
{
	public static void main(String[] args)
	{
		Users.populate_users();
		Departments.populate_deps();
		Application app = new Application();
		app.run();
	}
}