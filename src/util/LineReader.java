package util;

import java.util.Scanner;

public class LineReader
{
	private final Scanner sc;
	public LineReader(Scanner sc)
	{
		this.sc = sc;
	}
	public String nextLine()
	{
		String s = sc.nextLine();
		if(s.charAt(s.length()-1) == '\n')
			return s.substring(0, s.length()-1);
		return s.substring(0, s.length()); // without \n at the end
	}
}
