package util;

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
			System.out.println("your input '"+mf+"' is not a valid option");
			System.out.print("valid options are numbers 0 through "+max_opt);
			System.out.println();
			System.out.println("enter a real value: ");
			mf = sc.nextLine().trim();
		}
		return str_to_int(mf);
	}
}
