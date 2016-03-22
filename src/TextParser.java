import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser
{
	public static Date parseDate(String text, String field) throws Exception
	{
		Date date = new Date(0);
		if (isCorrectDate(text))
		{
			String[] vals = text.split("/");
			int day = Integer.parseInt(vals[0]);
			int month = Integer.parseInt(vals[1]);
			int year = Integer.parseInt(vals[2]);
			date = new Date(new GregorianCalendar(year,month,day).getTimeInMillis());
		}
		else throw new Exception("�������� ������ ����. ����-"+field);
		return date;
	}
	
	private static boolean isCorrectDate(String text)
	{
		//dd/mm/yyyy
		Pattern p = Pattern.compile("(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d");
		Matcher m = p.matcher(text);
		return m.matches();
	}
	
	public static Integer parseInteger(String text, String field) throws Exception
	{
		Integer res = 0;
		try
		{
			res = Integer.parseInt(text);
		}
		catch (Exception e)
		{
			throw new Exception("�������� ������������� ��������. ����: "+field);
		}
		return res;
	}
	
	public static Double parseDouble (String text, String field) throws Exception
	{
		Double res = 0.0;
		try
		{
			res = Double.parseDouble(text);
		}
		catch (Exception e)
		{
			throw new Exception("�������� �������� � ��������� ������. ����: "+field);
		}
		return res;
	}
	
}
