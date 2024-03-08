package FileIO;

import Exceptions.InvalidCode;
import data.Departments;
import data.Users;
import university.Department;
import university.Student;
import university.course.Course;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Loader
{
	public static final File users_file = new File("users.txt"); // or users.json :think:
	public static void write_users()
	{
		try
		{
			FileWriter fw = new FileWriter(users_file);
			PrintWriter pw = new PrintWriter(fw);
			for(Student st: Users.studnets)
				pw.println(st.toJson()); // each line is exactly one student
			fw.close();
		}
		catch (IOException e)
		{
			System.out.println("[warning]: couldn't open users file. will continue with defaults");
		}
	}
	public static void load_users()
	{
		try
		{
			Scanner sc = new Scanner(users_file);
			ArrayList<Student> stds = new ArrayList<>(); // not a really fitting name but has some sort of ambiguity :holyjoy:
			while (sc.hasNextLine())
			{
				String s = sc.nextLine();
				Student st = Student.fromJson(MyJson.parseJson(s));
				stds.add(st);
			}
			for(Student st: stds)
				Users.add_and_replace_user(st);
		}
		catch (FileNotFoundException e)
		{
			System.out.println("[warning]: couldn't open users file. wont load anything");
		}
		catch (InvalidCode e)
		{
			System.out.println("invalid user encountered aborting mission");
		}
		catch (Exception e)
		{
			System.out.println("unhandled exception in loading users don't know what to do");
		}
	}
	public static void write_courses(File file)
	{
		try
		{
			FileWriter fw = new FileWriter(file);
			PrintWriter pw = new PrintWriter(fw);
			for(Department dep: Departments.deps)
			{
				for(Course c: dep.getCourses())
					pw.println(c.toJson());
			}
			fw.close();
		}
		catch (IOException e)
		{
			System.out.println("[warning] couldn't open specified file"+file.getName());
		}
	}
	public static void load_courses(File file)
	{
		try
		{
			Scanner sc = new Scanner(file);
			while(sc.hasNextLine())
			{
				String s = sc.nextLine();
				Course c = Course.fromJson(MyJson.parseJson(s));
				Department dep = Departments.get_dep_by_code(c.getDep_code());
				if(dep != null)
					dep.add_course(c);
			}
		}
		catch (IOException e)
		{
			System.out.println("[warning] couldn't open specified file"+file.getName());
		}
		catch (Exception e)
		{
			System.out.println("[warning] hit unhandled exception in load courses aborting mission");
		}
	}
}
