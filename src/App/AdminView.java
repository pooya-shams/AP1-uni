package App;

import Exceptions.CourseFullException;
import Exceptions.CourseInterference;
import Exceptions.TooManyOmoomi;
import Exceptions.TooManyUnits;
import data.Departments;
import data.Users;
import university.Admin;
import university.Department;
import university.Student;
import university.course.Course;
import util.IOHelper;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminView
{
	private final Admin admin;
	private final Scanner sc;

	public AdminView(Admin admin, Scanner sc)
	{
		this.admin = admin;
		this.sc = sc;
	}

	private void increase_capacity(Course c)
	{
		System.out.println("increasing capacity of course "+c.get_summary());
		System.out.print("please enter a new capacity (or 0 to go back): ");
		String mf = sc.nextLine().trim();
		while( ! (IOHelper.is_numeric(mf)) )
		{
			System.out.println("your input '"+mf+"' is not a valid option");
			System.out.print("please enter a real capacity (or 0 to go back): ");
			mf = sc.nextLine().trim();
		}
		if(mf.equals("0"))
			return;
		c.setCapacity(IOHelper.str_to_int(mf));
		System.out.println("changed capacity");
	}

	private void remove_participant(Course c)
	{
		ArrayList<Student> all_stds = Users.get_students_by_course(c);
		System.out.print("all participants of this course: (" + all_stds.size() + " in total)"  );
		for(Student s: all_stds)
			System.out.print(s+", ");
		System.out.println();
		System.out.println("choosing user for removal");
		Student mf = IOHelper.get_student_code_as_input(all_stds, sc);
		if(mf == null) // user entered 0
			return;
		mf.remove_course(c.getCode());
	}

	private void add_student(Course c)
	{
		System.out.println("adding student to course "+c.get_summary());
		Student mf = IOHelper.get_student_code_as_input(Users.studnets, sc);
		if(mf == null) // input is 0 you know the drill by now
			return;
		try
		{
			mf.add_course(c);
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
		catch (TooManyOmoomi e)
		{
			System.out.print("[Error] Too Many Omoomi: ");
			System.out.println(e.getMessage());
		}
		catch (CourseFullException e)
		{
			System.out.print("[Error] Course is Full: ");
			System.out.println(e.getMessage());
		}
	}

	private void do_course_actions(Course c)
	{
		System.out.println("working with course " + c.get_summary());
		System.out.println(
			"[0] go back\n" +
			"[1] show participants (and possibly remove them)\n" +
			"[2] add an student\n"+
			"[3] increase capacity (or decrease it. I don't know man be careful)"
		);
		int type = IOHelper.get_valid_input(3, sc);
		if(type == 0)
		{
			return;
		}
		else if(type == 1)
		{
			remove_participant(c);
		}
		else if(type == 2)
		{
			add_student(c);
		}
		else if(type == 3)
		{
			increase_capacity(c);
		}
		else
		{
			System.out.println("unreachable place do action course");
		}
	}

	private void show_deps()
	{
		for(Department dep: Departments.deps)
		{
			System.out.println("=======");
			System.out.println(dep.get_summary());
		}
		System.out.print("choose department by code or enter 0 to go back: ");
		int dep_code = IOHelper.get_dep_code(sc);
		if(dep_code == 0)
			return;
		Department dep = Departments.get_dep_by_code(dep_code);
		if(dep == null)
		{
			System.out.println("unexpected error in admin show dep: dep");
			return;
		}
		System.out.println(dep);
		int course_code = IOHelper.get_course_code(sc, dep_code);
		if(course_code == 0)
			return;
		Course c = dep.get_course(course_code);
		if(c == null)
		{
			System.out.println("unexpected error in admin show dep: course");
			return;
		}
		do_course_actions(c);
	}

	public void run()
	{
		while(true)
		{
			System.out.println(
				"[0] go back\n"+
				"[1] show departments"
			);
			int type = IOHelper.get_valid_input(1, this.sc);
			if(type == 0)
			{
				System.out.println("logging out of admin");
				return;
			}
			else if(type == 1)
			{
				show_deps();
			}
			else
			{
				System.out.println("not possible admin view");
			}
		}
	}
}
