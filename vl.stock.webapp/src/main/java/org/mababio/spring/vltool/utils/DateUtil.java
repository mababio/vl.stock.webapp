/*
 * Created on Jan 17, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.mababio.spring.vltool.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * @author Isaac
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DateUtil {
	public static final String DEFAULT_DATE_FORMAT = "MM/dd/yyyy";
	public static final String DEFAULT_TIMESTAMP_FORMAT ="MM/dd/yyyy HH:mm:ss";
	final static Logger iLOG = Logger.getLogger(DateUtil.class.getName());
	private static final String HYPHEN = "-";
	private static final String COLON = ":";
	private static final String SPACE = " ";
	private static final String INVALID_DATE = "Invalid input date(s) ";
	private static final String INVALID_FORMAT = "Invalid input format ";
	private static final String DAY = "DAY";
	private static final String MONTH = "MONTH";
	private static final String YEAR = "YEAR";
	private static final String WEEK = "WEEK";
	private static final String DATE_CONST="0.0-0000";
	/**
	 * Clears the time fields from the date.
	 * @param date the date to clear.
	 * @return the cleared date.
	 */
	public static Date clearTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	public static Date getTime(String inDate) {
		iLOG.debug("<<getFormattedDate    getTime [ "+inDate+" ]");
		
		Calendar calendar = Calendar.getInstance();
		String[] doBArr=inDate.split("-");
		
		calendar.set(Calendar.YEAR, Integer.valueOf(doBArr[0]));
		calendar.set(Calendar.MONTH, Integer.valueOf(doBArr[1])-1);
		calendar.set(Calendar.DAY_OF_MONTH, Integer.valueOf(doBArr[2]));
		return calendar.getTime();
	}
	
	public static String getDateTime(Date inDate) {
		iLOG.debug("<<getDateTime    getTime [ "+inDate+" ]");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(inDate);
		DateFormat timeformat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		timeformat.setTimeZone(TimeZone.getTimeZone("PST"));
		
		return timeformat.format(calendar.getTime()) ;
	}
	
	public static Date getDateTimeFromString(String inDate)throws Exception {
		iLOG.debug("<<getDateTimeFromString    Input String  [ "+inDate+" ]");
		DateFormat timeformat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return timeformat.parse(inDate);
	}
	public static java.sql.Date getSymetricDateFromString(String inDate) {
		// String format is ----> 	
		iLOG.debug("<<getSymetricDateFromString    Input String  [ "+inDate+" ]");
		DateFormat timeformat=new SimpleDateFormat("yyyy-MM-dd");
	//	return timeformat.parse(inDate);
		Date local = null;
		
		try {
			local = timeformat.parse(inDate);
		} catch (ParseException e) {
			@SuppressWarnings("unused")
			DateFormat timeformat2=new SimpleDateFormat("MM-dd-yy");
			try {
				local = timeformat.parse(inDate);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
		}
		 java.sql.Date sqlDate =  new java.sql.Date(local.getTime());
		 iLOG.debug("<< Returning SQL Date ---> " + sqlDate);
		return sqlDate;
	}
	
	
	
	
	public static String getFormattedDate(String refID, String inDate) {
		iLOG.debug("<<getFormattedDate   refID ["+refID+"]   FormattedDate [ "+inDate+" ]");
		if(StringUtils.endsWith(inDate, DATE_CONST)){
			return inDate;
		}
		SimpleDateFormat timDate= new java.text.SimpleDateFormat("yyyyMMdd",Locale.ENGLISH);
		Date idobirth=getTime(inDate);
		inDate=timDate.format(idobirth);
		inDate=inDate+"000000.0-0000";
		
		return inDate;
	}
	
	public static String getFormattedDateShort(String refID, String inDate) {
		SimpleDateFormat timDate= new java.text.SimpleDateFormat("yyyyMMdd",Locale.ENGLISH);//YYYYMMDD
		Date idobirth=getTime(inDate);
		inDate=timDate.format(idobirth);
		//inDate=inDate+"000000.0-0000";
		iLOG.debug("<<getFormattedDate   refID ["+refID+"]   FormattedDate [ "+inDate+" ]");
		return inDate;
	}
	
	/**
	 * Clears the time fields from the date.
	 * @param date the date to clear.
	 * @return the cleared date.
	 */
	public static Calendar clearTime(Calendar calendar) {
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar;
	}
	
	/**
	 * Checks if date is not null.
	  *	@TODO may want some validation on this value
	 * @param date the date to check.
	 * @return true is the date has a value.
	 */
	public static  boolean hasValue(Date date) {
		boolean hasValue = false;
		if (date != null) {
			hasValue = true;
		}
		return hasValue;
	}	
	
	/**
	 * Convert a java.util.Date to a formatted date string
	 * 
	 * @param 	date	java.util.Date to be formatted
	 * @param 	format	Format of output date string, defaults to "MM/dd/yyyy" if null.
	 * @return 	String	Formatted date string.
	 */
	public static String dateToString(Date date, String format)
	{
		SimpleDateFormat formatter;

		if (format == null)
			formatter = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		else
			formatter = new SimpleDateFormat(format);

		return formatter.format(date);
	}
	
	/**
	 * Convert a java.util.Calendar to a formatted date string
	 * 
	 * @param 	date	java.util.Calendar to be formatted
	 * @param 	format	Format of output date string, defaults to "MM/dd/yyyy" if null.
	 * @return 	String	Formatted date string.
	 */
	public static String dateToString(Calendar date, String format)
	{
		SimpleDateFormat formatter;
		Date d = date.getTime();

		if (format == null)
			formatter = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		else
			formatter = new SimpleDateFormat(format);

		return formatter.format(d);
	}

	/**
	 * Convert a date string to java.sql.Date
	 * 
	 * @param 	date	Input date string.
	 * @param 	format	Format of input date string, defaults to "MM/dd/yyyy" if null.
	 * @return 	java.sql.Date.
	 */
	public static java.sql.Date stringToSqlDate(String date, String format)
	{
		SimpleDateFormat formatter;

		try
		{
			if (format == null)
				formatter = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
			else
				formatter = new SimpleDateFormat(format);
			//return new java.sql.Date(new java.util.Date().getTime());
			return new java.sql.Date(formatter.parse(date).getTime());
		}
		catch (Exception e)
		{
			throw new RuntimeException(e.getMessage());
		}

	}

	/**
	 * Convert a date string to java.sql.Timestamp
	 * 
	 * @param 	date	Input date string.
	 * @param 	format	Format of input date string, defaults to "MM/dd/yyyy HH:mm:ss" if null.
	 * @return 	java.sql.Timestamp
	 */
	public static java.sql.Timestamp stringToSqlTimestamp(
		String dateTime,
		String format)
	{
		SimpleDateFormat formatter;

		try
		{
			if (format == null)
				formatter = new SimpleDateFormat(DEFAULT_TIMESTAMP_FORMAT);
			else
				formatter = new SimpleDateFormat(format);
			return new Timestamp(formatter.parse(dateTime).getTime());
		}
		catch (Exception e)
		{
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * Convert current date string into long data type
	 * 
	 * @param 	date	Input date string.
	 * @param 	format	Format of input date string, defaults to "MM/dd/yyyy" if null.
	 * @return 	long	Current date as long data type
	 */
	public static long stringToLongDate(String date, String format)
	{
		SimpleDateFormat formatter;

		try
		{
			if (format == null)
				formatter = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
			else
				formatter = new SimpleDateFormat(format);

			return formatter.parse(date).getTime();
		}
		catch (Exception e)
		{
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * Convert date string into java.util.Date
	 * 
	 * @param 	date	Input date string.
	 * @param 	format	Format of input date string, defaults to "MM/dd/yyyy" if null.
	 * @return 	java.util.Date	
	 */
	public static Date stringToDate(String date, String format)
	{

		SimpleDateFormat formatter;

		try
		{
			if (format == null)
				formatter = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
			else
				formatter = new SimpleDateFormat(format);

			return formatter.parse(date);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/**
	 * Convert one date format to another
	 * @author ltn
	 * @param dateStr	date string
	 * @param formatIn 	format of the input date
	 * @param formatOut format of the output date, if null defaults to "MM/dd/yyyy"
	 * @return String   converted date
	 */
	public static String convertDateFormat(
		String dateStr,
		String formatIn,
		String formatOut)
	{
		if (formatIn == null || formatIn.length() <= 0)
			throw new RuntimeException(INVALID_FORMAT);

		String outFormat = formatOut;
		if (outFormat == null)
			outFormat = DEFAULT_DATE_FORMAT;

		long ldate = stringToLongDate(dateStr, formatIn);

		Date date = new Date(ldate);
		return dateToString(date, outFormat);

	}

	/**
	 * Get current java.sql.Date from a current instance of Calendar. The Calendar object must
	 * not be initialized with user date.
	 * 
	 * @author	Dharmesh Patel
	 * @param 	cal 			input java.util.Calendar
	 * @return 	java.sql.Date
	 */
	public static java.sql.Date getCalendarSqlDate(java.util.Calendar cal)
	{
		java.util.Date dateU = cal.getTime();
		return new java.sql.Date(dateU.getTime());
	}

	/**
	 * Get current java.sql.Date
	 * 
	 * @return 	java.sql.Date
	 */
	public static java.sql.Date getCurrentSqlDate()
	{
		try
		{
			SimpleDateFormat formatter = new SimpleDateFormat();
			String dtS = formatter.format(new Date(System.currentTimeMillis()));
			return new java.sql.Date(formatter.parse(dtS).getTime());
		}
		catch (Exception e)
		{
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * Get current date/time string in specified format.
	 * 
	 * @param 	format	Format of output date string, defaults to "MM/dd/yyyy HH:mm:ss" if null.
	 * @return 	String	Formatted date
	 */
	public static String getCurrentDateTime(String format)
	{
		SimpleDateFormat formatter;

		Date date = new Date(System.currentTimeMillis());
		if (format == null)
			formatter = new SimpleDateFormat(DEFAULT_TIMESTAMP_FORMAT);
		else
			formatter = new SimpleDateFormat(format);

		return formatter.format(date);

	}

	/**
	 * Get current Timestamp
	 *
	 * @return	java.sql.Timestamp
	 */
	public static Timestamp getCurrentTimestamp()
	{
		java.sql.Timestamp currtime;
		java.util.GregorianCalendar cal = new java.util.GregorianCalendar();
		int month = cal.get(java.util.Calendar.MONTH);
		month = month + 1;
		int year = cal.get(java.util.Calendar.YEAR);
		int day = cal.get(java.util.Calendar.DATE);
		int hour = cal.get(java.util.Calendar.HOUR_OF_DAY);
		int min = cal.get(java.util.Calendar.MINUTE);
		int sec = cal.get(java.util.Calendar.SECOND);
		String temp =
			year
				+ HYPHEN
				+ month
				+ HYPHEN
				+ day
				+ SPACE
				+ hour
				+ COLON
				+ min
				+ COLON
				+ sec;
		currtime = java.sql.Timestamp.valueOf(temp);
		return currtime;
	}

	/**
	 * Get number of days in month from a GregorianCalendar instance.
	 * 
	 * @param 	cal	GregorianCalendar initialized with a date (month is 0-based).
	 * @return 	int	Number of days in month
	 */
	public static int getDaysInMonth(GregorianCalendar cal)
	{
		int[] daysInMonths = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		daysInMonths[1] += cal.isLeapYear(cal.get(GregorianCalendar.YEAR))
			? 1
			: 0;
		return daysInMonths[cal.get(GregorianCalendar.MONTH)];
	}

	/**
	 * Get number of days in current month
	 * 
	 * @param 	
	 * @return 	int	Number of days in current month
	 */
	public static int getDaysInCurrentMonth()
	{
		GregorianCalendar cal = new GregorianCalendar();
		int[] daysInMonths = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		daysInMonths[1] += cal.isLeapYear(cal.get(GregorianCalendar.YEAR))
			? 1
			: 0;
		return daysInMonths[cal.get(GregorianCalendar.MONTH)];
	}

	/**
	 * Calculates date based on a given date and unit (DAY,MONTH,YEAR)]
	 * to add or subtract.
	 * @author ltn
	 * @param dateStr			Input date string
	 * @param pOffset 			Number to adjust the date (positive or negative)
	 * @param unit   			unit of offset (DAY,WEEK,MONTH,YEAR)
	 * @param format 			Format of the input date string
	 * @return java.util.Date	(use dateToString(Date date, String format) to get a string date).
	 */
	public static Date getDateOffset(
		String dateStr,
		int pOffset,
		String unit,
		String format)
	{
		long ldate;

		if (format == null)
			ldate = stringToLongDate(dateStr, DEFAULT_DATE_FORMAT);
		else
			ldate = stringToLongDate(dateStr, format);

		Date date = new Date(ldate);
		return getDateOffset(date, pOffset, unit);

	}

	/**
	 * Calculates date based on a given date and unit (DAY,MONTH,YEAR)]
	 * to add or subtract.
	 * @author ltn
	 * @param dt				Input java.util.Date
	 * @param pOffset 			Number to adjust the date (positive or negative)
	 * @param unit   			unit of offset (DAY,WEEK,MONTH,YEAR)
	 * @return java.util.Date	(use dateToString(Date date, String format) to get a string date).
	 */
	public static Date getDateOffset(Date date, int pOffset, String unit)
	{
		int offset = pOffset;
		int field = Calendar.DATE;

		if (unit.equalsIgnoreCase(DAY))
			field = Calendar.DATE;
		else
			if (unit.equalsIgnoreCase(MONTH))
				field = Calendar.MONTH;
			else
				if (unit.equalsIgnoreCase(YEAR))
					field = Calendar.YEAR;
				else
					if (unit.equalsIgnoreCase(WEEK))
					{
						field = Calendar.DATE;
						offset = pOffset * 7;
					}

		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(field, offset);
		return cal.getTime();

	}

	/**
	 * Calculate rounded Age in year given a birth date.
	 * Example of return values:
	 * 		Less that one year = 0 year
	 * 		1 year and 3 months = 1 year 
	 * 
	 * @param 	dateOfBirth	The date of birth as a String.
	 * @param 	format		Format of the date of birth, defaults to "MM/dd/yyyy" if null.
	 * @return	int			calculated age in year.
	 * @author 	Dharmesh Patel
	 */
	public static int getAge(String dateOfBirth, String format)
	{
		if (dateOfBirth == null)
			throw new RuntimeException(INVALID_DATE + dateOfBirth);
		java.sql.Date dob = stringToSqlDate(dateOfBirth, format);
		return getAge(dob);

	}

	/**
	 * Calculate rounded Age in year given a birth date.
	 * Example of return values:
	 * 		Less that one year = 0 year
	 * 		1 year and 3 months = 1 year 
	 * 
	 * @param 	dateOfBirth	The date of birth as a java.util.Date.
	 * @return	int			calculated age in year.
	 * @author 	Dharmesh Patel
	 */
	public static int getAge(java.util.Date dob)
	{
		if (dob == null)
			throw new RuntimeException(INVALID_DATE + dob);
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		int currentDayOfYear = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);

		Calendar dobCal = Calendar.getInstance();
		dobCal.setTime(dob);
		int dobYear = dobCal.get(Calendar.YEAR);
		int dobDayOfYear = dobCal.get(Calendar.DAY_OF_YEAR);

		int age = currentYear - dobYear;
		if (age == 0 && currentDayOfYear < dobDayOfYear)
			throw new RuntimeException(INVALID_DATE + dob);
		if (age > 0 && currentDayOfYear < dobDayOfYear)
			age = age - 1;
		if (age < 0)
			throw new RuntimeException(INVALID_DATE + dob);
		return age;

	}

	/**
	 * Verify that date is valid for the month and year given.
	 * @param tgtMM		Target month
	 * @param tgtYYYY	Target year
	 * @param date		date to verify
	 * @param format	format of date (defaults to MM/dd/yyyy HH:mm:ss)
	 * @return			true if given date is valid for the target month and year.
	 */
	public static boolean isDateInMonth(
		int tgtMM,
		int tgtYYYY,
		String date,
		String format)
	{

		boolean isTrue;

		try
		{
			GregorianCalendar begDt =
				new GregorianCalendar(tgtYYYY, tgtMM - 1, 1, 0, 0, 0);
			int daysInMonth = getDaysInMonth(begDt);
			GregorianCalendar endDt =
				new GregorianCalendar(
					tgtYYYY,
					tgtMM - 1,
					daysInMonth,
					23,
					59,
					59);

			SimpleDateFormat formatter;
			if (format == null)
				formatter = new SimpleDateFormat(DEFAULT_TIMESTAMP_FORMAT);
			else
				formatter = new SimpleDateFormat(format);

			formatter.parse(date);
			Calendar cal = formatter.getCalendar();
			GregorianCalendar gCal =
				new GregorianCalendar(
					cal.get(Calendar.YEAR),
					cal.get(Calendar.MONTH),
					cal.get(Calendar.DATE),
					cal.get(Calendar.HOUR_OF_DAY),
					cal.get(Calendar.MINUTE),
					cal.get(Calendar.SECOND));

			if (gCal.before(begDt) || gCal.after(endDt))
				isTrue = false;
			else
				isTrue = true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

		return isTrue;

	}
	/**
	 * Get elapsed time in hours:minutes:seconds format given two timestamp strings
	 * @param date1		String representing timestamp, format defaults to "MM/dd/yyyy HH:mm:ss"
	 * @param date2		String representing timestamp, format defaults to "MM/dd/yyyy HH:mm:ss",
	 * 					must be later than date1.
	 * @param format	String representing input format, defaults to "MM/dd/yyyy HH:mm:ss" if null 
	 * @return			String representing elapsed hour/minute/second in "H:M:S" format
	 */
	public static String getElapsedHMS(
		String date1,
		String date2,
		String format)
	{
		String fmt;
		if (format == null)
			fmt = DEFAULT_TIMESTAMP_FORMAT;
		else
			fmt = format;

		long lDate1 = stringToLongDate(date1, fmt);
		long lDate2 = stringToLongDate(date2, fmt);
		if (lDate2 < lDate1)
			throw new RuntimeException(INVALID_DATE);

		long lMilSecs = lDate2 - lDate1;
		long elapsedSecs = lMilSecs / 1000;

		StringBuffer elapsedHms = new StringBuffer(16);
		long hours, minutes, seconds;
		hours = elapsedSecs / 3600;
		elapsedSecs = elapsedSecs - (hours * 3600);
		minutes = elapsedSecs / 60;
		elapsedSecs = elapsedSecs - (minutes * 60);
		seconds = elapsedSecs;
		elapsedHms.append(hours);
		elapsedHms.append(COLON);
		elapsedHms.append(minutes);
		elapsedHms.append(COLON);
		elapsedHms.append(seconds);

		return elapsedHms.toString();
	}

	/**
	 * Get elapsed hours given two timestamp. This function accounts for the time portion
	 * of the timestamp string.
	 * @param 	date1	java.sql.Timestamp
	 * @param 	date2	java.sql.Timestamp,	must be later than date1
	 * @param 	rounded	Rounded if set to true (seconds and minutes greater than 30 will be rounded up)
	 * @return	long	Number of elapsed hours
	 */
	public static long getElapsedHours(
		Timestamp date1,
		Timestamp date2,
		boolean rounded)
	{
		long hours = getElapsedHours(dateToString(date1, DEFAULT_TIMESTAMP_FORMAT), dateToString(date2, DEFAULT_TIMESTAMP_FORMAT), DEFAULT_TIMESTAMP_FORMAT, rounded);
		return hours;
	}
	
	/**
	 * Get elapsed hours given two timestamp. This function accounts for the time portion
	 * of the timestamp string.
	 * @param 	date1	java.sql.Timestamp
	 * @param 	date2	java.sql.Timestamp,	must be later than date1
	 * @param 	rounded	Rounded if set to true (seconds and minutes greater than 30 will be rounded up)
	 * @return	long	Number of elapsed hours
	 */
	public static float getElapsedHours(
		Timestamp date1,
		Timestamp date2)
	{
		float hours = getElapsedHours(dateToString(date1, DEFAULT_TIMESTAMP_FORMAT), dateToString(date2, DEFAULT_TIMESTAMP_FORMAT), DEFAULT_TIMESTAMP_FORMAT);
		return hours;
	}
	
	/**
	 * Get elapsed hours given two timestamp strings. This function accounts for the time portion
	 * of the timestamp string.
	 * @param 	date1	String representing timestamp, format defaults to "MM/dd/yyyy HH:mm:ss"
	 * @param 	date2	String representing timestamp, format defaults to "MM/dd/yyyy HH:mm:ss",
	 * 					must be later than date1
	 * @param 	format	String representing input format, defaults to "MM/dd/yyyy HH:mm:ss" if null
	 * @param 	rounded	Rounded if set to true (seconds and minutes greater than 30 will be rounded up)
	 * @return	long	Number of elapsed hours
	 */
	public static long getElapsedHours(
		String date1,
		String date2,
		String format,
		boolean rounded)
	{
		String fmt;
		if (format == null)
			fmt = DEFAULT_TIMESTAMP_FORMAT;
		else
			fmt = format;

		long lDate1 = stringToLongDate(date1, fmt);
		long lDate2 = stringToLongDate(date2, fmt);
		if (lDate2 < lDate1)
			throw new RuntimeException(INVALID_DATE);

		long lMilSecs = lDate2 - lDate1;
		long elapsedSecs = lMilSecs / 1000;

		long hours, minutes, seconds;
		hours = elapsedSecs / 3600;
		elapsedSecs = elapsedSecs - (hours * 3600);
		minutes = elapsedSecs / 60;
		elapsedSecs = elapsedSecs - (minutes * 60);
		seconds = elapsedSecs;
		if (rounded)
		{
			if (seconds > 30)
				minutes++;
			if (minutes > 30)
				hours++;
		}
		return hours;
	}
	/**
	 * Get elapsed hours and fraction thereof given two timestamp strings. This function accounts for the time portion
	 * of the timestamp string.
	 * @param 	date1	String representing timestamp, format defaults to "MM/dd/yyyy HH:mm:ss"
	 * @param 	date2	String representing timestamp, format defaults to "MM/dd/yyyy HH:mm:ss",
	 * 					must be later than date1
	 * @param 	format	String representing input format, defaults to "MM/dd/yyyy HH:mm:ss" if null
	 * @param 	rounded	Rounded if set to true (seconds and minutes greater than 30 will be rounded up)
	 * @return	long	Number of elapsed hours
	 */
	public static float getElapsedHours(
		String date1,
		String date2,
		String format)
	{
		String fmt;
		if (format == null)
			fmt = DEFAULT_TIMESTAMP_FORMAT;
		else
			fmt = format;

		long lDate1 = stringToLongDate(date1, fmt);
		long lDate2 = stringToLongDate(date2, fmt);
		if (lDate2 < lDate1)
			throw new RuntimeException(INVALID_DATE);

		long lMilSecs = lDate2 - lDate1;
		float elapsedSecs = lMilSecs / 1000;

		@SuppressWarnings("unused")
		float hours, minutes, seconds;
		hours = elapsedSecs / 3600;
		return hours;
	}
	/**
	 * Get elapsed days given two timestamp strings. This function accounts for the time portion
	 * of the timestamp string.
	 * @param 	date1	String representing timestamp, format defaults to "MM/dd/yyyy HH:mm:ss"
	 * @param 	date2	String representing timestamp, format defaults to "MM/dd/yyyy HH:mm:ss",
	 * 					must be later than date1
	 * @param 	format	String representing input format, defaults to "MM/dd/yyyy HH:mm:ss" if null
	 * @param 	rounded	Rounded up if set to true (Hours greater than 12 is rounded to 1 day)
	 * @return	long	Number of elapsed days
	 */
	public static long getElapsedDays(
		String date1,
		String date2,
		String format,
		boolean rounded)
	{
		long elapsedHours = getElapsedHours(date1, date2, format, rounded);
		long elapsedDays = elapsedHours / 24;
		long remainder = elapsedHours % 24;
		if (rounded)
		{
			if (remainder > 12)
				elapsedDays++;
		}

		return elapsedDays;
	}

	/**
	 * Get elapsed days given two timestamp strings. This function also returns
	 * fractional remainder (e.g. 2.5 days).
	 * @param 	date1	String representing timestamp, format defaults to "MM/dd/yyyy HH:mm:ss"
	 * @param 	date2	String representing timestamp, format defaults to "MM/dd/yyyy HH:mm:ss",
	 * 					must be later than date1
	 * @param 	format	String representing input format, defaults to "MM/dd/yyyy HH:mm:ss" if null
	 * @return	float	Number of elapsed days
	 */
	public static float getElapsedDays(
		String date1,
		String date2,
		String format)
	{
		float elapsedHours = getElapsedHours(date1, date2, format, false);
		float elapsedDays = elapsedHours / 24;
		return elapsedDays;
	}
	/**
	 * Get elapsed days given two date strings. This method uses the day unit change formula which
	 * simply counts the number of date changes, and does not account for time unit.
	 * Example:
	 * "04/01/2004", "04/03/2004" = 2 days 
	 * @param 	date1	String representing date, format defaults to "MM/dd/yyyy"
	 * @param 	date2	String representing date, format defaults to "MM/dd/yyyy",
	 * 					must be later than date1
	 * @param 	format	String representing input format, defaults to "MM/dd/yyyy" if null
	 * @return	long	Number of elapsed days
	 */
	public static long getDays(String date1, String date2, String format)
	{
		long elapsed = 0;
		String fmt;
		if (format == null)
			fmt = DEFAULT_DATE_FORMAT;
		else
			fmt = format;
			
		Calendar gc1 = getGregorianCal(date1, fmt);
		Calendar gc2 = getGregorianCal(date2, fmt);

		if (gc2.before(gc1))
			throw new RuntimeException(INVALID_DATE);

		while (gc1.before(gc2))
		{
			gc1.add(Calendar.DATE, 1);
			elapsed++;
		}
		return elapsed;
	}
	/**
	 * Get elapsed months given two date strings. This method uses the month unit change formula which
	 * counts the number of month and day changes. Note example below:
	 * Example:
	 * "01/01/2004", "06/01/2004" = 5 months
	 * "01/01/2004", "06/02/2004" = 6 months
	 * "01/01/2004", "07/01/2004" = 6 months
	 *  
	 * @param 	date1	String representing date, format defaults to "MM/dd/yyyy"
	 * @param 	date2	String representing date, format defaults to "MM/dd/yyyy",
	 * 					must be later than date1
	 * @param 	format	String representing input format, defaults to "MM/dd/yyyy" if null
	 * @return	long	Number of elapsed months
	 */
	public static long getMonths(String date1, String date2, String format)
	{
		long elapsed = 0;
		String fmt;
		if (format == null)
			fmt = DEFAULT_DATE_FORMAT;
		else
			fmt = format;
			
		Calendar gc1 = getGregorianCal(date1, fmt);
		Calendar gc2 = getGregorianCal(date2, fmt);

		if (gc2.before(gc1))
			throw new RuntimeException(INVALID_DATE);

		while (gc1.before(gc2))
		{
			gc1.add(Calendar.MONTH, 1);
			elapsed++;
		}
		return elapsed;
	}

	/**
	 * Get elapsed years given two date strings. This method is based on the getMonths()which
	 * counts the number of month/day changes. See doc and example in getMonths(). 
	 * @param 	date1	String representing date, format defaults to "MM/dd/yyyy"
	 * @param 	date2	String representing date, format defaults to "MM/dd/yyyy",
	 * 					must be later than date1
	 * @param 	format	String representing input format, defaults to "MM/dd/yyyy" if null
	 * @param	rounded	Rounded up if set to true (if months greater than 6).
	 * @return	long	Number of elapsed years
	 */
	public static long getYears(String date1, String date2, String format, boolean rounded)
	{
		long months = getMonths(date1, date2, format);
		long years = months / 12;
		if (rounded)
		{
			long remainder = months % 12;
			if (remainder > 6)
				years++;
		}

		return years;
	}

	/**
	 * This routine returns a GregorianCalendar object
	 * @param date
	 * @param format	Format of input date string, defaults to "MM/dd/yyyy" if null.
	 * @return
	 */
	public static GregorianCalendar getGregCal(int date, String format)
	{
	    GregorianCalendar cal = null;
        if (date > 0)
            cal = getGregorianCal(String.valueOf(date), format);
        
        return cal;
	}
	
	/**
	 * Convert date string into java.util.GregorianCalendar
	 * 
	 * @param 	date	Input date string.
	 * @param 	format	Format of input date string, defaults to "MM/dd/yyyy" if null.
	 * @return 	java.util.GregorianCalendar
	 */
	public static GregorianCalendar getGregorianCal(String date, String format)
	{
		SimpleDateFormat formatter;

		try
		{
			if (format == null)
				formatter = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
			else
				formatter = new SimpleDateFormat(format);

			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(formatter.parse(date));
			return cal;
		}
		catch (Exception e)
		{
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/**
	 * This routine converts Calendar to GregorianCalendar class
	 * @param gc
	 * @return
	 */
	public static GregorianCalendar getGregorianCal(Calendar gc)
	{
		if (gc==null)
			return null;
		
		int year = gc.get(Calendar.YEAR);
		int month = gc.get(Calendar.MONTH);
		int day = gc.get(Calendar.DATE);
		int hour = gc.get(Calendar.HOUR_OF_DAY);
		int minute = gc.get(Calendar.MINUTE);
		int second = gc.get(Calendar.SECOND);
		GregorianCalendar cal = new GregorianCalendar(year, month, day, hour, minute, second);
		return cal;
	}
	
	/**
	 * This reoutine extract the date from Calendar object and return it as
	 * an integer in format YYYYMMDD.
	 * @param gc
	 * @return
	 */
	public static int getDateYYYYMMDD(Calendar gc)
	{
		if (gc==null)
			return 0;
		
		String date = String.valueOf(gc.get(GregorianCalendar.YEAR));
		int num = gc.get(GregorianCalendar.MONTH);
		num++;
		if (num < 10)
			date = date + "0" + String.valueOf(num);
		else
			date = date + String.valueOf(num);
		
		num = gc.get(GregorianCalendar.DATE);
		if (num < 10)
			date = date + "0" + String.valueOf(num);
		else
			date = date + String.valueOf(num);
		
		return Integer.parseInt(date);
	}
	
	/**
	 * Compare two date strings
	 * @param date1		date string to compare
	 * @param date2		date string to compare
	 * @param format	date string format, defaults to MM/dd/yyyy.	
	 * @return 0 if dates are equal, 1 if date2 is before date1, 2 if date2 is after date1
	 */
	public static int compareDates(String date1, String date2, String format)
	{
	    int i = 0;
	    Calendar c1 = getGregorianCal(date1, format);
	    Calendar c2 = getGregorianCal(date2, format);

	    if (c2.before(c1))
	        i = 1;
	    else
	        if (c2.after(c1))
	            i = 2;
	        else
	        {
	            if (c2.equals(c1))
	                i = 0;
	        }
	    
	    return i;
	}
	
	public static String convertPersonDate(String dateString) throws Throwable{
		System.out.println("<<< convertPersonDate dateString["+dateString+"]");
		if(org.apache.commons.lang.StringUtils.isEmpty(dateString)){
	      iLOG.debug("dateString <<<["+dateString+"]");
	      throw new Exception("convertPersonDate :dateString <<<["+dateString+"]");
		}
		String _canonicalDateFormat = "000000.0-0000";
		//dateString=StringUtils.delete(dateString, "-");
		//dateString=StringUtils.delete(dateString, "/");
		//dateString=dateString+_canonicalDateFormat;
		SimpleDateFormat timDate= new java.text.SimpleDateFormat("yyyyMMdd",Locale.ENGLISH);
		//SimpleDateFormat simpDate= new java.text.SimpleDateFormat("MM/dd/yyyy",Locale.ENGLISH);
		Date fulldate = timDate.parse(dateString); 
		dateString=timDate.format(fulldate);
		System.out.println(">>>> 777777777777777777777777 dateString["+dateString+"]");
		return dateString+_canonicalDateFormat;
	}

	
	
}
