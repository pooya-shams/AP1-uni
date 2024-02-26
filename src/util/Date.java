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

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Date date = (Date) o;
		return even_days == date.even_days && start == date.start && length == date.length;
	}

	public boolean interferes(Date o) // checks if a course interferes with another course
	{
		if(o == null) return false;
		if(this.even_days != o.even_days) return false;
		return this.getr() > o.getl() && o.getr() > this.getl();
	}

	private int getl()
	{
		return this.start;
	}
	private int getr()
	{
		return this.start + this.length;
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
