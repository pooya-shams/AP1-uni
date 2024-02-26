package university;

import Exceptions.InvalidCode;
import university.course.Course;

import java.util.ArrayList;

public class Student extends User
{
	private final String code; // since it is so long
	private final ArrayList<Course> courses = new ArrayList<>();
	public Student(String code, String password)
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
}
