package App;

import Exceptions.CourseInterference;
import Exceptions.TooManyUnits;
import data.Departments;
import university.Department;
import university.Student;
import university.User;
import university.course.Course;
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

	private void add_course()
	{
		System.out.println("Choose department:");
		for(Department dep: Departments.deps)
		{
			System.out.println("==========");
			System.out.println(dep);
		}
		int dep_code = IOHelper.get_dep_code(sc);
		if(dep_code == 0)
			return;
		int course_code = IOHelper.get_course_code(sc, dep_code);
		if(course_code == 0)
			return;
		Department dep = Departments.get_dep_by_code(dep_code);
		if(dep == null)
			return;
		Course c = dep.get_course(course_code);
		if(c == null)
			return; // god fucking damn it fuck null
		try
		{
			this.user.add_course(c);
		}
		catch (TooManyUnits e)
		{
			System.out.print("[Error] Too Many Units: ");
			System.out.println(e.getMessage());
		}
		catch (CourseInterference e)
		{
			System.out.print("[Error] Course Interference: ");
			System.out.println(e.getMessage());
		}
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
				add_course();
			}
			else
			{
				System.err.println("nope not possible (student view)");
			}
		}
	}

}
