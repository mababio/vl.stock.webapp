/**
 * 
 */
package org.mababio.spring.vltool.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.mababio.spring.vltool.domain.Stock;


@SuppressWarnings("resource")
public class VLineUtils {
	private static Logger iLOG = Logger.getLogger(VLineUtils.class);
	private static Pattern doublePattern = Pattern.compile("([0-9]*)\\.([0-9]*)");
	public static final String IS_WORST="Worst";
	public static final String IS_SAVED="saved";
	
	/**
	 * @param inpLine
	 * @return
	 */

	 
	public static DateToken getDateToken(String inpLine) {
		String date=inpLine.substring(0, inpLine.indexOf("SUMMARY"));
		System.out.println("date = " + date);
		Scanner scnr=new Scanner(date);
		DateToken dtk=new DateToken();
		int i=0;
		while(scnr.hasNext()){
			String newtk=scnr.next();
			i++;
			if(StringUtils.endsWithIgnoreCase(newtk, ",")){
				newtk=StringUtils.remove(newtk, ",");
			}
			switch(i){
			case 1:
				dtk.setFirstToken(newtk);
				//System.out.println("FirstToken = "+date);
				break;
			case 2:
				dtk.setSecondToken(newtk);
				//System.out.println("SecondToken = "+date);
				break;
			case 3:
				dtk.setThirdToken(newtk);
				//System.out.println("ThirdToken = "+date);
				break;
				default:
					//System.out.println("Empty dtk = ");
					return dtk;
				
			}
			//System.out.println("token = "+newtk);
		}
		return dtk;
	}
	
