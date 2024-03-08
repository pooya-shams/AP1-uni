package data;

import university.Department;
import university.course.Course;
import university.course.Ecourse;
import university.course.Ocourse;
import util.CourseTime;
import util.ExamDate;

import java.util.ArrayList;

public class Departments
{
	public static final ArrayList<Department> deps = new ArrayList<>();
	private static boolean lock_populate = false; // haha try running that twice now
	public static void populate_deps()
	{
		if(lock_populate) return;
		lock_populate = true;
		// math
		Department Math = new Department("Math", 1);
		Math.add_course(new Ocourse("Riazi 1", "Dr moghadasi", new ExamDate(2024, 5, 12, 9, 4), new CourseTime(true, 8, 2), 4, 120, 22015, 1));
		Math.add_course(new Ocourse("Riazi 2", "Dr poornaki", new ExamDate(2024, 5, 13, 9, 4), new CourseTime(false, 10, 2), 4, 250, 22016, 1));
		Math.add_course(new Ecourse("Mabani riazi", "Dr Ardeshir", new ExamDate(2024, 5, 14, 10, 2), new CourseTime(false, 10, 2), 4, 60, 22030, 1));
		// electrical engineering
		Department EE = new Department("Electrical Engineering", 2);
		EE.add_course(new Ocourse("Tarich bargh", "Dr gholi", new ExamDate(2024, 5, 12, 10, 2), new CourseTime(false, 8, 1), 2, 50, 23010, 2));
		EE.add_course(new Ecourse("Elec 1", "Dr mamad", new ExamDate(2024, 5, 13, 9, 4), new CourseTime(true, 10, 2), 3, 100, 23011, 2));
		EE.add_course(new Ecourse("Elec 2", "Dr barghi no.3", new ExamDate(2024, 5, 14, 9, 4), new CourseTime(true, 8, 2), 3, 100, 23012, 2));
		// computer engineering
		Department CE = new Department("Computer Engineering", 3);
		CE.add_course(new Ocourse("Kargah", "Dr zarabi", new ExamDate(2024, 5, 12, 9, 1), new CourseTime(false, 14, 2), 1, 200, 24010, 3));
		CE.add_course(new Ecourse("BP", "Dr hadise", new ExamDate(2024, 5, 13, 9, 4), new CourseTime(true, 13, 2), 3, 200, 24011, 3));
		CE.add_course(new Ecourse("AP", "Dr hadise 2", new ExamDate(2024, 5, 14, 9, 4), new CourseTime(false, 14, 2), 3, 200, 24012, 3));
		// physics
		Department phys = new Department("Physics", 4);
		phys.add_course(new Ocourse("Phys 1", "Dr moghimi", new ExamDate(2024, 4, 20, 9, 3), new CourseTime(true, 8, 2), 3, 250, 36010, 4));
		phys.add_course(new Ocourse("Phys 2", "Dr karegarian", new ExamDate(2024, 4, 21, 9, 3), new CourseTime(true, 10, 2), 3, 250, 36011, 4));
		phys.add_course(new Ecourse("static", "Dr hesabi", new ExamDate(2024, 6, 23, 9, 2), new CourseTime(false, 17, 2), 2, 20, 36012, 4));
		//
		deps.add(Math);
		deps.add(EE);
		deps.add(CE);
		deps.add(phys);
	}
	public static Department get_dep_by_code(int n)
	{
		for(Department dep: deps)
			if(dep.getCode() == n)
				return dep;
		return null;
	}
	public static Course search_for_course_by_code(int code) // null means we didn't find shit
	{
		for(Department dep: deps)
		{
			Course f = dep.get_course(code);
			if(f != null)
				return f;
		}
		return null;
	}
	public static void load_file()
	{
		// TODO
	}
}
