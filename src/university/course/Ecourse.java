package university.course;

import util.Date;

public class Ecourse extends Course// ekhtesasi
{
	public Ecourse(String course_name, String instructor_name, String exam_date, Date time, int units, int capacity, int code)
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
