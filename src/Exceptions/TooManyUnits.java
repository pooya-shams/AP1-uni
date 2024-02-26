package Exceptions;

public class TooManyUnits extends Exception
{
	public TooManyUnits()
	{
		super();
	}
	public TooManyUnits(String msg)
	{
		super(msg);
	}
}
