package university.course;

import util.CourseTime;
import util.ExamDate;

public class Ecourse extends Course// ekhtesasi
{
	public Ecourse(String course_name, String instructor_name, ExamDate exam_date, CourseTime time, int units, int capacity, int code)
	{
		super(course_name, instructor_name, exam_date, time, units, capacity, code);
		super.isEkht = true; // ekhtesasi
	}

	@Override
	public int get_omoomi_units()
	{
		return 0; // peak object oriented thinking
	}
}
