package ir.behmerd.weightcontrol.common;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PersianCalendar {

	public convert Convert;
	public now Now;
	public utilities Utilities;

	public PersianCalendar(){
		this.Convert = new convert();
		this.Now = new now();
		this.Utilities = new utilities();
	}

	public class convert{

		public convert(){}

		public String toPersian(int Year, int Month, int Day)
		{
			if (Utilities.GregorianLeapYear(Year))
				return ConvertPersianDate_Leap(Year, Month, Day);
			else
				return ConvertPersianDate_NotLeap(Year, Month, Day, Utilities.GregorianLeapYear(Year - 1));
		}

		public String toPersian(String FullDate)
		{
			String[] date = Utilities.SplitDate(FullDate);
			return toPersian(Integer.valueOf(date[0]), Integer.valueOf(date[1]), Integer.valueOf(date[2]));
		}

		public String toGregorian(int Year, int Month, int Day)
		{
			boolean L = false;
			if ((Month >= 1) && (Month <= 9))
				L = Utilities.GregorianLeapYear(Year + 621);
			else if (Month == 10)
			{
				if (Utilities.PersianLeapYear(Year))
				{
					if ((Day >= 1) && (Day <= 11))
						L = Utilities.GregorianLeapYear(Year + 621);
				}
				else
				{
					if ((Day >= 1) && (Day <= 10))
						L = Utilities.GregorianLeapYear(Year + 621);
				}
			}
			else
				L = Utilities.GregorianLeapYear(Year + 622);
			if (L)
				return ConvertGregorianDate_Leap(Year, Month, Day);
			else
				return ConvertGregorianDate_NotLeap(Year, Month, Day, Utilities.PersianLeapYear(Year));
		}

		public String toGregorian(String FullDate)
		{
			String[] date = Utilities.SplitDate(FullDate);
			return toGregorian(Integer.valueOf(date[0]), Integer.valueOf(date[1]), Integer.valueOf(date[2]));
		}

		private String ConvertPersianDate_NotLeap(int Year, int Month, int Day, boolean AL)
		{
			String datestr;
			switch(Month)
			{
				case 1:
					if(AL)
					{
						if((Day >= 1) && (Day <= 19))
						{
							Day += 11;
							Month += 9;
						}
						else if((Day >= 20) && (Day <= 31))
						{
							Day -= 19;
							Month += 10;
						}
					}
					else
					{
						if((Day >= 1) && (Day <= 20))
						{
							Day += 10;
							Month += 9;
						}
						else if((Day >= 21) && (Day <= 31))
						{
							Day -= 20;
							Month += 10;
						}
					}
					Year -= 622;
					break;
				case 2:
					if(AL)
					{
						if((Day >= 1) && (Day <= 18))
						{
							Day += 12;
							Month += 9;
						}
						else if((Day >= 19) && (Day <= 28))
						{
							Day -= 18;
							Month += 10;
						}
					}
					else
					{
						if((Day >= 1) && (Day <= 19))
						{
							Day += 11;
							Month += 9;
						}
						else if((Day >= 20) && (Day <= 28))
						{
							Day -= 19;
							Month += 10;
						}
					}
					Year -= 622;
					break;
				case 3:
					if((Day >= 1) && (Day <= 20))
					{
						if(AL)
						{
							Day += 10;
							Month += 9;
						}
						else
						{
							Day += 9;
							Month += 9;
						}
						Year -= 622;
					}
					else if((Day >= 21) && (Day <= 31))
					{
						Day -= 20;
						Month -= 2;
						Year -= 621;
					}
					break;
				case 4:
					if((Day >= 1) && (Day <= 20))
					{
						Day += 11;
						Month -= 3;
					}
					else if((Day >= 21) && (Day <= 30))
					{
						Day -= 20;
						Month -= 2;
					}
					Year -= 621;
					break;
				case 5:
					if((Day >= 1) && (Day <= 21))
					{
						Day += 10;
						Month -= 3;
					}
					else if((Day >= 22) && (Day <= 31))
					{
						Day -= 21;
						Month -= 2;
					}
					Year -= 621;
					break;
				case 6:
					if((Day >= 1) && (Day <= 21))
					{
						Day += 10;
						Month -= 3;
					}
					else if((Day >= 22) && (Day <= 30))
					{
						Day -= 21;
						Month -= 2;
					}
					Year -= 621;
					break;
				case 7:
					if((Day >= 1) && (Day <= 22))
					{
						Day += 9;
						Month -= 3;
					}
					else if((Day >= 23) && (Day <= 31))
					{
						Day -= 22;
						Month -= 2;
					}
					Year -= 621;
					break;
				case 8:
					if((Day >= 1) && (Day <= 22))
					{
						Day += 9;
						Month -= 3;
					}
					else if((Day >= 23) && (Day <= 31))
					{
						Day -= 22;
						Month -= 2;
					}
					Year -= 621;
					break;
				case 9:
					if((Day >= 1) && (Day <= 22))
					{
						Day += 9;
						Month -= 3;
					}
					else if((Day >= 23) && (Day <= 30))
					{
						Day -= 22;
						Month -= 2;
					}
					Year -= 621;
					break;
				case 10:
					if((Day >= 1) && (Day <= 22))
					{
						Day += 8;
						Month -= 3;
					}
					else if((Day >= 23) && (Day <= 31))
					{
						Day -= 22;
						Month -= 2;
					}
					Year -= 621;
					break;
				case 11:
					if((Day >= 1) && (Day <= 21))
					{
						Day += 9;
						Month -= 3;
					}
					else if((Day >= 22) && (Day <= 30))
					{
						Day -= 21;
						Month -= 2;
					}
					Year -= 621;
					break;
				case 12:
					if((Day >= 1) && (Day <= 21))
					{
						Day += 9;
						Month -= 3;
					}
					else if((Day >= 22) && (Day <= 31))
					{
						Day -= 21;
						Month -= 2;
					}
					Year -= 621;
					break;
			}

			datestr = String.valueOf(Year);
			datestr = datestr + "/";
			if(Month < 10)
				datestr = datestr + "0" + Month;
			else
				datestr = datestr + Month;

			datestr = datestr + "/";
			if(Day < 10)
				datestr = datestr + "0" + Day;
			else
				datestr = datestr + Day;

			return datestr;
		}

		private String ConvertPersianDate_Leap(int Year, int Month, int Day)
		{
			String datestr;
			switch(Month)
			{
				case 1:
					if((Day >= 1) && (Day <= 20))
					{
						Day += 10;
						Month += 9;
					}
					else if((Day >= 21) && (Day <= 31))
					{
						Day -= 20;
						Month += 10;
					}
					Year -= 622;
					break;
				case 2:
					if((Day >= 1) && (Day <= 19))
					{
						Day += 11;
						Month += 9;
					}
					else if((Day >= 20) && (Day <= 29))
					{
						Day -= 19;
						Month += 10;
					}
					Year -= 622;
					break;
				case 3:
					if((Day >= 1) && (Day <= 19))
					{
						Day += 10;
						Month += 9;
						Year -= 622;
					}
					else if((Day >= 20) && (Day <= 31))
					{
						Day -= 19;
						Month -= 2;
						Year -= 621;
					}
					break;
				case 4:
					if((Day >= 1) && (Day <= 19))
					{
						Day += 12;
						Month -= 3;
					}
					else if((Day >= 20) && (Day <= 30))
					{
						Day -= 19;
						Month -= 2;
					}
					Year -= 621;
					break;
				case 5:
					if((Day >= 1) && (Day <= 20))
					{
						Day += 11;
						Month -= 3;
					}
					else if((Day >= 21) && (Day <= 31))
					{
						Day -= 20;
						Month -= 2;
					}
					Year -= 621;
					break;
				case 6:
					if((Day >= 1) && (Day <= 20))
					{
						Day += 11;
						Month -= 3;
					}
					else if((Day >= 21) && (Day <= 30))
					{
						Day -= 20;
						Month -= 2;
					}
					Year -= 621;
					break;
				case 7:
					if((Day >= 1) && (Day <= 21))
					{
						Day += 10;
						Month -= 3;
					}
					else if((Day >= 22) && (Day <= 31))
					{
						Day -= 21;
						Month -= 2;
					}
					Year -= 621;
					break;
				case 8:
					if((Day >= 1) && (Day <= 21))
					{
						Day += 10;
						Month -= 3;
					}
					else if((Day >= 22) && (Day <= 31))
					{
						Day -= 21;
						Month -= 2;
					}
					Year -= 621;
					break;
				case 9:
					if((Day >= 1) && (Day <= 21))
					{
						Day += 10;
						Month -= 3;
					}
					else if((Day >= 22) && (Day <= 30))
					{
						Day -= 21;
						Month -= 2;
					}
					Year -= 621;
					break;
				case 10:
					if((Day >= 1) && (Day <= 21))
					{
						Day += 9;
						Month -= 3;
					}
					else if((Day >= 22) && (Day <= 31))
					{
						Day -= 21;
						Month -= 2;
					}
					Year -= 621;
					break;
				case 11:
					if((Day >= 1) && (Day <= 20))
					{
						Day += 10;
						Month -= 3;
					}
					else if((Day >= 21) && (Day <= 30))
					{
						Day -= 20;
						Month -= 2;
					}
					Year -= 621;
					break;
				case 12:
					if((Day >= 1) && (Day <= 20))
					{
						Day += 10;
						Month -= 3;
					}
					else if((Day >= 21) && (Day <= 31))
					{
						Day -= 20;
						Month -= 2;
					}
					Year -= 621;
					break;
			}


			datestr = String.valueOf(Year);
			datestr = datestr + "/";
			if(Month < 10)
				datestr = datestr + "0" + Month;
			else
				datestr = datestr + Month;

			datestr = datestr + "/";
			if(Day < 10)
				datestr = datestr + "0" + Day;
			else
				datestr = datestr + Day;

			return datestr;
		}

		private String ConvertGregorianDate_NotLeap(int Year, int Month, int Day, boolean L)
		{
			String datestr;
			switch(Month)
			{
				case 1:
					if((Day >= 1) && (Day <= 11))
					{
						Day += 20;
						Month += 2;
					}
					else if((Day >= 12) && (Day <= 31))
					{
						Day -= 11;
						Month += 3;
					}
					Year += 621;
					break;
				case 2:
					if((Day >= 1) && (Day <= 10))
					{
						Day += 20;
						Month += 2;
					}
					else if((Day >= 11) && (Day <= 31))
					{
						Day -= 10;
						Month += 3;
					}
					Year += 621;
					break;
				case 3:
					if((Day >= 1) && (Day <= 10))
					{
						Day += 21;
						Month += 2;
					}
					else if((Day >= 11) && (Day <= 31))
					{
						Day -= 10;
						Month += 3;
					}
					Year += 621;
					break;
				case 4:
					if((Day >= 1) && (Day <= 9))
					{
						Day += 21;
						Month += 2;
					}
					else if((Day >= 10) && (Day <= 31))
					{
						Day -= 9;
						Month += 3;
					}
					Year += 621;
					break;
				case 5:
					if((Day >= 1) && (Day <= 9))
					{
						Day += 22;
						Month += 2;
					}
					else if((Day >= 10) && (Day <= 31))
					{
						Day -= 9;
						Month += 3;
					}
					Year += 621;
					break;
				case 6:
					if((Day >= 1) && (Day <= 9))
					{
						Day += 22;
						Month += 2;
					}
					else if((Day >= 10) && (Day <= 31))
					{
						Day -= 9;
						Month += 3;
					}
					Year += 621;
					break;
				case 7:
					if((Day >= 1) && (Day <= 8))
					{
						Day += 22;
						Month += 2;
					}
					else if((Day >= 9) && (Day <= 30))
					{
						Day -= 8;
						Month += 3;
					}
					Year += 621;
					break;
				case 8:
					if((Day >= 1) && (Day <= 9))
					{
						Day += 22;
						Month += 2;
					}
					else if((Day >= 10) && (Day <= 30))
					{
						Day -= 9;
						Month += 3;
					}
					Year += 621;
					break;
				case 9:
					if((Day >= 1) && (Day <= 9))
					{
						Day += 21;
						Month += 2;
					}
					else if((Day >= 10) && (Day <= 30))
					{
						Day -= 9;
						Month += 3;
					}
					Year += 621;
					break;
				case 10:
					if(L)
					{
						if((Day >= 12) && (Day <= 30))
						{
							Day -= 11;
							Month -= 9;
							Year += 622;
						}
					}
					else
					{
						if((Day >= 1) && (Day <= 10))
						{
							Day += 21;
							Month += 2;
							Year += 621;
						}
						else if((Day >= 11) && (Day <= 30))
						{
							Day -= 10;
							Month -= 9;
							Year += 622;
						}
					}
					break;
				case 11:
					if(L)
					{
						if((Day >= 1) && (Day <= 12))
						{
							Day += 19;
							Month -= 10;
						}
						else if((Day >= 13) && (Day <= 30))
						{
							Day -= 12;
							Month -= 9;
						}
					}
					else
					{
						if((Day >= 1) && (Day <= 11))
						{
							Day += 20;
							Month -= 10;
						}
						else if((Day >= 12) && (Day <= 30))
						{
							Day -= 11;
							Month -= 9;
						}
					}
					Year += 622;
					break;
				case 12:
					if(L)
					{
						if((Day >= 1) && (Day <= 10))
						{
							Day += 18;
							Month -= 10;
						}
						else if((Day >= 11) && (Day <= 30))
						{
							Day -= 10;
							Month -= 9;
						}
					}
					else
					{
						if((Day >= 1) && (Day <= 9))
						{
							Day += 19;
							Month -= 10;
						}
						else if((Day >= 10) && (Day <= 29))
						{
							Day -= 9;
							Month -= 9;
						}
					}
					Year += 622;
					break;
			}

			if(Month < 10)
				datestr = "0" + String.valueOf(Month);
			else
				datestr = String.valueOf(Month);

			datestr = datestr + "/";
			if(Day < 10)
				datestr = datestr + "0" + Day;
			else
				datestr = datestr + Day;

			datestr = datestr + "/";
			datestr = datestr + Year;

			return datestr;
		}

		private String ConvertGregorianDate_Leap(int Year, int Month, int Day)
		{
			String datestr;
			switch(Month)
			{
				case 1:
					if((Day >= 1) && (Day <= 12))
					{
						Day += 19;
						Month += 2;
					}
					else if((Day >= 13) && (Day <= 31))
					{
						Day -= 12;
						Month += 3;
					}
					Year += 621;
					break;
				case 2:
					if((Day >= 1) && (Day <= 11))
					{
						Day += 19;
						Month += 2;
					}
					else if((Day >= 12) && (Day <= 31))
					{
						Day -= 11;
						Month += 3;
					}
					Year += 621;
					break;
				case 3:
					if((Day >= 1) && (Day <= 11))
					{
						Day += 20;
						Month += 2;
					}
					else if((Day >= 12) && (Day <= 31))
					{
						Day -= 11;
						Month += 3;
					}
					Year += 621;
					break;
				case 4:
					if((Day >= 1) && (Day <= 10))
					{
						Day += 20;
						Month += 2;
					}
					else if((Day >= 11) && (Day <= 31))
					{
						Day -= 10;
						Month += 3;
					}
					Year += 621;
					break;
				case 5:
					if((Day >= 1) && (Day <= 10))
					{
						Day += 21;
						Month += 2;
					}
					else if((Day >= 11) && (Day <= 31))
					{
						Day -= 10;
						Month += 3;
					}
					Year += 621;
					break;
				case 6:
					if((Day >= 1) && (Day <= 10))
					{
						Day += 21;
						Month += 2;
					}
					else if((Day >= 11) && (Day <= 31))
					{
						Day -= 10;
						Month += 3;
					}
					Year += 621;
					break;
				case 7:
					if((Day >= 1) && (Day <= 9))
					{
						Day += 21;
						Month += 2;
					}
					else if((Day >= 10) && (Day <= 30))
					{
						Day -= 9;
						Month += 3;
					}
					Year += 621;
					break;
				case 8:
					if((Day >= 1) && (Day <= 10))
					{
						Day += 21;
						Month += 2;
					}
					else if((Day >= 11) && (Day <= 30))
					{
						Day -= 10;
						Month += 3;
					}
					Year += 621;
					break;
				case 9:
					if((Day >= 1) && (Day <= 10))
					{
						Day += 20;
						Month += 2;
					}
					else if((Day >= 11) && (Day <= 30))
					{
						Day -= 10;
						Month += 3;
					}
					Year += 621;
					break;
				case 10:
					if((Day >= 1) && (Day <= 11) && (Utilities.PersianLeapYear(Year)))
					{
						Day += 20;
						Month += 2;
						Year += 621;
					}
					else if((Day >= 11) && (Day <= 30) && (!Utilities.PersianLeapYear(Year)))
					{
						Day -= 10;
						Month -= 9;
						Year += 622;
					}
					break;
				case 11:
					if((Day >= 1) && (Day <= 11))
					{
						Day += 20;
						Month -= 10;
					}
					else if((Day >= 12) && (Day <= 30))
					{
						Day -= 11;
						Month -= 9;
					}
					Year += 622;
					break;
				case 12:
					if((Day >= 1) && (Day <= 10))
					{
						Day += 19;
						Month -= 10;
					}
					else if((Day >= 11) && (Day <= 29))
					{
						Day -= 10;
						Month -= 9;
					}
					Year += 622;
					break;
			}

			if(Month < 10)
				datestr = "0" + String.valueOf(Month);
			else
				datestr = String.valueOf(Month);

			datestr = datestr + "/";
			if(Day < 10)
				datestr = datestr + "0" + Day;
			else
				datestr = datestr + Day;

			datestr = datestr + "/";
			datestr = datestr + Year;

			return datestr;
		}

	}


	public class now{

		public now(){}

		public String Today()
		{
			Calendar calendar = Calendar.getInstance();
			return  Convert.toPersian(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
		}

		public int Year()
		{
			Calendar calendar = Calendar.getInstance();
			String date = Convert.toPersian(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DAY_OF_MONTH));
			String[] year = Utilities.SplitDate(date);
			return Integer.valueOf(year[0]);
		}

		public int Month()
		{
			Calendar calendar = Calendar.getInstance();
			String date = Convert.toPersian(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DAY_OF_MONTH));
			String[] month = Utilities.SplitDate(date);
			return Integer.valueOf(month[1]);
		}

		public int Day()
		{
			Calendar calendar = Calendar.getInstance();
			String date = Convert.toPersian(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DAY_OF_MONTH));
			String[] day = Utilities.SplitDate(date);
			return Integer.valueOf(day[2]);
		}

	}


	public class utilities{

		public utilities(){}

		public int getYearOf(int Year, int Month, int Day)
		{
			String date = Convert.toPersian(Year, Month, Day);
			String[] year = SplitDate(date);
			return Integer.valueOf(year[0]);
		}

		public int getMonthOf(int Year, int Month, int Day)
		{
			String date = Convert.toPersian(Year, Month, Day);
			String[] month = SplitDate(date);
			return Integer.valueOf(month[1]);
		}

		public int getDayOf(int Year, int Month, int Day)
		{
			String date = Convert.toPersian(Year, Month, Day);
			String[] day = SplitDate(date);
			return Integer.valueOf(day[2]);
		}

		public int extractYear(String FullDate)
		{
			String[] year = SplitDate(FullDate);
			return Integer.valueOf(year[0]);
		}

		public int extractMonth(String FullDate)
		{
			String[] month = SplitDate(FullDate);
			return Integer.valueOf(month[1]);
		}

		public int extractDay(String FullDate)
		{
			String[] day = SplitDate(FullDate);
			return Integer.valueOf(day[2]);
		}

		public int getMaxDay(int year, int month)
		{
			int maxday;

			if(month>=1 && month<=6)
				maxday = 31;
			else if(month>=7 && month<=11)
				maxday = 30;
			else
				maxday = (PersianLeapYear(year)) ? 30 : 29;

			return maxday;
		}

		public int getDayOfWeek(String fullDate)
		{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(StringToDate(Convert.toGregorian(fullDate),"MM/dd/yyyy"));
			int weekday = calendar.get(Calendar.DAY_OF_WEEK);
			if(weekday==7)
				return 0;
			else
				return weekday;
		}

		public boolean GregorianLeapYear(int Year)
		{
			if(Year%4 == 0)
			{
				if(Year%100 != 0)
					return true;
				else
					return (Year%400 == 0);
			}
			return false;
		}

		public boolean PersianLeapYear(int Year)
		{
			int tens, ones;
			tens = Year % 100;
			ones = Year % 10;
			tens -= ones;
			tens /= 10;
			if ((tens == 1 || tens % 2 == 1) && (ones == 1 || ones % 4 == 1))
				return true;
			else
				return ((tens == 0 || tens % 2 == 0) && (ones == 3 || ones == 7));
		}

		private String[] SplitDate(String FullDate){
			String[] date = FullDate.split("/");
			if(date.length<2)
				date = FullDate.split("-");
			return date;
		}

		private Date StringToDate(String date,String format)
		{
			if(date==null)
				return null;
			ParsePosition position = new ParsePosition(0);
			SimpleDateFormat simpledateformat = new SimpleDateFormat(format);
			return simpledateformat.parse(date, position);
		}

	}

}