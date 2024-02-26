package Exceptions;

public class InvalidCode extends IllegalArgumentException
{
	public InvalidCode()
	{
		super();
	}
	public InvalidCode(String msg)
	{
		super(msg);
	}
}
