package util;

public class ExamDate
{
	private final int year;
	private final int month;
	private final int day;
	private final int start;
	private final int length;

	public ExamDate(int year, int month, int day, int start, int length)
	{
		this.year = year;
		this.month = month;
		this.day = day;
		this.start = start;
		this.length = length;
	}

	public int getYear()
	{
		return year;
	}

	public int getMonth()
	{
		return month;
	}

	public int getDay()
	{
		return day;
	}

	public int getStart()
	{
		return start;
	}

	public int getLength()
	{
		return length;
	}
}
