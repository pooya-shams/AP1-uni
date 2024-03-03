package util;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class IOHelper
{
	public static String get_valid_input(ArrayList<String> valids, Scanner sc)
	{
		String mf;
		mf = sc.next();
		while(!valids.contains(mf))
		{
			System.out.println("your input '"+mf+"' is not a valid option");
			System.out.print("valid options are: ");
			for(String v: valids)
				System.out.print(v+", ");
			System.out.println();
			System.out.println("enter a real value: ");
			mf = sc.next();
		}
		return mf;
	}
}
