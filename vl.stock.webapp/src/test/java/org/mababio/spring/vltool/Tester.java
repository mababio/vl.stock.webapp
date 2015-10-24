package org.mababio.spring.vltool;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ea on 8/23/15.
 * Author: Michael Ababio
 */
public class Tester {

    public static void main(String[] args) {
    	
    	
    
    	
       String test = "1234 Westmoreland Research ArWR 28.87 84.8% 4 3 5"; //3512 Westmoreland Coal CoWLB 28.87 84.8% 4 3 5


       String firstToken = "(\\d{4}\\s)";
        String secondToken =      "(\\b[(\\S\\s)]+\\b)"; 
        String thridToken = "([A-Z a-z]{3,4}\\s)";
        String forthToken = "([0-9]+\\.[0-9]{2}\\s)";
        String fifthToken = "(-?[0-9]+\\.[0-9]%\\s)";
        String sixthToken = "([0-9]\\s|.*)";
        String seventhToken = "([0-9]\\s|.*)";
        String eighthToken = "([0-9]\\b|.*)";

        Pattern pattern =   Pattern.compile(firstToken + secondToken+ thridToken+forthToken+fifthToken+sixthToken+seventhToken +eighthToken);

        Matcher matcher  = pattern.matcher(test);

        while (matcher.find()) {
            System.out.println("group 1: " + matcher.group(1));
           System.out.println("group 2: " + matcher.group(2));
           System.out.println("group 3: " + matcher.group(3));
            System.out.println("group 4: " + matcher.group(4));
            System.out.println("group 5: " + matcher.group(5));
            System.out.println("group 6: " + matcher.group(6));
            System.out.println("group 7: " + matcher.group(7));
            System.out.println("group 8: " + matcher.group(8));

        }

       /* boolean val  = Pattern.matches(fifthToken,"185.4%");
        System.out.println(val);
*/




    }
}
