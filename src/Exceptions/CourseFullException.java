package Exceptions;

public class CourseFullException extends Exception
{
	public CourseFullException()
	{
		super();
	}
	public CourseFullException(String msg)
	{
		super(msg);
	}
}
