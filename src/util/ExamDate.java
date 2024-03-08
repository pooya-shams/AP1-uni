package util;

import FileIO.JsonAble;

import java.util.Map;

public class ExamDate implements JsonAble
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

	private int getl()
	{
		return this.start;
	}
	private int getr()
	{
		return this.start+this.length;
	}

	public boolean interferes(ExamDate o)
	{
		if(o == null) return false;
		if(this.year != o.year || this.month != o.month || this.day != o.day) return false;
		return this.getr() > o.getl() && o.getr() > this.getl();
	}

	@Override
	public String toString()
	{
		return "ExamDate{" +
			"year=" + year +
			", month=" + month +
			", day=" + day +
			", start=" + start +
			", length=" + length +
			'}';
	}

	@Override
	public String toJson()
	{
		return "{type: ExamDate, " + "year:" + year + ", month:" + month + ", day:" + day + ", start:" + start + ", length:" + length + "}";
	}

	public static ExamDate fromJson(Map<String, Object> js)
	{
		return new ExamDate(
			(Integer)(js.get("year")),
			(Integer)(js.get("month")),
			(Integer)(js.get("day")),
			(Integer)(js.get("start")),
			(Integer)(js.get("length"))
		);
	}
}
