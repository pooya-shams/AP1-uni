package university;

import data.Departments;
import university.course.Course;

import java.util.ArrayList;

public class Department
{
	private final ArrayList<Course> courses = new ArrayList<>();
	private final String name;
	private final int code;
	public Department(String name, int code)
	{
		this.name = name;
		this.code = code;
	}

	public ArrayList<Course> getCourses()
	{
		return courses;
	}
	public String getName()
	{
		return name;
	}
	public int getCode()
	{
		return code;
	}

	public void add_course(Course c)
	{
		if(c == null)
			throw new IllegalArgumentException("course shouldn't be null");
		this.courses.add(c);
	}
	public Course get_course(int code) // I'm tired it will return null if shit doesn't exist
	{
		for(Course c: this.courses)
			if(c.getCode() == code)
				return c;
		return null;
	}

	public String get_summary()
	{
		return this.name+": "+this.code+"\n";
	}
	@Override
	public String toString()
	{
		StringBuilder out = new StringBuilder(this.name+": "+this.code+"\n");
		for(Course c: this.courses)
		{
			out.append("--------\n");
			out.append(c.toString());
			out.append("\n");
		}
		return out.toString();
	}
}
