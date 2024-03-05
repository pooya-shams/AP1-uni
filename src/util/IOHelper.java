package util;

import data.Departments;
import university.Department;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IOHelper
{
	public static boolean is_numeric(String s)
	{
		for(char ch: s.toCharArray())
			if(!Character.isDigit(ch))
				return false;
		return true;
	}
	public static int str_to_int(String s)
	{
		if(!is_numeric(s))
			throw new IllegalArgumentException("String "+s+" is not a numeric string");
		int t = 0;
		for(char ch: s.toCharArray())
			t = t*10 + (ch-'0');
		return t;
	}
	public static boolean in_range(int x, int min, int max)
	{
		return min <= x && x <= max;
	}
	public static int get_valid_input(int max_opt, Scanner sc)
	{
		String mf;
		mf = sc.nextLine().trim();
		while( ! (IOHelper.is_numeric(mf) && IOHelper.in_range(IOHelper.str_to_int(mf), 0, max_opt) ) )
		{
			System.out.println("valid options are numbers 0 through "+max_opt);
			System.out.print("enter a real value: ");
			mf = sc.nextLine().trim();
		}
		return str_to_int(mf);
	}
	public static boolean is_real_dep_code(int n)
	{
		if(n == 0) // special case
			return true;
		for(Department dep: Departments.deps)
		{
			if(dep.getCode() == n)
				return true;
		}
		return false;
	}
	public static int get_dep_code(Scanner sc)
	{
		System.out.print("choose department by code (or 0 to go back): ");
		String mf = sc.nextLine().trim();
		while( ! (IOHelper.is_numeric(mf) && IOHelper.is_real_dep_code(IOHelper.str_to_int(mf)) ) )
		{
			System.out.println("your input '"+mf+"' is not a valid option");
			System.out.print("please enter a real department code or 0: ");
			mf = sc.nextLine().trim();
		}
		return str_to_int(mf);
	}
}
