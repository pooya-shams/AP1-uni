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
		return s.substring(0, s.length()-1); // without \n at the end
	}
}
