package App;

import data.Departments;
import university.Department;
import university.Student;
import university.User;
import util.IOHelper;

import java.util.Scanner;

public class StudentView
{
	private final Student user;
	private final Scanner sc;

	public StudentView(Student mf, Scanner sc)
	{
		this.user = mf;
		this.sc = sc;
	}

	private void rem_course()
	{
		this.user.print_courses();
		System.out.print("if you want to remove a course enter its code otherwise enter 0 to go back: ");
		String code = sc.nextLine().trim();
		if(code.equals("0"))
			return;
		if(!IOHelper.is_numeric(code))
		{
			System.out.println("invalid code");
			return;
		}
		this.user.remove_course(IOHelper.str_to_int(code));
	}

	public void run()
	{
		while(true)
		{
			System.out.println(
				"[0] go back\n" +
				"[1] show registered courses\n" +
				"[2] show available courses");
			int type = IOHelper.get_valid_input(2, sc);
			if (type == 0)
			{
				System.out.println("logging out of student " + this.user.getUsername());
				return;
			}
			else if (type == 1)
			{
				rem_course();
			}
			else if(type == 2)
			{
				for(Department dep: Departments.deps)
				{
				}
			}
			else
			{
				System.err.println("nope not possible (student view)");
			}
		}
	}

}
