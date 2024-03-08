package university;

import Exceptions.*;
import FileIO.JsonAble;
import data.Users;
import university.course.Course;

import java.util.ArrayList;

public class Student extends User implements JsonAble
{
	private final static int MAX_UNITS = 20;
	private final static int MAX_OMOOMI_UNITS = 5;
	private final String code; // since it is so long
	private final ArrayList<Course> courses = new ArrayList<>();
	public Student(String code, String password) throws InvalidCode
	{
		super(code, password);
		if(!check_is_numeric(code))
			throw new InvalidCode("student code: '"+code+"' is invalid");
		this.code = code;
	}
	public String getCode()
	{
		return this.code;
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
	public int get_omoomi_units()
	{
		int t = 0;
		for(Course c: courses)
			t += c.get_omoomi_units(); // :\
		return t;
	}
	public void add_course(Course c) throws TooManyUnits, CourseInterference, TooManyOmoomi, CourseFullException
	{
		if(c.getUnits() + this.get_total_units() > Student.MAX_UNITS)
			throw new TooManyUnits("you are using too many units");
		if(c.get_omoomi_units() + this.get_omoomi_units() > Student.MAX_OMOOMI_UNITS)
			throw new TooManyOmoomi("you are using too many omoomi courses");
		for(Course oc: this.courses)
		{
			if(c.getTime().interferes(oc.getTime()))
				throw new CourseInterference("this course's time interferes with old course: "+oc.get_summary());
			if(c.getExam_date().interferes(oc.getExam_date()))
				throw new CourseInterference("this course's exam date interferes with old course: "+oc.get_summary());
		}
		ArrayList<Student> arr = Users.get_students_by_course(c);
		if(arr.size() >= c.getCapacity())
			throw new CourseFullException("course is full with capacity "+c.getCapacity());
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
	public void remove_course(int code) // actually should be named try and remove course
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
			System.out.println("removed course with code "+code+" from student "+this.getCode());
		}
	}
	public boolean has_course(Course c)
	{
		return this.courses.contains(c);
	}

	@Override
	public String toString()
	{
		return this.code;
	}

	@Override
	public String toJson()
	{
		StringBuilder out = new StringBuilder();
		out.append("{type: Student, ");
		out.append("code: '"+this.code+"', "); // same as username
		out.append("password: '"+this.getPassword()+"', ");
		out.append("courses: [");
		for(int i = 0; i < this.courses.size(); i++)
		{
			out.append(this.courses.get(i).getCode());
			if(i != this.courses.size()-1)
				out.append(", ");
		}
		out.append("]}");
		return out.toString();
	}
}