	/**
	 * @return
	 */
	public static Set<String> getNamesOfMonths(){
		Set<String> monthNames = Calendar.getInstance().getDisplayNames(Calendar.MONTH,Calendar.SHORT,Locale.getDefault()).keySet();
		return monthNames;
	}
	/**
	 * 
	 * @param dtk
	 * @return
	 * DateTime(
302                int year,
303                int monthOfYear,
304                int dayOfMonth,
305                int hourOfDay,
306                int minuteOfHour) {
	 */
	/**
	 * @param dtk
	 * @return
	 */
	public static int getWeekNumber(DateToken dtk){
		int weekNum=-1;
		int yearForDate=Integer.valueOf(dtk.getThirdToken());
		int monthForDate=getMonthAsInt(dtk.getFirstToken(), dtk.getThirdToken(), dtk.getSecondToken());
		int dayForDate=Integer.valueOf(dtk.getSecondToken());
		System.out.println("yearForDate="+yearForDate+" monthForDate "+monthForDate+" dayForDate "+dayForDate);

		Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.YEAR, yearForDate);
	    cal.set(Calendar.MONTH, monthForDate);
	    cal.set(Calendar.DAY_OF_MONTH, dayForDate);
	    weekNum= cal.get(Calendar.WEEK_OF_YEAR);
	    //System.out.println( " weekNum : " + weekNum);
		return weekNum;
	}
	
	public static int getWkNumber(DateToken dtk){
		int weekNum=-1;
		int yearForDate=Integer.valueOf(dtk.getThirdToken());
		int monthForDate=Integer.valueOf(dtk.getFirstToken());
		int dayForDate=Integer.valueOf(dtk.getSecondToken());
		//System.out.println("yearForDate="+yearForDate+" monthForDate "+monthForDate+" dayForDate "+dayForDate);
		
		Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.YEAR, yearForDate);
	    cal.set(Calendar.MONTH, monthForDate);
	    cal.set(Calendar.DAY_OF_MONTH, dayForDate);
	    weekNum= cal.get(Calendar.WEEK_OF_YEAR);
	    //System.out.println( " weekNum : " + weekNum);
		return weekNum;
	}
	
	/**
	 * @param dtk
	 * @return
	 */
	public static Calendar getVLCalendar(DateToken dtk){
		int yearForDate=Integer.valueOf(dtk.getThirdToken());
		int monthForDate=getMonthAsInt(dtk.getFirstToken(), dtk.getThirdToken(), dtk.getSecondToken());
		int dayForDate=Integer.valueOf(dtk.getSecondToken());
		Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.YEAR, yearForDate);
	    cal.set(Calendar.MONTH, monthForDate);
	    cal.set(Calendar.DAY_OF_MONTH, dayForDate);
	    return cal;
	}
	
	/**
	 * @param cal
	 * @return
	 */
	public static DateToken getDTKFromCalendar(Calendar cal){
		DateToken dtk =new DateToken();
		dtk.setThirdToken(String.valueOf(cal.get(Calendar.YEAR)));
		dtk.setFirstToken(String.valueOf(cal.get(Calendar.MONTH)));
		dtk.setSecondToken(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
		
	    return dtk;
	}
	
	/**
	 * @param monthName
	 * @param yYear
	 * @param day
	 * @return
	 */
	private static int getMonthAsInt(String monthName,String yYear,String day){
		int monthNumber=-1;
		try{
			int year = Integer.valueOf(yYear);
			SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
			if(StringUtils.length(day)==1){
				day="0"+day+"-";
			}else{
				day=day+"-";	
			}
			 Date iDate = format.parse(day+ monthName + "-" + year);
			 Calendar now = Calendar.getInstance();
			 now.setTime(iDate);
			 monthNumber=now.get(Calendar.MONTH);
		}catch(Throwable e){
			iLOG.fatal("getMonthAsInt --> "+e.getMessage());
		}/*finally{
			iLOG.info("MonthAsInt:month["+monthName+"]["+monthNumber+"]");
		}
		iLOG.info( " <<<< monthNumber : " + monthNumber);*/
		return monthNumber;
	}





/*

	private static Boolean isStockName(){}




	private static   Boolean isTicker(String token){

	}
*/


	private static Boolean isVlinePrice(String token){
		return (Character.isDigit(token.charAt(0))||token.charAt(0)=='-' && doublePattern.matcher(token).matches() )? true : false;
	}

/*
	private static  Boolean isVlinePercentage(){}
	private static  Boolean isRefRank(){}
	private static  Boolean isTechRank(){}
	private static  Boolean isSafteyRank(){}
	private static  Boolean ischnageInPrice(){}
	private static  Boolean isWorsrt(){}
	private static  Boolean isBest(){}*/







    public static Stock getValueLineStock(String stckLine,DateToken dtk){







        return  new Stock();
    }


	/**
	 * @param /stckLine
//	 * @param /dtk
	 * @return Stock Entity from one pdf line.
	 */
	@SuppressWarnings("unused")
	/*public static Stock getValueLineStock(String stckLine,DateToken dtk){
		
		Scanner stockRawSringScanner=new Scanner(stckLine);
		StringBuffer buffer=new StringBuffer();

		 String SPECIAL_CHARACTER = "-"; // And others
		 String SPECIAL_END="%";
		int i=0;
		
		List<String> myList=new ArrayList<String>();
		Stock stock=new Stock();
		while(stockRawSringScanner.hasNext()){
			String newtoken=stockRawSringScanner.next();
			newtoken=StringUtils.trim(newtoken);
			i++;
			if(Character.isDigit(newtoken.charAt(0))||newtoken.charAt(0)=='-'){
				if(doublePattern.matcher(newtoken).matches()){//recent price
					stock.setVlinePrice(new BigDecimal(newtoken));
				}else if(StringUtils.endsWithIgnoreCase(newtoken, SPECIAL_END)){//percent change
					newtoken=StringUtils.remove(newtoken, SPECIAL_END);
					stock.setVlinePercentage(new BigDecimal(newtoken));
				}
			}else if(!StringUtils.startsWithIgnoreCase(newtoken, SPECIAL_CHARACTER)&&!StringUtils.startsWithIgnoreCase(newtoken, "*")){
				myList.add(newtoken);
			}
		
		}//end of while
		String symbol=myList.get(myList.size()-1);
		stock.setTicker(symbol);
		for(int k=0;k<myList.size()-1;k++){
			String listValue=myList.get(k);
			listValue=listValue+" ";
			buffer.append(listValue);
		}
		 stock.setStkName(buffer.toString(), s->s.trim());
		return stock;
	}
	*/
	/**
	 * @param emf
	 * @param kemf
	 * @return
	 */
	
	
	/**
	 * @param e
	 * @return Writes entire stack trace to printwriter.
	 */
	public static String getStackTrace(Throwable e){
	    if (e == null) {
	            return "";
	        }
	        StringWriter sw = new StringWriter();
	        PrintWriter pw = new PrintWriter(sw);
	        e.printStackTrace(pw);
	        return sw.toString();
	  }
	
	
	/**
	 * @param vlStatus
	 * @return
	 */
	public static boolean isWorstStock(String vlStatus){
		return StringUtils.equalsIgnoreCase(IS_WORST, vlStatus);
	}
	
	/**
	 * @param stcks--Collection Stock
	 * @return LinkedHashSet<Date>
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static LinkedHashSet<Date> getDatesFromStock(Collection stcks){
		LinkedHashSet<Date> dateSet=new LinkedHashSet<Date>();
		Iterator<Stock> dtItrx=stcks.iterator();
		/*while(dtItrx.hasNext()){
			// java.sql.Timestamp stmp=dtItrx.next().getOccurSinceDate();
				//Date utilDate= DatabaseUtility.convertTimestampToUtilDate(stmp);
				//dateSet.add(utilDate);	
		}*/
		return dateSet;
	}
	
	
	
}
