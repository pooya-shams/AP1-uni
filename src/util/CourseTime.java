package util;

import FileIO.JsonAble;

import java.util.Map;

public class CourseTime implements JsonAble// week day + hour + length
{
	private boolean even_days;
	private int start;
	private int length;

	public CourseTime(boolean even_days, int start, int length)
	{
		this.even_days = even_days;
		this.start = start;
		this.length = length;
		assert this.length >= 0;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CourseTime courseTime = (CourseTime) o;
		return even_days == courseTime.even_days && start == courseTime.start && length == courseTime.length;
	}

	public boolean interferes(CourseTime o) // checks if a course interferes with another course
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

	@Override
	public String toString()
	{
		return (this.even_days ? "Saturday & Monday" : "Sunday & Tuesday") + " from " + start + " until " + (start+length);
	}

	@Override
	public String toJson()
	{
		return "{type: CourseTime, " + "even_days: " + even_days + ", start: " + start + ", length: " + length + "}";
	}

	public static CourseTime fromJson(Map<String, Object> js)
	{
		return new CourseTime(
			(Boolean)(js.get("even_days")),
			(Integer)(js.get("start")),
			(Integer)(js.get("length"))
		);
	}
}
