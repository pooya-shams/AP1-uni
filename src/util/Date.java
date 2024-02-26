package util;

public class Date // week day + hour + length
{
	private boolean even_days;
	private int start;
	private int length;

	public Date(boolean even_days, int start, int length)
	{
		this.even_days = even_days;
		this.start = start;
		this.length = length;
	}

	public boolean isEven_days()
	{
		return even_days;
	}
	public void setEven_days(boolean even_days)
	{
		this.even_days = even_days;
	}

	public int getStart()
	{
		return start;
	}
	public void setStart(int start)
	{
		this.start = start;
	}

	public int getLength()
	{
		return length;
	}
	public void setLength(int length)
	{
		this.length = length;
	}
}
