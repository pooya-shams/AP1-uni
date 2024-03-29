package data;

import Exceptions.InvalidCode;
import Exceptions.UserAlreadyExists;
import university.Admin;
import university.Student;
import university.course.Course;
import university.course.Ecourse;
import util.CourseTime;
import util.ExamDate;

import java.util.ArrayList;

public class Users // static data class
{
	public static final ArrayList<Student> studnets = new ArrayList<>();
	public static final Admin admin = new Admin();
	private static boolean lock_populate = false; // haha try running that twice now
	public static void add_user(Student mf) throws UserAlreadyExists
	{
		if(mf == null)
			throw new IllegalArgumentException("student shouldn't be null");
		for(Student st: studnets)
			if(mf.getUsername().equals(st.getUsername()))
				throw new UserAlreadyExists("user with username '"+mf.getUsername()+"' already exists");
		studnets.add(mf);
	}
	public static void add_and_replace_user(Student mf)
	{
		if(mf == null)
			throw new IllegalArgumentException("student shouldn't be null");
		for(int i = 0; i < studnets.size(); i++)
		{
			Student st = studnets.get(i);
			if (mf.getUsername().equals(st.getUsername()))
			{
				studnets.set(i, mf);
				return;
			}
		}
		// being here means we didn't enter the if statement in the loop
		studnets.add(mf);
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
				studnets.get(0).add_course(Departments.search_for_course_by_code(22015));
				studnets.get(1).add_course(Departments.search_for_course_by_code(22016));
				studnets.get(2).add_course(Departments.search_for_course_by_code(23011));
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
