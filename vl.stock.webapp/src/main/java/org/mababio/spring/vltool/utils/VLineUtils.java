/**
 * 
 */
package org.mababio.spring.vltool.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.mababio.spring.vltool.domain.Stock;
import org.mababio.spring.vltool.domain.ValueLine;


@SuppressWarnings("resource")
public class VLineUtils {
	
	
	private static Logger iLOG = Logger.getLogger(VLineUtils.class);
	private static Pattern doublePattern = Pattern.compile("([0-9]*)\\.([0-9]*)");

	
	
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
				break;
			case 2:
				dtk.setSecondToken(newtk);
				break;
			case 3:
				dtk.setThirdToken(newtk);
				break;
			default:
				return dtk;
			}
		}
		return dtk;
	}
	
	
	

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
		}finally{
			iLOG.info("MonthAsInt:month["+monthName+"]["+monthNumber+"]");
		}
		iLOG.info( " <<<< monthNumber : " + monthNumber);
		return monthNumber;
	}
	
	/*
	
	public static Set<String> getNamesOfMonths(){
		Set<String> monthNames = Calendar.getInstance().getDisplayNames(Calendar.MONTH,Calendar.SHORT,Locale.getDefault()).keySet();
		return monthNames;
	}
	*//**
	 * 
	 * @param dtk
	 * @return
	 * DateTime(
302                int year,
303                int monthOfYear,
304                int dayOfMonth,
305                int hourOfDay,
306                int minuteOfHour) {
	 *//*
	*//**
	 * @param dtk
	 * @return
	 *//*
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
	
	
	
	*//**
	 * @param cal
	 * @return
	 *//*
	public static DateToken getDTKFromCalendar(Calendar cal){
		DateToken dtk =new DateToken();
		dtk.setThirdToken(String.valueOf(cal.get(Calendar.YEAR)));
		dtk.setFirstToken(String.valueOf(cal.get(Calendar.MONTH)));
		dtk.setSecondToken(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
		
	    return dtk;
	}
	

	

*/



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







    public static Stock getValueLineStock(String stckLine,ValueLine valueLine){


        Stock stock = new Stock();
        
        
    /*regex*/
        final String pageNumR = "(\\d{4}\\s)";
        final String stockNameR = "(\\b[(\\S|\\s)]+\\b)"; 
        final String tickerR = "([A-Z a-z]{1,7}\\s)";
        final String recentPriceR = "([0-9]+\\.[0-9]{2}\\s)";
        final String changeInPriceR = "(-?[0-9]+\\.[0-9]%\\s)";
        final String perRankR = "([0-9]\\s|.*)";
        final String techRankR = "([0-9]\\s|.*)";
        final String safelyRankR = "([0-9]\\b|.*)";
    /*regex*/

        Pattern pattern =   Pattern.compile(pageNumR + stockNameR+ tickerR+recentPriceR+changeInPriceR+perRankR+techRankR +safelyRankR);
        Matcher matcher  = pattern.matcher(stckLine);

      
        
        
        if(matcher.find()){
        	/*performance type*/
        String performanceType = matcher.group(5).contains("-") ? "WORST" :"BEST";

        Integer pageNum = Integer.parseInt(matcher.group(1).trim());
        String stockName = matcher.group(2).trim();
        String ticker =  matcher.group(3).trim();
        BigDecimal  recentPrice  = new BigDecimal(Double.parseDouble(matcher.group(4).trim())) ;
        Double changeInPrice =  Double.parseDouble(matcher.group(5).trim().replace("%", "")) ;
        Integer  perRank =  matcher.group(6).length()!=0 && Character.isDigit(matcher.group(6).charAt(0))  ? Integer.parseInt(matcher.group(6).trim()) : 0;
        Integer  techRank = matcher.group(7).length()!=0 && Character.isDigit(matcher.group(7).charAt(0))  ? Integer.parseInt(matcher.group(7).trim()) : 0;
        Integer safelyRank =  matcher.group(8).length()!=0 && Character.isDigit(matcher.group(8).charAt(0))  ? Integer.parseInt(matcher.group(8).trim()) : 0;


        stock.setPageNumber(pageNum)
                .setStkName(stockName)
                .setTicker(ticker)
                .setVlinePrice(recentPrice)
                .setChangeInPrice(changeInPrice)
                .setPerRank(perRank)
                .setTechRank(techRank)
                .setSafetyRank(safelyRank)
                .setPerformanceType(performanceType)
                .setValueLine(valueLine);
        return stock;
        }else {
        	System.err.println("ERROR CHECK RAW  ---->" +  stckLine );
        	stock.setStkName("NULL");
		  return stock;
		}
        
        
        
    }



	
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
