package university.course;

import util.CourseTime;

public class Ocourse extends Course// omoomi
{
	public Ocourse(String course_name, String instructor_name, String exam_date, CourseTime time, int units, int capacity, int code)
	{
		super(course_name, instructor_name, exam_date, time, units, capacity, code);
		super.isEkht = false; // omoomi
	}

	@Override
	public int get_omoomi_units()
	{
		return 1; // wow such an omoomi course
	}
}
