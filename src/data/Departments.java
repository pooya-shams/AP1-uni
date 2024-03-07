package data;

import university.Department;
import university.course.Ocourse;
import util.CourseTime;

import java.util.ArrayList;

public class Departments
{
	public static final ArrayList<Department> deps = new ArrayList<>();
	private static boolean lock_populate = false; // haha try running that twice now
	public static void populate_deps()
	{
		if(lock_populate) return;
		lock_populate = true;
		deps.add(new Department("Math", 1));
		deps.add(new Department("Electrical Engineering", 2));
		deps.add(new Department("Computer Engineering", 3));
		deps.add(new Department("Physics", 4));
		deps.get(0).add_course(new Ocourse("Riz 1", "mogh adasi", "1998/10/1", new CourseTime(true, 12, 2), 4, 42, 22015));
		deps.get(3).add_course(new Ocourse("Phys 1", "mogh imi", "1999/10/1", new CourseTime(true, 11, 2), 3, 56, 36011));
	}
	public static Department get_dep_by_code(int n)
	{
		for(Department dep: deps)
			if(dep.getCode() == n)
				return dep;
		return null;
	}
	public static void load_file()
	{
		// TODO
	}
}
