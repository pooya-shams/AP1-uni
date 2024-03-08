package App;

import Exceptions.InvalidCode;
import Exceptions.UserAlreadyExists;
import data.Users;
import university.Student;
import university.User;
import util.IOHelper;
import util.LineReader;

import java.util.Scanner;

public class Application
{
	private final LineReader sc = new LineReader(new Scanner(System.in));
	public Application()
	{
	}
	public void run()
	{
		System.out.println("Welcome!");
		System.out.println("you can always go back by typing 0");
		while(true)
		{
			System.out.println(
				"[0] Exit\n"+
				"[1] login (I already have an account)\n"+
				"[2] sign up (I don't have an account)"
			);
			int type = IOHelper.get_valid_input(2, sc);
			if(type == 0)
			{
				System.out.println("See you next time!");
				return;
			}
			else if(type == 1)
			{
				general_login();
			}
			else if(type == 2)
			{
				sign_up();
			}
			else
			{
				System.out.println("unreachable statement no. 29281821");
			}
		}
	}
	private boolean general_login() // returns if we should die
	{
		System.out.println("Choose your login type:\n"+
			"[0] go back\n" +
			"[1] Student\n"+
			"[2] Admin");
		int type = IOHelper.get_valid_input(2, sc); // I fucking hate java you stupid piece of shit
		if(type == 0)
		{
			return true;
		}
		else if(type == 1)
		{
			student_login();
		}
		else if(type == 2)
		{
			admin_login();
		}
		else
		{
			System.err.println("nope not possible");
		}
		return false;
	}
	private void sign_up()
	{
		System.out.println("please enter your student id. it will be used as a username.");
		System.out.println("please note that it can't contain whitespace and must contain only digits");
		String username = sc.nextLine();
		System.out.println("now please enter your password: ");
		String password = sc.nextLine();
		try
		{
			Student st = new Student(username, password);
			Users.add_user(st);
		}
		catch (InvalidCode e)
		{
			System.out.println("[ERROR] you entered an invalid student code. aborting");
			System.out.println(e.getMessage());
		}
		catch (UserAlreadyExists e)
		{
			System.out.println("[ERROR] invalid username");
			System.out.println(e.getMessage());
		}
	}
	private void student_login() // oh and also camel case is nonsense
	{
		System.out.print("enter your username: ");
		String username = sc.nextLine();
		System.out.print("enter your password (can't end with whitespace): ");
		String password = sc.nextLine();
		for(Student mf: Users.studnets) // relies on the fact that there wont be duplicate usernames
		{
			if(username.equals(mf.getUsername()))
			{
				if(password.equals(mf.getPassword()))
				{
					System.out.println("Congrats! successful login");
					new StudentView(mf, sc).run();
					return;
				}
				else
				{
					System.out.println("invalid password you lose");
					return;
				}
			}
		}
		System.out.println("username not found");
	}
	private void admin_login()
	{
		System.out.print("enter your username: ");
		String username = sc.nextLine();
		System.out.print("enter your password (can't end with whitespace): ");
		String password = sc.nextLine();
		if(username.equals(Users.admin.getUsername()))
		{
			if(password.equals(Users.admin.getPassword()))
			{
				System.out.println("Congrats you hacked the mainframe");
				new AdminView(Users.admin, sc).run();
			}
			else
			{
				System.out.println("Invalid password");
			}
		}
		else
		{
			System.out.println("Invalid username");
		}
	}
}
