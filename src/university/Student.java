package university;

import university.course.Course;

import java.util.ArrayList;

public class Student extends User
{
	private final String code; // since it is so long
	private final ArrayList<Course> courses = new ArrayList<>();
	public Student(String code, String password)
	{
		super(code, password);
		this.code = code;
	}
}
