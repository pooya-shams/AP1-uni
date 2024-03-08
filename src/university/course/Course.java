package university.course;

import FileIO.JsonAble;
import FileIO.MyJson;
import util.CourseTime;
import util.ExamDate;

import java.util.Map;

public abstract class Course implements JsonAble
{
	private String course_name;
	private String instructor_name;
	private ExamDate exam_date;
	private CourseTime time;
	private int units;
	private int capacity;
	private int code;
	protected boolean isEkht; // is ekhtesasi or not

	public Course(String course_name, String instructor_name, ExamDate exam_date, CourseTime time, int units, int capacity, int code)
	{
		this.course_name = course_name;
		this.instructor_name = instructor_name;
		this.exam_date = exam_date;
		this.time = time;
		this.units = units;
		this.capacity = capacity;
		this.code = code;
	}

	public String getCourse_name()
	{
		return course_name;
	}
	public void setCourse_name(String course_name)
	{
		this.course_name = course_name;
	}

	public String getInstructor_name()
	{
		return instructor_name;
	}
	public void setInstructor_name(String instructor_name)
	{
		this.instructor_name = instructor_name;
	}

	public ExamDate getExam_date()
	{
		return exam_date;
	}
	public void setExam_date(ExamDate exam_date)
	{
		this.exam_date = exam_date;
	}

	public CourseTime getTime()
	{
		return time;
	}
	public void setTime(CourseTime time)
	{
		this.time = time;
	}

	public int getUnits()
	{
		return units;
	}
	public void setUnits(int units)
	{
		this.units = units;
	}

	public int getCapacity()
	{
		return capacity;
	}
	public void setCapacity(int capacity)
	{
		this.capacity = capacity;
	}

	public int getCode()
	{
		return code;
	}
	public void setCode(int code)
	{
		this.code = code;
	}

	public abstract int get_omoomi_units();

	public String get_summary()
	{
		return (isEkht ? "[Ecourse]" : "[Ocourse]") + this.course_name + " " + this.code;
	}
	@Override
	public String toString()
	{
		return (isEkht ? "[Ecourse]" : "[Ocourse]") + this.course_name + "\n" +
			"instructor name: " + this.instructor_name + "\n" +
			"exam date: " + this.exam_date + "\n" +
			"time: " + this.time + "\n" +
			"units: " + this.units + "\n" +
			"capacity: " + this.capacity + "\n" +
			"code: " + this.code + "\n";
	}

	@Override
	public String toJson()
	{
		return "{type:Course, " + "course_name: '" + course_name + "'"
			+ ", instructor_name: '" + instructor_name + "'"
			+ ", exam_date: " + exam_date.toJson()
			+ ", time: " + time.toJson()
			+ ", units: " + units
			+ ", capacity: " + capacity
			+ ", code: " + code
			+ ", isEkht: " + isEkht
			+ "}";
	}

	public static Course fromJson(Map<String, Object> js)
	{
		boolean ise = (Boolean)(js.get("isEkht"));
		if(ise)
		{
			return new Ecourse(
				(String)js.get("course_name"),
				(String)js.get("instructor_name"),
				ExamDate.fromJson((Map<String, Object>)js.get("exam_date")),
				CourseTime.fromJson((Map<String, Object>)js.get("time")),
				(Integer)(js.get("units")),
				(Integer)(js.get("capacity")),
				(Integer)(js.get("code"))
			);
		}
		else
		{
			return new Ocourse(
				(String)js.get("course_name"),
				(String)js.get("instructor_name"),
				ExamDate.fromJson((Map<String, Object>)js.get("exam_date")),
				CourseTime.fromJson((Map<String, Object>)js.get("time")),
				(Integer)(js.get("units")),
				(Integer)(js.get("capacity")),
				(Integer)(js.get("code"))
			);
		}
	}
}
