package App;

import Exceptions.CourseFullException;
import Exceptions.CourseInterference;
import Exceptions.TooManyOmoomi;
import Exceptions.TooManyUnits;
import FileIO.Loader;
import data.Departments;
import data.Users;
import university.Admin;
import university.Department;
import university.Student;
import university.course.Course;
import university.course.Ecourse;
import university.course.Ocourse;
import util.CourseTime;
import util.ExamDate;
import util.IOHelper;
import util.LineReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class AdminView
{
	private final Admin admin;
	private final LineReader sc;

	public AdminView(Admin admin, LineReader sc)
	{
		this.admin = admin;
		this.sc = sc;
	}

	private void increase_capacity(Course c)
	{
		System.out.println("increasing capacity of course "+c.get_summary());
		System.out.print("please enter a new capacity (or 0 to go back): ");
		String mf = sc.nextLine();
		while( ! (IOHelper.is_numeric(mf)) )
		{
			System.out.println("your input '"+mf+"' is not a valid option");
			System.out.print("please enter a real capacity (or 0 to go back): ");
			mf = sc.nextLine();
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

	private void remove_course(Department dep, Course c)
	{
		dep.remove_course(c);
	}

	private ExamDate get_exam_date() // null means bad input
	{
		String[] names = {"year", "month", "day", "starting hour", "length"};
		int[] values = new int[5];
		for(int i = 0; i < 5; i++)
		{
			System.out.print("please enter exam "+names[i]+"(number): ");
			String tmp = sc.nextLine();
			if(tmp.equals("0"))
				return null;
			if (!IOHelper.is_numeric(tmp)) return null;
			values[i] = IOHelper.str_to_int(tmp);
		}
		if(values[1] < 1 || values[1] > 12)
		{
			System.out.println("bad month");
			return null;
		}
		if(values[2] < 1 || values[2] > 31)
		{
			System.out.println("bad day");
			return null;
		}
		if(values[3] < 1 || values[3] > 24)
		{
			System.out.println("bad hour");
			return null;
		}
		if(values[4] < 1 || values[4] > 4)
		{
			System.out.println("aborting due to inhuman exam lengths. you should be ashamed of yourself. (max is 4 hours)");
			return null;
		}
		return new ExamDate(values[0], values[1], values[2], values[3], values[4]);
	}

	private CourseTime get_time() // null means abort
	{
		String tmp;
		System.out.println("is this course on even days or odd days?");
		System.out.println(
			"[0] abort and go back\n"+
			"[1] even days\n"+
			"[2] odd days"
		);
		int e = IOHelper.get_valid_input(2, sc);
		if(e == 0) return null;
		System.out.print("please enter starting hour: ");
		tmp = sc.nextLine();
		if(tmp.equals("0"))
			return null;
		if(!(IOHelper.is_numeric(tmp) && IOHelper.in_range(IOHelper.str_to_int(tmp), 1, 24)) )
		{
			System.out.println("bad starting hour");
			return null;
		}
		int start = IOHelper.str_to_int(tmp);
		System.out.print("please enter length of class: ");
		tmp = sc.nextLine();
		if(tmp.equals("0"))
			return null;
		if(! (IOHelper.is_numeric(tmp) && IOHelper.in_range(IOHelper.str_to_int(tmp), 1, 4)) )
		{
			System.out.println("invalid or inhuman class lengths. aborting");
			return null;
		}
		int length = IOHelper.str_to_int(tmp);
		return new CourseTime((e == 1), start, length);
	}

	private void add_course(Department dep)
	{
		String tmp;
		System.out.println("Adding course to department "+dep.get_summary());
		System.out.println("choosing 0 at any point will abort");
		System.out.println("any invalid answer will also abort");
		System.out.print("please enter course's name: ");
		String course_name = sc.nextLine();
		System.out.print("please enter instructor's name: ");
		String instructor_name = sc.nextLine();
		System.out.println("please enter exam date");
		ExamDate exam_date = get_exam_date();
		if(exam_date == null)
			return;
		System.out.println("please enter class times");
		CourseTime time = get_time();
		if(time == null)
			return;
		System.out.print("please enter number of units (max is 4 and 0 aborts): ");
		int units = IOHelper.get_valid_input(4, sc);
		if(units == 0)
			return;
		System.out.print("please enter capacity (max is 500 and 0 aborts): ");
		int capacity = IOHelper.get_valid_input(500, sc);
		if(capacity == 0)
			return;
		System.out.print("please enter code (a 5 digit number and anything less aborts): ");
		int code = IOHelper.get_valid_input(99999, sc);
		if(code < 9999)
			return;
		System.out.println("is it takhasosi or omoomi?");
		System.out.println(
			"[0] abort and go back\n"+
			"[1] takhasosi\n"+
			"[2] omoomi"
		);
		int e = IOHelper.get_valid_input(2, sc);
		if(e == 0) return;
		Course course;
		if(e == 1)
		{
			course = new Ecourse(course_name, instructor_name, exam_date, time, units, capacity, code, dep.getCode());
		}
		else
		{
			course = new Ocourse(course_name, instructor_name, exam_date, time, units, capacity, code, dep.getCode());
		}
		dep.add_course(course);
	}

	private void do_dep_actions(Department dep)
	{
		System.out.println(dep);
		System.out.println("choose what to do with this department");
		System.out.println(
			"[0] go back\n" +
			"[1] show participants of a course (and possibly remove them)\n" +
			"[2] add an student to a course\n" +
			"[3] increase a course's capacity (or decrease it. I don't know man be careful)\n" +
			"[4] remove a course\n" +
			"[5] add a course"
		);
		int type = IOHelper.get_valid_input(5, sc);
		if (type == 0)
		{
			return;
		}
		else
		{
			Course c = null;
			if(type < 5)
			{
				int course_code = IOHelper.get_course_code(sc, dep);
				if (course_code == 0)
					return;
				c = dep.get_course(course_code);
				if (c == null)
				{
					System.out.println("unexpected error in admin dep actions: course is null");
					return;
				}
			}
			if (type == 1)
			{
				remove_participant(c);
			}
			else if (type == 2)
			{
				add_student(c);
			}
			else if (type == 3)
			{
				increase_capacity(c);
			}
			else if (type == 4)
			{
				remove_course(dep, c);
			}
			else if (type == 5)
			{
				add_course(dep);
			}
			else
			{
				System.out.println("unreachable statement dep actions");
			}
		}
	}

	private void show_deps()
	{
		for (Department dep : Departments.deps)
		{
			System.out.println("=======");
			System.out.println(dep.get_summary());
		}
		System.out.print("choose department by code or enter 0 to go back: ");
		int dep_code = IOHelper.get_dep_code(sc);
		if (dep_code == 0)
			return;
		Department dep = Departments.get_dep_by_code(dep_code);
		if (dep == null)
		{
			System.out.println("unexpected error in admin show dep: dep");
			return;
		}
		do_dep_actions(dep);
	}

	private void load_course()
	{
		System.out.print("please enter a filename to load from (normal stuff please): ");
		Loader.load_courses(new File(sc.nextLine()));
		Loader.load_users(); // in case new courses are added for some students
	}

	private void write_courses()
	{
		System.out.print("please enter a filename to write to (normal stuff please): ");
		Loader.write_courses(new File(sc.nextLine()));
	}

	public void run()
	{
		while(true)
		{
			System.out.println(
				"[0] go back\n"+
				"[1] show departments\n"+
				"[2] load courses from a file (import)\n"+
				"[3] write course to a file (export)"
			);
			int type = IOHelper.get_valid_input(3, this.sc);
			if(type == 0)
			{
				System.out.println("logging out of admin");
				return;
			}
			else if(type == 1)
			{
				show_deps();
			}
			else if(type == 2)
			{
				load_course();
			}
			else if(type == 3)
			{
				write_courses();
			}
			else
			{
				System.out.println("not possible admin view");
			}
		}
	}
}
