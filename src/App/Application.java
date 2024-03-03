package App;

import data.Users;
import university.User;
import util.IOHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Application
{
	private final Scanner sc = new Scanner(System.in);
	public Application()
	{
	}
	public void run()
	{
		System.out.println("Welcome!");
		while(true)
		{
			this.general_login();
		}
	}
	private void general_login()
	{
		System.out.print("Choose your login type:\n"+
			"[1] Student\n"+
			"[2] Admin\n");
		String type = IOHelper.get_valid_input( new String[]{"1", "2"}, sc ); // I fucking hate java you stupid piece of shit
		if(type.equals("1"))
		{
			student_login();
		}
		else if(type.equals("2"))
		{
			admin_login();
		}
		else
		{
			System.err.println("nope not possible");
		}
	}
	private void student_login() // oh and also camel case is nonsense
	{
		System.out.print("enter your username: ");
		String username = sc.nextLine();
		System.out.print("enter your password: ");
		String password = sc.nextLine();
		for(User mf: Users.users) // relies on the fact that there wont be duplicate usernames
		{
			if(username.equals(mf.getUsername()))
			{
				if(password.equals(mf.getPassword()))
				{
					System.out.println("Congrats! successful login");
					new StudentView(mf).run();
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
		// TODO
	}
}
