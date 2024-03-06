package data;

import Exceptions.InvalidCode;
import university.Admin;
import university.Student;
import university.User;
import university.course.Course;
import university.course.Ecourse;
import util.Date;

import java.util.ArrayList;

public class Users // static data class
{
	public static final ArrayList<Student> studnets = new ArrayList<>();
	public static final Admin admin = new Admin();
	private static boolean lock_populate = false; // haha try running that twice now
	public static void add_user(Student mf)
	{
		studnets.add(mf);
	}
	public static void get_and_add_user()
	{
		// TODO
	}
	public static ArrayList<Student> get_students_by_course(Course c)
	{
		ArrayList<Student> out = new ArrayList<>();
		for(Student s: studnets)
		{
			if(s.has_course(c))
				out.add(s);
		}
		return out;
	}
	public static void populate_users()
	{
		if(lock_populate) return;
		lock_populate = true;
		try
		{
			studnets.add(new Student("402111000", "bad password"));
			studnets.add(new Student("402111001", "bad password"));
			studnets.add(new Student("402111002", "bad password"));
			try
			{
				studnets.get(0).add_course(new Ecourse("riz 1", "mogh", "1998/01/10", new Date(false, 12, 2), 4, 45, 22015));
			}
			catch (Exception e)
			{
				System.out.println("hmm");
			}
		}
		catch (InvalidCode e)
		{
			System.err.println("we wont reach here and if we did I don't know what would i do");
		}
	}
}
