package university;

import Exceptions.CourseInterference;
import Exceptions.InvalidCode;
import Exceptions.TooManyUnits;
import university.course.Course;

import java.util.ArrayList;

public class Student extends User
{
	private final static int max_units = 20;
	private final String code; // since it is so long
	private final ArrayList<Course> courses = new ArrayList<>();
	public Student(String code, String password) throws InvalidCode
	{
		super(code, password);
		if(!check_is_numeric(code))
			throw new InvalidCode("student code: '"+code+"' is invalid");
		this.code = code;
	}
	private static boolean check_is_numeric(String code)
	{
		for(char ch: code.toCharArray())
			if(!Character.isDigit(ch))
				return false;
		return true;
	}
	public int get_total_units()
	{
		int t = 0;
		for(Course c: courses)
			t += c.getUnits();
		return t;
	}
	public void add_course(Course c) throws TooManyUnits, CourseInterference
	{
		if(c.getUnits() + this.get_total_units() > Student.max_units)
			throw new TooManyUnits("you are using too many units");
		for(Course oc: this.courses)
		{
			if(c.getTime().interferes(oc.getTime()))
				throw new CourseInterference("this course interferes with old course: "+oc);
		}
		this.courses.add(c);
	}
	public void print_courses()
	{
		for(Course c: this.courses)
		{
			System.out.println("----------");
			System.out.println(c);
		}
		if(this.courses.isEmpty())
			System.out.println("you have no registered course");
	}
	public void remove_course(int code)
	{
		int index = -1;
		for(int i = 0; i < this.courses.size(); i++)
		{
			if(this.courses.get(i).getCode() == code)
				index = i;
		}
		if(index == -1)
		{
			System.out.println("invalid code couldn't remove");
		}
		else
		{
			this.courses.remove(index);
			System.out.println("removed course with code "+code);
		}
	}
}
