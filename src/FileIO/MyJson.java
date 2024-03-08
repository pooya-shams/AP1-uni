package FileIO;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

public class MyJson
{
	private static class Pair
	{
		String x;
		String y;

		public Pair(String x, String y)
		{
			this.x = x;
			this.y = y;
		}

		public String getX()
		{
			return x;
		}

		public void setX(String x)
		{
			this.x = x;
		}

		public String getY()
		{
			return y;
		}

		public void setY(String y)
		{
			this.y = y;
		}
	}

	private static Pair get_pair(String p)
	{
		int mf = p.indexOf(":");
		return new Pair(p.substring(0, mf).trim(), p.substring(mf+1).trim());
	}

	private static ArrayList<Integer> get_splts(String js)
	{
		assert(js.charAt(0) == '{');
		int bal = 0;
		ArrayList<Integer> splts = new ArrayList<>();
		boolean in_string = false;
		for(int i = 0; i < js.length(); i++)
		{
			char ch = js.charAt(i);
			if(in_string)
			{
				if(ch == '\'')
					in_string = false;
			}
			else
			{
				if(ch == '{' || ch == '[')
					bal++;
				else if(ch == '\'')
					in_string = true;
				else if(ch == '}' || ch == ']')
					bal--;
				else if(ch == ',' && bal == 1)
					splts.add(i);
			}
		}
		return splts;
	}
	public static Map<String, Object> parseJson(String js) // a dict
	{
		Map<String, Object> out = new TreeMap<>();
		ArrayList<Integer> splts = get_splts(js);
		splts.add(js.length()-1);
		int last = 1;
		for(int idx: splts)
		{
			Pair p = get_pair(js.substring(last, idx));
			out.put(p.getX(), MyJson.parseStuff(p.getY()) );
			last = idx+1;
		}
		return out;
	}
	public static Integer[] parseArray(String js) // a list (of integers currently)
	{
		String[] split = js.substring(1, js.length()-1).split(",");
		Integer[] out = new Integer[split.length];
		for (int i = 0; i < split.length; i++)
			out[i] = Integer.parseInt(split[i].trim());
		return out;
	}
	public static Object parseStuff(String js) // anything: int, string, bool, json, list...
	{
		char first = js.charAt(0);
		if (first == '\'') // string
		{
			return js.substring(1, js.length() - 1);
		}
		else if (first == '{')
		{
			return MyJson.parseJson(js);
		}
		else if (first == '[')
		{
			return MyJson.parseArray(js);
		}
		else if (js.equals("true"))
		{
			return Boolean.TRUE;
		}
		else if (js.equals("false"))
		{
			return Boolean.FALSE;
		}
		else if(Character.isDigit(first))
		{
			return Integer.parseInt(js);
		}
		else // unexpected stuff
		{
			return js;
		}
	}
}
